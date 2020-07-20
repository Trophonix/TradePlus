package com.trophonix.tradeplus.gui;

import com.trophonix.tradeplus.util.ItemFactory;
import org.bukkit.configuration.ConfigurationSection;

public enum MenuAction {
  ACCEPT,
  CANCEL,
  THEY_ACCEPT,
  THEY_CANCEL,

  MY_HEAD,
  THEIR_HEAD,

  MY_ITEM_SLOTS,
  THEIR_ITEM_SLOTS,

  EXTRA_ECONOMY,
  EXTRA_EXPERIENCE,
  EXTRA_GRIEF_PREVENTION,
  EXTRA_ENJIN_POINTS,
  EXTRA_PLAYER_POINTS,
  EXTRA_TOKEN_ENCHANT,
  EXTRA_TOKEN_MANAGER,
  EXTRA_VOTING_PLUGIN;

  private ItemFactory factory;
  private boolean enabled = true;

  public void load(ConfigurationSection section) {
    enabled = section.getBoolean("enabled", true);
    factory =
        new ItemFactory(section.getString("material"))
            .amount(section.getInt("amount", 1))
            .customModelData(section.getInt("customModelData", 0))
            .display('&', section.getString("display", "&4ERROR"))
            .lore('&', section.getStringList("lore"))
            .flag("HIDE_ATTRIBUTES");
  }

  public boolean isEnabled() {
    return enabled;
  }
}
