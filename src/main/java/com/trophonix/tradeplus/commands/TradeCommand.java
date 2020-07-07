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
            if (pl.getTradeConfig().getAliases() != null) {
              addAll(pl.getTradeConfig().getAliases());
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
      if (pl.getTradeConfig().isWorldguardTradingFlag()) {
        if (Bukkit.getServer().getPluginManager().isPluginEnabled("WorldGuard")) {
          if (!WorldGuardHook.isTradingAllowed(player, player.getLocation())) {
            pl.getTradeConfig().getWorldguardTradingNotAllowed().send(player);
            return;
          }
        }
      }
    } catch (Throwable ignored) {

    }

    try {
      if (!pl.getTradeConfig().isFactionsAllowTradeInEnemyTerritory()) {
        if (FactionsHook.isPlayerInEnemyTerritory(player)) {
          pl.getTradeConfig().getFactionsEnemyTerritory().send(player);
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
                    pl.getTradeConfig().getTheyDenied().send(req.sender, "%PLAYER%", player.getName());
                  }
                }
              });
          pl.getTradeConfig().getYouDenied().send(player);
          return;
        }
        pl.getTradeConfig().getErrorsPlayerNotFound().send(player);
        return;
      }

      if (player == receiver) {
        pl.getTradeConfig().getErrorsSelfTrade().send(player);
        return;
      }

      if (!pl.getTradeConfig().isAllowSameIpTrade()) {
        InetSocketAddress address = player.getAddress();
        InetSocketAddress receiverAddress = receiver.getAddress();
        if (address != null
            && receiverAddress != null
            && address.getHostName().equals(receiverAddress.getHostName())) {
          pl.getTradeConfig().getErrorsSameIp().send(player);
          return;
        }
      }

      if (!pl.getTradeConfig().isAllowTradeInCreative()) {
        if (player.getGameMode().equals(GameMode.CREATIVE)) {
          pl.getTradeConfig().getErrorsCreative().send(player);
          return;
        } else if (receiver.getGameMode().equals(GameMode.CREATIVE)) {
          pl.getTradeConfig().getErrorsCreativeThem().send(player, "%PLAYER%", receiver.getName());
          return;
        }
      }

      if (player.getWorld().equals(receiver.getWorld())) {
        double amount = pl.getTradeConfig().getSameWorldRange();
        if (amount != 0.0
            && player.getLocation().distanceSquared(receiver.getLocation()) > Math.pow(amount, 2)) {
          pl.getTradeConfig().getErrorsSameWorldRange().send(player, "%PLAYER%", receiver.getName(), "%AMOUNT%", format.format(amount));
          return;
        }
      } else {
        if (pl.getTradeConfig().isAllowCrossWorld()) {
          double amount = Math.pow(pl.getTradeConfig().getCrossWorldRange(), 2);
          Location test = receiver.getLocation().clone();
          test.setWorld(player.getWorld());
          if (amount != 0.0 && player.getLocation().distanceSquared(test) > amount) {
            pl.getTradeConfig().getErrorsCrossWorldRange().send(player, "%PLAYER%", receiver.getName(), "%AMOUNT%", format.format(amount));
            return;
          }
        } else {
          pl.getTradeConfig().getErrorsNoCrossWorld().send(player, "%PLAYER%", receiver.getName());
          return;
        }
      }

      for (TradeRequest req : requests) {
        if (req.sender == player) {
          pl.getTradeConfig().getErrorsWaitForExpire().send(player, "%PLAYER%", receiver.getName());
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
        pl.getTradeConfig().getAcceptSender().send(receiver, "%PLAYER%", player.getName());
        pl.getTradeConfig().getAcceptReceiver().send(player, "%PLAYER%", receiver.getName());
        new Trade(receiver, player);
        requests.removeIf(req -> req.contains(player) && req.contains(receiver));
      } else {
        String sendPermission = pl.getTradeConfig().getSendPermission();
        if (permissionRequired) {
          if (!sender.hasPermission(sendPermission)) {
            pl.getTradeConfig().getErrorsNoPermsAccept().send(player);
            return;
          }
        }

        String acceptPermission =
            pl.getTradeConfig().getAcceptPermission();
        if (permissionRequired && !receiver.hasPermission(acceptPermission)) {
          pl.getTradeConfig().getErrorsNoPermsReceive().send(player, "%PLAYER%", receiver.getName());
          return;
        }

        TradeRequestEvent event = new TradeRequestEvent(player, receiver);
        Bukkit.getPluginManager().callEvent(event);
        if (event.isCancelled()) return;
        final TradeRequest request = new TradeRequest(player, receiver);
        requests.add(request);
        pl.getTradeConfig().getRequestSent().send(player, "%PLAYER%", receiver.getName());
        pl.getTradeConfig().getRequestReceived().setOnClick("/trade " + player.getName()).send(receiver, "%PLAYER%", player.getName());
        Bukkit.getScheduler()
            .runTaskLater(
                pl,
                () -> {
                  boolean was = requests.remove(request);
                  if (player.isOnline() && was) {
                    pl.getTradeConfig().getExpired().send(player, "%PLAYER%", receiver.getName());
                  }
                },
                20 * pl.getTradeConfig().getRequestCooldownSeconds());
      }
      return;
    }
    pl.getTradeConfig().getErrorsInvalidUsage().send(player);
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
