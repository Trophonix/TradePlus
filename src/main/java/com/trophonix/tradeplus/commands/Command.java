package com.trophonix.tradeplus.commands;

import org.bukkit.command.CommandSender;

import java.util.List;

abstract class Command {

  private List<String> aliases;

  Command(List<String> aliases) {
    this.aliases = aliases;
  }

  public boolean isAlias(String command) {
    return aliases.contains(command.toLowerCase());
  }

  public abstract void onCommand(CommandSender sender, String[] args);

}
