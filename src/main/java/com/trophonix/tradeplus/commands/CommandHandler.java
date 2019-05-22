package com.trophonix.tradeplus.commands;

import com.trophonix.tradeplus.TradePlus;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CommandHandler implements Listener {

  private List<Command> commands = new ArrayList<>();

  public CommandHandler(TradePlus pl) {
    pl.getServer().getPluginManager().registerEvents(this, pl);
    try {
      Class.forName("org.bukkit.event.server.TabCompleteEvent");
      Bukkit.getPluginManager().registerEvents(new CommandHandler.TabCompleter() {
        @Override public List<String> getCompletions(CommandSender sender, String cmd, String[] args, String buffer) {
          Command command = commands.stream()
              .filter(c -> c.isAlias(cmd))
              .findFirst().orElse(null);
          return command != null ? command.onTabComplete(sender, args, buffer)
                     : Collections.emptyList();
        }
      }, pl);
    } catch (ClassNotFoundException ignored) { }
  }

  public void add(Command command) {
    commands.add(command);
  }

  public void clear() {
    commands.clear();
  }

  @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
  public void onCommand(PlayerCommandPreprocessEvent event) {
    String[] cmd = event.getMessage().substring(1).split("\\s+");
    if (cmd.length > 0) {
      String[] args = new String[cmd.length - 1];
      System.arraycopy(cmd, 1, args, 0, cmd.length - 1);
      commands.stream()
              .filter(command -> command.isAlias(cmd[0]))
              .findFirst().ifPresent(command -> {
        command.onCommand(event.getPlayer(), args);
        event.setCancelled(true);
      });
    }
  }

  private abstract class TabCompleter implements Listener {

    @EventHandler
    public void onTabComplete(org.bukkit.event.server.TabCompleteEvent event) {
      String[] cmd = event.getBuffer().split("\\s+");
      if (cmd.length > 0) {
        String[] args = new String[cmd.length - 1];
        System.arraycopy(cmd, 1, args, 0, cmd.length - 1);
        event.setCompletions(getCompletions(event.getSender(), cmd[0], args, event.getBuffer()));
      }
    }

    public abstract List<String> getCompletions(CommandSender sender, String command, String[] args, String buffer);

  }

}
