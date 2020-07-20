package com.trophonix.tradeplus.gui;

import lombok.Getter;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.Map;

@Getter
public class TradeMenu {

  private Inventory inventory;
  private Player player;
  private TradeMenu partner;

  private Map<Integer, MenuButton> buttons;
}
