package com.trophonix.tradeplus.extras;

import com.trophonix.tradeplus.TradePlus;
import com.trophonix.tradeplus.trade.Trade;
import com.trophonix.tradeplus.util.ItemFactory;
import com.trophonix.tradeplus.util.XP;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class ExperienceExtra extends Extra {

  private boolean levelMode;

  public ExperienceExtra(Player player1, Player player2, TradePlus pl, Trade trade) {
    super("experience", player1, player2, pl, trade);
    levelMode = pl.getConfig().getBoolean("extras.experience.levelMode", false);
  }

  @Override
  public double getMax(Player player) {
    //    if (levelMode) return player.getLevel();
    //    else
    return XP.getExp(player);
  }

  @Override
  public void onTradeEnd() {
    if (taxPercent > 0) {
      value1 -= (value1 * taxPercent) / 100;
      value2 -= (value2 * taxPercent) / 100;
    }
    if (value1 > 0) {
      changeXp(player1, -value1);
      changeXp(player2, value1);
    }
    if (value2 > 0) {
      changeXp(player2, -value2);
      changeXp(player1, value2);
    }
  }

  @Override
  public ItemStack _getIcon(Player player) {
    return ItemFactory.replaceInMeta(
        icon,
        "%AMOUNT%",
        decimalFormat.format(player.equals(player1) ? value1 : value2),
        "%INCREMENT%",
        decimalFormat.format(increment),
        "%PLAYERINCREMENT%",
        decimalFormat.format(player.equals(player1) ? increment1 : increment2),
        "%LEVELS%",
        Integer.toString(
            player.equals(player1)
                ? getLevelChangeFromXp(player1, -value1)
                : getLevelChangeFromXp(player2, -value2)));
  }

  @Override
  public ItemStack _getTheirIcon(Player player) {
    return ItemFactory.replaceInMeta(
        theirIcon,
        "%AMOUNT%",
        decimalFormat.format(player.equals(player1) ? value1 : value2),
        "%LEVELS%",
        Integer.toString(
            player.equals(player1)
                ? getLevelChangeFromXp(player2, value1)
                : getLevelChangeFromXp(player1, value2)));
  }

  private int getLevelChangeFromXp(Player receiver, double amount) {
    int currentXp = XP.getExp(receiver);
    double currentLevel = XP.getLevelFromExp(currentXp);
    return (int) (XP.getLevelFromExp(currentXp + (int) amount) - currentLevel);
  }

  private void changeXp(Player player, Number amount) {
    XP.changeExp(player, amount.intValue());
  }
}
