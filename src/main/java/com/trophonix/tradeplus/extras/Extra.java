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
        TradePlus pl1 = pl;
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
                    if (increment1 < 0) increment1 = 0;
                } else if (player.equals(player2)) {
                    increment2 -= increment;
                    if (increment2 < 0) increment1 = 0;
                }
            } else {
                if (player.equals(player1)) {
                    value1 -= increment1;
                    if (value1 < 0) value1 = 0;
                } else if (player.equals(player2)) {
                    value2 -= increment2;
                    if (value2 < 0) value2 = 0;
                }
            }
        } else if (click.isRightClick()) {
            double max = getMax(player);
            if (click.isShiftClick()) {
                if (player.equals(player1)) {
                    increment1 += increment;
                } else if (player.equals(player2)) {
                    increment2 += increment;
                }
            } else {
                if (player.equals(player1)) {
                    value1 += increment1;
                    if (value1 > max) value1 = max;
                } else if (player.equals(player2)) {
                    value2 += increment2;
                    if (value2 > max) value2 = max;
                }
            }
        }
    }

    protected abstract double getMax(Player player);

    public abstract void onTradeEnd();

    public abstract ItemStack getIcon(Player player);

    public abstract ItemStack getTheirIcon(Player player);

}
