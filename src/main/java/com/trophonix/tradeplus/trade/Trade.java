package com.trophonix.tradeplus.trade;

import com.trophonix.tradeplus.TradePlus;
import com.trophonix.tradeplus.extras.*;
import com.trophonix.tradeplus.util.InvUtils;
import com.trophonix.tradeplus.util.MsgUtils;
import com.trophonix.tradeplus.util.Sounds;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.*;
import org.bukkit.event.player.*;
import org.bukkit.event.server.PluginDisableEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitTask;

import java.util.*;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;
import java.util.stream.Collectors;

public class Trade implements Listener {

  private static final List<Integer> extraSlots = new LinkedList<>(Arrays.asList(45, 46, 47, 48, 39, 38, 37, 36, 27, 28, 29, 30));
  public final Player player1;
  public final Player player2;
  public final Inventory spectatorInv;
  private final TradePlus pl = (TradePlus) Bukkit.getPluginManager().getPlugin("TradePlus");
  private final Inventory inv1;
  private final Inventory inv2;
  private final List<Extra> extras = new ArrayList<>();
  private final long startTime = System.currentTimeMillis();
  private boolean accept1, accept2;
  private ItemStack[] accepted1, accepted2;
  private boolean forced = false;
  private BukkitTask task = null;

  public Trade(Player player1, Player player2) {
    this.player1 = player1;
    this.player2 = player2;
    this.inv1 = InvUtils.getTradeInventory(player1, player2);
    this.inv2 = InvUtils.getTradeInventory(player2, player1);
    this.spectatorInv = InvUtils.getSpectatorInventory(player1, player2);
    Bukkit.getOnlinePlayers().forEach(p -> {
      if (p.hasPermission("tradeplus.admin") && !p.hasPermission("tradeplus.admin.silent")) {
        MsgUtils.send(p, pl.getConfig().getString("spectate.hover", "&6&lClick here to spectate this trade"), "/tradeplus spectate " + player1.getName() + " " + player2.getName(),
                pl.getLang().getString("spectate.message", "&6&l(!) &e%PLAYER1% &6and &e%PLAYER2% &6have started a trade %NEWLINE%&6&l(!) &6Type &e/tradeplus spectate %PLAYER1% %PLAYER2% &6to spectate")
                  .replace("%PLAYER1%", player1.getName()).replace("%PLAYER2%", player2.getName()).split("%NEWLINE%"));
      }
    });
    player1.openInventory(inv1);
    player2.openInventory(inv2);
    if (pl.getConfig().getBoolean("extras.economy.enabled", true) && pl.getServer().getPluginManager().isPluginEnabled("Vault")) {
      try {
        if (pl.getServer().getServicesManager().getRegistration(Class.forName("net.milkbowl.vault.economy.Economy")) != null)
          extras.add(new EconomyExtra(player1, player2, pl));
      } catch (Exception ignored) {
      }
    }
    if (pl.getConfig().getBoolean("extras.experience.enabled", true))
      extras.add(new ExperienceExtra(player1, player2, pl));
    if (pl.getConfig().getBoolean("extras.playerpoints.enabled", true) && pl.getServer().getPluginManager().isPluginEnabled("PlayerPoints"))
      extras.add(new PlayerPointsExtra(player1, player2, pl));
    if (pl.getConfig().getBoolean("extras.griefprevention.enabled", true) && pl.getServer().getPluginManager().isPluginEnabled("GriefPrevention"))
      extras.add(new GriefPreventionExtra(player1, player2, pl));
    if (pl.getConfig().getBoolean("extras.enjinpoints.enabled", false) && pl.getServer().getPluginManager().isPluginEnabled("EnjinMinecraftPlugin"))
      extras.add(new EnjinPointsExtra(player1, player2, pl));
    if (pl.getConfig().getBoolean("extras.tokenenchant.enabled", true) && pl.getServer().getPluginManager().isPluginEnabled("TokenEnchant"))
      extras.add(new TokenEnchantExtra(player1, player2, pl));
    updateExtras();
    pl.getServer().getPluginManager().registerEvents(this, pl);
    pl.ongoingTrades.add(this);
  }

