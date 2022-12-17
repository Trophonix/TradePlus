package com.trophonix.tradeplus.trade;

import com.trophonix.tradeplus.TradePlus;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPickupItemEvent;

public class EntityPickupItemEventListener implements Listener {

  private final Trade trade;

  public EntityPickupItemEventListener(Trade trade) {
    this.trade = trade;
  }

  @EventHandler
  public void onPickup(EntityPickupItemEvent event) {
    if (trade.isCancelled()) return;
    if (!(event.getEntity() instanceof Player)) return;
    Player player = (Player) event.getEntity();
    if (player.equals(trade.getPlayer1()) || player.equals(trade.getPlayer2())) {
      event.setCancelled(true);
    }
  }

}
