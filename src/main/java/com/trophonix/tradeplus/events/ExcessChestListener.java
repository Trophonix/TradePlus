package com.trophonix.tradeplus.events;

import com.trophonix.tradeplus.TradePlus;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class ExcessChestListener implements Listener {

  private TradePlus pl;

  public ExcessChestListener(TradePlus pl) {
    this.pl = pl;
    pl.getServer().getPluginManager().registerEvents(this, pl);
  }

  @EventHandler
  public void onClose(InventoryCloseEvent event) {
    Inventory closed = event.getInventory();

    if (pl.getExcessChests().contains(closed)) {
      for (ItemStack i : event.getInventory()) {
        if (i == null || i.getType() == Material.AIR) continue;
        event.getPlayer().getWorld().dropItemNaturally(event.getPlayer().getLocation(), i);
      }
      pl.getExcessChests().remove(closed);
    }
  }
}
