package com.trophonix.tradeplus.extras;

import com.google.common.base.Preconditions;
import com.trophonix.tradeplus.TradePlus;
import com.trophonix.tradeplus.trade.Trade;
import com.trophonix.tradeplus.util.ItemFactory;
import com.trophonix.tradeplus.util.Sounds;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.conversations.ConversationContext;
import org.bukkit.conversations.ConversationFactory;
import org.bukkit.conversations.NumericPrompt;
import org.bukkit.conversations.Prompt;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.*;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;

import java.text.DecimalFormat;

public abstract class Extra {

  static final DecimalFormat decimalFormat = new DecimalFormat("###,##0.##");

  private TradePlus pl;
  public final ItemStack icon;
  public final String name;
  final Player player1;
  final Player player2;
  final double increment;
  final ItemStack theirIcon;
  final double taxPercent;
  public double value1 = 0, value2 = 0;
  private double max1;
  private double max2;
  private long lastUpdatedMax = System.currentTimeMillis();
  double increment1;
  double increment2;
  private boolean typeMode;
  private Trade trade;

  Extra(String name, Player player1, Player player2, TradePlus pl, Trade trade) {
    this.pl = pl;
    this.name = name;
    ConfigurationSection section = Preconditions.checkNotNull(pl.getConfig().getConfigurationSection("extras." + name));
    this.player1 = player1;
    this.player2 = player2;
    this.increment = section.getDouble("increment", 1D);
    this.increment1 = increment;
    this.increment2 = increment;
    ItemFactory factory = new ItemFactory(section.getString("material", "PAPER"), Material.PAPER)
            .display('&', section.getString("display", "&4ERROR"));
    if (section.contains("lore"))
      factory.lore('&', section.getStringList("lore"));
    this.icon = factory.flag(ItemFlag.HIDE_ATTRIBUTES).build();
    this.theirIcon = new ItemFactory(section.getString("material", "PAPER"), Material.PAPER)
            .display('&', section.getString("theirdisplay", "&4ERROR")).build();
    this.taxPercent = section.getDouble("taxpercent", 0);
    this.typeMode = section.getString("mode", "increment").equalsIgnoreCase("type");
    this.trade = trade;
  }

  public void init() {
    this.max1 = getMax(player1);
    this.max2 = getMax(player2);
    this.pl.log("'" + name + "' extra initialized. Balances: [" + max1 + ", " + max2 + "]");
  }

  public void onClick(Player player, ClickType click) {
    if (typeMode) {
      trade.setCancelOnClose(player, false);
      player.closeInventory();
      double offer = player1.equals(player) ? value1 : value2;

      new ConversationFactory(pl).withFirstPrompt(new NumericPrompt() {
        @Override protected Prompt acceptValidatedInput(ConversationContext conversationContext, Number number) {
          if (trade.cancelled) return null;
          if (number.doubleValue() >= getMax(player)) {
            return new NumericPrompt() {
              @Override protected Prompt acceptValidatedInput(ConversationContext conversationContext, Number number) {
                if (trade.cancelled) return null;
                if (number.doubleValue() > getMax(player)) {
                  return this;
                }
                if (player1.equals(player)) {
                  value1 = number.doubleValue();
                } else if (player2.equals(player)) {
                  value2 = number.doubleValue();
                }
                return null;
              }
              @Override public String getPromptText(ConversationContext conversationContext) {
                return pl.getTypeMaximum().replace("%BALANCE%", decimalFormat.format(getMax(player)));
              }
            };
          }
          if (player1.equals(player)) {
            value1 = number.doubleValue();
          } else if (player2.equals(player)) {
            value2 = number.doubleValue();
          }
          return null;
        }

        @Override public String getPromptText(ConversationContext conversationContext) {
          return pl.getTypeEmpty().replace("%BALANCE%", decimalFormat.format(getMax(player)))
              .replace("%AMOUNT%", decimalFormat.format(offer));
        }
      }).withTimeout(30).addConversationAbandonedListener(event -> {
        if (trade.cancelled) return;
        if (!event.gracefulExit()) Sounds.villagerHmm(player, 1f);
        trade.open(player);
        trade.updateExtras();
        trade.setCancelOnClose(player, true);
      }).buildConversation(player)
          .begin();
      /*gui = new AnvilGUI(pl, player, decimalFormat.format(offer), (p, input) -> {
        if (input == null || input.isEmpty())
          return pl.getTypeEmpty();
        try {
          double value = Double.parseDouble(input.replace(",", ""));
          if (value > getMax(p)) return pl.getTypeMaximum().replace("%BALANCE%", decimalFormat.format(bal))
                                     .replace("%AMOUNT%", decimalFormat.format(value));
          if (player1.equals(p)) value1 = value;
          else if (player2.equals(p)) value2 = value;
          trade.updateExtras();
          return null;
        } catch (NumberFormatException ignored) {
          return pl.getTypeInvalid().replace("%BALANCE%", decimalFormat.format(bal)).replace("%AMOUNT%", input);
        }
      }) {
        @Override public void closeInventory() {
          super.closeInventory();
          Bukkit.getScheduler().runTaskLater(pl, () -> {
            if (player1.equals(player)) player.openInventory(trade.inv1);
            else player.openInventory(trade.inv2);
            trade.setCancelOnClose(player, true);
            if (pl.getConfig().getBoolean("soundeffects.enabled", true) && pl.getConfig().getBoolean("soundeffects.onchange")) {
              Sounds.click(player1, 2);
              Sounds.click(player2, 2);
              trade.spectatorInv.getViewers().stream().filter(Player.class::isInstance).forEach(p -> Sounds.click((Player) p, 2));
            }
          }, 1L);
        }
      };*/
    } else {
      if (click.isLeftClick()) {
        if (click.isShiftClick()) {
          if (player.equals(player1)) {
            increment1 -= increment;
          } else if (player.equals(player2)) {
            increment2 -= increment;
          }
        } else {
          if (player.equals(player1)) {
            value1 -= increment1;
          } else if (player.equals(player2)) {
            value2 -= increment2;
          }
        }
      } else if (click.isRightClick()) {
        if (click.isShiftClick()) {
          if (player.equals(player1)) {
            increment1 += increment;
          } else if (player.equals(player2)) {
            increment2 += increment;
          }
        } else {
          if (player.equals(player1)) {
            value1 += increment1;
          } else if (player.equals(player2)) {
            value2 += increment2;
          }
        }
      }

    }
    if (increment1 < 0) increment1 = 0;
    if (increment2 < 0) increment2 = 0;

    if (value1 < 0) value1 = 0;
    if (value2 < 0) value2 = 0;

    long now = System.currentTimeMillis();
    if (now > lastUpdatedMax + 5000) {
      max1 = getMax(player1);
      max2 = getMax(player2);
      lastUpdatedMax = now;
    }
    if (value1 > max1) value1 = max1;
    if (value2 > max2) value2 = max2;
  }

  protected abstract double getMax(Player player);

  public abstract void onTradeEnd();

  public abstract ItemStack getIcon(Player player);

  public abstract ItemStack getTheirIcon(Player player);

}
