package com.trophonix.tradeplus.hooks;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.codemc.worldguardwrapper.WorldGuardWrapper;
import org.codemc.worldguardwrapper.flag.IWrappedFlag;

public class WorldGuardHook {

  private static IWrappedFlag<Boolean> tradingFlag;

  public static void init() {
    tradingFlag =
        WorldGuardWrapper.getInstance()
            .registerFlag("trading", Boolean.TYPE, true)
            .orElse(WorldGuardWrapper.getInstance().getFlag("trading", Boolean.TYPE).orElse(null));
  }

  public static boolean isTradingAllowed(Player player, Location location) {
    if (tradingFlag == null) return true;
    return WorldGuardWrapper.getInstance().queryFlag(player, location, tradingFlag).orElse(true);
  }
}
