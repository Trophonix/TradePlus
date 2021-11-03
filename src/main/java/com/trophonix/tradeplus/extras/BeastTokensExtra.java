package com.trophonix.tradeplus.extras;

import com.trophonix.tradeplus.TradePlus;
import com.trophonix.tradeplus.trade.Trade;
import com.trophonix.tradeplus.util.ItemFactory;
import me.mraxetv.beasttokens.BeastTokensAPI;
import me.mraxetv.beasttokens.api.handlers.PlayersManager;
import me.mraxetv.beasttokens.api.handlers.TokensManager;
import me.realized.tokenmanager.api.TokenManager;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class BeastTokensExtra extends Extra {

  private TokensManager api;

  public BeastTokensExtra(Player player1, Player player2, TradePlus pl, Trade trade) {
    super("beasttokens", player1, player2, pl, trade);
    api = BeastTokensAPI.getTokensManager();
  }

  @Override
  public double getMax(Player player) { return api.getTokens(player); }

  @Override
  public void onTradeEnd() {
    if (value1 > 0) {
      api.setTokens(player1, api.getTokens(player1) - (long) value1);
      api.setTokens(player2, api.getTokens(player2) + (long) value1);
    }
    if (value2 > 0) {
      api.setTokens(player2, api.getTokens(player2) - (long) value2);
      api.setTokens(player1, api.getTokens(player1) + (long) value2);
    }
  }

  @Override
  public ItemStack _getIcon(Player player) {
    return ItemFactory.replaceInMeta(
        icon,
        "%AMOUNT%",
        Long.toString((long) (player.equals(player1) ? value1 : value2)),
        "%INCREMENT%",
        Long.toString((long) increment),
        "%PLAYERINCREMENT%",
        Long.toString((long) (player.equals(player1) ? increment1 : increment2)));
  }

  @Override
  public ItemStack _getTheirIcon(Player player) {
    return ItemFactory.replaceInMeta(
        theirIcon, "%AMOUNT%", Long.toString((long) (player.equals(player1) ? value1 : value2)));
  }
}
