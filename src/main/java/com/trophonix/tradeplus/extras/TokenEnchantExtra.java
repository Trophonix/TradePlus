package com.trophonix.tradeplus.extras;

import com.trophonix.tradeplus.TradePlus;
import com.trophonix.tradeplus.util.ItemFactory;
import com.vk2gpz.tokenenchant.api.TokenEnchantAPI;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class TokenEnchantExtra extends Extra {

  public TokenEnchantExtra(Player player1, Player player2, TradePlus pl) {
    super("tokenenchant", player1, player2, pl);
  }

  @Override
  public double getMax(Player player) {
    return TokenEnchantAPI.getInstance().getTokens(player);
  }

  @Override
  public void onTradeEnd() {
    if (value1 > 0) {
      TokenEnchantAPI.getInstance().removeTokens(player1, value1);
      TokenEnchantAPI.getInstance().addTokens(player2, value1);
    }
    if (value2 > 0) {
      TokenEnchantAPI.getInstance().removeTokens(player2, value2);
      TokenEnchantAPI.getInstance().addTokens(player1, value2);
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
