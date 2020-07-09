package com.trophonix.tradeplus.config;

import com.trophonix.tradeplus.TradePlus;
import com.trophonix.tradeplus.util.Sounds;
import lombok.Getter;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Getter
public class TradePlusConfig {

  private TradePlus plugin;

  private File configFile;
  private FileConfiguration config;
  private File langFile;
  private FileConfiguration lang;

  private List<String> aliases;
  private boolean tradeCompatMode;

  private boolean tradeLogs;
  private boolean allowSameIpTrade;

  private boolean permissionsRequired;
  private String sendPermission, acceptPermission;

  private int requestCooldownSeconds;
  private boolean allowTradeInCreative;

  private List<String> itemBlacklist;
  private boolean denyNamedItems;
  private List<String> loreBlacklist;

  private String action;

  private double sameWorldRange, crossWorldRange;
  private boolean allowCrossWorld;
  private List<String> blockedWorlds;

  private int antiscamCountdown;
  private boolean antiscamCancelOnChange, preventChangeOnAccept, discrepancyDetection;

  private boolean spectateEnabled, spectateBroadcast;

  private String guiTitle;
  private String spectatorTitle;

  private String guiHeadDisplayName;
  private boolean showHead;

  private String guiAcceptDisplay, guiCancelDisplay;
  private boolean showAccept;
  private String guiTheirAcceptDisplay, guiTheirCancelDisplay;

  private String guiAcceptType, guiCancelType, guiSeparatorType;

  private boolean guiForceEnabled;
  private String guiForceType, guiForceName;
  private List<String> guiForceLore;

  private int guiAcceptModelData, guiCancelModelData, guiSeparatorModelData;

  private String extrasTypePrefix, extrasTypeEmpty, extrasTypeValid, extrasTypeInvalid, extrasTypeMaximum;

  private boolean factionsAllowTradeInEnemyTerritory, worldguardTradingFlag;

  private boolean soundEffectsEnabled, soundOnChange, soundOnAccept, soundOnComplete, soundOnCountdown;

  private boolean excessChest;
  private String excessTitle;
  private String typeEmpty, typeValid, typeInvalid, typeMaximum;

  private boolean debugMode;

  private ConfigMessage requestSent;
  private ConfigMessage requestReceived;

  private ConfigMessage errorsCreative, errorsCreativeThem, errorsSameIp;
  private ConfigMessage errorsSameWorldRange, errorsCrossWorldRange, errorsNoCrossWorld;

  private ConfigMessage acceptSender, acceptReceiver;
  private ConfigMessage cancelled, expired;

  private ConfigMessage errorsWaitForExpire, errorsPlayerNotFound, errorsSelfTrade, errorsInvalidUsage;
  private ConfigMessage errorsNoPermsAccept, errorsNoPermsSend, errorsNoPermsReceive, errorsNoPermsAdmin;

  private ConfigMessage tradeComplete, forcedTrade;
  private ConfigMessage theyDenied, youDenied;

  private ConfigMessage spectateMessage;
  private ConfigMessage discrepancyDetected;

  private ConfigMessage adminConfigReloaded, adminInvalidPlayers, adminForcedTrade, adminPlayersOnly, adminNoTrade;

  private ConfigMessage factionsEnemyTerritory, worldguardTradingNotAllowed;

  public TradePlusConfig(TradePlus plugin) {
    this.plugin = plugin;
  }

