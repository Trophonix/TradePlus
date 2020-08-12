package com.trophonix.tradeplus.util;

import com.trophonix.tradeplus.hooks.EssentialsHook;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.metadata.MetadataValue;

public class PlayerUtil {

  public static boolean isVanished(Player player) {
    if (Bukkit.getPluginManager().isPluginEnabled("Essentials")) {
      if (EssentialsHook.isVanished(player)) return true;
    }

    for (MetadataValue meta : player.getMetadata("vanished")) {
      if (meta.asBoolean()) return true;
    }
    return false;
  }
}
