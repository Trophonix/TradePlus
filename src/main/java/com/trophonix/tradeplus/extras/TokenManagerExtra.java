package com.trophonix.tradeplus.extras;

import com.trophonix.tradeplus.TradePlus;
import com.trophonix.tradeplus.trade.Trade;
import com.trophonix.tradeplus.util.ItemFactory;
import me.realized.tokenmanager.api.TokenManager;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class TokenManagerExtra extends Extra {

  private TokenManager api;

  public TokenManagerExtra(Player player1, Player player2, TradePlus pl, Trade trade) {
    super("tokenmanager", player1, player2, pl, trade);
    api = (TokenManager)pl.getServer().getPluginManager().getPlugin("TokenManager");
  }

  @Override
  public double getMax(Player player) {
    return api.getTokens(player).orElse(0);
  }

  @Override
  public void onTradeEnd() {
    if (value1 > 0) {
      api.setTokens(player1, api.getTokens(player1).orElse((long)value1) - (long)value1);
      api.setTokens(player2, api.getTokens(player2).orElse(0L) + (long)value1);
    }
    if (value2 > 0) {
      api.setTokens(player2, api.getTokens(player2).orElse((long)value2) - (long)value2);
      api.setTokens(player1, api.getTokens(player1).orElse(0L) + (long)value2);
    }
  }

  @Override
  public ItemStack _getIcon(Player player) {
    return ItemFactory.replaceInMeta(icon, "%AMOUNT%", Long.toString((long)(player.equals(player1) ? value1 : value2)),
            "%INCREMENT%", Long.toString((long)increment),
            "%PLAYERINCREMENT%", Long.toString((long)(player.equals(player1) ? increment1 : increment2)));
  }

  @Override
  public ItemStack _getTheirIcon(Player player) {
    return ItemFactory.replaceInMeta(theirIcon, "%AMOUNT%", Long.toString((long)(player.equals(player1) ? value1 : value2)));
  }

}
