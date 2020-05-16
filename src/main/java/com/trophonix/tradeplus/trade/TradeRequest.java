package com.trophonix.tradeplus.trade;

import org.bukkit.entity.Player;

/** Created by lucas on 5/26/16. */
public class TradeRequest {

  public final Player sender;
  public final Player receiver;

  public TradeRequest(Player sender, Player receiver) {
    this.sender = sender;
    this.receiver = receiver;
  }

  public boolean contains(Player player) {
    return sender.equals(player) || receiver.equals(player);
  }
}
