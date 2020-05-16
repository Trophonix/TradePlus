package com.trophonix.tradeplus.extras;

import com.google.common.base.Preconditions;
import com.trophonix.tradeplus.TradePlus;
import com.trophonix.tradeplus.trade.Trade;
import com.trophonix.tradeplus.util.ItemFactory;
import com.trophonix.tradeplus.util.Sounds;
import lombok.Getter;
import net.wesjd.anvilgui.AnvilGUI;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.conversations.ConversationContext;
import org.bukkit.conversations.ConversationFactory;
import org.bukkit.conversations.NumericPrompt;
import org.bukkit.conversations.Prompt;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

import java.text.DecimalFormat;

public abstract class Extra implements Listener {

  static final DecimalFormat decimalFormat = new DecimalFormat("###,##0.##");
  public final String name;
  final ItemStack icon;
  final Player player1;
  final Player player2;
  final double increment;
  final ItemStack theirIcon;
  final double taxPercent;
  public double value1 = 0, value2 = 0;
  double increment1;
  double increment2;
  @Getter private String displayName;
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
    this.displayName = section.getString("name");
    this.player1 = player1;
    this.player2 = player2;
    this.increment = section.getDouble("increment", 1D);
    this.increment1 = increment;
    this.increment2 = increment;
    ItemFactory factory =
        new ItemFactory(section.getString("material", "PAPER"), Material.PAPER)
            .display('&', section.getString("display", "&4ERROR"))
            .customModelData(section.getInt("customModelData", 0));
    if (section.contains("lore")) factory.lore('&', section.getStringList("lore"));
    this.icon = factory.flag("HIDE_ATTRIBUTES").build();
    this.theirIcon =
        new ItemFactory(section.getString("material", "PAPER"), Material.PAPER)
            .display('&', section.getString("theirdisplay", "&4ERROR"))
            .customModelData(section.getInt("customModelData", 0))
            .build();
    this.taxPercent = section.getDouble("taxpercent", 0);
    this.mode = section.getString("mode", "chat").toLowerCase();
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
                        pl.getTypeMaximum()
                            .replace("%BALANCE%", String.valueOf(max))
                            .replace("%EXTRA%", displayName));
                  else {
                    if (player1.equals(player)) setValue1(value);
                    else setValue2(value);
                    updateMax(false);
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
          .withPrefix(
              conversationContext ->
                  ChatColor.translateAlternateColorCodes(
                      '&', pl.getConfig().getString("extras.type.prefix", "&6&l!!&6> ")))
          .withFirstPrompt(
              new NumericPrompt() {
                @Override
                protected Prompt acceptValidatedInput(
                    ConversationContext conversationContext, Number number) {
                  if (trade.isCancelled()) return null;
                  if (number.doubleValue() < 0 || number.doubleValue() > getMax(player)) {
                    return new NumericPrompt() {
                      @Override
                      protected Prompt acceptValidatedInput(
                          ConversationContext conversationContext, Number number) {
                        if (trade.isCancelled()) return null;
                        if (number.doubleValue() < 0 || number.doubleValue() > getMax(player)) {
                          return this;
                        }
                        if (player1.equals(player)) {
                          setValue1(number.doubleValue());
                        } else if (player2.equals(player)) {
                          setValue2(number.doubleValue());
                        }
                        updateMax(false);
                        return null;
                      }

                      @Override
                      public String getPromptText(ConversationContext conversationContext) {
                        return pl.getTypeMaximum()
                            .replace("%BALANCE%", decimalFormat.format(getMax(player)))
                            .replace("%EXTRA%", displayName);
                      }
                    };
                  }
                  if (player1.equals(player)) {
                    setValue1(number.doubleValue());
                  } else if (player2.equals(player)) {
                    setValue2(number.doubleValue());
                  }
                  updateMax(false);
                  return null;
                }

                @Override
                public String getPromptText(ConversationContext conversationContext) {
                  return pl.getTypeEmpty()
                      .replace("%BALANCE%", decimalFormat.format(getMax(player)))
                      .replace("%AMOUNT%", decimalFormat.format(offer))
                      .replace("%EXTRA%", displayName);
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

    updateMax(true);
  }

  public void setValue1(double value1) {
    this.value1 = value1;
  }

  public void setValue2(double value2) {
    this.value2 = value2;
  }

  public void updateMax(boolean delay) {
    long now = System.currentTimeMillis();
    if (!delay || now > lastUpdatedMax + 5000) {
      max1 = getMax(player1);
      max2 = getMax(player2);
      lastUpdatedMax = now;
    }
    if (value1 > max1) value1 = max1;
    if (value2 > max2) value2 = max2;
  }

  protected abstract double getMax(Player player);

  public abstract void onTradeEnd();

  public ItemStack getIcon(Player player) {
    return ItemFactory.replaceInMeta(
        _getIcon(player), "%BALANCE%", Double.toString(getMax(player)), "%EXTRA%", displayName);
  }

  public ItemStack getTheirIcon(Player player) {
    return ItemFactory.replaceInMeta(
        _getTheirIcon(player),
        "%BALANCE%",
        Double.toString(getMax(player1.equals(player) ? player2 : player1)),
        "%EXTRA%",
        displayName);
  }

  protected abstract ItemStack _getIcon(Player player);

  protected abstract ItemStack _getTheirIcon(Player player);
}