  public void save() {
    try {
      config.save(configFile);
      lang.save(langFile);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void reload() {
    load();

    aliases = config.getStringList("aliases");
    tradeCompatMode = config.getBoolean("trade-command-compatible-mode", false);

    excessChest = config.getBoolean("excess-chest.enabled", true);
    excessTitle =
            ChatColor.translateAlternateColorCodes(
                    '&', config.getString("excess-chest.title", "&7Your inventory is full!"));

    tradeLogs = config.getBoolean("trade-logs", false);
    allowSameIpTrade = config.getBoolean("allow-same-ip-trade", true);

    permissionsRequired = config.getBoolean("permissions.required", config.getBoolean("permissionrequired", false));
    sendPermission = config.getString("permissions.send", config.getString("permissionnode", "tradeplus.send"));
    acceptPermission = config.getString("permissions.accept", "tradeplus.accept");

    requestCooldownSeconds = config.getInt("requestcooldownseconds", 20);
    allowTradeInCreative = config.getBoolean("allow-trade-in-creative", false);

    itemBlacklist = config.getStringList("blocked.blacklist");
    denyNamedItems = config.getBoolean("blocked.named-items", false);
    loreBlacklist = config.getStringList("blocked.lore");

    action = config.getString("action", "crouchrightclick");

    sameWorldRange = config.getDouble("ranges.sameworld", 10.0);
    crossWorldRange = config.getDouble("ranges.crossworld", 0.0);
    allowCrossWorld = config.getBoolean("ranges.allowcrossworld", false);
    blockedWorlds = config.getStringList("ranges.blocked-worlds");

    antiscamCountdown = config.getInt("antiscam.countdown", 10);
    antiscamCancelOnChange = config.getBoolean("antiscam.cancelonchange", true);
    preventChangeOnAccept = config.getBoolean("antiscam.preventchangeonaccept", true);
    discrepancyDetection = config.getBoolean("antiscam.discrepancy-detection", true);

    spectateEnabled = config.getBoolean("spectate.enabled", true);
    spectateBroadcast = config.getBoolean("spectate.broadcast", true);

    guiTitle = ChatColor.translateAlternateColorCodes('&', config.getString("gui.title", "Your Items <|     |> Their Items"));
    spectatorTitle = ChatColor.translateAlternateColorCodes('&', config.getString("gui.spectator-title", "Player 1 <|          |> Player 2"));

    guiHeadDisplayName = ChatColor.translateAlternateColorCodes('&', config.getString("gui.head", "&7You are trading with: &3&l%PLAYER%"));
    showHead = config.getBoolean("gui.showhead", !config.contains("showhead") || config.getBoolean("showhead"));

    guiAcceptDisplay = ChatColor.translateAlternateColorCodes('&', config.getString("gui.accept.display", "&a&lClick to accept the trade"));
    guiCancelDisplay = ChatColor.translateAlternateColorCodes('&', config.getString("gui.cancel.display", "&c&lClick to cancel the trade"));

    showAccept = config.getBoolean("gui.showaccept", true);

    guiTheirAcceptDisplay = ChatColor.translateAlternateColorCodes('&', config.getString("gui.accept.theirdisplay", " "));
    guiTheirCancelDisplay = ChatColor.translateAlternateColorCodes('&', config.getString("gui.cancel.theirdisplay", " "));

    guiAcceptType = config.getString("gui.accept.type", "emerald");
    guiCancelType = config.getString("gui.cancel.type", "barrier");
    guiSeparatorType = config.getString("gui.separator.type", "black_stained_glass_pane");

    guiForceEnabled = config.getBoolean("gui.force.enabled", true);
    guiForceType = config.getString("gui.force.type");
    guiForceName = ChatColor.translateAlternateColorCodes('&', config.getString("gui.force.name", "&4&lForce Trade"));
    guiForceLore = config.getStringList("gui.force.lore");

    guiAcceptModelData = config.getInt("gui.accept.customModelData", 0);
    guiCancelModelData = config.getInt("gui.cancel.customModelData", 0);
    guiSeparatorModelData = config.getInt("gui.separator.customModelData", 0);

    extrasTypePrefix = config.getString("extras.type.prefix", "&6&l!!&6> ");
    extrasTypeEmpty = config.getString("extras.type.empty", "&eHow much %EXTRA% to offer?");
    extrasTypeValid = config.getString("extras.type.valid", "&aClick output slot to submit offer.");
    extrasTypeInvalid = config.getString("extras.type.invalid", "&cInvalid amount entered!");
    extrasTypeMaximum = config.getString("extras.type.maximum", "&cYou have %BALANCE% %EXTRA%");

    factionsAllowTradeInEnemyTerritory = config.getBoolean("hooks.factions.allow-trades-in-enemy-territory", false);
    worldguardTradingFlag = config.getBoolean("hooks.worldguard.trading-flag", true);

    soundEffectsEnabled = config.getBoolean("soundeffects.enabled", true);
    soundOnChange = config.getBoolean("soundeffects.onchange", true);
    soundOnAccept = config.getBoolean("soundeffects.onaccept", true);
    soundOnComplete = config.getBoolean("soundeffects.oncomplete", true);
    soundOnCountdown = config.getBoolean("soundeffects.oncountdown", true);

    requestSent = new ConfigMessage(lang, "request.sent", "&6&l(!) &r&6You sent a trade request to &e%PLAYER%");
    requestReceived = new ConfigMessage(lang, "request.received", "&6&l(!) &r&6You received a trade request from &e%PLAYER%%NEWLINE%&6&l(!) &r&6Type &e/trade %PLAYER% &6to begin trading");

    errorsCreative = new ConfigMessage(lang, "errors.creative", "&4&l(!) &r&4You can't trade in creative mode!");
    errorsCreativeThem = new ConfigMessage(lang, "errors.creative-them", "&4&l(!) &r&4That player is in creative mode!");
    errorsSameIp = new ConfigMessage(lang, "errors.same-ip", "&4&l(!) &4Players aren't allowed to trade on same IP!");

    errorsSameWorldRange = new ConfigMessage(lang, "errors.within-range.same-world", "&4&l(!) &r&4You must be within %AMOUNT% blocks of a player to trade with them");
    errorsCrossWorldRange = new ConfigMessage(lang, "errors.within-range.cross-world", "&4&l(!) &r&4You must be within %AMOUNT% blocks of a player%NEWLINE%&4&l(!) &r&4in a different world to trade with them!");
    errorsNoCrossWorld = new ConfigMessage(lang, "errors.no-cross-world", "&4&l(!) &r&4You must be in the same world as a player to trade with them!");

    acceptSender = new ConfigMessage(lang, "accept.sender", "&6&l(!) &r&e%PLAYER% &6accepted your trade request");
    acceptReceiver = new ConfigMessage(lang, "accept.receiver", "&6&l(!) &r&6You accepted &e%PLAYER%'s &6trade request");
    cancelled = new ConfigMessage(lang, "cancelled", "&4&l(!) &r&4The trade was cancelled");
    expired = new ConfigMessage(lang, "expired", "&4&l(!) &r&4Your last trade request expired");

    errorsWaitForExpire = new ConfigMessage(lang, "errors.wait-for-expire", "&4&l(!) &r&4You still have an active trade request%NEWLINE%&4&l(!) &r&4It will expire shortly");
    errorsPlayerNotFound = new ConfigMessage(lang, "errors.player-not-found", "&4&l(!) &r&4Could not find specified player");
    errorsSelfTrade = new ConfigMessage(lang, "errors.self-trade", "&4&l(!) &r&4You cannot trade with yourself");
    errorsInvalidUsage = new ConfigMessage(lang, "errors.invalid-usage", "&4&l(!) &r&4Invalid arguments. Usage: %NEWLINE%"
            + "    &c- /trade <player name>%NEWLINE%"
            + "    &c- /trade deny");
    errorsNoPermsAccept = new ConfigMessage(lang, "errors.no-perms.accept", "&4&l(!) &r&4You do not have permission to trade");
    errorsNoPermsSend = new ConfigMessage(lang, "errors.no-perms.send", "&4&l(!) &r&4You do not have permission to send a trade");
    errorsNoPermsReceive = new ConfigMessage(lang, "errors.no-perms.receive", "&4&l(!) &r&4That player does not have permission to accept a trade");
    errorsNoPermsAdmin = new ConfigMessage(lang, "errors.no-perms.admin", "&4&l(!) &r&4You do not have permission to use this command");

    tradeComplete = new ConfigMessage(lang, "trade-complete", "&6&l(!) &r&6The trade was successful!");
    forcedTrade = new ConfigMessage(lang, "forced-trade", "&6&l(!) &r&6You've been forced into a trade with &e%PLAYER%");

    theyDenied = new ConfigMessage(lang, "denied.them", "&4&l(!) &r&4Your trade request to &c%PLAYER% &4was denied");
    youDenied = new ConfigMessage(lang, "denied.you", "&4&l(!) &r&4Any recent incoming trade requests have been denied.");

    spectateMessage = new ConfigMessage(lang, "spectate", "&6&l(!) &e%PLAYER1% &6and &e%PLAYER2% &6have started a trade %NEWLINE%&6&l(!) &6Type &e/tradeplus spectate %PLAYER1% %PLAYER2% &6to spectate");
    discrepancyDetected = new ConfigMessage(lang, "antiscam.discrepancy", "&4&l(!) &r&4A discrepancy was detected in the traded items.%NEWLINE%&4&l(!) &4The trade has been cancelled.");

    adminConfigReloaded = new ConfigMessage(lang, "admin.configs-reloaded", "&6&l(!) &6Configs reloaded!");
    adminInvalidPlayers = new ConfigMessage(lang, "admin.invalid-players", "&4&l(!) &4Invalid players!");
    adminForcedTrade = new ConfigMessage(lang, "admin.forced-trade", "&6&l(!) &6You forced a trade between &e%PLAYER1% &6and &e%PLAYER2%");
    adminPlayersOnly = new ConfigMessage(lang, "admin.players-only", "&4&l(!) &4This command is for players only.");
    adminNoTrade = new ConfigMessage(lang, "admin.no-trade", "&4&l(!) &4This command is for players only.");

    factionsEnemyTerritory = new ConfigMessage(lang, "hooks.factions.enemy-territory", "&4&l(!) &4You can't trade in enemy territory!");
    worldguardTradingNotAllowed = new ConfigMessage(lang, "hooks.worldguard.trading-not-allowed", "&4&l(!) &4You can't trade in this area.");

    debugMode = config.getBoolean("debug-mode", false);
  }

  public void load() {
    Sounds.loadSounds();

    configFile = new File(plugin.getDataFolder(), "config.yml");
    loadConfig();

    langFile = new File(plugin.getDataFolder(), "lang.yml");
    loadLang();

    save();
  }

  public void loadConfig() {
    if (configFile.exists()) {
      config = YamlConfiguration.loadConfiguration(configFile);
    } else {
      plugin.getDataFolder().mkdirs();
      try {
        configFile.createNewFile();
      } catch (IOException ex) {
        ex.printStackTrace();
      }

      config = YamlConfiguration.loadConfiguration(configFile);

      config.set("aliases", Collections.singletonList("trade+"));
      config.set("trade-command-compatible-mode", false);

      config.set("excess-chest.enabled", true);
      config.set("excess-chest.title", "&7Your inventory is full!");

      config.set("trade-logs", false);
      config.set("allow-same-ip-trade", true);

      config.set("permissions.required", config.getBoolean("permissionrequired", false));
      config.set("permissions.send", config.getString("permissionnode", "tradeplus.send"));
      config.set("permissions.accept", "tradeplus.accept");

      config.set("requestcooldownseconds", 20);
      config.set("allow-trade-in-creative", false);

      config.set("blocked.blacklist", Arrays.asList("bedrock", "monster_egg"));
      config.set("blocked.named-items", false);
      config.set("blocked.lore", Collections.singletonList("EXAMPLE_BLOCKED_LORE"));
      config.set("blocked.regex", "");

      config.set("action", "crouchrightclick");

      config.set("ranges.sameworld", 10.0);
      config.set("ranges.crossworld", 0.0);
      config.set("ranges.allowcrossworld", false);
      config.set(
          "ranges.blocked-worlds",
          Arrays.asList("ThisWorldDoesntExistButItsBlocked", "NeitherDoesThisOneButItIsToo"));

      config.set("antiscam.countdown", 10);
      config.set("antiscam.cancelonchange", true);
      config.set("antiscam.preventchangeonaccept", true);
      config.set("antiscam.discrepancy-detection", true);

      config.set("spectate.enabled", true);
      config.set("spectate.broadcast", true);

      config.set("gui.title", "Your Items <|     |> Their Items");
      config.set("gui.spectator-title", "Player 1 <|          |> Player 2");
      config.set("gui.head", "&7You are trading with: &3&l%PLAYER%");
      config.set("gui.showhead", !config.contains("showhead") || config.getBoolean("showhead"));

      config.set("gui.accept.display", "&a&lClick to accept the trade");
      config.set("gui.cancel.display", "&c&lClick to cancel the trade");
      config.set("gui.showaccept", true);
      config.set("gui.accept.theirdisplay", " ");
      config.set("gui.cancel.theirdisplay", " ");

      if (Sounds.version < 113) {
        config.set("gui.accept.type", "stained_glass_pane:13");
        config.set("gui.cancel.type", "stained_glass_pane:14");
        config.set("gui.separator.type", "stained_glass_pane:15");
      } else {
        config.set("gui.accept.type", "green_stained_glass_pane");
        config.set("gui.cancel.type", "red_stained_glass_pane");
        config.set("gui.separator.type", "black_stained_glass_pane");
      }
      config.set("gui.force.enabled", true);
      if (Sounds.version < 113) {
        config.set("gui.force.type", "watch");
      } else {
        config.set("gui.force.type", "clock");
      }
      config.set("gui.force.name", "&4&lForce Trade");
      config.set(
          "gui.force.lore",
          Arrays.asList(
              "&cClick here to force", "&cacceptance.", "", "&cThis shows only for admins."));

      config.set("gui.separator.customModelData", 0);
      config.set("gui.accept.customModelData", 0);
      config.set("gui.cancel.customModelData", 0);

      config.set("extras.type.prefix", "&6&l!!&6> ");
      config.set("extras.type.empty", "&eHow much %EXTRA% to offer?");
      config.set("extras.type.valid", "&aClick output slot to submit offer.");
      config.set("extras.type.invalid", "&cInvalid amount entered!");
      config.set("extras.type.maximum", "&cYou have %BALANCE% %EXTRA%");

      config.set("extras.economy.enabled", true);
      config.set("extras.economy.name", "money");
      config.set("extras.economy.material", "gold_ingot");
      config.set("extras.economy.display", "&eYour current money offer is &6%AMOUNT%");
      config.set("extras.economy.theirdisplay", "&eTheir current money offer is &6%AMOUNT%");
      config.set("extras.economy.lore", Collections.singletonList("&fClick to edit your offer!"));
      config.set("extras.economy.customModelData", 0);
      config.set("extras.economy.increment", 10.0);
      config.set("extras.economy.taxpercent", 0);
      config.set("extras.economy.mode", "chat");

      config.set("extras.experience.enabled", true);
      config.set("extras.experience.name", "experience points");
      config.set(
          "extras.experience.material", Sounds.version < 113 ? "exp_bottle" : "experience_bottle");
      config.set("extras.experience.display", "&aYour current XP offer is &2%AMOUNT%");
      config.set(
          "extras.experience.theirdisplay",
          "&aTheir current XP offer is &2%AMOUNT% &a(+%LEVELS% levels)");
      config.set(
          "extras.experience.lore",
          Arrays.asList("&fClick to edit your offer!", "&fYou have %BALANCE% XP."));
      config.set("extras.experience.customModelData", 0);
      config.set("extras.experience.increment", 5);
      config.set("extras.experience.taxpercent", 0);
      config.set("extras.experience.mode", "chat");
      config.set("extras.experience.levelMode", false);

      config.set("extras.playerpoints.enabled", true);
      config.set("extras.playerpoints.name", "player points");
      config.set("extras.playerpoints.material", "diamond");
      config.set("extras.playerpoints.display", "&bYour current PlayerPoints offer is &3%AMOUNT%");
      config.set(
          "extras.playerpoints.theirdisplay", "&bTheir current PlayerPoints offer is &3%AMOUNT%");
      config.set(
          "extras.playerpoints.lore", Collections.singletonList("&fClick to edit your offer!"));
      config.set("extras.playerpoints.customModelData", 0);
      config.set("extras.playerpoints.increment", 5);
      config.set("extras.playerpoints.taxpercent", 0);
      config.set("extras.playerpoints.mode", "chat");

      config.set("extras.griefprevention.enabled", true);
      config.set("extras.griefprevention.name", "grief prevention");
      config.set(
          "extras.griefprevention.material", Sounds.version > 112 ? "golden_shovel" : "gold_spade");
      config.set(
          "extras.griefprevention.display", "&eYour current GriefPrevention offer is &6%AMOUNT%");
      config.set(
          "extras.griefprevention.theirdisplay",
          "&eTheir current GriefPrevention offer is &6%AMOUNT%");
      config.set(
          "extras.griefprevention.lore", Collections.singletonList("&fClick to edit your offer!"));
      config.set("extras.griefprevention.customModelData", 0);
      config.set("extras.griefprevention.increment", 1);
      config.set("extras.griefprevention.taxperecent", 0);
      config.set("extras.griefprevention.mode", "chat");

      config.set("extras.enjinpoints.enabled", false);
      config.set("extras.enjinpoints.name", "enjin points");
      config.set("extras.enjinpoints.material", "emerald");
      config.set("extras.enjinpoints.display", "&eYour current EnjinPoints offer is &6%AMOUNT%");
      config.set(
          "extras.enjinpoints.theirdisplay", "&eTheir current EnjinPoints offer is &6%AMOUNT%");
      config.set(
          "extras.enjinpoints.lore", Collections.singletonList("&fClick to edit your offer!"));
      config.set("extras.enjinpoints.customModelData", 0);
      config.set("extras.enjinpoints.increment", 1);
      config.set("extras.enjinpoints.taxpercent", 0);
      config.set("extras.enjinpoints.mode", "chat");

      config.set("extras.tokenenchant.enabled", true);
      config.set("extras.tokenenchant.name", "token enchant points");
      config.set("extras.tokenenchant.material", "enchanted_book");
      config.set(
          "extras.tokenenchant.display", "&eYour current TokenEnchant tokens offer is &6%AMOUNT%");
      config.set(
          "extras.tokenenchant.theirdisplay",
          "&eTheir current TokenEnchants tokens offer is &6%AMOUNT%");
      config.set(
          "extras.tokenenchant.lore", Collections.singletonList("&fClick to edit your offer!"));
      config.set("extras.tokenenchant.customModelData", 0);
      config.set("extras.tokenenchant.increment", 1);
      config.set("extras.tokenenchant.taxpercent", 0);
      config.set("extras.tokenenchant.mode", "chat");

      config.set("extras.tokenmanager.enabled", true);
      config.set("extras.tokenmanager.name", "tokens");
      config.set("extras.tokenmanager.material", "emerald");
      config.set("extras.tokenmanager.display", "&eYour current tokens offer is &6%AMOUNT%");
      config.set(
          "extras.tokenmanager.theirdisplay",
          "&eTheir current TokenManager tokens offer is &6%AMOUNT%");
      config.set(
          "extras.tokenmanager.lore", Collections.singletonList("&fClick to edit your offer!"));
      config.set("extras.tokenmanager.customModelData", 0);
      config.set("extras.tokenmanager.increment", 1);
      config.set("extras.tokenmanager.taxpercent", 0);
      config.set("extras.tokenmanager.mode", "chat");

      config.set("extras.votingplugin.name", "vote points");
      config.set("extras.votingplugin.enabled", false);
      config.set("extras.votingplugin.material", "sunflower");
      config.set("extras.votingplugin.display", "&7Your current vote points offer is &b%AMOUNT%");
      config.set(
          "extras.votingplugin.theirdisplay", "&7Their current vote points offer is &b%AMOUNT%");
      config.set("extras.votingplugin.lore", Arrays.asList("&fClick to edit your offer!"));
      config.set("extras.votingplugin.taxpercent", 0);

      config.set("hooks.factions.allow-trades-in-enemy-territory", false);

      config.set("hooks.worldguard.trading-flag", true);

      config.set("soundeffects.enabled", true);
      config.set("soundeffects.onchange", true);
      config.set("soundeffects.onaccept", true);
      config.set("soundeffects.oncomplete", true);
      config.set("soundeffects.oncountdown", true);

      config.set("debug-mode", false);

      config.set("configversion", Double.parseDouble(plugin.getDescription().getVersion()));
    }
  }

  public void loadLang() {
    if (langFile.exists()) {
      lang = YamlConfiguration.loadConfiguration(langFile);
    } else {
      try {
        langFile.createNewFile();
      } catch (IOException ex) {
        ex.printStackTrace();
      }

      lang = YamlConfiguration.loadConfiguration(langFile);

      lang.set("request.sent", "&6&l(!) &r&6You sent a trade request to &e%PLAYER%");
      lang.set(
          "request.received.text",
          "&6&l(!) &r&6You received a trade request from &e%PLAYER%%NEWLINE%&6&l(!) &r&6Type &e/trade %PLAYER% &6to begin trading");
      lang.set("request.received.hover", "&6&lClick here to trade with &e&l%PLAYER%");

      lang.set("errors.creative", "&4&l(!) &r&4You can't trade in creative mode!");
      lang.set("errors.creative-them", "&4&l(!) &r&4That player is in creative mode!");
      lang.set(
          "errors.within-range.same-world",
          "&4&l(!) &r&4You must be within %AMOUNT% blocks of a player to trade with them");
      lang.set(
          "errors.within-range.cross-world",
          "&4&l(!) &r&4You must be within %AMOUNT% blocks of a player%NEWLINE%&4&l(!) &r&4in a different world to trade with them!");
      lang.set(
          "errors.no-cross-world",
          "&4&l(!) &r&4You must be in the same world as a player to trade with them!");
      lang.set("errors.same-ip", "&4&l(!) &4Players aren't allowed to trade on same IP!");

      lang.set("accept.sender", "&6&l(!) &r&e%PLAYER% &6accepted your trade request");
      lang.set("accept.receiver", "&6&l(!) &r&6You accepted &e%PLAYER%'s &6trade request");
      lang.set("cancelled", "&4&l(!) &r&4The trade was cancelled");
      lang.set("expired", "&4&l(!) &r&4Your last trade request expired");

      lang.set(
          "errors.wait-for-expire",
          "&4&l(!) &r&4You still have an active trade request%NEWLINE%&4&l(!) &r&4It will expire shortly");
      lang.set("errors.player-not-found", "&4&l(!) &r&4Could not find specified player");
      lang.set("errors.self-trade", "&4&l(!) &r&4You cannot trade with yourself");
      lang.set(
          "errors.invalid-usage",
          "&4&l(!) &r&4Invalid arguments. Usage: %NEWLINE%"
              + "    &c- /trade <player name>%NEWLINE%"
              + "    &c- /trade deny");
      lang.set("errors.no-perms.accept", "&4&l(!) &r&4You do not have permission to trade");
      lang.set("errors.no-perms.send", "&4&l(!) &r&4You do not have permission to send a trade");
      lang.set(
          "errors.no-perms.receive",
          "&4&l(!) &r&4That player does not have permission to accept a trade");
      lang.set(
          "errors.no-perms.admin", "&4&l(!) &r&4You do not have permission to use this command");

      lang.set("trade-complete", "&6&l(!) &r&6The trade was successful!");
      lang.set("forced-trade", "&6&l(!) &r&6You've been forced into a trade with &e%PLAYER%");
      lang.set("denied.them", "&4&l(!) &r&4Your trade request to &c%PLAYER% &4was denied");
      lang.set("denied.you", "&4&l(!) &r&4Any recent incoming trade requests have been denied.");
      lang.set(
              "spectate.text", "&6&l(!) &e%PLAYER1% &6and &e%PLAYER2% &6have started a trade %NEWLINE%&6&l(!) &6Type &e/tradeplus spectate %PLAYER1% %PLAYER2% &6to spectate");
      lang.set("spectate.hover", "&6&lClick here to spectate this trade");
      lang.set(
          "antiscam.discrepancy",
          "&4&l(!) &r&4A discrepancy was detected in the traded items.%NEWLINE%&4&l(!) &4The trade has been cancelled.");
      lang.set("admin.configs-reloaded", "&6&l(!) &6Configs reloaded!");
      lang.set("admin.invalid-players", "&4&l(!) &4Invalid players!");
      lang.set(
          "admin.forced-trade",
          "&6&l(!) &6You forced a trade between &e%PLAYER1% &6and &e%PLAYER2%");
      lang.set("admin.players-only", "&4&l(!) &4This command is for players only.");
      lang.set("admin.no-trade", "&4&l(!) &4No trade was found with those arguments.");
      lang.set("hooks.factions.enemy-territory", "&4&l(!) &4You can't trade in enemy territory!");
      lang.set("hooks.worldguard.trading-not-allowed", "&4&l(!) &4You can't trade in this area.");
    }
  }

  public void update() {
    double configVersion =
        config.contains("configversion") && config.isDouble("configversion")
            ? config.getDouble("configversion")
            : 0;

    if (configVersion < 1.11) {
      config.set(
          "gui.title",
          config.contains("guititle")
              ? config.get("guititle")
              : "Your Items <|     |> Their Items");
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
      config.set(
          "economy.theiroffer", "&7Their current money offer is &3&l%MONEYAMOUNT% %CURRENCYNAME%");
      config.set(
          "economy.higher", "&a&lClick to raise your money offer by %MONEYAMOUNT% %CURRENCYNAME%");
      config.set(
          "economy.lower", "&c&lClick to lower your money offer by %MONEYAMOUNT% %CURRENCYNAME%");
      config.set("economy.higheramount", 5.0);
      config.set("economy.loweramount", 5.0);
      config.set("soundeffects.enabled", true);
      config.set("soundeffects.onchange", true);
      config.set("soundeffects.onaccept", true);
      config.set("soundeffects.oncomplete", true);
      config.set("soundeffects.oncountdown", true);
    }

    if (configVersion < 1.22) {
      config.set("gui.separatorId", "stained_glass_pane:15");
    }

    if (configVersion < 1.23) {
      config.set("gui.separatorid", config.getString("gui.separatorId"));
      config.set("gui.separatorId", null);
      config.set("gui.acceptid", "stained_glass_pane:13");
      config.set("gui.cancelid", "stained_glass_pane:14");
    }

    if (configVersion < 1.24) {
      config.set("messages.withinrange", config.getString("messages.within10"));
      config.set("messages.within10", null);
      config.set("ranges.sameworld", 10.0);
      config.set("ranges.crossworld", 0.0);
      config.set("ranges.allowcrossworld", false);
      config.set(
          "messages.withinrangecrossworld",
          "&4&l(!) &r&4You must be within %AMOUNT% blocks of a player%NEWLINE%&4&l(!) &r&4in a different world to trade with them!");
      config.set(
          "messages.nocrossworld",
          "&4&l(!) &r&4You must be in the same world as a player to trade with them!");
    }

    if (configVersion < 1.3) {
      config.set("blocked-items", Collections.singletonList("bedrock"));
    }

    if (configVersion < 1.31) {
      config.set("action", "crouchrightclick");
      config.set("economy.increment", config.getDouble("economy.higheramount", 10.0));
      config.set("economy.higheramount", null);
      config.set("economy.loweramount", null);
      config.set("gui.acceptid", "stained_glass_pane:14");
      config.set("gui.cancelid", "stained_glass_pane:13");
    }

    if (configVersion < 1.34) {
      config.set("hooks.economy.enabled", config.getBoolean("economy.enabled", true));
      config.set("hooks.economy.material", "gold_ingot");
      config.set(
          "hooks.economy.youroffer",
          config.getString("economy.offer", "&7Your current money offer is &e%MONEYAMOUNT%"));
      config.set(
          "hooks.economy.theiroffer",
          config.getString("economy.theiroffer", "&7Their current money offer is &e%MONEYAMOUNT%"));
      config.set("hooks.economy.increment", config.getDouble("economy.increment", 10.0));
      config.set("economy", null);
    }

    if (configVersion < 1.5) {
      config.set("hooks.xp.enabled", true);
      config.set("hooks.xp.material", Sounds.version < 113 ? "exp_bottle" : "experience_bottle");
      config.set("hooks.xp.youroffer", "&7Your current XP offer is &e%XPAMOUNT%");
      config.set("hooks.xp.theiroffer", "&7Their current XP offer is &e%XPAMOUNT%");
      config.set("hooks.xp.increment", 5);
    }

    if (configVersion < 2.0) {
      if (config.contains("messages")) {
        for (String key : config.getConfigurationSection("messages").getKeys(false))
          lang.set(
              key,
              config
                  .getString("messages." + key)
                  .replace("%PLAYERNAME%", "%PLAYER%")
                  .replace("%MONEYAMOUNT%", "%AMOUNT%")
                  .replace("%XPAMOUNT%", "%AMOUNT%"));
        config.set("messages", null);
      }
      if (config.contains("hooks")) {
        for (String key : config.getConfigurationSection("hooks.economy").getKeys(false))
          config.set(
              "extras.economy."
                  + key.replace("youroffer", "display").replace("theiroffer", "theirdisplay"),
              config.get("hooks.economy." + key));
        for (String key : config.getConfigurationSection("hooks.xp").getKeys(false))
          config.set(
              "extras.experience."
                  + key.replace("youroffer", "display").replace("theiroffer", "theirdisplay"),
              config.get("hooks.experience." + key));
        config.set("hooks", null);
        config.set(
            "extras.economy.lore",
            Arrays.asList(
                "&aLeft Click to &clower &ayour offer by %PLAYERINCREMENT%",
                "&aRight Click to &braise &ayour offer by %PLAYERINCREMENT%",
                "&aShift + Left Click to &clower &ayour increment by %INCREMENT%",
                "&aShift + Right Click to &braise &ayour increment by %INCREMENT%"));
        config.set(
            "extras.experience.lore",
            Arrays.asList(
                "&aLeft Click to &clower &ayour offer by %PLAYERINCREMENT%",
                "&aRight Click to &braise &ayour offer by %PLAYERINCREMENT%",
                "&aShift + Left Click to &clower &ayour increment by %INCREMENT%",
                "&aShift + Right Click to &braise &ayour increment by %INCREMENT%"));
        config.set("extras.economy.taxpercent", 0);
        config.set("extras.experience.taxpercent", 0);
      }
      config.set(
          "gui.head",
          config
              .getString("gui.headname", "&7You are trading with: &3&l%PLAYER%")
              .replace("%PLAYERNAME%", "%PLAYER%"));
      config.set("gui.headname", null);
    }

    if (configVersion < 2.1) {
      config.set("extras.playerpoints.enabled", true);
      config.set("extras.playerpoints.material", "diamond");
      config.set("extras.playerpoints.display", "&7Your current PlayerPoints offer is &b%AMOUNT%");
      config.set(
          "extras.playerpoints.theirdisplay", "&7Their current PlayerPoints offer is &b%AMOUNT%");
      config.set(
          "extras.playerpoints.lore",
          Arrays.asList(
              "&aLeft Click to &clower &ayour offer by %PLAYERINCREMENT%",
              "&aRight Click to &braise &ayour offer by %PLAYERINCREMENT%",
              "&aShift + Left Click to &clower &ayour increment by %INCREMENT%",
              "&aShift + Right Click to &braise &ayour increment by %INCREMENT%"));
      config.set("extras.playerpoints.increment", 5);
      config.set("extras.playerpoints.taxpercent", 0);
    }

    if (configVersion < 2.11) {
      config.set("blocked.blacklist", config.getStringList("blocked-items"));
      config.set("blocked.named-items", false);
    }

    if (configVersion < 2.12) {
      config.set("extras.griefprevention.enabled", true);
      config.set("extras.griefprevention.material", "diamond_pickaxe");
      config.set(
          "extras.griefprevention.display", "&7Your current GriefPrevention offer is &b%AMOUNT%");
      config.set(
          "extras.griefprevention.theirdisplay",
          "&7Their current GriefPrevention offer is &b%AMOUNT%");
      config.set(
          "extras.griefprevention.lore",
          Arrays.asList(
              "&aLeft Click to &clower &ayour offer by %PLAYERINCREMENT%",
              "&aRight Click to &braise &ayour offer by %PLAYERINCREMENT%",
              "&aShift + Left Click to &clower &ayour increment by %INCREMENT%",
              "&aShift + Right Click to &braise &ayour increment by %INCREMENT%"));
      config.set("extras.griefprevention.increment", 1);
      config.set("extras.griefprevention.taxperecent", 0);
    }

    if (configVersion < 2.14) {
      config.set("requestcooldownseconds", 20);
      lang.set("denied-them", "&4&l(!) &r&4Your trade request to &c%PLAYER% &4was denied");
      lang.set("denied-you", "&4&l(!) &r&4Any recent incoming trade requests have been denied.");
      if (lang.getString(
              "invalidusage", "&4&l(!) &r&4Invalid arguments. Usage: &c/trade <player name>")
          .equals("&4&l(!) &r&4Invalid arguments. Usage: &c/trade <player name>")) {
        lang.set(
            "invalidusage",
            "&4&l(!) &r&4Invalid arguments. Usage: %NEWLINE%"
                + "    &c- /trade <player name>%NEWLINE%"
                + "    &c- /trade deny");
      }
    }

    if (configVersion < 2.20) {
      config.set("gui.spectator-title", "%PLAYER1%              %PLAYER2%");
      config.set("gui.force.enabled", config.getBoolean("gui.showadminforce", true));
      config.set("gui.showadminforce", null);
      if (Sounds.version < 113) {
        config.set("gui.force.type", "watch");
      } else {
        config.set("gui.force.type", "clock");
      }
      config.set("gui.force.name", "&4&lForce Trade");
      config.set(
          "gui.force.lore",
          Arrays.asList(
              "&cClick here to force", "&cacceptance.", "", "&cThis shows only for admins."));
      config.set("gui.showaccept", true);
    }

    if (configVersion < 2.22) {
      if (Sounds.version < 19) config.set("gui.title", "Your Items <|     |> Their Items");
    }

    if (configVersion < 2.30) {
      config.set(
          "ranges.blocked-worlds",
          Arrays.asList("ThisWorldDoesntExistButItsBlocked", "NeitherDoesThisOneButItIsToo"));
    }

    if (configVersion < 2.42) {
      config.set("allow-trade-in-creative", false);
      config.set("blocked.lore", Collections.singletonList("EXAMPLE_BLOCKED_LORE"));
      config.set("blocked.regex", "");
      lang.set("creative", "&4&l(!) &r&4You can't trade in creative mode!");
      lang.set("creativethem", "&4&l(!) &r&4That player is in creative mode!");
    }

    if (configVersion < 2.45) {
      config.set("permissions.required", config.getBoolean("permissionrequired", false));
      config.set("permissions.send", config.getString("permissionnode", "tradeplus.send"));
      config.set("permissions.accept", "tradeplus.accept");
      config.set("permissionrequired", null);
      config.set("permissionnode", null);
    }

    if (configVersion < 2.50) {
      config.set("extras.enjinpoints.enabled", false);
      config.set("extras.enjinpoints.material", "emerald");
      config.set("extras.enjinpoints.display", "&7Your current EnjinPoints offer is &b%AMOUNT%");
      config.set(
          "extras.enjinpoints.theirdisplay", "&7Their current EnjinPoints offer is &b%AMOUNT%");
      config.set(
          "extras.enjinpoints.lore",
          Arrays.asList(
              "&aLeft Click to &clower &ayour offer by %PLAYERINCREMENT%",
              "&aRight Click to &braise &ayour offer by %PLAYERINCREMENT%",
              "&aShift + Left Click to &clower &ayour increment by %INCREMENT%",
              "&aShift + Right Click to &braise &ayour increment by %INCREMENT%"));
      config.set("extras.enjinpoints.increment", 1);
      config.set("extras.enjinpoints.taxpercent", 0);
    }

    if (configVersion < 2.51) {
      config.set("aliases", Collections.singletonList("trade+"));
    }

    if (configVersion < 2.52) {
      config.set("extras.tokenenchant.enabled", true);
      config.set("extras.tokenenchant.material", "enchanted_book");
      config.set(
          "extras.tokenenchant.display", "&7Your current TokenEnchant tokens offer is &b%AMOUNT%");
      config.set(
          "extras.tokenenchant.theirdisplay",
          "Their current TokenEnchants tokens offer is &b%AMOUNT%");
      config.set(
          "extras.tokenenchant.lore",
          Arrays.asList(
              "&aLeft Click to &clower &ayour offer by %PLAYERINCREMENT%",
              "&aRight Click to &braise &ayour offer by %PLAYERINCREMENT%",
              "&aShift + Left Click to &clower &ayour increment by %INCREMENT%",
              "&aShift + Right Click to &braise &ayour increment by %INCREMENT%"));
      config.set("extras.tokenenchant.increment", 1);
      config.set("extras.tokenenchant.taxpercent", 0);
    }

    if (configVersion < 2.53) {
      lang.set("nopermssender", "&4&l(!) &r&4You do not have permission to send a trade");
      lang.set(
          "nopermsreceiver", "&4&l(!) &r&4That player does not have permission to accept a trade");
      lang.set("nopermssenderadmin", "&4&l(!) &r&4You do not have permission to use this command");
    }

    if (configVersion < 2.56) {
      lang.set(
          "spectate.text",
          "&6&l(!) &e%PLAYER1% &6and &e%PLAYER2% &6have started a trade %NEWLINE%&6&l(!) &6Type &e/tradeplus spectate %PLAYER1% %PLAYER2% &6to spectate");
      lang.set("spectate.hover", "&6&lClick here to spectate this trade");
    }

    if (configVersion < 3.1) {
      config.set("antiscam.discrepancy-detection", true);

      lang.set(
          "antiscam.discrepancy",
          "&4&l(!) &r&4A discrepancy was detected in the traded items.%NEWLINE%&4&l(!) &4The trade has been cancelled.");
      lang.set("admin.configs-reloaded", "&6&l(!) &6Configs reloaded!");
      lang.set("admin.invalid-players", "&4&l(!) &4Invalid players!");
      lang.set(
          "admin.forced-trade",
          "&6&l(!) &6You forced a trade between &e%PLAYER1% &6and &e%PLAYER2%");
      lang.set("admin.players-only", "&4&l(!) &4This command is for players only.");
      lang.set("admin.no-trade", "&4&l(!) &4No trade was found with those arguments.");

      // New lang keys
      lang.set(
          "request.sent",
          lang.getString("sentrequest", "&6&l(!) &r&6You sent a trade request to &e%PLAYER%"));
      lang.set(
          "request.received.text",
          lang.getString(
              "receivedrequest",
              "&6&l(!) &r&6You received a trade request from &e%PLAYER%%NEWLINE%&6&l(!) &r&6Type &e/trade %PLAYER% &6to begin trading"));
      lang.set(
          "request.received.hover",
          lang.getString("received-request-hover", "&6&lClick here to trade with &e&l%PLAYER%"));
      lang.set(
          "accept.sender",
          lang.getString("senderaccept", "&6&l(!) &r&e%PLAYER% &6accepted your trade request"));
      lang.set(
          "accept.receiver",
          lang.getString(
              "receiveraccept", "&6&l(!) &r&6You accepted &e%PLAYER%'s &6trade request"));
      lang.set(
          "forced-trade",
          lang.getString(
              "forcedtrade", "&6&l(!) &r&6You've been forced into a trade with &e%PLAYER%"));
      lang.set(
          "trade-complete",
          lang.getString("tradecomplete", "&6&l(!) &r&6The trade was successful!"));
      lang.set(
          "errors.creative",
          lang.getString("creative", "&4&l(!) &r&4You can't trade in creative mode!"));
      lang.set(
          "errors.creative-them",
          lang.getString("creativethem", "&4&l(!) &r&4That player is in creative mode!"));
      lang.set(
          "errors.within-range.same-world",
          lang.getString(
              "withinrange",
              "&4&l(!) &r&4You must be within %AMOUNT% blocks of a player to trade with them"));
      lang.set(
          "errors.within-range.cross-world",
          lang.getString(
              "withinrangecrossworld",
              "&4&l(!) &r&4You must be within %AMOUNT% blocks of a player%NEWLINE%&4&l(!) &r&4in a different world to trade with them!"));
      lang.set(
          "errors.no-cross-world",
          lang.getString(
              "nocrossworld",
              "&4&l(!) &r&4You must be in the same world as a player to trade with them!"));
      lang.set(
          "errors.wait-for-expire",
          lang.getString(
              "waitforexpire",
              "&4&l(!) &r&4You still have an active trade request%NEWLINE%&4&l(!) &r&4It will expire shortly"));
      lang.set(
          "errors.player-not-found",
          lang.getString("playernotfound", "&4&l(!) &r&4Could not find specified player"));
      lang.set(
          "errors.self-trade",
          lang.getString("tradewithself", "&4&l(!) &r&4You cannot trade with yourself"));
      lang.set(
          "errors.invalid-usage",
          lang.getString(
              "invalidusage",
              "&4&l(!) &r&4Invalid arguments. Usage: %NEWLINE%"
                  + "    &c- /trade <player name>%NEWLINE%"
                  + "    &c- /trade deny"));
      lang.set(
          "errors.no-perms.accept",
          lang.getString("noperms", "&4&l(!) &r&4You do not have permission to trade"));
      lang.set(
          "errors.no-perms.send",
          lang.getString(
              "nopermssender", "&4&l(!) &r&4You do not have permission to send a trade"));
      lang.set(
          "errors.no-perms.receive",
          lang.getString(
              "nopermsreceiver",
              "&4&l(!) &r&4That player does not have permission to accept a trade"));
      lang.set(
          "errors.no-perms.admin",
          lang.getString(
              "nopermssenderadmin", "&4&l(!) &r&4You do not have permission to use this command"));
      lang.set(
          "denied.them",
          lang.getString(
              "denied-them", "&4&l(!) &r&4Your trade request to &c%PLAYER% &4was denied"));
      lang.set(
          "denied.you",
          lang.getString(
              "denied-you", "&4&l(!) &r&4Any recent incoming trade requests have been denied."));
    }

    if (configVersion < 3.15) {
      config.set("extras.tokenmanager.enabled", true);
      config.set("extras.tokenmanager.material", "emerald");
      config.set(
          "extras.tokenmanager.display", "&7Your current TokenManager tokens offer is &b%AMOUNT%");
      config.set(
          "extras.tokenmanager.theirdisplay",
          "&7Their current TokenManager tokens offer is &b%AMOUNT%");
      config.set(
          "extras.tokenmanager.lore",
          Arrays.asList(
              "&aLeft Click to &clower &ayour offer by %PLAYERINCREMENT%",
              "&aRight Click to &braise &ayour offer by %PLAYERINCREMENT%",
              "&aShift + Left Click to &clower &ayour increment by %INCREMENT%",
              "&aShift + Right Click to &braise &ayour increment by %INCREMENT%"));
      config.set("extras.tokenmanager.increment", 1);
      config.set("extras.tokenmanager.taxpercent", 0);
    }

    if (configVersion < 3.23) {
      config.set("debug-mode", false);
    }

    if (configVersion < 3.30) {
      for (String extra : config.getConfigurationSection("extras").getKeys(false)) {
        config.set("extras." + extra + ".mode", "chat");
      }
      config.set("extras.type.empty", "&eHow much %EXTRA% to offer?");
      config.set("extras.type.valid", "&aClick output slot to submit offer.");
      config.set("extras.type.invalid", "&cInvalid amount entered!");
      config.set("extras.type.maximum", "&cYou have %BALANCE% %EXTRA%");
    }

    if (configVersion < 3.40) {
      config.set("trade-logs", false);
    }

    if (configVersion < 3.46) {
      config.set("allow-same-ip-trade", true);
      config.set("hooks.factions.allow-trades-in-enemy-territory", false);
      lang.set("errors.same-ip", "&4&l(!) &4Players aren't allowed to trade on same IP!");
      lang.set("hooks.factions.enemy-territory", "&4&l(!) &4You can't trade in enemy territory!");
    }

    if (configVersion < 3.55) {
      config.set("extras.experience.levelMode", false);
    }

    if (configVersion < 3.63) {
      config.set("extras.type.prefix", "&6&l!!&6> ");
    }

    if (configVersion < 3.65) {
      config.set("trade-command-compatible-mode", false);
    }

    if (configVersion < 3.66) {
      config.set("gui.accept.display", config.get("gui.accept"));
      config.set("gui.cancel.display", config.get("gui.cancel"));

      config.set("gui.accept.theirdisplay", config.get("gui.theyaccept"));
      config.set("gui.cancel.theirdisplay", config.get("gui.theycancel"));

      config.set("gui.separator.type", config.get("gui.separatorid"));
      config.set("gui.accept.type", config.get("gui.acceptid"));
      config.set("gui.cancel.type", config.get("gui.cancelid"));

      config.set("gui.separatorid", null);
      config.set("gui.acceptid", null);
      config.set("gui.cancelid", null);

      config.set("gui.separator.customModelData", 0);
      config.set("gui.accept.customModelData", 0);
      config.set("gui.cancel.customModelData", 0);

      for (String extra : config.getConfigurationSection("extras").getKeys(false)) {
        config.set("extras." + extra + ".customModelData", 0);
      }
    }

    if (configVersion < 3.67) {
      config.set("extras.economy.name", "money");
      config.set("extras.experience.name", "experience points");
      config.set("extras.playerpoints.name", "player points");
      config.set("extras.griefprevention.name", "grief prevention");
      config.set("extras.enjinpoints.name", "enjin points");
      config.set("extras.tokenenchant.name", "token enchant points");
      config.set("extras.tokenmanager.name", "tokens");
    }

    if (configVersion < 3.68) {
      config.set("excess-chest.enabled", true);
      config.set("excess-chest.title", "&7Your inventory is full!");
      config.set("hooks.worldguard.trading-flag", true);
      lang.set("hooks.worldguard.trading-not-allowed", "&4&l(!) &4You can't trade in this area.");
    }

    if (configVersion < 3.69) {
      config.set("spectate.enabled", true);
      config.set("spectate.broadcast", true);
    }

    if (configVersion < 3.70) {
      config.set("extras.votingplugin.name", "vote points");
      config.set("extras.votingplugin.material", "sunflower");
      config.set("extras.votingplugin.display", "&7Your current vote points offer is &b%AMOUNT%");
      config.set(
          "extras.votingplugin.theirdisplay", "&7Their current vote points offer is &b%AMOUNT%");
      config.set("extras.votingplugin.lore", Arrays.asList("&fClick to edit your offer!"));
      config.set("extras.votingplugin.taxpercent", 0);
    }

    if (configVersion < 3.71) {
      if (!config
          .getString("extras.experience.theirdisplay", "&aTheir current XP offer is &2%AMOUNT%")
          .contains("%LEVELS%")) {
        config.set(
            "extras.experience.theirdisplay",
            config.getString(
                    "extras.experience.theirdisplay", "&aTheir current XP offer is &2%AMOUNT%")
                + " &a(+%LEVELS% levels)");
      }
    }

    if (configVersion < 3.72) {
      config.set("extras.votingplugin.name", "vote points");
      config.set("extras.votingplugin.enabled", false);
      config.set("extras.votingplugin.material", "sunflower");
      config.set("extras.votingplugin.display", "&7Your current vote points offer is &b%AMOUNT%");
      config.set(
          "extras.votingplugin.theirdisplay", "&7Their current vote points offer is &b%AMOUNT%");
      config.set("extras.votingplugin.lore", Arrays.asList("&fClick to edit your offer!"));
      config.set("extras.votingplugin.taxpercent", 0);
    }

    if (configVersion < 3.73) {
      lang.set(
              "spectate.text",
              lang.getString("spectate.message", "&6&l(!) &e%PLAYER1% &6and &e%PLAYER2% &6have started a trade %NEWLINE%&6&l(!) &6Type &e/tradeplus spectate %PLAYER1% %PLAYER2% &6to spectate"));
      if (!lang.isString("errors.same-ip")) lang.set("errors.same-ip", "&4&l(!) &4Players aren't allowed to trade on same IP!");
    }

    config.set("configversion", Double.parseDouble(plugin.getDescription().getVersion()));
  }
}
