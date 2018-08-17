package com.trophonix.tradeplus.util;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class Sounds {

  public static final int version;
  private static Sound pling;
  private static Sound click;
  private static Sound levelUp;
  private static Sound villagerHit;

  static {
    String[] split = Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3].split("_");
    version = Integer.parseInt(split[0].replace("v", "") + split[1]);
    System.out.println("You appear to be running version " + version);
  }

  public static void loadSounds() {
    try {
      pling = Sound.BLOCK_NOTE_BLOCK_PLING;
      click = Sound.UI_BUTTON_CLICK;
      levelUp = Sound.ENTITY_PLAYER_LEVELUP;
      villagerHit = Sound.ENTITY_VILLAGER_HURT;
    } catch (IllegalArgumentException | NullPointerException ex) {
      Bukkit.getConsoleSender().sendMessage(ChatColor.DARK_RED + "Unable to load sounds! Sound effects will be disabled.");
    }
  }

  public static void pling(Player player, float v1) {
    if (pling != null)
      player.playSound(player.getEyeLocation(), pling, 1, v1);
  }

  public static void click(Player player, float v1) {
    if (click != null)
      player.playSound(player.getEyeLocation(), click, 1, v1);
  }

  public static void levelUp(Player player, float v1) {
    if (levelUp != null)
      player.playSound(player.getEyeLocation(), levelUp, 1, v1);
  }

  public static void villagerHit(Player player, float v1) {
    if (villagerHit != null)
      player.playSound(player.getEyeLocation(), villagerHit, 1, v1);
  }

}
