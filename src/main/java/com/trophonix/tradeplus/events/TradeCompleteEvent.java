package com.trophonix.tradeplus.events;

import com.trophonix.tradeplus.logging.TradeLog;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.ItemStack;

import java.util.Set;

class TradeCompleteEvent extends Event {

  private static final HandlerList handlers = new HandlerList();

  private final TradeLog trade;
  private final Player playerOne;
  private final Player playerTwo;
  private final Set<ItemStack> playerOneTrades;
  private final Set<ItemStack> playerTwoTrades;

  public TradeCompleteEvent(TradeLog trade, Player playerOne, Player playerTwo, Set<ItemStack> playerOneTrades, Set<ItemStack> playerTwoTrades) {
    this.trade = trade;
    this.playerOne = playerOne;
    this.playerTwo = playerTwo;
    this.playerOneTrades = playerOneTrades;
    this.playerTwoTrades = playerTwoTrades;
  }

  public static HandlerList getHandlerList() {
    return handlers;
  }

  public TradeLog getTrade() { return trade; }

  public Player getPlayerOne() { return playerOne; }

  public Player getPlayerTwo() { return playerTwo; }

  public Set<ItemStack> getPlayerOneTrades() { return playerOneTrades; }

  public Set<ItemStack> getPlayerTwoTrades() { return playerTwoTrades; }

  @Override
  public HandlerList getHandlers() {
    return handlers;
  }

}
