package com.trophonix.tradeplus.events;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

/**
 * Created by Lucas on 5/20/17.
 */
public class TradeAcceptEvent extends Event {

  private static final HandlerList handlers = new HandlerList();

  private final Player sender;
  private final Player receiver;

  public TradeAcceptEvent(Player sender, Player receiver) {
    this.sender = sender;
    this.receiver = receiver;
  }

  public static HandlerList getHandlerList() {
    return handlers;
  }

  public Player getSender() {
    return sender;
  }

  public Player getReceiver() {
    return receiver;
  }

  @Override
  public HandlerList getHandlers() {
    return handlers;
  }

}
