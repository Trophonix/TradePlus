package com.trophonix.tradeplus.hooks;

import com.trophonix.tradeplus.hooks.factions.MassiveCraftFactionsHook;
import org.bukkit.entity.Player;

public class FactionsHook {

  private static boolean massiveCraft;

  static {
    try {
      Class.forName("com.massivecraft.factions.FPlayers");
      massiveCraft = true;
    } catch (Exception ignored) {
      massiveCraft = false;
    }
  }

  public static boolean isPlayerInEnemyTerritory(Player player) {
    if (massiveCraft) {
      return MassiveCraftFactionsHook.isPlayerInEnemyTerritory(player);
    }
    return false;
  }
}
