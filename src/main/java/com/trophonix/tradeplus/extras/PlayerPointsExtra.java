package com.trophonix.tradeplus.extras;

import com.trophonix.tradeplus.TradePlus;
import com.trophonix.tradeplus.util.ItemFactory;
import org.black_ixx.playerpoints.PlayerPoints;
import org.black_ixx.playerpoints.PlayerPointsAPI;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class PlayerPointsExtra extends Extra {

  private final PlayerPointsAPI playerPointsAPI;

  public PlayerPointsExtra(Player player1, Player player2, TradePlus pl) {
    super("playerpoints", player1, player2, pl);
    this.playerPointsAPI = new PlayerPointsAPI((PlayerPoints) pl.getServer().getPluginManager().getPlugin("PlayerPoints"));
  }

  @Override
  public double getMax(Player player) {
    return playerPointsAPI.look(player.getUniqueId());
  }

  @Override
  public void onTradeEnd() {
    if (value1 > 0) {
      playerPointsAPI.take(player1.getUniqueId(), (int) value1);
      playerPointsAPI.give(player2.getUniqueId(), (int) (value1 - ((value1 / 100) * taxPercent)));
    }
    if (value2 > 0) {
      playerPointsAPI.take(player2.getUniqueId(), (int) value2);
      playerPointsAPI.give(player1.getUniqueId(), (int) (value2 - ((value2 / 100) * taxPercent)));
    }
  }

  @Override
  public ItemStack getIcon(Player player) {
    return ItemFactory.replaceInMeta(icon, "%AMOUNT%", Double.toString(player.equals(player1) ? value1 : value2),
            "%INCREMENT%", Double.toString(increment),
            "%PLAYERINCREMENT%", Double.toString(player.equals(player1) ? increment1 : increment2));
  }

  @Override
  public ItemStack getTheirIcon(Player player) {
    return ItemFactory.replaceInMeta(theirIcon, "%AMOUNT%", Double.toString(player.equals(player1) ? value1 : value2));
  }

}
