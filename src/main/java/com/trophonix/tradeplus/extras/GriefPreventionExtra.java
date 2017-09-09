package com.trophonix.tradeplus.extras;

import com.trophonix.tradeplus.TradePlus;
import com.trophonix.tradeplus.util.ItemFactory;
import me.ryanhamshire.GriefPrevention.GriefPrevention;
import me.ryanhamshire.GriefPrevention.PlayerData;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class GriefPreventionExtra extends Extra {

    public GriefPreventionExtra(Player player1, Player player2, TradePlus pl) {
        super("griefprevention", player1, player2, pl);
    }

    public double getMax(Player player) {
        PlayerData data = GriefPrevention.instance.dataStore.getPlayerData(player.getUniqueId());
        return data.getAccruedClaimBlocks();
    }

    @Override
    public void onTradeEnd() {
        PlayerData data1 = GriefPrevention.instance.dataStore.getPlayerData(player1.getUniqueId());
        PlayerData data2 = GriefPrevention.instance.dataStore.getPlayerData(player2.getUniqueId());
        if (value1 > 0) {
            data1.setAccruedClaimBlocks(data1.getAccruedClaimBlocks() - (int)value1);
            data2.setAccruedClaimBlocks(data2.getAccruedClaimBlocks() + (int)(value1 - ((value1 / 100) * taxPercent)));
        }
        if (value2 > 0) {
            data2.setAccruedClaimBlocks(data2.getAccruedClaimBlocks() - (int)value2);
            data1.setAccruedClaimBlocks(data1.getAccruedClaimBlocks() + (int)(value2 - ((value2 / 100) * taxPercent)));
        }
    }

    @Override
    public ItemStack getIcon(Player player) {
        return ItemFactory.replaceInMeta(icon, "%AMOUNT%", Double.toString(player.equals(player1) ? value1 : value2),
                "%INCREMENT%", Double.toString(increment),
                "%PLAYERINCREMENT%", Double.toString(player.equals(player1) ? increment1 : increment2));
    }

    @Override
    public ItemStack getTheirIcon(Player player) {
        return ItemFactory.replaceInMeta(theirIcon, "%AMOUNT%", Double.toString(player.equals(player1) ? value1 : value2));
    }

}
