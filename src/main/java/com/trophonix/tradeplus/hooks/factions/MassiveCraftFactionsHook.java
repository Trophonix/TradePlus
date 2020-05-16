package com.trophonix.tradeplus.hooks.factions;

import com.massivecraft.factions.*;
import org.bukkit.entity.Player;

public class MassiveCraftFactionsHook {

  public static boolean isPlayerInEnemyTerritory(Player player) {
    FPlayer me = FPlayers.getInstance().getByPlayer(player);
    Faction faction = Board.getInstance().getFactionAt(new FLocation(player.getLocation()));
    return me.getRelationTo(faction).isEnemy();
  }
}
