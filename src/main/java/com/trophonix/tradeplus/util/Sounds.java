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
  private static Sound villagerHmm;

  static {
    String[] split = Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3].split("_");
    version = Integer.parseInt(split[0].replace("v", "") + split[1]);
    System.out.println("You appear to be running version " + version);
  }

  public static void loadSounds() {
    try {
      if (version < 19) {
        pling = Sound.valueOf("NOTE_PLING");
        click = Sound.valueOf("CLICK");
        levelUp = Sound.valueOf("LEVEL_UP");
        villagerHit = Sound.valueOf("VILLAGER_HIT");
        villagerHmm = Sound.valueOf("VILLAGER_IDLE");
      } else if (version < 113) {
        pling = Sound.valueOf("BLOCK_NOTE_PLING");
        click = Sound.valueOf("UI_BUTTON_CLICK");
        levelUp = Sound.valueOf("ENTITY_PLAYER_LEVELUP");
        villagerHit = Sound.valueOf("ENTITY_VILLAGER_HURT");
        villagerHmm = Sound.valueOf("ENTITY_VILLAGER_AMBIENT");
      } else {
        pling = Sound.valueOf("BLOCK_NOTE_BLOCK_PLING");
        click = Sound.valueOf("UI_BUTTON_CLICK");
        levelUp = Sound.valueOf("ENTITY_PLAYER_LEVELUP");
        villagerHit = Sound.valueOf("ENTITY_VILLAGER_HURT");
        villagerHmm = Sound.valueOf("ENTITY_VILLAGER_AMBIENT");
      }
    } catch (IllegalArgumentException | NullPointerException | NoSuchFieldError ex) {
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

  public static void villagerHmm(Player player, float v1) {

  }

}
