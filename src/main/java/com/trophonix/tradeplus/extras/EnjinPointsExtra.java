package com.trophonix.tradeplus.extras;

import com.enjin.core.EnjinServices;
import com.enjin.rpc.mappings.mappings.general.RPCData;
import com.enjin.rpc.mappings.services.PointService;
import com.trophonix.tradeplus.TradePlus;
import com.trophonix.tradeplus.util.ItemFactory;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class EnjinPointsExtra extends Extra {

    private TradePlus pl;
    private PointService pointService;

    public EnjinPointsExtra(Player player1, Player player2, TradePlus pl) {
        super("enjinpoints", player1, player2, pl);
        this.pl = pl;
        this.pointService = EnjinServices.getService(PointService.class);
    }

    @Override
    protected double getMax(Player player) {
        try {
            return pointService.get(player.getName()).getResult();
        } catch (Exception ex) {
            pl.getLogger().warning("Failed to get enjinpoints for player: " + player.getName());
            return 0;
        }
    }

    private void transact(Player take, Player give, double points) {
        RPCData<Integer> withdrawResponse = pointService.remove(take.getName(), (int)points);
        if (withdrawResponse == null) {
            pl.getLogger().warning("Failed to withdraw " + points + " points from " + take.getName() + ":");
            pl.getLogger().warning("Couldn't connect to enjin points api");
        } else if (withdrawResponse.getError() != null) {
            pl.getLogger().warning("Failed to withdraw " + points + " points from " + take.getName() + ":");
            pl.getLogger().warning(withdrawResponse.getError().getMessage());
        } else {
            RPCData<Integer> depositResponse = pointService.add(give.getName(), (int)points);
            if (depositResponse == null) {
                pl.getLogger().warning("Failed to give " + points + " points to " + give.getName() + ":");
                pl.getLogger().warning("Couldn't connect to enjin points api");
            } else if (depositResponse.getError() != null) {
                pl.getLogger().warning("Failed to give " + points + " points to " + give.getName() + ":");
                pl.getLogger().warning(depositResponse.getError().getMessage());
            }
        }
    }

    @Override
    public void onTradeEnd() {
        if (value1 > 0) {
            transact(player1, player2, value1);
        }
        if (value2 > 0) {
            transact(player2, player1, value2);
        }
    }

    @Override
    public ItemStack getIcon(Player player) {
        return ItemFactory.replaceInMeta(icon, "%AMOUNT%", Double.toString(player.equals(player1) ? value1 : value2),
                "%CURRENCY%", "Enjin points",
                "%INCREMENT%", Double.toString(increment),
                "%PLAYERINCREMENT%", Double.toString(player.equals(player1) ? increment1 : increment2));
    }

    @Override
    public ItemStack getTheirIcon(Player player) {
        return ItemFactory.replaceInMeta(theirIcon, "%AMOUNT%", Double.toString(player.equals(player1) ? value1 : value2),
                "%CURRENCY%", "Enjin points");
    }

}
