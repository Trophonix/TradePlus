package com.trophonix.tradeplus.events;

import com.trophonix.tradeplus.logging.TradeLog;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.ItemStack;

import java.util.Set;

@AllArgsConstructor @Getter
public class TradeCompleteEvent extends Event {

  private static final HandlerList handlers = new HandlerList();

  private final TradeLog trade;
  private final Player playerOne;
  private final Player playerTwo;

  public static HandlerList getHandlerList() {
    return handlers;
  }

  @Override
  public HandlerList getHandlers() {
    return handlers;
  }
}
