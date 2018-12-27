package com.trophonix.tradeplus.extras;

import com.trophonix.tradeplus.TradePlus;
import com.trophonix.tradeplus.util.ItemFactory;
import me.realized.tokenmanager.TokenManagerPlugin;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class TokenManagerExtra extends Extra {
  
  public TokenManagerExtra(Player player1, Player player2, TradePlus pl) {
    super("tokenmanager", player1, player2, pl);
  }

  @Override
  public double getMax(Player player) {
    return TokenManagerPlugin.getInstance().getTokens(player).orElse(0);
  }

  @Override
  public void onTradeEnd() {
    if (value1 > 0) {
      TokenManagerPlugin.getInstance().removeTokens(player1.getUniqueId().toString(), (long)value1);
      TokenManagerPlugin.getInstance().addTokens(player2.getUniqueId().toString(), (long)value1);
    }
    if (value2 > 0) {
      TokenManagerPlugin.getInstance().removeTokens(player2.getUniqueId().toString(), (long)value2);
      TokenManagerPlugin.getInstance().addTokens(player1.getUniqueId().toString(), (long)value2);
    }
  }

  @Override
  public ItemStack getIcon(Player player) {
    return ItemFactory.replaceInMeta(icon, "%AMOUNT%", Long.toString((long)(player.equals(player1) ? value1 : value2)),
            "%INCREMENT%", Long.toString((long)increment),
            "%PLAYERINCREMENT%", Long.toString((long)(player.equals(player1) ? increment1 : increment2)));
  }

  @Override
  public ItemStack getTheirIcon(Player player) {
    return ItemFactory.replaceInMeta(theirIcon, "%AMOUNT%", Long.toString((long)(player.equals(player1) ? value1 : value2)));
  }

}
