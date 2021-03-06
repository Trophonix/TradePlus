package com.trophonix.tradeplus.util;

import com.trophonix.tradeplus.TradePlus;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

public class PDCUtils {

  private static NamespacedKey ALLOW_TRADING;

  public static boolean allowTrading(Player player) {
    PersistentDataContainer pdc = player.getPersistentDataContainer();
    return pdc.getOrDefault(ALLOW_TRADING, PersistentDataType.BYTE, (byte)1) == 1;
  }

  public static boolean toggleTrading(Player player) {
    PersistentDataContainer pdc = player.getPersistentDataContainer();
    boolean allow = allowTrading(player);
    pdc.set(ALLOW_TRADING, PersistentDataType.BYTE, allow ? (byte)0 : (byte)1);
    return !allow;
  }

  public static void initialize(TradePlus plugin) {
    ALLOW_TRADING = new NamespacedKey(plugin, "allow_trading");
  }

}
