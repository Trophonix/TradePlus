package com.trophonix.tradeplus.util;

import com.trophonix.tradeplus.TradePlus;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.PrepareAnvilEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;

import static com.trophonix.tradeplus.util.NMSManager.*;

/**
 * Created by chasechocolate
 *
 * <p>Edited to use NMS by PhilipsNostrum
 *
 * <p>Cleaned up, Content upgrade & Edited to use in V1_14 by Gecolay
 */
public class AnvilGUI {

  private Plugin plugin = TradePlus.getPlugin(TradePlus.class);

  private boolean colorrename = true;

  private Player player;

  private char colorchar = '&';

  private String title = "";

  private String defaulttext = "";

  private Inventory inventory;

  private HashMap<AnvilSlot, ItemStack> items = new HashMap<>();

  private Listener listener;

  private AnvilClickEventHandler handler;
  private Procedure onClose;

  private Class<?> BlockPosition;
  private Class<?> PacketPlayOutOpenWindow;
  private Class<?> ContainerAnvil;
  private Class<?> ChatMessage;
  private Class<?> EntityHuman;
  private Class<?> ContainerAccess;
  private Class<?> Containers;

  private boolean useNewVersion = useNewVersion();

  public AnvilGUI(Player Player, final AnvilClickEventHandler Handler, Procedure Finally) {
    loadClasses();
    player = Player;
    handler = Handler;
    onClose = Finally;
    listener = new Listener() {
          @EventHandler
          public void ICE(InventoryClickEvent e) {
            if (e.getInventory().equals(inventory)) {
              e.setCancelled(true);
              if (e.getClick() != ClickType.LEFT && e.getClick() != ClickType.RIGHT) {
                return;
              }
              ItemStack IS = e.getCurrentItem();
              int S = e.getRawSlot();
              String T = null;
              if (IS != null && IS.hasItemMeta()) {
                ItemMeta M = IS.getItemMeta();
                assert M != null;
                if (M.hasDisplayName()) {
                  T = M.getDisplayName();
                }
              }
              AnvilClickEvent ACE = new AnvilClickEvent(AnvilSlot.bySlot(S), IS, T);
              handler.onAnvilClick(ACE);
              if (ACE.getWillClose()) {
                e.getWhoClicked().closeInventory();
              }
            }
          }

          @EventHandler
          public void PAE(PrepareAnvilEvent e) {
            ItemStack IS = e.getResult();
            if (colorrename && IS != null && IS.hasItemMeta()) {
              ItemMeta M = IS.getItemMeta();
              assert M != null;
              if (M.hasDisplayName()) {
                M.setDisplayName(
                    ChatColor.translateAlternateColorCodes(colorchar, M.getDisplayName()));
              }
              IS.setItemMeta(M);
              e.setResult(IS);
            }
          }

          @EventHandler
          public void ICE(InventoryCloseEvent e) {
            if (e.getInventory().equals(inventory)) {
              player.setLevel(player.getLevel() - 1);
              inventory.clear();
              HandlerList.unregisterAll(listener);
              Bukkit.getScheduler().runTaskLater(plugin, () -> onClose.invoke(), 1L);
            }
          }

          @EventHandler
          public void PQE(PlayerQuitEvent e) {
            if (e.getPlayer().equals(player)) {
              player.setLevel(player.getLevel() - 1);
              HandlerList.unregisterAll(listener);
            }
          }
        };

    Bukkit.getPluginManager().registerEvents(listener, plugin);
  }

  private void loadClasses() {
    BlockPosition = getNMSClass("BlockPosition");
    PacketPlayOutOpenWindow = getNMSClass("PacketPlayOutOpenWindow");
    ContainerAnvil = getNMSClass("ContainerAnvil");
    ChatMessage = getNMSClass("ChatMessage");
    EntityHuman = getNMSClass("EntityHuman");
    if (useNewVersion) {
      ContainerAccess = getNMSClass("ContainerAccess");
      Containers = getNMSClass("Containers");
    }
  }

  public Player getPlayer() {
    return player;
  }

  public void setTitle(String Title) {
    title = Title;
  }

  public void setDefaultText(String DefaultText) {
    defaulttext = DefaultText;
  }

  public ItemStack getSlot(AnvilSlot Slot) {
    return items.get(Slot);
  }

  public void setSlot(AnvilSlot Slot, ItemStack Item) {
    items.put(Slot, Item);
    if (inventory != null) inventory.setItem(Slot.slot, Item);
  }

  public void setSlotName(AnvilSlot Slot, String Name) {
    ItemStack IS = getSlot(Slot);
    if (IS != null) {
      ItemMeta M = IS.getItemMeta();
      M.setDisplayName(
          Name != null ? ChatColor.translateAlternateColorCodes(colorchar, Name) : null);
      IS.setItemMeta(M);
      setSlot(Slot, IS);
    }
  }

  public void open() {
    open(title);
  }

