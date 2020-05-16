package com.trophonix.tradeplus.util;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MsgUtils {

  private static boolean clickableMessages;

  static {
    try {
      clickableMessages = Class.forName("net.md_5.bungee.api.chat.TextComponent") != null;
    } catch (ClassNotFoundException ignored) {
      clickableMessages = false;
    }
  }

  public static void send(Player player, String onHover, String onClick, String[] messages) {
    if (clickableMessages) {
      MsgUtils1_8.send(player, onHover, onClick, messages);
    } else {
      send(player, messages);
    }
  }

  public static void send(Player player, String onHover, String onClick, String message) {
    if (message.contains("%NEWLINE%")) {
      send(player, onHover, onClick, message.split("%NEWLINE%"));
    } else {
      send(player, onHover, onClick, new String[] {message});
    }
  }

  public static void send(CommandSender sender, String[] messages) {
    for (String message : messages) {
      send(sender, message);
    }
  }

  public static void send(CommandSender sender, String message) {
    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', message));
  }
}
