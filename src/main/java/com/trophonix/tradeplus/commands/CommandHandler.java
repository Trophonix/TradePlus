package com.trophonix.tradeplus.commands;

import com.trophonix.tradeplus.TradePlus;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.event.Cancellable;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.server.ServerCommandEvent;

import java.util.ArrayList;
import java.util.List;

public class CommandHandler implements Listener, CommandExecutor {

  @Getter private List<Command> commands = new ArrayList<>();

  public CommandHandler(TradePlus pl, boolean compatMode) {
    try {
      Class.forName("org.bukkit.event.server.TabCompleteEvent");
      Bukkit.getPluginManager()
          .registerEvents(
              new CommandHandler.TabCompleter() {
                @Override
                public List<String> getCompletions(
                    CommandSender sender, String cmd, String[] args, String buffer) {
                  Command command =
                      commands.stream().filter(c -> c.isAlias(cmd)).findFirst().orElse(null);
                  return command != null ? command.onTabComplete(sender, args, buffer) : null;
                }
              },
              pl);
    } catch (ClassNotFoundException ignored) {
    }
  }

  public void add(Command command) {
    commands.add(command);
  }

  public void clear() {
    commands.clear();
  }

  @Override
  public boolean onCommand(
      CommandSender sender, org.bukkit.command.Command command, String label, String[] args) {
    String[] cmd = new String[args.length + 1];
    cmd[0] = label;
    for (int i = 0; i < args.length; i++) {
      cmd[i+1] = args[i];
    }
    testAndRun(null, sender, cmd);
    return true;
  }

//  @EventHandler(ignoreCancelled = true)
//  public void onCommandEvent(PlayerCommandPreprocessEvent event) {
//    String[] cmd = event.getMessage().substring(1).split("\\s+");
//    testAndRun(event, event.getPlayer(), cmd);
//  }
//
//  @EventHandler(ignoreCancelled = true)
//  public void onServerCommand(ServerCommandEvent event) {
//    String[] cmd = event.getCommand().split("\\s+");
//    testAndRun(event, event.getSender(), cmd);
//  }

  private void testAndRun(Cancellable event, CommandSender sender, String[] cmd) {
    if (cmd.length > 0) {
      String[] args = new String[cmd.length - 1];
      System.arraycopy(cmd, 1, args, 0, cmd.length - 1);
      commands.stream()
          .filter(command -> command.isAlias(cmd[0]))
          .findFirst()
          .ifPresent(
              command -> {
                command.onCommand(sender, args);
                if (event != null) event.setCancelled(true);
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
        List<String> completions =
            getCompletions(event.getSender(), cmd[0], args, event.getBuffer());
        if (completions != null) event.setCompletions(completions);
      }
    }

    protected abstract List<String> getCompletions(
        CommandSender sender, String command, String[] args, String buffer);
  }
}
