package com.trophonix.tradeplus.trade;

import com.trophonix.tradeplus.TradePlus;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;

public class InteractListener implements Listener {

    private TradePlus pl;

    public InteractListener(TradePlus pl) { this.pl = pl; }

    @EventHandler
    public void onInteract(PlayerInteractAtEntityEvent event) {
        if (event.getRightClicked() instanceof Player) {
            Player player = event.getPlayer();
            Player interacted = (Player) event.getRightClicked();
            String action = pl.getConfig().getString("action", "").toLowerCase();
            if ((action.contains("sneak") || action.contains("crouch") || action.contains("shift")) && !player.isSneaking())
                return;
            if (action.contains("right")) {
                event.setCancelled(true);
                player.performCommand("trade " + interacted.getName());
            }
        }
    }

    @EventHandler
    public void onDamage(EntityDamageByEntityEvent event) {
        if (event.getDamager() instanceof Player && event.getEntity() instanceof Player) {
            Player player = (Player) event.getDamager();
            Player interacted = (Player) event.getEntity();
            String action = pl.getConfig().getString("action", "").toLowerCase();
            if ((action.contains("sneak") || action.contains("crouch") || action.contains("shift")) && !player.isSneaking())
                return;
            if (action.contains("left")) {
                event.setCancelled(true);
                player.performCommand("trade " + interacted.getName());
            }
        }
    }

}