  @EventHandler
  public void onDrag(InventoryDragEvent event) {
    if (!(event.getWhoClicked() instanceof Player)) return;
    Player player = (Player) event.getWhoClicked();
    Inventory inv = event.getInventory();
    if (inv == null) return;
    if (inv.equals(inv1) || inv.equals(inv2)) {
      if (accept1 && accept2) {
        event.setCancelled(true);
        return;
      }

      for (int slot : event.getInventorySlots()) {
        if (!InvUtils.leftSlots.contains(slot)) {
          event.setCancelled(true);
          return;
        }
      }
      if (event.getInventorySlots().size() > 0) {
        if (pl.getConfig().getBoolean("antiscam.preventchangeonaccept") && ((player.equals(player1) && accept1) || (player.equals(player2) && accept2))) {
          event.setCancelled(true);
          return;
        }
        Bukkit.getScheduler().runTaskLater(pl, this::updateInventories, 1L);
        if (pl.getConfig().getBoolean("soundeffects.enabled", true) && pl.getConfig().getBoolean("soundeffects.onchange")) {
          Sounds.click(player1, 2);
          Sounds.click(player2, 2);
          spectatorInv.getViewers().stream().filter(Player.class::isInstance).forEach(p ->
                  Sounds.click((Player) p, 2));
        }
      }
    } else if (inv.equals(spectatorInv)) {
      event.setCancelled(true);
    }
  }

