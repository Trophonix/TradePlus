package com.trophonix.tradeplus.commands;

import com.trophonix.tradeplus.TradePlus;
import com.trophonix.tradeplus.events.TradeAcceptEvent;
import com.trophonix.tradeplus.events.TradeRequestEvent;
import com.trophonix.tradeplus.trade.Trade;
import com.trophonix.tradeplus.trade.TradeRequest;
import com.trophonix.tradeplus.util.MsgUtils;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentLinkedQueue;

public class TradeCommand extends Command {

  private final ConcurrentLinkedQueue<TradeRequest> requests = new ConcurrentLinkedQueue<>();

  private final TradePlus pl;

  public TradeCommand(TradePlus pl) {
    super(new ArrayList<String>() {{
      add("trade");
      if (pl.getConfig().contains("aliases")) {
        addAll(pl.getConfig().getStringList("aliases"));
      }
    }});
    this.pl = pl;
  }

  @Override
  public void onCommand(CommandSender sender, String[] args) {
    if (!(sender instanceof Player)) {
      MsgUtils.send(sender, "&cThis command can only be executed by players!");
      return;
    }
    final Player player = (Player) sender;

    boolean permissionRequired = pl.getConfig().getBoolean("permissions.required", false);
    
    String sendPermission = pl.getConfig().getString("permissions.send", "tradeplus.send");
    if (permissionRequired && !sender.hasPermission(sendPermission)) {
      MsgUtils.send(player, pl.getLang().getString("nopermssender").replace("%PLAYER%", receiver.getName()).split("%NEWLINE%"));
      return;
    }

    if (args.length == 1) {
      final Player receiver = Bukkit.getPlayer(args[0]);
      if (receiver == null) {
        if (args[0].equalsIgnoreCase("deny")) {
          requests.forEach(req -> {
            if (req.receiver == player) {
              requests.remove(req);
              if (req.sender.isOnline()) {
                MsgUtils.send(req.sender, pl.getLang().getString("denied-them").replace("%PLAYER%", player.getName()).split("%NEWLINE%"));
              }
            }
          });
          MsgUtils.send(player, pl.getLang().getString("denied-you").split("%NEWLINE%"));
          return;
        }
        MsgUtils.send(player, pl.getLang().getString("playernotfound").replace("%PLAYER%", args[0]).split("%NEWLINE%"));
        return;
      }
      if (pl.getConfig().getBoolean("allow-trade-in-creative", true)) {
        if (player.getGameMode().equals(GameMode.CREATIVE)) {
          MsgUtils.send(player, pl.getLang().getString("creative").split("%NEWLINE%"));
          return;
        } else if (receiver.getGameMode().equals(GameMode.CREATIVE)) {
          MsgUtils.send(player, pl.getLang().getString("creativethem").split("%NEWLINE%"));
          return;
        }
      }
      if (player == receiver) {
        MsgUtils.send(player, pl.getLang().getString("tradewithself").split("%NEWLINE%"));
        return;
      }
      String acceptPermission = pl.getConfig().getString("permissions.accept", "tradeplus.accept");
      if (permissionRequired && !receiver.hasPermission(acceptPermission)) {
        MsgUtils.send(player, pl.getLang().getString("nopermsreceiver").replace("%PLAYER%", receiver.getName()).split("%NEWLINE%"));
        return;
      }
      if (player.getWorld().equals(receiver.getWorld())) {
        double amount = pl.getConfig().getDouble("ranges.sameworld");
        if (amount != 0.0 && player.getLocation().distance(receiver.getLocation()) > amount) {
          MsgUtils.send(player, pl.getLang().getString("withinrange").replace("%PLAYER%", receiver.getName()).replace("%AMOUNT%", amount + "").split("%NEWLINE%"));
          return;
        }
      } else {
        if (pl.getConfig().getBoolean("ranges.allowcrossworld")) {
          double amount = pl.getConfig().getDouble("ranges.crossworld");
          if (amount != 0.0 && player.getLocation().distance(new Location(player.getWorld(), receiver.getLocation().getX(), receiver.getLocation().getY(), receiver.getLocation().getZ())) > amount) {
            MsgUtils.send(player, pl.getLang().getString("withinrangecrossworld").replace("%PLAYER%", receiver.getName()).replace("%AMOUNT%", amount + "").split("%NEWLINE%"));
            return;
          }
        } else {
          MsgUtils.send(player, pl.getLang().getString("nocrossworld").replace("%PLAYER%", receiver.getName()).split("%NEWLINE%"));
          return;
        }
      }
      for (TradeRequest req : requests) {
        if (req.sender == player) {
          MsgUtils.send(player, pl.getLang().getString("waitforexpire").split("%NEWLINE%"));
          return;
        }
      }

      boolean accept = false;
      for (TradeRequest req : requests) {
        if (req.sender.equals(receiver) && req.receiver.equals(player))
          accept = true;
      }
      if (accept) {
        Bukkit.getPluginManager().callEvent(new TradeAcceptEvent(receiver, player));
        MsgUtils.send(receiver, pl.getLang().getString("senderaccept").replace("%PLAYER%", player.getName()).split("%NEWLINE%"));
        MsgUtils.send(player, pl.getLang().getString("receiveraccept").replace("%PLAYER%", receiver.getName()).split("%NEWLINE%"));
        new Trade(player, receiver);
        requests.removeIf(req -> (req.sender.equals(player) && req.receiver.equals(receiver)) || (req.sender.equals(receiver) && req.receiver.equals(player)));
      } else {
        String sendPermission = pl.getConfig().getString("permissions.send");
        if (permissionRequired) {
          if (!player.hasPermission(sendPermission)) {
            MsgUtils.send(player, pl.getLang().getString("noperms").split("%NEWLINE%"));
            return;
          }
        }
        TradeRequestEvent event = new TradeRequestEvent(player, receiver);
        Bukkit.getPluginManager().callEvent(event);
        if (event.isCancelled()) return;
        final TradeRequest request = new TradeRequest(player, receiver);
        requests.add(request);
        MsgUtils.send(player, pl.getLang().getString("sentrequest").replace("%PLAYER%", receiver.getName()).split("%NEWLINE%"));
        MsgUtils.send(receiver, pl.getLang().getString("receivedrequesthover").replace("%PLAYER%", player.getName()), "/trade " + player.getName(),
                pl.getLang().getString("receivedrequest").replace("%PLAYER%", player.getName()).split("%NEWLINE%"));
        Bukkit.getScheduler().runTaskLater(pl, () -> {
          boolean was = requests.remove(request);
          if (player.isOnline() && was) {
            MsgUtils.send(player, pl.getLang().getString("expired").replace("%PLAYER%", receiver.getName()).split("%NEWLINE%"));
          }
        }, 20 * pl.getConfig().getInt("requestcooldownseconds", 15));
      }
      return;
    }
    MsgUtils.send(player, pl.getLang().getString("invalidusage").split("%NEWLINE%"));
  }

}
