package com.trophonix.tradeplus.hooks;

import com.earth2me.essentials.Essentials;
import com.earth2me.essentials.User;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class EssentialsHook {

    public static boolean isVanished(Player player) {
        Essentials essentials = (Essentials) Bukkit.getPluginManager().getPlugin("Essentials");
        User user = essentials.getUser(player);
        return user.isVanished();
    }

}
