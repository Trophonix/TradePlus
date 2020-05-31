package com.trophonix.tradeplus.extras;

import com.Ben12345rocks.VotingPlugin.Objects.User;
import com.Ben12345rocks.VotingPlugin.UserManager.UserManager;
import com.trophonix.tradeplus.TradePlus;
import com.trophonix.tradeplus.trade.Trade;
import com.trophonix.tradeplus.util.ItemFactory;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class VotingPluginExtra extends Extra {

  public VotingPluginExtra(Player player1, Player player2, TradePlus pl, Trade trade) {
    super("votingplugin", player1, player2, pl, trade);
  }

  @Override protected double getMax(Player player) {
    User user = UserManager.getInstance().getVotingPluginUser(player.getUniqueId());
    return user.getPoints();
  }

  @Override public void onTradeEnd() {
    User user1 = UserManager.getInstance().getVotingPluginUser(player1);
    User user2 = UserManager.getInstance().getVotingPluginUser(player2);
    if (value1 > 0) {
      user1.setPoints(user1.getPoints() - (int)value1);
      user2.setPoints(user2.getPoints() + (int)value1);
    }
    if (value2 > 0) {
      user2.setPoints(user2.getPoints() - (int)value2);
      user1.setPoints(user1.getPoints() + (int)value2);
    }
  }

  @Override protected ItemStack _getIcon(Player player) {
    return ItemFactory.replaceInMeta(
        icon,
        "%AMOUNT%",
        Integer.toString((int)(player.equals(player1) ? value1 : value2)),
        "%INCREMENT%",
        Integer.toString((int)increment),
        "%PLAYERINCREMENT%",
        Integer.toString((int)(player.equals(player1) ? increment1 : increment2)));
  }

  @Override protected ItemStack _getTheirIcon(Player player) {
    return ItemFactory.replaceInMeta(
        theirIcon, "%AMOUNT%", Integer.toString((int)(player.equals(player1) ? value1 : value2)));
  }

}
