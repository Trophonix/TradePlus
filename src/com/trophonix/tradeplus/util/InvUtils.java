package com.trophonix.tradeplus.util;

import com.trophonix.tradeplus.TradePlus;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class InvUtils {

    private static TradePlus pl;

    public static List<Integer> leftSlots = new LinkedList<>(Arrays.asList(1, 2, 3, 9, 10, 11, 12, 18, 19, 20, 21,
                                                                            27, 28, 29, 30, 36, 37, 38, 39, 45, 46,
                                                                            47, 48));

    public static ItemStack placeHolder;

    public static ItemStack acceptTrade;
    public static ItemStack cancelTrade;

    public static ItemStack theyAccepted;
    public static ItemStack theyCancelled;

    public static void reloadItems(TradePlus pl) {
        InvUtils.pl = pl;
        placeHolder = new ItemFactory(pl.getConfig().getString("gui.separatorid", "160:15")).display(" ").build();
        acceptTrade = new ItemFactory(pl.getConfig().getString("gui.acceptid", "160:14")).display('&', pl.getConfig().getString("gui.accept")).amount(pl.getConfig().getInt("antiscam.countdown", 10)).build();
        cancelTrade = new ItemFactory(pl.getConfig().getString("gui.cancelid", "160:13")).display('&', pl.getConfig().getString("gui.cancel")).amount(pl.getConfig().getInt("antiscam.countdown", 10)).build();
        theyAccepted = new ItemFactory(pl.getConfig().getString("gui.cancelid", "160:13")).display('&', pl.getConfig().getString("gui.theyaccept")).amount(pl.getConfig().getInt("antiscam.countdown", 10)).build();
        theyCancelled = new ItemFactory(pl.getConfig().getString("gui.acceptid", "160:14")).display('&', pl.getConfig().getString("gui.theycancel")).amount(pl.getConfig().getInt("antiscam.countdown", 10)).build();
    }

    public static Inventory getTradeInventory(Player player1, Player player2) {
        Inventory inv = Bukkit.createInventory(player1.getInventory().getHolder(), 54, pl.getConfig().getString("gui.title"));
        for (int i = 4; i <= 49; i += 9)
            inv.setItem(i, placeHolder);
        inv.setItem(0, acceptTrade);
        inv.setItem(8, theyCancelled);
        if (pl.getConfig().getBoolean("gui.showhead", true))
            inv.setItem(4, ItemFactory.getPlayerSkull(player2.getName(), pl.getConfig().getString("gui.head").replace("%PLAYER%", player2.getName())));
        if (pl.getConfig().getBoolean("gui.showadminforce", true) && player1.hasPermission("tradeplus.admin"))
            inv.setItem(49, new ItemFactory("WATCH", Material.WATCH).display('&', "&4&lForce Trade")
                .lore('&', Arrays.asList("&cClick here to force", "&cacceptance.", "", "&cThis shows only for admins.")).build());
        return inv;
    }

    public static Inventory getSpectatorInventory(Player player1, Player player2) {
        Inventory inv = Bukkit.createInventory(player1.getInventory().getHolder(), 54, pl.getConfig().getString("gui.title"));
        for (int i = 4; i <= 49; i += 9)
            inv.setItem(i, placeHolder);
        for (int i = 45; i <= 53; i++)
            inv.setItem(i, placeHolder);
        inv.setItem(0, ItemFactory.getPlayerSkull(player1.getName(), "&f" + player1.getName()));
        inv.setItem(8, ItemFactory.getPlayerSkull(player2.getName(), "&f" + player2.getName()));
        inv.setItem(4, theyCancelled);
        return inv;
    }

}