  public void open(String Title) {
    player.setLevel(player.getLevel() + 1);
    try {
      Object P = getHandle(player);
      Constructor<?> CM = ChatMessage.getConstructor(String.class, Object[].class);
      if (useNewVersion) {
        Method CAM =
            getMethod(
                "at", ContainerAccess, getNMSClass("World"), BlockPosition);
        Object CA =
            ContainerAnvil.getConstructor(
                    int.class, getNMSClass("PlayerInventory"), ContainerAccess)
                .newInstance(
                    9,
                    getPlayerField(player, "inventory"),
                    CAM.invoke(
                        ContainerAccess,
                        getPlayerField(player, "world"),
                        BlockPosition.getConstructor(int.class, int.class, int.class)
                            .newInstance(0, 0, 0)));
        getField(getNMSClass("Container"), "checkReachable").set(CA, false);

        inventory =
            (Inventory)
                invokeMethod(
                    "getTopInventory", invokeMethod("getBukkitView", CA));

        for (AnvilSlot AS : items.keySet()) {
          inventory.setItem(AS.getSlot(), items.get(AS));
        }

        int ID = (Integer) invokeMethod("nextContainerCounter", P);

        Object PC = getPlayerField(player, "playerConnection");
        Object PPOOW =
            PacketPlayOutOpenWindow.getConstructor(
                    int.class, Containers, getNMSClass("IChatBaseComponent"))
                .newInstance(
                    ID,
                    getField(Containers, "ANVIL").get(Containers),
                    CM.newInstance(
                        ChatColor.translateAlternateColorCodes(colorchar, Title), new Object[] {}));

        Method SP = getMethod("sendPacket", PC.getClass(), PacketPlayOutOpenWindow);
        SP.invoke(PC, PPOOW);
        Field AC = getField(EntityHuman, "activeContainer");
        if (AC != null) {
          AC.set(P, CA);
          getField(getNMSClass("Container"), "windowId").set(AC.get(P), ID);

          getMethod("addSlotListener", AC.get(P).getClass(), P.getClass())
              .invoke(AC.get(P), P);
        }

      } else {
        Object CA =
            ContainerAnvil.getConstructor(
                    getNMSClass("PlayerInventory"),
                    getNMSClass("World"),
                    BlockPosition,
                    EntityHuman)
                .newInstance(
                    getPlayerField(player, "inventory"),
                    getPlayerField(player, "world"),
                    BlockPosition.getConstructor(int.class, int.class, int.class)
                        .newInstance(0, 0, 0),
                    P);
        getField(getNMSClass("Container"), "checkReachable").set(CA, false);

        inventory =
            (Inventory)
                invokeMethod(
                    "getTopInventory", invokeMethod("getBukkitView", CA));

        for (AnvilSlot AS : items.keySet()) {
          inventory.setItem(AS.getSlot(), items.get(AS));
        }

        int ID = (Integer) invokeMethod("nextContainerCounter", P);

        Object PC = getPlayerField(player, "playerConnection");
        Object PPOOW =
            PacketPlayOutOpenWindow.getConstructor(
                    int.class,
                    String.class,
                    getNMSClass("IChatBaseComponent"),
                    int.class)
                .newInstance(
                    ID,
                    "minecraft:anvil",
                    CM.newInstance(
                        ChatColor.translateAlternateColorCodes(colorchar, Title), new Object[] {}),
                    0);

        Method SP = getMethod("sendPacket", PC.getClass(), PacketPlayOutOpenWindow);
        SP.invoke(PC, PPOOW);

        Field AC = getField(EntityHuman, "activeContainer");

        if (AC != null) {

          AC.set(P, CA);

          getField(getNMSClass("Container"), "windowId").set(AC.get(P), ID);

          getMethod("addSlotListener", AC.get(P).getClass(), P.getClass())
              .invoke(AC.get(P), P);
        }
      }

    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public enum AnvilSlot {
    INPUT_LEFT(0),
    INPUT_RIGHT(1),
    OUTPUT(2);

    private int slot;

    private AnvilSlot(int Slot) {
      slot = Slot;
    }

    public static AnvilSlot bySlot(int Slot) {

      for (AnvilSlot AS : values()) {
        if (AS.getSlot() == Slot) {
          return AS;
        }
      }

      return null;
    }

    public int getSlot() {
      return slot;
    }
  }

  public interface AnvilClickEventHandler {

    void onAnvilClick(AnvilClickEvent event);
  }

  public class AnvilClickEvent {

    private AnvilSlot slot;
    private ItemStack item;
    private String text;
    private boolean close = false;
    private boolean destroy = false;

    public AnvilClickEvent(AnvilSlot Slot, ItemStack Item, String Text) {

      slot = Slot;
      item = Item;
      text = Text;
    }

    public AnvilSlot getSlot() {
      return slot;
    }

    public ItemStack getItemStack() {
      return item;
    }

    public void setItemStack(ItemStack Item) {
      item = Item;
    }

    public boolean hasText() {
      return text != null;
    }

    public String getText() {
      return text != null ? text : defaulttext;
    }

    public boolean getWillClose() {
      return close;
    }

    public void setWillClose(boolean Close) {
      close = Close;
    }
  }
}