  @EventHandler
  public void onClick(InventoryClickEvent event) {
    if (!(event.getWhoClicked() instanceof Player)) return;
    Player player = (Player) event.getWhoClicked();
    Inventory inv = event.getClickedInventory();
    ClickType click = event.getClick();
    if (inv == null || ((event.getCurrentItem() == null || event.getCurrentItem().getType().equals(Material.AIR)) &&
            (event.getCursor() == null || event.getCursor().getType().equals(Material.AIR)))) return;
    int slot = event.getSlot();
    if (inv.equals(inv1) || inv.equals(inv2)) {
      if (inv.getItem(49) == null || inv.getItem(49).getType().equals(Material.AIR)) {
        event.setCancelled(true);
        event.setResult(InventoryClickEvent.Result.DENY);
        return;
      }
      if (click.equals(ClickType.DOUBLE_CLICK)) {
        event.setCancelled(true);
        return;
      }
      if (InvUtils.leftSlots.contains(slot) && getExtra(slot) == null) {
        if (accept1 && accept2) {
          event.setCancelled(true);
          return;
        }

        if (pl.getConfig().getBoolean("antiscam.preventchangeonaccept") && ((player.equals(player1) && accept1) || (player.equals(player2) && accept2))) {
          event.setCancelled(true);
          event.setResult(InventoryClickEvent.Result.DENY);
          return;
        }
        if (pl.getConfig().getBoolean("antiscam.cancelonchange")) {
          accept1 = false;
          accept2 = false;
          updateAcceptance();
        }
        Bukkit.getScheduler().runTaskLater(pl, this::updateInventories, 1L);
        if (pl.getConfig().getBoolean("soundeffects.enabled", true) && pl.getConfig().getBoolean("soundeffects.onchange")) {
          Sounds.click(player1, 2);
          Sounds.click(player2, 2);
          spectatorInv.getViewers().stream().filter(Player.class::isInstance).forEach(p ->
                  Sounds.click((Player) p, 2));
        }
      } else {
        event.setCancelled(true);
        event.setResult(InventoryClickEvent.Result.DENY);
        if (inv.getItem(slot) != null && (inv.getItem(slot).isSimilar(InvUtils.acceptTrade)
                || inv.getItem(slot).isSimilar(InvUtils.cancelTrade))) {
          if (!forced) {
            if (player.equals(player1)) {
              accept1 = !accept1;
            } else {
              accept2 = !accept2;
            }
            updateAcceptance();
            checkAcceptance();
          }
        } else if (slot == 49) {
          if (inv.getItem(slot).isSimilar(InvUtils.force)) {
            if (forced) {
              forced = false;
              accept1 = false;
              accept2 = false;
              updateAcceptance();
              checkAcceptance();
            } else {
              forced = true;
              accept1 = true;
              accept2 = true;
              updateAcceptance();
              checkAcceptance();
            }
          }
        } else {
          Extra extra = getExtra(slot);
          if (extra != null) {
            if (pl.getConfig().getBoolean("antiscam.preventchangeonaccept", true) && ((player.equals(player1) && accept1) || (player.equals(player2) && accept2)))
              return;
            if (task != null)
              return;
            extra.onClick(player, event.getClick());
            updateExtras();
            if (pl.getConfig().getBoolean("soundeffects.enabled", true) && pl.getConfig().getBoolean("soundeffects.onchange")) {
              Sounds.click(player1, 2);
              Sounds.click(player2, 2);
              spectatorInv.getViewers().stream().filter(h -> h instanceof Player).forEach(p ->
                      Sounds.click((Player) p, 2));
            }
          }
        }
      }
    } else if (inv.equals(player.getInventory())) {
      Inventory open = player.getOpenInventory().getTopInventory();
      if (open != null && (open.equals(inv1) || open.equals(inv2))) {
        if (click.equals(ClickType.DOUBLE_CLICK)) {
          event.setCancelled(true);
          if (accept1 && accept2) {
            return;
          }
          ItemStack item = event.getCurrentItem();
          ItemStack cursor = player.getItemOnCursor();
          if ((item == null || item.getType().equals(Material.AIR)) && !(cursor == null || cursor.getType().equals(Material.AIR))) {
            for (int j : InvUtils.leftSlots) {
              ItemStack i = open.getItem(j);
              if (i != null && i.isSimilar(cursor)) {
                int amount = cursor.getAmount() + i.getAmount();
                if (amount <= 64) {
                  open.setItem(j, null);
                  cursor.setAmount(amount);
                } else {
                  int buffer = 64 - cursor.getAmount();
                  i.setAmount(i.getAmount() - buffer);
                  cursor.setAmount(64);
                }
                if (cursor.getAmount() >= 64)
                  break;
              }
            }
          }
        } else if (click == ClickType.SHIFT_LEFT) {
          event.setCancelled(true);
          if (accept1 && accept2) return;
          ItemStack current = event.getCurrentItem();
          if (current != null) {
            player.getInventory().setItem(event.getSlot(), putOnLeft(player.equals(player1) ? inv1 : inv2, current));
            if (pl.getConfig().getBoolean("soundeffects.enabled", true) && pl.getConfig().getBoolean("soundeffects.onchange")) {
              Sounds.click(player1, 2);
              Sounds.click(player2, 2);
              spectatorInv.getViewers().stream().filter(h -> h instanceof Player).forEach(p ->
                      Sounds.click((Player) p, 2));
            }
          }
        }
        if (pl.getConfig().getBoolean("antiscam.preventchangeonaccept") && ((player.equals(player1) && accept1) || (player.equals(player2) && accept2))) {
          event.setCancelled(true);
          return;
        }
        if (pl.getConfig().getBoolean("antiscam.cancelonchange")) {
          accept1 = false;
          accept2 = false;
          updateAcceptance();
        }
        Bukkit.getScheduler().runTaskLater(pl, this::updateInventories, 1L);
      }
    } else if (inv.equals(spectatorInv)) {
      event.setCancelled(true);
    }
  }

