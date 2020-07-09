package com.trophonix.tradeplus.util;

import com.trophonix.tradeplus.TradePlus;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class InvUtils {

  public static final List<Integer> leftSlots =
      new LinkedList<>(
          Arrays.asList(
              1, 2, 3, 9, 10, 11, 12, 18, 19, 20, 21, 27, 28, 29, 30, 36, 37, 38, 39, 45, 46, 47,
              48));
  public static ItemStack placeHolder;
  public static ItemStack acceptTrade;
  public static ItemStack cancelTrade;
  public static ItemStack theyAccepted;
  public static ItemStack theyCancelled;
  public static ItemStack force;
  private static TradePlus pl;

  public static void reloadItems(TradePlus pl) {
    InvUtils.pl = pl;
    placeHolder =
        new ItemFactory(
                pl.getTradeConfig().getGuiSeparatorType())
            .display(" ")
            .customModelData(pl.getTradeConfig().getGuiSeparatorModelData())
            .flag("HIDE_ATTRIBUTES")
            .build();
    acceptTrade =
        new ItemFactory(
                pl.getTradeConfig().getGuiCancelType(),
                Material.EMERALD)
            .display(pl.getTradeConfig().getGuiAcceptDisplay())
            .customModelData(pl.getTradeConfig().getGuiAcceptModelData())
            .amount(pl.getTradeConfig().getAntiscamCountdown())
            .flag("HIDE_ATTRIBUTES")
            .build();
    cancelTrade =
        new ItemFactory(
                pl.getTradeConfig().getGuiAcceptType(),
                Material.REDSTONE)
            .display(pl.getTradeConfig().getGuiCancelDisplay())
            .customModelData(pl.getTradeConfig().getGuiCancelModelData())
            .amount(pl.getTradeConfig().getAntiscamCountdown())
            .flag("HIDE_ATTRIBUTES")
            .build();
    theyAccepted =
            new ItemFactory(
                    pl.getTradeConfig().getGuiAcceptType(),
                    Material.EMERALD)
            .display(pl.getTradeConfig().getGuiTheirAcceptDisplay())
            .customModelData(pl.getTradeConfig().getGuiAcceptModelData())
            .amount(pl.getTradeConfig().getAntiscamCountdown())
            .flag("HIDE_ATTRIBUTES")
            .build();
    theyCancelled =
        new ItemFactory(
                pl.getTradeConfig().getGuiCancelType(),
                Material.REDSTONE)
            .display(pl.getTradeConfig().getGuiTheirCancelDisplay())
            .customModelData(pl.getTradeConfig().getGuiCancelModelData())
            .amount(pl.getTradeConfig().getAntiscamCountdown())
            .flag("HIDE_ATTRIBUTES")
            .build();
    force =
        new ItemFactory(
                pl.getTradeConfig().getGuiForceType(),
                Sounds.version < 113
                    ? Material.getMaterial("WATCH")
                    : Material.getMaterial("CLOCK"))
            .display(pl.getTradeConfig().getGuiForceName())
            .lore('&', pl.getTradeConfig().getGuiForceLore())
            .flag("HIDE_ATTRIBUTES")
            .build();
  }

  public static Inventory getTradeInventory(Player player1, Player player2) {
    Inventory inv =
        Bukkit.createInventory(
            player1.getInventory().getHolder(),
            54,
            pl.getTradeConfig().getGuiTitle()
                .replace("%PLAYER%", player2.getName()));
    for (int i = 4; i <= 49; i += 9) inv.setItem(i, placeHolder);
    if (pl.getTradeConfig().isShowAccept()) {
      inv.setItem(0, acceptTrade);
      inv.setItem(8, theyCancelled);
      if (pl.getConfig().getBoolean("gui.force.enabled", true)
          && player1.hasPermission("tradeplus.admin")) inv.setItem(49, force);
    } else {
      inv.setItem(0, placeHolder);
      inv.setItem(8, placeHolder);
    }
    if (pl.getTradeConfig().isShowHead())
      inv.setItem(
          4,
          ItemFactory.getPlayerSkull(
              player2,
              pl.getTradeConfig().getGuiHeadDisplayName().replace("%PLAYER%", player2.getName())));
    return inv;
  }

  public static Inventory getSpectatorInventory(Player player1, Player player2) {
    String title =
        ChatColor.translateAlternateColorCodes(
            '&', pl.getTradeConfig().getSpectatorTitle());
    if (Sounds.version > 1.8)
      title = title.replace("%PLAYER1%", player1.getName()).replace("%PLAYER2%", player2.getName());
    Inventory inv = Bukkit.createInventory(player1.getInventory().getHolder(), 54, title);
    for (int i = 4; i <= 49; i += 9) inv.setItem(i, placeHolder);
    for (int i = 45; i <= 53; i++) inv.setItem(i, placeHolder);
    inv.setItem(0, ItemFactory.getPlayerSkull(player1, "&f" + player1.getName()));
    inv.setItem(8, ItemFactory.getPlayerSkull(player2, "&f" + player2.getName()));
    inv.setItem(4, theyCancelled);
    return inv;
  }
}
