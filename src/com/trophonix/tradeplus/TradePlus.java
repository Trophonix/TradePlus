package com.trophonix.tradeplus;

import com.trophonix.tradeplus.commands.TradeCommand;
import com.trophonix.tradeplus.commands.TradePlusCommand;
import com.trophonix.tradeplus.trade.InteractListener;
import com.trophonix.tradeplus.util.InvUtils;
import com.trophonix.tradeplus.util.MsgUtils;
import com.trophonix.tradeplus.util.Sounds;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.Scanner;

public class TradePlus extends JavaPlugin {
    private File configFile;
    private FileConfiguration config;

    private File langFile;
    private FileConfiguration lang;

    @Override
    public void onEnable() {
        configFile = new File("plugins" + File.separator + this.getName() + File.separator + "config.yml");
        config = YamlConfiguration.loadConfiguration(configFile);
        langFile = new File(getDataFolder(), "lang.yml");
        lang = YamlConfiguration.loadConfiguration(langFile);
        MsgUtils.initMsgUtils();
        if (!getDataFolder().exists()) {
            getDataFolder().mkdirs();
            try { configFile.createNewFile(); } catch (IOException ex) { ex.printStackTrace(); }
            config.set("permissionnode", "tradeplus.trade");
            config.set("permissionrequired", false);
            config.set("blocked-items", Arrays.asList("bedrock", "97:3"));
            config.set("action", "crouchrightclick");
            config.set("ranges.sameworld", 10.0);
            config.set("ranges.crossworld", 0.0);
            config.set("ranges.allowcrossworld", false);
            config.set("antiscam.countdown", 10);
            config.set("antiscam.cancelonchange", true);
            config.set("antiscam.preventchangeonaccept", true);
            config.set("gui.title", "Your Items <|     |> Their Items");
            config.set("gui.head", "&7You are trading with: &3&l%PLAYER%");
            config.set("gui.showhead", !config.contains("showhead") || config.getBoolean("showhead"));
            config.set("gui.accept", "&a&lClick to accept the trade");
            config.set("gui.cancel", "&c&lClick to cancel the trade");
            config.set("gui.theyaccept", " ");
            config.set("gui.theycancel", " ");
            config.set("gui.acceptid", "160:14");
            config.set("gui.cancelid", "160:13");
            config.set("gui.separatorid", "160:15");
            config.set("extras.economy.enabled", true);
            config.set("extras.economy.material", "266");
            config.set("extras.economy.display", "&7Your current money offer is &e%AMOUNT%");
            config.set("extras.economy.theirdisplay", "&7Their current money offer is &e%AMOUNT%");
            config.set("extras.economy.lore", Arrays.asList("&aLeft Click to &clower &ayour offer by %PLAYERINCREMENT%",
                    "&aRight Click to &braise &ayour offer by %PLAYERINCREMENT%",
                    "&aShift + Left Click to &clower &ayour increment by %INCREMENT%",
                    "&aShift + Right Click to &braise &ayour increment by %INCREMENT%"));
            config.set("extras.economy.increment", 10.0);
            config.set("extras.economy.taxpercent", 0);
            config.set("extras.experience.enabled", true);
            config.set("extras.experience.material", "exp_bottle");
            config.set("extras.experience.display", "&7Your current XP offer is &e%AMOUNT%");
            config.set("extras.experience.theirdisplay", "&7Their current XP offer is &e%AMOUNT%");
            config.set("extras.experience.lore", Arrays.asList("&aLeft Click to &clower &ayour offer by %PLAYERINCREMENT%",
                    "&aRight Click to &braise &ayour offer by %PLAYERINCREMENT%",
                    "&aShift + Left Click to &clower &ayour increment by %INCREMENT%",
                    "&aShift + Right Click to &braise &ayour increment by %INCREMENT%"));
            config.set("extras.experience.increment", 5);
            config.set("extras.experience.taxpercent", 0);
            config.set("soundeffects.enabled", true);
            config.set("soundeffects.onchange", true);
            config.set("soundeffects.onaccept", true);
            config.set("soundeffects.oncomplete", true);
            config.set("soundeffects.oncountdown", true);
            config.set("configversion", Double.parseDouble(getDescription().getVersion()));
            try { langFile.createNewFile(); } catch (IOException ex) { ex.printStackTrace(); }
            lang.set("sentrequest", "&6&l(!) &r&6You sent a trade request to &e%PLAYER%");
            lang.set("receivedrequest", "&6&l(!) &r&6You received a trade request from &e%PLAYER%%NEWLINE%&6&l(!) &r&6Type &e/trade %PLAYER% &6to begin trading");
            lang.set("receivedrequesthover", "&6&lClick here to trade with &e&l%PLAYER%");
            lang.set("withinrange", "&4&l(!) &r&4You must be within %AMOUNT% blocks of a player to trade with them");
            lang.set("withinrangecrossworld", "&4&l(!) &r&4You must be within %AMOUNT% blocks of a player%NEWLINE%&4&l(!) &r&4in a different world to trade with them!");
            lang.set("nocrossworld", "&4&l(!) &r&4You must be in the same world as a player to trade with them!");
            lang.set("senderaccept", "&6&l(!) &r&e%PLAYER% &6accepted your trade request");
            lang.set("receiveraccept", "&6&l(!) &r&6You accepted &e%PLAYER%'s &6trade request");
            lang.set("cancelled", "&4&l(!) &r&4The trade was cancelled");
            lang.set("expired", "&4&l(!) &r&4Your last trade request expired");
            lang.set("waitforexpire", "&4&l(!) &r&4You still have an active trade request%NEWLINE%&4&l(!) &r&4It will expire shortly");
            lang.set("playernotfound", "&4&l(!) &r&4Could not find specified player");
            lang.set("tradewithself", "&4&l(!) &r&4You cannot trade with yourself");
            lang.set("invalidusage", "&4&l(!) &r&4Invalid arguments. Usage: &c/trade <player name>");
            lang.set("noperms", "&4&l(!) &r&4You do not have permission to trade");
            lang.set("nopermsreceiver", "&4&l(!) &r&4That player does not have permission to trade");
            lang.set("tradecomplete", "&6&l(!) &r&6The trade was successful!");
            lang.set("forcedtrade", "&6&l(!) &r&6You've been forced into a trade with &e%PLAYER%");
            saveLang();
        } else {
            double configVersion = config.contains("configversion") && config.isDouble("configversion") ? config.getDouble("configversion") : 0;

            if (configVersion < 1.11) {
                config.set("gui.title", config.contains("guititle") ? config.get("guititle") : "Your Items <|     |> Their Items");
                config.set("guititle", null);
                config.set("gui.showhead", !config.contains("showhead") || config.getBoolean("showhead"));
                config.set("showhead", null);
                config.set("gui.accept", "&a&lClick to accept the trade");
                config.set("gui.cancel", "&c&lClick to cancel the trade");
                config.set("gui.theyaccept", " ");
                config.set("gui.theycancel", " ");
            }

            if (configVersion < 1.12) {
                config.set("messages.tradecomplete", "&6&l(!) &r&6The trade was successful!");
                config.set("gui.headname", "&7You are trading with &3&l%PLAYERNAME%");
            }

            if (configVersion < 1.2) {
                config.set("economy.enabled", true);
                config.set("economy.clear", "&4&lClick to clear your money offer");
                config.set("economy.offer", "&7Your current money offer is &3&l%MONEYAMOUNT% %CURRENCYNAME%");
                config.set("economy.theiroffer", "&7Their current money offer is &3&l%MONEYAMOUNT% %CURRENCYNAME%");
                config.set("economy.higher", "&a&lClick to raise your money offer by %MONEYAMOUNT% %CURRENCYNAME%");
                config.set("economy.lower", "&c&lClick to lower your money offer by %MONEYAMOUNT% %CURRENCYNAME%");
                config.set("economy.higheramount", 5.0);
                config.set("economy.loweramount", 5.0);
                config.set("soundeffects.enabled", true);
                config.set("soundeffects.onchange", true);
                config.set("soundeffects.onaccept", true);
                config.set("soundeffects.oncomplete", true);
                config.set("soundeffects.oncountdown", true);
            }

            if (configVersion < 1.22) {
                config.set("gui.separatorId", "160:15");
            }

            if (configVersion < 1.23) {
                config.set("gui.separatorid", config.getString("gui.separatorId"));
                config.set("gui.separatorId", null);
                config.set("gui.acceptid", "160:13");
                config.set("gui.cancelid", "160:14");
            }

            if (configVersion < 1.24) {
                config.set("messages.withinrange", config.getString("messages.within10"));
                config.set("messages.within10", null);
                config.set("ranges.sameworld", 10.0);
                config.set("ranges.crossworld", 0.0);
                config.set("ranges.allowcrossworld", false);
                config.set("messages.withinrangecrossworld", "&4&l(!) &r&4You must be within %AMOUNT% blocks of a player%NEWLINE%&4&l(!) &r&4in a different world to trade with them!");
                config.set("messages.nocrossworld", "&4&l(!) &r&4You must be in the same world as a player to trade with them!");
            }

            if (configVersion < 1.3) {
                config.set("blocked-items", Arrays.asList("bedrock", "97:3"));
            }

            if (configVersion < 1.31) {
                config.set("action", "crouchrightclick");
                config.set("economy.increment", config.getDouble("economy.higheramount", 10.0));
                config.set("economy.higheramount", null);
                config.set("economy.loweramount", null);
                config.set("gui.acceptid", "160:14");
                config.set("gui.cancelid", "160:13");
            }

            if (configVersion < 1.34) {
                config.set("hooks.economy.enabled", config.getBoolean("economy.enabled", true));
                config.set("hooks.economy.material", "266");
                config.set("hooks.economy.youroffer", config.getString("economy.offer", "&7Your current money offer is &e%MONEYAMOUNT%"));
                config.set("hooks.economy.theiroffer", config.getString("economy.theiroffer", "&7Their current money offer is &e%MONEYAMOUNT%"));
                config.set("hooks.economy.increment", config.getDouble("economy.increment", 10.0));
                config.set("economy", null);
            }

            if (configVersion < 1.5) {
                config.set("hooks.xp.enabled", true);
                config.set("hooks.xp.material", "exp_bottle");
                config.set("hooks.xp.youroffer", "&7Your current XP offer is &e%XPAMOUNT%");
                config.set("hooks.xp.theiroffer", "&7Their current XP offer is &e%XPAMOUNT%");
                config.set("hooks.xp.increment", 5);
            }

            if (configVersion < 2.0) {
                if (config.contains("messages")) {
                    for (String key : config.getConfigurationSection("messages").getKeys(false))
                        lang.set(key, config.getString("messages." + key).replace("%PLAYERNAME%", "%PLAYER%")
                                .replace("%MONEYAMOUNT%", "%AMOUNT%").replace("%XPAMOUNT%", "%AMOUNT%"));
                    config.set("messages", null);
                }
                if (config.contains("hooks")) {
                    for (String key : config.getConfigurationSection("hooks.economy").getKeys(false))
                        config.set("extras.economy." + key.replace("youroffer", "display").replace("theiroffer", "theirdisplay"), config.get("hooks.economy." + key));
                    for (String key : config.getConfigurationSection("hooks.xp").getKeys(false))
                        config.set("extras.experience." + key.replace("youroffer", "display").replace("theiroffer", "theirdisplay"), config.get("hooks.experience." + key));
                    config.set("hooks", null);
                    config.set("extras.economy.lore", Arrays.asList("&aLeft Click to &clower &ayour offer by %PLAYERINCREMENT%",
                            "&aRight Click to &braise &ayour offer by %PLAYERINCREMENT%",
                            "&aShift + Left Click to &clower &ayour increment by %INCREMENT%",
                            "&aShift + Right Click to &braise &ayour increment by %INCREMENT%"));
                    config.set("extras.experience.lore", Arrays.asList("&aLeft Click to &clower &ayour offer by %PLAYERINCREMENT%",
                            "&aRight Click to &braise &ayour offer by %PLAYERINCREMENT%",
                            "&aShift + Left Click to &clower &ayour increment by %INCREMENT%",
                            "&aShift + Right Click to &braise &ayour increment by %INCREMENT%"));
                    config.set("extras.economy.taxpercent", 0);
                    config.set("extras.experience.taxpercent", 0);
                }
                config.set("gui.head", config.getString("gui.headname", "&7You are trading with: &3&l%PLAYER%").replace("%PLAYERNAME%", "%PLAYER%"));
                config.set("gui.headname", null);
                saveLang();
            }
        }
        getConfig().set("configversion", Double.parseDouble(getDescription().getVersion()));
        saveConfig();
        InvUtils.reloadItems(this);
        Sounds.loadSounds();
        if (Sounds.version > 17)
            getServer().getPluginManager().registerEvents(new InteractListener(this), this);
        getCommand("trade").setExecutor(new TradeCommand(this));
        getCommand("tradeplus").setExecutor(new TradePlusCommand(this));
    }

    private String center(String string) {
        String result = string;
        while (result.length() < 48) {
            result = " " + result + " ";
        }
        return result;
    }

    @Override public FileConfiguration getConfig() { return this.config; }

    @Override public File getFile() { return configFile; }

    @Override public void saveConfig() {
        try {
            config.save(configFile);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void reload() {
        config = YamlConfiguration.loadConfiguration(configFile);
        lang = YamlConfiguration.loadConfiguration(langFile);
        InvUtils.reloadItems(this);
    }

    public FileConfiguration getLang() { return lang; }

    private void saveLang() {
        try {
            lang.save(langFile);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static final String uid = "%%__USER__%%";

}