  @EventHandler
  public void onClose(InventoryCloseEvent event) {
    Inventory closed = event.getInventory();
    if (closed == null || closed.getSize() < 54) return;
    if (closed.equals(inv1) || closed.equals(inv2)) {
      if (closed.getItem(49) == null) return;
      pl.ongoingTrades.remove(this);
      if (task != null) {
        task.cancel();
        task = null;
      }
      inv1.setItem(49, null);
      inv2.setItem(49, null);
      giveItemsOnLeft(inv1, player1);
      giveItemsOnLeft(inv2, player2);
      giveOnCursor(player1);
      giveOnCursor(player2);
      player1.closeInventory();
      player2.closeInventory();
      MsgUtils.send(player1, pl.getLang().getString("cancelled"));
      MsgUtils.send(player2, pl.getLang().getString("cancelled"));
      spectatorInv.getViewers().forEach(HumanEntity::closeInventory);
      HandlerList.unregisterAll(this);
    }
  }

  @EventHandler
  public void onMove(PlayerMoveEvent event) {
    Player player = event.getPlayer();
    if (player.equals(player1) || player.equals(player2)) {
      if (System.currentTimeMillis() < startTime + 1000) return;
      event.setCancelled(true);
    }
  }

  @EventHandler
  public void onInventoryPickupEvent(InventoryPickupItemEvent event) {
    if (accept1 && accept2 && (event.getInventory() == inv1 || event.getInventory() == inv2)) {
      event.setCancelled(true);
    }
  }

  @EventHandler
  public void onInventoryInteract(InventoryInteractEvent event) {
    if (accept1 && accept2 && (event.getInventory() == inv1 || event.getInventory() == inv2)) {
      event.setCancelled(true);
    }
  }

  @EventHandler
  public void onDropItem(PlayerDropItemEvent event) {
    if (player1.equals(event.getPlayer()) || player2.equals(event.getPlayer())) {
      event.setCancelled(true);
      if (accept1 && accept2) giveOnCursor(event.getPlayer());
    }
  }

  private void giveOnCursor(Player player) {
    if (player.getItemOnCursor() != null && player.getItemOnCursor().getType() != Material.AIR) {
      player.getInventory().addItem(player.getItemOnCursor()).forEach((i, j) ->
              player.getWorld().dropItemNaturally(player.getLocation(), j));
      player.setItemOnCursor(null);
    }
  }

  @EventHandler
  public void onQuit(PlayerQuitEvent event) {
    Player p = event.getPlayer();
    if (p.equals(player1) || p.equals(player2)) {
      p.closeInventory();
    }
  }

  @EventHandler
  public void onDisable(PluginDisableEvent event) {
    if (event.getPlugin().getName().equalsIgnoreCase("TradePlus")) {
      player1.closeInventory();
    }
  }

  @EventHandler
  public void onPickup(final PlayerPickupItemEvent event) {
    Player player = event.getPlayer();
    if (player.equals(this.player1) || player.equals(this.player2)) {
      event.setCancelled(true);
    }
  }

  private void giveItemsOnLeft(Inventory inv, Player player) {
    System.out.println("Giving [INV " + (inv == inv1 ? "1" : "2") + "] to [PLAYER " + (player == player1 ? "1" : "2") + "]");
    InvUtils.leftSlots.forEach(slot -> {
      if (getExtra(slot) == null) {
        ItemStack item = inv.getItem(slot);
        if (item != null)
          player.getInventory().addItem(item).values().stream().findFirst().ifPresent(i ->
                  player.getWorld().dropItemNaturally(player.getLocation(), i));
      }
    });
  }

