package com.trophonix.tradeplus.extras.economy;

import com.trophonix.tradeplus.TradePlus;
import com.trophonix.tradeplus.extras.Extra;
import com.trophonix.tradeplus.util.ItemFactory;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.economy.EconomyResponse;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class EconomyExtra extends Extra {

    private Economy economy;

    public EconomyExtra(Player player1, Player player2, TradePlus pl) {
        super("economy", player1, player2, pl);
        this.economy = pl.getServer().getServicesManager().getRegistration(Economy.class).getProvider();
    }

    public double getMax(Player player) { return economy.getBalance(player); }

    @Override
    public void onTradeEnd() {
        if (value1 > 0) {
            if (economy.withdrawPlayer(player1, value1).type.equals(EconomyResponse.ResponseType.SUCCESS))
                economy.depositPlayer(player2, value1 - ((value1 / 100) * taxPercent));
        }
        if (value2 > 0) {
            if (economy.withdrawPlayer(player2, value2).type.equals(EconomyResponse.ResponseType.SUCCESS))
                economy.depositPlayer(player1, value2 - ((value2 / 100) * taxPercent));
        }
    }

    @Override
    public ItemStack getIcon(Player player) {
        return ItemFactory.replaceInMeta(icon, "%AMOUNT%", Double.toString(player.equals(player1) ? value1 : value2),
                "%CURRENCY%", economy.getBalance(player) == 1 ? economy.currencyNameSingular() : economy.currencyNamePlural(),
                "%INCREMENT%", Double.toString(increment),
                "%PLAYERINCREMENT%", Double.toString(player.equals(player1) ? increment1 : increment2));
    }

    @Override
    public ItemStack getTheirIcon(Player player) {
        return ItemFactory.replaceInMeta(theirIcon, "%AMOUNT%", Double.toString(player.equals(player1) ? value1 : value2),
                "%CURRENCY%", economy.getBalance(player) == 1 ? economy.currencyNameSingular() : economy.currencyNamePlural());
    }

}
