package com.trophonix.tradeplus.extras;

import com.google.common.base.Preconditions;
import com.trophonix.tradeplus.TradePlus;
import com.trophonix.tradeplus.trade.Trade;
import com.trophonix.tradeplus.util.ItemFactory;
import com.trophonix.tradeplus.util.Sounds;
import lombok.AccessLevel;
import lombok.Setter;
import net.wesjd.anvilgui.AnvilGUI;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.conversations.*;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

import java.text.DecimalFormat;

public abstract class Extra implements Listener {

  static final DecimalFormat decimalFormat = new DecimalFormat("###,##0.##");
  final ItemStack icon;
  public final String name;
  final Player player1;
  final Player player2;
  final double increment;
  final ItemStack theirIcon;
  final double taxPercent;
  @Setter(AccessLevel.PRIVATE)
  public double value1 = 0, value2 = 0;
  double increment1;
  double increment2;
  private TradePlus pl;
  private double max1;
  private double max2;
  private long lastUpdatedMax = System.currentTimeMillis();
  private String mode;
  private Trade trade;

  Extra(String name, Player player1, Player player2, TradePlus pl, Trade trade) {
    this.pl = pl;
    this.name = name;
    ConfigurationSection section =
        Preconditions.checkNotNull(pl.getConfig().getConfigurationSection("extras." + name));
    this.player1 = player1;
    this.player2 = player2;
    this.increment = section.getDouble("increment", 1D);
    this.increment1 = increment;
    this.increment2 = increment;
    ItemFactory factory =
        new ItemFactory(section.getString("material", "PAPER"), Material.PAPER)
            .display('&', section.getString("display", "&4ERROR"));
    if (section.contains("lore")) factory.lore('&', section.getStringList("lore"));
    this.icon = factory.flag("HIDE_ATTRIBUTES").build();
    this.theirIcon =
        new ItemFactory(section.getString("material", "PAPER"), Material.PAPER)
            .display('&', section.getString("theirdisplay", "&4ERROR"))
            .build();
    this.taxPercent = section.getDouble("taxpercent", 0);
    this.mode = section.getString("mode", "increment").toLowerCase();
    if (mode.equals("type") || mode.equals("anvil")) {
      mode = "chat";
      section.set("mode", "chat");
      pl.saveConfig();
    }
    this.trade = trade;
  }

  public void init() {
    this.max1 = getMax(player1);
    this.max2 = getMax(player2);
    this.pl.log("'" + name + "' extra initialized. Balances: [" + max1 + ", " + max2 + "]");
  }

  public void onClick(Player player, ClickType click) {
    double offer = player1.equals(player) ? value1 : value2;
    if (mode.equals("anvil")) {
      String insert = String.valueOf(offer);
      trade.setCancelOnClose(player, false);
      player.closeInventory();
      new AnvilGUI.Builder()
          .plugin(pl)
          .text(insert)
          .onComplete(
              (_player, input) -> {
                try {
                  double value = Double.parseDouble(input);
                  if (value < 0) throw new NumberFormatException();
                  double max = getMax(player);
                  if (value > max)
                    return AnvilGUI.Response.text(
                        pl.getTypeMaximum().replace("%BALANCE%", String.valueOf(max)));
                  else {
                    if (player1.equals(player)) setValue1(value);
                    else setValue2(value);
                    trade.updateExtras();
                  }
                  return AnvilGUI.Response.close();
                } catch (NumberFormatException ignored) {
                  return AnvilGUI.Response.text(pl.getTypeInvalid());
                }
              })
          .open(player);
    } else if (mode.equals("chat")) {
      trade.setCancelOnClose(player, false);
      player.closeInventory();
      new ConversationFactory(pl)
          .withPrefix(conversationContext -> ChatColor.translateAlternateColorCodes('&', pl.getConfig().getString("extras.type.prefix", "&6&l!!&6> ")))
          .withFirstPrompt(
              new NumericPrompt() {
                @Override
                protected Prompt acceptValidatedInput(
                    ConversationContext conversationContext, Number number) {
                  if (trade.isCancelled()) return null;
                  if (number.doubleValue() > getMax(player)) {
                    return new NumericPrompt() {
                      @Override
                      protected Prompt acceptValidatedInput(
                          ConversationContext conversationContext, Number number) {
                        if (trade.isCancelled()) return null;
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

                      @Override
                      public String getPromptText(ConversationContext conversationContext) {
                        return pl.getTypeMaximum()
                            .replace("%BALANCE%", decimalFormat.format(getMax(player)));
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

                @Override
                public String getPromptText(ConversationContext conversationContext) {
                  return pl.getTypeEmpty()
                      .replace("%BALANCE%", decimalFormat.format(getMax(player)))
                      .replace("%AMOUNT%", decimalFormat.format(offer));
                }
              })
          .withTimeout(30)
          .addConversationAbandonedListener(
              event -> {
                if (trade.isCancelled()) return;
                if (!event.gracefulExit()) Sounds.villagerHmm(player, 1f);
                trade.open(player);
                trade.updateExtras();
                trade.setCancelOnClose(player, true);
              })
          .buildConversation(player)
          .begin();
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