  private void updateInventories() {
    InvUtils.leftSlots.forEach(slot -> {
      if (getExtra(slot) == null) {
        ItemStack item1 = inv1.getItem(slot);
        if (isBlocked(item1)) {
          Sounds.villagerHit(player1, 1);
          inv1.setItem(slot, null);
          player1.getInventory().addItem(item1).values().stream().findFirst().ifPresent(i ->
                  player1.getWorld().dropItemNaturally(player1.getLocation(), i));
        } else {
          inv2.setItem(getRight(slot), item1);
          spectatorInv.setItem(slot, item1);
        }
        ItemStack item2 = inv2.getItem(slot);
        if (isBlocked(item2)) {
          Sounds.villagerHit(player2, 1);
          inv2.setItem(slot, null);
          player2.getInventory().addItem(item2).values().stream().findFirst().ifPresent(i ->
                  player2.getWorld().dropItemNaturally(player2.getLocation(), i));
        } else {
          inv1.setItem(getRight(slot), item2);
          spectatorInv.setItem(getRight(slot), item2);
        }
      }
    });
    player1.updateInventory();
    player2.updateInventory();
  }

  private void updateExtras() {
    int slot1 = 0, slot2a = 0, slot2b = 0;
    for (int i = 0; i < extraSlots.size(); i++) {
      if (i >= extras.size()) break;
      int slot = extraSlots.get(i);
      inv1.setItem(slot, InvUtils.placeHolder);
      inv1.setItem(getRight(slot), InvUtils.placeHolder);
      inv2.setItem(slot, InvUtils.placeHolder);
      inv2.setItem(getRight(slot), InvUtils.placeHolder);
    }
    for (Extra extra : extras) {
      inv1.setItem(extraSlots.get(slot1), extra.getIcon(player1));
      inv2.setItem(extraSlots.get(slot1), extra.getIcon(player2));
      slot1++;
      if (extra.value1 > 0) {
        inv2.setItem(getRight(extraSlots.get(slot2a)), extra.getTheirIcon(player1));
        spectatorInv.setItem(extraSlots.get(slot2a), extra.getTheirIcon(player1));
        slot2a++;
      }
      if (extra.value2 > 0) {
        inv1.setItem(getRight(extraSlots.get(slot2b)), extra.getTheirIcon(player2));
        spectatorInv.setItem(getRight(extraSlots.get(slot2b)), extra.getTheirIcon(player2));
        slot2b++;
      }
    }
    player1.updateInventory();
    player2.updateInventory();
  }

  private void updateAcceptance() {
    if (pl.getConfig().getBoolean("gui.showaccept", true)) {
      inv1.setItem(0, accept1 ? InvUtils.cancelTrade : InvUtils.acceptTrade);
      inv1.setItem(8, accept2 ? InvUtils.theyAccepted : InvUtils.theyCancelled);
      inv2.setItem(0, accept2 ? InvUtils.cancelTrade : InvUtils.acceptTrade);
      inv2.setItem(8, accept1 ? InvUtils.theyAccepted : InvUtils.theyCancelled);
      spectatorInv.setItem(4, accept1 && accept2 ? InvUtils.theyAccepted : InvUtils.theyCancelled);
    }
  }

