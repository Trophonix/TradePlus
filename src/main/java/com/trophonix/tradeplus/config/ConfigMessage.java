package com.trophonix.tradeplus.config;

import com.trophonix.tradeplus.util.MsgUtils;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

public class ConfigMessage {

  private String[] message;
  private String onHover;
  private String onClick;

  public ConfigMessage(ConfigurationSection yml, String key, String defaultText) {
    String text = null;
    if (yml.isString(key)) {
      text = yml.getString(key);
    } else if (yml.isConfigurationSection(key)) {
      text = yml.getString(key + ".text");
      onHover = yml.getString(key + ".hover");
    }
    if (text == null) text = defaultText;
    if (text.contains("%NEWLINE%")) {
      message = text.split("%NEWLINE%");
      for (int i = 0; i < message.length; i++) {
        message[i] = MsgUtils.color(message[i]);
      }
    } else {
      message = new String[] {MsgUtils.color(text)};
    }
  }

  public void send(CommandSender player, String... replacements) {
    String hover = this.onHover;
    if (hover != null)
      for (int i = 0; i < replacements.length - 1; i += 2) {
        hover = hover.replace(replacements[i], replacements[i + 1]);
      }
    for (String line : message) {
      for (int i = 0; i < replacements.length - 1; i += 2) {
        line = line.replace(replacements[i], replacements[i + 1]);
      }
      if (onHover == null && onClick == null) {
        player.sendMessage(line);
      } else {
        MsgUtils.send((Player) player, hover, onClick, line);
      }
    }
  }

  public ConfigMessage setOnClick(String command) {
    onClick = command;
    return this;
  }
}
