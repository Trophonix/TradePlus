package com.trophonix.tradeplus.commands;

import com.trophonix.tradeplus.TradePlus;
import com.trophonix.tradeplus.trade.Trade;
import com.trophonix.tradeplus.util.MsgUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class TradePlusCommand extends Command {

  private final TradePlus pl;

  public TradePlusCommand(TradePlus pl) {
    super(Collections.singletonList("tradeplus"));
    this.pl = pl;
  }

  @Override
  public void onCommand(CommandSender sender, String[] args) {
    if (!sender.hasPermission("tradeplus.admin")) {
      MsgUtils.send(sender, pl.getLang().getString("errors.no-perms.admin").split("%NEWLINE%"));
      return;
    }

    switch (args.length) {
      case 1:
        if (args[0].equalsIgnoreCase("reload") || args[0].equalsIgnoreCase("rl")) {
          pl.reload();
          MsgUtils.send(sender, pl.getLang().getString("admin.configs-reloaded", "&6&l(!) &6Configs reloaded!"));
          return;
        }
        break;
      case 2:
        if (args[0].equalsIgnoreCase("spectate")) {
          Player player = Bukkit.getPlayer(args[1]);
          if (player == null || !player.isOnline()) {
            MsgUtils.send(sender, pl.getLang().getString("admin.invalid-players", "&4&l(!) &4Invalid players!"));
            return;
          }
          Trade trade = pl.getTrade(player);
          if (trade == null) {
            MsgUtils.send(player, pl.getLang().getString("admin.no-trade", "&4&l(!) &4No trade was found with those arguments."));
          } else {
            player.openInventory(trade.getSpectatorInv());
          }
          return;
        }
        break;
      case 3:
        if (args[0].equalsIgnoreCase("force")) {
          Player p1 = Bukkit.getPlayer(args[1]);
          Player p2 = Bukkit.getPlayer(args[2]);
          if (p1 == null || p2 == null || !p1.isOnline() || !p2.isOnline() || p1.equals(p2)) {
            MsgUtils.send(sender, pl.getLang().getString("admin.invalid-players", "&4&l(!) &4Invalid players!"));
            return;
          }
          MsgUtils.send(sender, pl.getLang().getString("admin.forced-trade", "&6&l(!) &6You forced a trade between &e%PLAYER1% &6and &e%PLAYER2%"));
          String message = pl.getLang().getString("forced-trade", "&6&l(!) &6You've been forced into a trade with &e%PLAYER%");
          MsgUtils.send(p1, message.replace("%PLAYER%", p2.getName()));
          MsgUtils.send(p2, message.replace("%PLAYER%", p1.getName()));
          Trade trade = new Trade(p1, p2);
          if (sender instanceof Player && !(sender.equals(p1) || sender.equals(p2)))
            ((Player) sender).openInventory(trade.getSpectatorInv());
          return;
        } else if (args[0].equalsIgnoreCase("spectate")) {
          if (!(sender instanceof Player)) {
            MsgUtils.send(sender, pl.getLang().getString("admin.player-only", "&4&l(!) &4This command is for players only."));
            return;
          }
          Player player = (Player) sender;
          Player p1 = Bukkit.getPlayer(args[1]);
          Player p2 = Bukkit.getPlayer(args[2]);
          if (p1 == null || p2 == null || !p1.isOnline() || !p2.isOnline() || p1.equals(p2)) {
            MsgUtils.send(sender, pl.getLang().getString("admin.invalid-players", "&4&l(!) &4Invalid players!"));
            return;
          }
          Trade trade = pl.getTrade(p1, p2);
          if (trade == null) {
            MsgUtils.send(player, pl.getLang().getString("admin.no-trade", "&4&l(!) &4No trade was found with those arguments."));
          } else {
            player.openInventory(trade.getSpectatorInv());
          }
          return;
        }
        break;
    }
    MsgUtils.send(sender, new String[]{
            "&6&l<----- Trade+ by Trophonix ----->",
            "&e/trade <player> &fSend a trade request",
            "&e/tradeplus reload &fReload config files",
            "&e/tradeplus force <player1> <player2> &fForce 2 players to trade",
            "&e/tradeplus spectate <player(s)> &fSpectate an ongoing trade"
    });
  }

  private List<String> arg0 = Arrays.asList("reload", "rl", "force", "spectate");

  @Override public List<String> onTabComplete(CommandSender sender, String[] args, String full) {
    if (args.length == 0) {
      return arg0;
    } else if (args.length == 1 && !full.endsWith(" ")) {
      return arg0.stream().filter(name -> !name.equalsIgnoreCase(args[0]) && name.startsWith(args[0].toLowerCase())).collect(Collectors.toList());
    } else if (args.length > 1 && !full.endsWith(" ") && (args[0].equalsIgnoreCase("force") || args[0].equalsIgnoreCase("spectate"))) {
      return Bukkit.getOnlinePlayers().stream().map(Player::getName).filter(
          name -> !name.equalsIgnoreCase(args[args.length - 1])  && name.toLowerCase().startsWith(
              args[args.length - 1])).collect(Collectors.toList());
    }
    return super.onTabComplete(sender, args, full);
  }
}