  private void checkAcceptance() {
    if (pl.getConfig().getBoolean("gui.showaccept", true)) {
      if (accept1 && accept2) {
        if (task != null) return;

        giveOnCursor(player1);
        giveOnCursor(player2);

        accepted1 = new ItemStack[InvUtils.leftSlots.size()];
        for (int i = 0; i < accepted1.length; i++) {
          accepted1[i] = inv1.getItem(InvUtils.leftSlots.get(i));
        }

        accepted2 = new ItemStack[InvUtils.leftSlots.size()];
        for (int i = 0; i < accepted2.length; i++) {
          accepted2[i] = inv2.getItem(InvUtils.leftSlots.get(i));
        }

        if (pl.getConfig().getBoolean("soundeffects.enabled", true) && pl.getConfig().getBoolean("soundeffects.onaccept", true)) {
          Sounds.pling(player1, 1);
          Sounds.pling(player2, 1);
          spectatorInv.getViewers().stream().filter(h -> h instanceof Player).forEach(p ->
                  Sounds.pling((Player) p, 1));
        }

        task = Bukkit.getScheduler().runTaskTimer(pl, () -> {
          int current = inv1.getItem(0).getAmount();
          if (current > 1) {
            inv1.getItem(0).setAmount(current - 1);
            inv1.getItem(8).setAmount(current - 1);
            inv2.getItem(0).setAmount(current - 1);
            inv2.getItem(8).setAmount(current - 1);
            spectatorInv.getItem(4).setAmount(current - 1);
            if (pl.getConfig().getBoolean("soundeffects.enabled", true) && pl.getConfig().getBoolean("soundeffects.oncountdown")) {
              Sounds.click(player1, 2);
              Sounds.click(player2, 2);
              spectatorInv.getViewers().stream().filter(h -> h instanceof Player).forEach(p ->
                      Sounds.click((Player) p, 2));
            }
          } else {
            if (task != null) {
              pl.ongoingTrades.remove(this);
              task.cancel();
              task = null;
              inv1.setItem(49, null);
              inv2.setItem(49, null);
              player1.closeInventory();
              player2.closeInventory();

              HandlerList.unregisterAll(this);

              if (pl.getConfig().getBoolean("antiscam.discrepancy-detection", true)) {
                for (int i = 0; i < InvUtils.leftSlots.size(); i++) {
                  int slot = InvUtils.leftSlots.get(i);
                  ItemStack item1 = inv1.getItem(slot);
                  ItemStack item2 = inv2.getItem(slot);
                  if (!(
                          ((item1 == null && accepted1[i] == null) || (item1 != null && accepted1[i] != null && item1.isSimilar(accepted1[i])))
                          && ((item2 == null && accepted2[i] == null) || (item2 != null && accepted2[i] != null && item2.isSimilar(accepted2[i])))
                  )) {
                    MsgUtils.send(player1, pl.getLang().getString("antiscam.discrepancy").replace("%PLAYER%", player2.getName()).split("%NEWLINE%"));
                    MsgUtils.send(player2, pl.getLang().getString("antiscam.discrepancy").replace("%PLAYER%", player1.getName()).split("%NEWLINE%"));
                    giveItemsOnLeft(inv1, player1);
                    giveItemsOnLeft(inv2, player2);
                    return;
                  }
                }
              }

              giveItemsOnLeft(inv1, player2);
              giveItemsOnLeft(inv2, player1);
              for (Extra extra : extras)
                extra.onTradeEnd();

              if (pl.getConfig().getBoolean("soundeffects.enabled", true) && pl.getConfig().getBoolean("soundeffects.oncomplete")) {
                Sounds.levelUp(player1, 1);
                Sounds.levelUp(player2, 1);
                List<Player> viewers = spectatorInv.getViewers().stream().filter(h -> h instanceof Player).map(h -> (Player) h).collect(Collectors.toList());
                viewers.forEach(p -> {
                  Sounds.levelUp(p, 1);
                  p.closeInventory();
                });
              }

              MsgUtils.send(player1, pl.getLang().getString("trade-complete"));
              MsgUtils.send(player2, pl.getLang().getString("trade-complete"));
            } else {
              updateAcceptance();
            }
          }
        }, 20L, 20L);
      } else {
        if (task != null) {
          task.cancel();
          accept1 = false;
          accept2 = false;
          task = null;
          if (pl.getConfig().getBoolean("soundeffects.enabled", true) && pl.getConfig().getBoolean("soundeffects.onchange")) {
            Sounds.click(player1, 2);
            Sounds.click(player2, 2);
            spectatorInv.getViewers().stream().filter(h -> h instanceof Player).forEach(p ->
                    Sounds.click((Player) p, 2));
          }
        }
      }
    }
  }

  private Extra getExtra(int slot) {
    ItemStack icon = inv1.getItem(slot);
    for (Extra extra : extras) {
      if (icon != null && icon.getType().equals(extra.icon.getType())) {
        return extra;
      }
    }
    return null;
  }

