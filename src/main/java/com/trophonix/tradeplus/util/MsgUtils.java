package com.trophonix.tradeplus.util;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MsgUtils {

  private static int version;

  public static void send(Player player, String onHover, String onClick, String[] messages) {
    if (version < 18) {
      send(player, messages);
    } else {
      MsgUtils1_8.send(player, onHover, onClick, messages);
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

  private static String center(String string) {
    StringBuilder result = new StringBuilder(string);
    while (result.length() < 48) {
      result = new StringBuilder(" " + result + " ");
    }
    return result.toString();
  }

  public static void initMsgUtils() {
    String[] split = Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3].split("_");
    version = Integer.parseInt(split[0].replace("v", "") + split[1]);
  }

}
