package com.trophonix.tradeplus.extras;

import com.trophonix.tradeplus.TradePlus;
import com.trophonix.tradeplus.trade.Trade;
import com.trophonix.tradeplus.util.ItemFactory;
import me.ryanhamshire.GriefPrevention.GriefPrevention;
import me.ryanhamshire.GriefPrevention.PlayerData;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class GriefPreventionExtra extends Extra {

  public GriefPreventionExtra(Player player1, Player player2, TradePlus pl, Trade trade) {
    super("griefprevention", player1, player2, pl, trade);
  }

  @Override
  public double getMax(Player player) {
    PlayerData data = GriefPrevention.instance.dataStore.getPlayerData(player.getUniqueId());
    if (data.getRemainingClaimBlocks() < data.getBonusClaimBlocks()) {
      return data.getRemainingClaimBlocks();
    } else {
      return data.getBonusClaimBlocks();
    }
  }

  @Override
  public void onTradeEnd() {
    GriefPrevention inst = GriefPrevention.instance;
    PlayerData data1 = inst.dataStore.getPlayerData(player1.getUniqueId());
    PlayerData data2 = inst.dataStore.getPlayerData(player2.getUniqueId());
    if (value1 > 0) {
      data1.setBonusClaimBlocks(data1.getBonusClaimBlocks() - (int) value1);
      data2.setBonusClaimBlocks(
          data2.getBonusClaimBlocks() + (int) (value1 - ((value1 / 100) * taxPercent)));
    }
    if (value2 > 0) {
      data2.setBonusClaimBlocks(data2.getBonusClaimBlocks() - (int) value2);
      data1.setBonusClaimBlocks(
          data1.getBonusClaimBlocks() + (int) (value2 - ((value2 / 100) * taxPercent)));
    }
    inst.dataStore.savePlayerData(player1.getUniqueId(), data1);
    inst.dataStore.savePlayerData(player2.getUniqueId(), data2);
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
        decimalFormat.format(player.equals(player1) ? increment1 : increment2));
  }

  @Override
  public ItemStack _getTheirIcon(Player player) {
    return ItemFactory.replaceInMeta(
        theirIcon, "%AMOUNT%", decimalFormat.format(player.equals(player1) ? value1 : value2));
  }
}