  private int getRight(int slot) {
    return roundUpToNine(slot) - 1 - Integer.remainderUnsigned(slot, 9);
  }

  private int roundUpToNine(int slot) {
    if (slot < 9) return 9;
    if (slot < 18) return 18;
    if (slot < 27) return 27;
    if (slot < 36) return 36;
    if (slot < 45) return 45;
    return 54;
  }

  private ItemStack putOnLeft(Inventory inv, ItemStack item) {
    for (int slot : InvUtils.leftSlots) {
      ItemStack i = inv.getItem(slot);
      if (i != null && i.isSimilar(item) && i.getAmount() < i.getType().getMaxStackSize()) {
        while (i.getAmount() < i.getType().getMaxStackSize() && item.getAmount() > 0) {
          i.setAmount(i.getAmount() + 1);
          item.setAmount(item.getAmount() - 1);
        }
        if (item.getAmount() <= 0) {
          return null;
        }
      }
    }
    for (int slot : InvUtils.leftSlots) {
      ItemStack i = inv.getItem(slot);
      if (!(i == null || i.getType().equals(Material.AIR))) continue;
      inv.setItem(slot, item);
      item = null;
    }
    return item;
  }

  private boolean isBlocked(ItemStack item) {
    if (item == null || item.getType() == null || item.getType().equals(Material.AIR)) return false;
    if (pl.getConfig().getBoolean("blocked.named-items") && item.hasItemMeta() && item.getItemMeta().hasDisplayName())
      return true;
    if (item.hasItemMeta()) {
      String regex = ChatColor.translateAlternateColorCodes('&', pl.getConfig().getString("blocked.regex", ""));
      if (!regex.isEmpty()) {
        try {
          Pattern pattern = Pattern.compile(regex);
          if (item.getItemMeta().hasDisplayName()) {
            String displayName = item.getItemMeta().getDisplayName();
            if (pattern.matcher(displayName).find()) return true;
          }
          if (item.getItemMeta().hasLore()) {
            List<String> lore = item.getItemMeta().getLore();
            if (lore.stream().anyMatch(s -> pattern.matcher(s).find())) return true;
          }
        } catch (PatternSyntaxException ex) {
          Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "Your blocked.regex is invalid!");
        }
      }
      List<String> blockedLore = pl.getConfig().getStringList("blocked.lore");
      if (blockedLore != null) {
        for (int i = 0; i < blockedLore.size(); i++) {
          String line = ChatColor.translateAlternateColorCodes('&', blockedLore.get(i));
          if (line.length() > 2) line = line.substring(1, line.length() - 1);
          blockedLore.set(i, line);
        }
        if (item.getItemMeta().hasDisplayName()) {
          String displayName = item.getItemMeta().getDisplayName();
          if (blockedLore.stream().anyMatch(displayName::contains)) return true;
        }
        if (item.getItemMeta().hasLore()) {
          List<String> lore = item.getItemMeta().getLore();
          for (String blocked : blockedLore) {
            for (String line : lore) {
              if (line.contains(blocked)) {
                return true;
              }
            }
          }
        }
      }
    }
    List<String> blocked = pl.getConfig().getStringList("blocked.blacklist");
    if (blocked == null) return false;
    List<String> checks = new ArrayList<>();
    String type = item.getType().toString();
    byte data = item.getData().getData();
    checks.add(type + ":" + data);
    checks.add(type.replace("_", "") + ":" + data);
    checks.add(type.replace("_", " ") + ":" + data);
    checks.add(item.getType().getId() + ":" + data);
    checks.add(type);
    checks.add(type.replace("_", ""));
    checks.add(type.replace("_", " "));
    checks.add(Integer.toString(item.getType().getId()));
    for (String block : blocked) {
      for (String check : checks) {
        if (block.equalsIgnoreCase(check)) {
          return true;
        }
      }
    }
    return false;
  }

}
