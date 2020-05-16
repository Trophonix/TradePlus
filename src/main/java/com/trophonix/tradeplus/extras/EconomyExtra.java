package com.trophonix.tradeplus.extras;

import com.trophonix.tradeplus.TradePlus;
import com.trophonix.tradeplus.trade.Trade;
import com.trophonix.tradeplus.util.ItemFactory;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.economy.EconomyResponse;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class EconomyExtra extends Extra {

  private final Economy economy;

  public EconomyExtra(Player player1, Player player2, TradePlus pl, Trade trade) {
    super("economy", player1, player2, pl, trade);
    this.economy = pl.getServer().getServicesManager().getRegistration(Economy.class).getProvider();
  }

  @Override
  public double getMax(Player player) {
    return economy.getBalance(player);
  }

  @Override
  public void onTradeEnd() {
    if (value1 > 0) {
      if (economy.withdrawPlayer(player1, value1).type.equals(EconomyResponse.ResponseType.SUCCESS))
        economy.depositPlayer(player2, value1 - ((value1 / 100) * taxPercent));
    }
    if (value2 > 0) {
      if (economy.withdrawPlayer(player2, value2).type.equals(EconomyResponse.ResponseType.SUCCESS))
        economy.depositPlayer(player1, value2 - ((value2 / 100) * taxPercent));
    }
  }

  @Override
  public ItemStack _getIcon(Player player) {
    return ItemFactory.replaceInMeta(
        icon,
        "%AMOUNT%",
        decimalFormat.format(player.equals(player1) ? value1 : value2),
        "%CURRENCY%",
        economy.getBalance(player) == 1
            ? economy.currencyNameSingular()
            : economy.currencyNamePlural(),
        "%INCREMENT%",
        decimalFormat.format(increment),
        "%PLAYERINCREMENT%",
        decimalFormat.format(player.equals(player1) ? increment1 : increment2));
  }

  @Override
  public ItemStack _getTheirIcon(Player player) {
    return ItemFactory.replaceInMeta(
        theirIcon,
        "%AMOUNT%",
        decimalFormat.format(player.equals(player1) ? value1 : value2),
        "%CURRENCY%",
        economy.getBalance(player) == 1
            ? economy.currencyNameSingular()
            : economy.currencyNamePlural());
  }
}
