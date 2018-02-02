package com.trophonix.tradeplus.extras;

import com.trophonix.tradeplus.TradePlus;
import com.trophonix.tradeplus.util.ItemFactory;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

public abstract class Extra {

    final Player player1;
    final Player player2;
    final double increment;
    double max1;
    double max2;
    long lastUpdatedMax = System.currentTimeMillis();
    double increment1;
    double increment2;
    public double value1 = 0, value2 = 0;
    public final ItemStack icon;
    final ItemStack theirIcon;
    final double taxPercent;

    Extra(String name, Player player1, Player player2, TradePlus pl) {
        ConfigurationSection section = pl.getConfig().getConfigurationSection("extras." + name);
        this.player1 = player1;
        this.player2 = player2;
        this.increment = section.getDouble("increment");
        this.increment1 = increment;
        this.increment2 = increment;
        ItemFactory factory = new ItemFactory(section.getString("material", "PAPER"), Material.PAPER)
                .display('&', section.getString("display", "&4ERROR"));
        if (section.contains("lore"))
            factory.lore('&', section.getStringList("lore"));
        this.icon = factory.build();
        this.theirIcon = new ItemFactory(section.getString("material", "PAPER"), Material.PAPER)
                .display('&', section.getString("theirdisplay", "&4ERROR")).build();
        this.taxPercent = section.getDouble("taxpercent", 0);
    }

    @SuppressWarnings("Duplicates")
    public void onClick(Player player, ClickType click) {
        if (click.isLeftClick()) {
            if (click.isShiftClick()) {
                if (player.equals(player1)) {
                    increment1 -= increment;
                } else if (player.equals(player2)) {
                    increment2 -= increment;
                }
            } else {
                if (player.equals(player1)) {
                    value1 -= increment1;
                } else if (player.equals(player2)) {
                    value2 -= increment2;
                }
            }
        } else if (click.isRightClick()) {
            if (click.isShiftClick()) {
                if (player.equals(player1)) {
                    increment1 += increment;
                } else if (player.equals(player2)) {
                    increment2 += increment;
                }
            } else {
                if (player.equals(player1)) {
                    value1 += increment1;
                } else if (player.equals(player2)) {
                    value2 += increment2;
                }
            }
        }

        if (increment1 < 0) increment1 = 0;
        if (increment2 < 0) increment2 = 0;

        if (value1 < 0) value1 = 0;
        if (value2 < 0) value2 = 0;

        long now = System.currentTimeMillis();
        if (now > lastUpdatedMax + 5000) {
            max1 = getMax(player1);
            max2 = getMax(player2);
            lastUpdatedMax = now;
            System.out.println("max1=" + max1 + ", max2=" + max2);
        }
        if (value1 > max1) value1 = max1;
        if (value2 > max2) value2 = max2;
    }

    protected abstract double getMax(Player player);

    public abstract void onTradeEnd();

    public abstract ItemStack getIcon(Player player);

    public abstract ItemStack getTheirIcon(Player player);

}
