package com.trophonix.tradeplus.commands;

import com.trophonix.tradeplus.TradePlus;
import com.trophonix.tradeplus.events.TradeAcceptEvent;
import com.trophonix.tradeplus.events.TradeRequestEvent;
import com.trophonix.tradeplus.hooks.FactionsHook;
import com.trophonix.tradeplus.hooks.WorldGuardHook;
import com.trophonix.tradeplus.trade.Trade;
import com.trophonix.tradeplus.trade.TradeRequest;
import com.trophonix.tradeplus.util.MsgUtils;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.net.InetSocketAddress;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.stream.Collectors;

public class TradeCommand extends Command {

  private static final DecimalFormat format = new DecimalFormat("0.##");

  private final ConcurrentLinkedQueue<TradeRequest> requests = new ConcurrentLinkedQueue<>();

  private final TradePlus pl;

  public TradeCommand(TradePlus pl) {
    super(
        new ArrayList<String>() {
          {
            add("trade");
            if (pl.getConfig().contains("aliases")) {
              addAll(pl.getConfig().getStringList("aliases"));
            }
          }
        });
    this.pl = pl;
  }

  @Override
  public void onCommand(CommandSender sender, String[] args) {
    if (!(sender instanceof Player)) {
      MsgUtils.send(sender, "&cThis command can only be executed by players!");
      return;
    }
    final Player player = (Player) sender;

    try {
      if (pl.getConfig().getBoolean("hooks.worldguard.trading-flag", true)) {
        if (Bukkit.getServer().getPluginManager().isPluginEnabled("WorldGuard")) {
          if (!WorldGuardHook.isTradingAllowed(player, player.getLocation())) {
            MsgUtils.send(
                player,
                pl.getLang()
                    .getString(
                        "hooks.worldguard.trading-not-allowed",
                        "&4&l(!) &4You can't trade in this area."));
            return;
          }
        }
      }
    } catch(Throwable ignored) {

    }

    try {
      if (!pl.getConfig().getBoolean("hooks.factions.allow-trades-in-enemy-territory", false)) {
        if (FactionsHook.isPlayerInEnemyTerritory(player)) {
          MsgUtils.send(
              player,
              pl.getConfig()
                  .getString(
                      "hooks.factions.enemy-territory",
                      "&4&l(!) &cYou can't trade in enemy territory.")
                  .split("%NEWLINE%"));
          return;
        }
      }
    } catch (Throwable ignored) {
    }

    boolean permissionRequired = pl.getConfig().getBoolean("permissions.required", false);

    if (args.length == 1) {
      final Player receiver = Bukkit.getPlayer(args[0]);
      if (receiver == null) {
        if (args[0].equalsIgnoreCase("deny")) {
          requests.forEach(
              req -> {
                if (req.receiver == player) {
                  requests.remove(req);
                  if (req.sender.isOnline()) {
                    MsgUtils.send(
                        req.sender,
                        pl.getLang()
                            .getString("denied.them")
                            .replace("%PLAYER%", player.getName())
                            .split("%NEWLINE%"));
                  }
                }
              });
          MsgUtils.send(player, pl.getLang().getString("denied.you").split("%NEWLINE%"));
          return;
        }
        MsgUtils.send(
            player,
            pl.getLang()
                .getString("errors.player-not-found")
                .replace("%PLAYER%", args[0])
                .split("%NEWLINE%"));
        return;
      }

      if (player == receiver) {
        MsgUtils.send(player, pl.getLang().getString("errors.self-trade").split("%NEWLINE%"));
        return;
      }

      if (!pl.getConfig().getBoolean("allow-same-ip-trade", true)) {
        InetSocketAddress address = player.getAddress();
        InetSocketAddress receiverAddress = receiver.getAddress();
        if (address != null
            && receiverAddress != null
            && address.getHostName().equals(receiverAddress.getHostName())) {
          MsgUtils.send(
              player,
              pl.getLang()
                  .getString(
                      "errors.same-ip", "&4&l(!) &4Players aren't allowed to trade on same IP!")
                  .split("%NEWLINE%"));
          return;
        }
      }

      if (!pl.getConfig().getBoolean("allow-trade-in-creative", true)) {
        if (player.getGameMode().equals(GameMode.CREATIVE)) {
          MsgUtils.send(
              player,
              pl.getLang()
                  .getString("errors.creative", "&4&l(!) &r&4You can't trade in creative mode!")
                  .split("%NEWLINE%"));
          return;
        } else if (receiver.getGameMode().equals(GameMode.CREATIVE)) {
          MsgUtils.send(
              player,
              pl.getLang()
                  .getString("errors.creative-them", "&4&l(!) &r&4That player is in creative mode!")
                  .split("%NEWLINE%"));
          return;
        }
      }

      if (player.getWorld().equals(receiver.getWorld())) {
        double amount = pl.getConfig().getDouble("ranges.sameworld");
        if (amount != 0.0
            && player.getLocation().distanceSquared(receiver.getLocation()) > Math.pow(amount, 2)) {
          MsgUtils.send(
              player,
              pl.getLang()
                  .getString("errors.within-range.same-world")
                  .replace("%PLAYER%", receiver.getName())
                  .replace("%AMOUNT%", format.format(amount))
                  .split("%NEWLINE%"));
          return;
        }
      } else {
        if (pl.getConfig().getBoolean("ranges.allowcrossworld", false)) {
          double amount = Math.pow(pl.getConfig().getDouble("ranges.crossworld"), 2);
          Location test = receiver.getLocation().clone();
          test.setWorld(player.getWorld());
          if (amount != 0.0 && player.getLocation().distanceSquared(test) > amount) {
            MsgUtils.send(
                player,
                pl.getLang()
                    .getString("errors.within-range.cross-world")
                    .replace("%PLAYER%", receiver.getName())
                    .replace("%AMOUNT%", amount + "")
                    .split("%NEWLINE%"));
            return;
          }
        } else {
          MsgUtils.send(
              player,
              pl.getLang()
                  .getString("errors.no-cross-world")
                  .replace("%PLAYER%", receiver.getName())
                  .split("%NEWLINE%"));
          return;
        }
      }

      for (TradeRequest req : requests) {
        if (req.sender == player) {
          MsgUtils.send(
              player,
              pl.getLang()
                  .getString("errors.wait-for-expire", "&4&l(!) &cYou already sent them a request.")
                  .split("%NEWLINE%"));
          return;
        }
      }

      boolean accept = false;
      for (TradeRequest req : requests) {
        if (req.contains(player) && req.contains(receiver)) accept = true;
      }
      if (accept) {
        TradeAcceptEvent tradeAcceptEvent = new TradeAcceptEvent(receiver, player);
        Bukkit.getPluginManager().callEvent(tradeAcceptEvent);
        if (tradeAcceptEvent.isCancelled()) return;
        MsgUtils.send(
            receiver,
            pl.getLang()
                .getString("accept.sender", "&6&l(!) &r&e%PLAYER% &6accepted your trade request")
                .replace("%PLAYER%", player.getName())
                .split("%NEWLINE%"));
        MsgUtils.send(
            player,
            pl.getLang()
                .getString(
                    "accept.receiver", "&6&l(!) &r&6You accepted &e%PLAYER%'s &6trade request")
                .replace("%PLAYER%", receiver.getName())
                .split("%NEWLINE%"));
        new Trade(receiver, player);
        requests.removeIf(req -> req.contains(player) && req.contains(receiver));
      } else {
        String sendPermission = pl.getConfig().getString("permissions.send", "tradeplus.send");
        if (permissionRequired) {
          if (!sender.hasPermission(sendPermission)) {
            MsgUtils.send(
                player,
                pl.getLang()
                    .getString(
                        "errors.no-perms.accept", "&4&l(!) &r&4You do not have permission to trade")
                    .split("%NEWLINE%"));
            return;
          }
        }

        String acceptPermission =
            pl.getConfig().getString("permissions.accept", "tradeplus.accept");
        if (permissionRequired && !receiver.hasPermission(acceptPermission)) {
          MsgUtils.send(
              player,
              pl.getLang()
                  .getString(
                      "errors.no-perms.receive",
                      "&4&l(!) &r&4That player does not have permission to accept a trade")
                  .replace("%PLAYER%", receiver.getName())
                  .split("%NEWLINE%"));
          return;
        }

        TradeRequestEvent event = new TradeRequestEvent(player, receiver);
        Bukkit.getPluginManager().callEvent(event);
        if (event.isCancelled()) return;
        final TradeRequest request = new TradeRequest(player, receiver);
        requests.add(request);
        MsgUtils.send(
            player,
            pl.getLang()
                .getString("request.sent", "&6&l(!) &r&6You sent a trade request to &e%PLAYER%")
                .replace("%PLAYER%", receiver.getName())
                .split("%NEWLINE%"));
        MsgUtils.send(
            receiver,
            pl.getLang()
                .getString("request.received.hover", "&6&lClick here to trade with &e&l%PLAYER%")
                .replace("%PLAYER%", player.getName()),
            "/trade " + player.getName(),
            pl.getLang()
                .getString(
                    "request.received.text",
                    "&6&l(!) &r&6You received a trade request from &e%PLAYER%"
                        + "%NEWLINE%"
                        + "&6&l(!) &r&6Type &e/trade %PLAYER% &6to begin trading")
                .replace("%PLAYER%", player.getName())
                .split("%NEWLINE%"));
        Bukkit.getScheduler()
            .runTaskLater(
                pl,
                () -> {
                  boolean was = requests.remove(request);
                  if (player.isOnline() && was) {
                    MsgUtils.send(
                        player,
                        pl.getLang()
                            .getString("expired", "&4&l(!) &r&4Your last trade request expired")
                            .replace("%PLAYER%", receiver.getName())
                            .split("%NEWLINE%"));
                  }
                },
                20 * pl.getConfig().getInt("requestcooldownseconds", 15));
      }
      return;
    }
    MsgUtils.send(
        player,
        pl.getLang()
            .getString(
                "errors.invalid-usage",
                "&4&l(!) &r&4Invalid arguments. Usage: %NEWLINE%"
                    + "    &c- /trade <player name>%NEWLINE%"
                    + "    &c- /trade deny")
            .split("%NEWLINE%"));
  }

  @Override
  public List<String> onTabComplete(CommandSender sender, String[] args, String full) {
    List<String> args0 = new ArrayList<>();
    args0.add("deny");
    args0.addAll(
        Bukkit.getOnlinePlayers().stream().map(Player::getName).collect(Collectors.toList()));
    if (args.length == 0) {
      return args0;
    } else if (args.length == 1) {
      return args0.stream()
          .filter(
              name ->
                  !name.equalsIgnoreCase(args[0])
                      && name.toLowerCase().startsWith(args[0].toLowerCase()))
          .collect(Collectors.toList());
    }
    return super.onTabComplete(sender, args, full);
  }
}
