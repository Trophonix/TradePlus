package com.trophonix.tradeplus;

import co.aikar.taskchain.BukkitTaskChainFactory;
import co.aikar.taskchain.TaskChainFactory;
import com.trophonix.tradeplus.commands.CommandHandler;
import com.trophonix.tradeplus.commands.TradeCommand;
import com.trophonix.tradeplus.commands.TradePlusCommand;
import com.trophonix.tradeplus.config.TradePlusConfig;
import com.trophonix.tradeplus.events.ExcessChestListener;
import com.trophonix.tradeplus.hooks.WorldGuardHook;
import com.trophonix.tradeplus.logging.Logs;
import com.trophonix.tradeplus.trade.InteractListener;
import com.trophonix.tradeplus.trade.Trade;
import com.trophonix.tradeplus.util.InvUtils;
import com.trophonix.tradeplus.util.PlayerUtil;
import com.trophonix.tradeplus.util.Sounds;
import lombok.Getter;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

public class TradePlus extends JavaPlugin implements Listener {

  public ConcurrentLinkedQueue<Trade> ongoingTrades = new ConcurrentLinkedQueue<>();
  @Getter private TaskChainFactory taskFactory;

  @Getter private TradePlusConfig tradeConfig;

//  private CommandHandler commandHandler;

  @Getter private List<Inventory> excessChests;

  private Logs logs;

  public Trade getTrade(Player player) {
    for (Trade trade : ongoingTrades) {
      if (trade.player1.equals(player) || trade.player2.equals(player)) return trade;
    }
    return null;
  }

  public Trade getTrade(Player player1, Player player2) {
    for (Trade trade : ongoingTrades) {
      if (trade.player1.equals(player1) && trade.player2.equals(player2)) return trade;
      if (trade.player2.equals(player1) && trade.player1.equals(player2)) return trade;
    }
    return null;
  }

  @Override
  public void onLoad() {
    try {
      WorldGuardHook.init();
    } catch (Throwable ignored) {
      getLogger().info("Failed to hook into worldguard. Ignore this if you don't have worldguard.");
    }
  }

  @Override
  public void onEnable() {
    tradeConfig = new TradePlusConfig(this);
    taskFactory = BukkitTaskChainFactory.create(this);
    taskFactory
        .newChain()
        .async(tradeConfig::load)
        .async(tradeConfig::update)
        .async(tradeConfig::save)
        .sync(
            () -> {
              excessChests = new ArrayList<>();
              setupCommands();
              reload();
              if (Sounds.version > 17) {
                getServer().getPluginManager().registerEvents(new InteractListener(this), this);
              }
              new ExcessChestListener(this);
            })
        .execute();
    getServer().getPluginManager().registerEvents(this, this);
  }

  @Override
  public void onDisable() {
    if (logs != null) {
      logs.save();
    }
  }

  private void setupCommands() {
    getCommand("trade").setExecutor(new TradeCommand(this));
    getCommand("tradeplus").setExecutor(new TradePlusCommand(this));
  }

  public void reload() {
    tradeConfig.reload();
    if (logs == null && tradeConfig.isTradeLogs()) {
      try {
        logs = new Logs(this, new File(getDataFolder(), "logs"));
        new BukkitRunnable() {
          @Override
          public void run() {
            try {
              logs.save();
            } catch (Exception | Error ex) {
              getLogger().info("The trade logger crashed.");
              cancel();
              logs = null;
            }
          }
        }.runTaskTimer(this, 5 * 60 * 20, 5 * 60 * 20);
        log("Initialized trade logger.");
      } catch (Exception | Error ex) {
        log("Failed to load trade logger.");
        ex.printStackTrace();
      }
    }
    InvUtils.reloadItems(this);
  }

  @EventHandler
  public void onJoin(PlayerJoinEvent event) {
    PlayerUtil.registerIP(event.getPlayer());
  }

  @EventHandler
  public void onQuit(PlayerQuitEvent event) {
    PlayerUtil.removeIP(event.getPlayer());
  }

  public void log(String message) {
    if (tradeConfig.isDebugMode()) {
      getLogger().info(message);
    }
  }

  public Logs getLogs() {
    return logs;
  }
}
