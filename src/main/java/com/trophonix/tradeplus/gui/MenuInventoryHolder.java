package com.trophonix.tradeplus.gui;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

public class MenuInventoryHolder implements InventoryHolder {

    @Getter @Setter private Inventory current;

    @Override
    public Inventory getInventory() {
        return current;
    }

}
