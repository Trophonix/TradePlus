package com.trophonix.tradeplus.extras;

import com.trophonix.tradeplus.TradePlus;
import com.trophonix.tradeplus.trade.Trade;
import com.trophonix.tradeplus.util.Experience;
import com.trophonix.tradeplus.util.ItemFactory;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class ExperienceExtra extends Extra {

  public ExperienceExtra(Player player1, Player player2, TradePlus pl, Trade trade) {
    super("experience", player1, player2, pl, trade);
  }

  @Override
  public double getMax(Player player) {
    return Experience.getExp(player);
  }

  @Override
  public void onTradeEnd() {
    if (value1 > 0) {
      Experience.changeExp(player1, (int) (0 - value1));
      Experience.changeExp(player2, (int) (value1 - ((value1 / 100) * taxPercent)));
    }
    if (value2 > 0) {
      Experience.changeExp(player2, (int) (0 - value2));
      Experience.changeExp(player1, (int) (value2 - ((value2 / 100) * taxPercent)));
    }
  }

  @Override
  public ItemStack getIcon(Player player) {
    return ItemFactory.replaceInMeta(icon, "%AMOUNT%", decimalFormat.format(player.equals(player1) ? value1 : value2),
            "%INCREMENT%", decimalFormat.format(increment),
            "%PLAYERINCREMENT%", decimalFormat.format(player.equals(player1) ? increment1 : increment2));
  }

  @Override
  public ItemStack getTheirIcon(Player player) {
    return ItemFactory.replaceInMeta(theirIcon, "%AMOUNT%", decimalFormat.format(player.equals(player1) ? value1 : value2));
  }

}
