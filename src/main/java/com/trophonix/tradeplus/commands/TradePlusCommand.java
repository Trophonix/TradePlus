package com.trophonix.tradeplus.commands;

import com.trophonix.tradeplus.TradePlus;
import com.trophonix.tradeplus.trade.Trade;
import com.trophonix.tradeplus.util.MsgUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Collections;

public class TradePlusCommand extends Command {

  private final TradePlus pl;

  public TradePlusCommand(TradePlus pl) {
    super(Collections.singletonList("tradeplus"));
    this.pl = pl;
  }

  @Override
  public void onCommand(CommandSender sender, String[] args) {
    if (!sender.hasPermission("tradeplus.admin")) {
      MsgUtils.send(sender, pl.getLang().getString("nopermssenderadmin").split("%NEWLINE%"));
      return;
    }
    
    if (args.length == 1) {
      if (args[0].equalsIgnoreCase("reload") || args[0].equalsIgnoreCase("rl")) {
        pl.reload();
        MsgUtils.send(sender, "&6&l(!) &6Configs reloaded!");
        return;
      }
    } else if (args.length == 3) {
      if (args[0].equalsIgnoreCase("force")) {
        Player p1 = Bukkit.getPlayer(args[1]);
        Player p2 = Bukkit.getPlayer(args[2]);
        if (p1 == null || p2 == null || !p1.isOnline() || !p2.isOnline() || p1.equals(p2)) {
          MsgUtils.send(sender, "&4&l(!) &4Invalid players!");
          return;
        }
        MsgUtils.send(sender, "&6&l(!) &6You forced a trade between &e" + p1.getName() + " &6and &e" + p2.getName());
        String message = pl.getLang().getString("forcedtrade", "&6&l(!) &6You've been forced into a trade with &e%PLAYER%");
        MsgUtils.send(p1, message.replace("%PLAYER%", p2.getName()));
        MsgUtils.send(p2, message.replace("%PLAYER%", p1.getName()));
        Trade trade = new Trade(p1, p2);
        if (sender instanceof Player && !(sender.equals(p1) || sender.equals(p2)))
          ((Player) sender).openInventory(trade.spectatorInv);
        return;
      } else if (args[0].equalsIgnoreCase("spectate")) {
        if (!(sender instanceof Player)) {
          MsgUtils.send(sender, "&cThis command can only be executed by players!");
          return;
        }
        Player player = (Player) sender;
        Player p1 = Bukkit.getPlayer(args[1]);
        Player p2 = Bukkit.getPlayer(args[2]);
        if (p1 == null || p2 == null || !p1.isOnline() || !p2.isOnline() || p1.equals(p2)) {
          MsgUtils.send(sender, "&4&l(!) &4Invalid players!");
          return;
        }
        Trade trade = pl.getTrade(p1, p2);
        if (trade == null) {
          MsgUtils.send(player, "&4&l(!) &4There is not an ongoing trade between those players!");
        } else {
          player.openInventory(trade.spectatorInv);
        }
        return;
      }
    }
    MsgUtils.send(sender, new String[]{
            "&6&l<----- Trade+ by Trophonix ----->",
            "&e/trade <player> &fSend a trade request",
            "&e/tradeplus reload &fReload config files",
            "&e/tradeplus force <player1> <player2> &fForce 2 players to trade",
            "&e/tradeplus spectate <player1> <player2> &fSpectate an ongoing trade"
    });
  }

}
