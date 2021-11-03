package com.trophonix.tradeplus.extras;

import com.trophonix.tradeplus.TradePlus;
import com.trophonix.tradeplus.trade.Trade;
import me.angeschossen.lands.api.integration.LandsIntegration;
import me.angeschossen.lands.api.land.Land;
import me.angeschossen.lands.api.player.LandPlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class LandsExtra extends Extra {

  private LandsIntegration landsApi;
  private String selectedLand1, selectedLand2;

  public LandsExtra(Player player1, Player player2, TradePlus pl, Trade trade) {
    super("lands", player1, player2, pl, trade);
    this.landsApi = new LandsIntegration(pl, false);
  }

  private Land getSelectedLand(Player player) {
    LandPlayer landPlayer = landsApi.getLandPlayer(player.getUniqueId());
    if (landPlayer == null) return null;

    Land land;
    if (player1.equals(player)) {
      land = landPlayer.getLand(selectedLand1);
      if (land == null) selectedLand1 = null;
    } else if (player2.equals(player)) {
      land = landPlayer.getLand(selectedLand2);
      if (land == null) selectedLand2 = null;
    } else return null;
    return land;
  }

  @Override
  public double getMax(Player player) {
    Land land = getSelectedLand(player);
    if (land == null) {
      return 0;
    }
    return land.getMaxChunks() - land.getSize();
  }

  @Override
  public void onTradeEnd() {
    Land land1 = getSelectedLand(player1);
    Land land2 = getSelectedLand(player2);
  }

  @Override
  protected ItemStack _getIcon(Player player) {
    return null;
  }

  @Override
  protected ItemStack _getTheirIcon(Player player) {
    return null;
  }
}
