package com.trophonix.tradeplus.util;

import com.trophonix.tradeplus.TradePlus;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ItemFactory {

  private short damage = 0;
  private Material material;
  private int amount = 1;
  private byte data = 0;
  private String display = "";
  private List<String> lore = new ArrayList<>();

    /*public ItemFactory(Material material) {
        this.material = material;
    }*/

  public ItemFactory(String parsable, Material fallback) {
    parsable = parsable.toUpperCase().replace(" ", "_");
    if (parsable.contains(":")) {
      String[] split = parsable.split(":");
      this.material = Material.getMaterial(split[0]);
      try {
        this.data = Byte.parseByte(split[1]);
      } catch (Exception ignored) {
      }
    } else {
      this.material = Material.getMaterial(parsable);
    }
    if (this.material == null) {
      this.material = fallback;
      TradePlus.getPlugin(TradePlus.class).getLogger().warning("Unknown material [" + parsable + "]." + (Sounds.version >= 113 ? " Make sure you've updated to the new 1.13 standard. Numerical item IDs are no longer supported. Using fallback: " + fallback.name() : ""));
    }
  }

  public ItemFactory(String parsable) {
    if (parsable != null) {
      parsable = parsable.toUpperCase().replace(" ", "_");
      if (parsable.contains(":")) {
        String[] split = parsable.split(":");
        this.material = Material.getMaterial(split[0]);
        try {
          this.data = Byte.parseByte(split[1]);
        } catch (Exception ignored) {
        }
      } else {
        this.material = Material.getMaterial(parsable);
        if (material == null) {
          material = Material.GLASS_PANE;
          TradePlus.getPlugin(TradePlus.class).getLogger().warning("Unknown material [" + parsable + "]." + (Sounds.version >= 113 ? " Make sure you've updated to the new 1.13 standard. Numerical item IDs are no longer supported. Using fallback: glass_pane" : ""));
        }
      }
    } else {
      throw new IllegalArgumentException("parsable can't be null");
    }
  }

  public static ItemStack getPlayerSkull(String name, String displayName) {
    ItemStack skull = new ItemStack(Sounds.version < 113 ? Material.getMaterial("SKULL_ITEM") : Material.getMaterial("PLAYER_HEAD"));
    skull.getData().setData((byte)3);
    SkullMeta meta = (SkullMeta) skull.getItemMeta();
    meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', displayName));
    meta.setOwner(name);
    skull.setItemMeta(meta);
    return skull;
  }

  public static ItemStack replaceInMeta(ItemStack item, String... replace) {
    item = item.clone();
    ItemMeta meta = item.getItemMeta();
    for (int i = 0; i < replace.length; i += 2) {
      if (replace.length > i) {
        String toReplace = replace[i];
        String replaceWith = replace[i + 1];
        meta.setDisplayName(meta.getDisplayName().replace(toReplace, replaceWith));
        List<String> lore = meta.hasLore() ? meta.getLore() : Collections.emptyList();
        for (int j = 0; j < lore.size(); j++)
          lore.set(j, lore.get(j).replace(toReplace, replaceWith));
        meta.setLore(lore);
      }
    }
    item.setItemMeta(meta);
    return item;
  }

  public ItemStack build() {
    ItemStack itemStack;
    if (data != (byte)0) {
      itemStack = new ItemStack(material, amount, damage, data);
    } else {
      itemStack = new ItemStack(material, amount, damage);
    }
    ItemMeta itemMeta = itemStack.getItemMeta();
    if (!display.equals(""))
      itemMeta.setDisplayName(display);
    if (!lore.isEmpty())
      itemMeta.setLore(lore);
    itemStack.setItemMeta(itemMeta);
    return itemStack;
  }

  public ItemFactory amount(int amount) {
    this.amount = amount;
    return this;
  }

  public ItemFactory display(String display) {
    this.display = display;
    return this;
  }

  public ItemFactory display(char colorChar, String display) {
    this.display = ChatColor.translateAlternateColorCodes(colorChar, display);
    return this;
  }

  public ItemFactory lore(List<String> lore) {
    this.lore = lore;
    return this;
  }

  public ItemFactory lore(char colorChar, List<String> lore) {
    for (int i = 0; i < lore.size(); i++)
      lore.set(i, ChatColor.translateAlternateColorCodes(colorChar, lore.get(i)));
    this.lore = lore;
    return this;
  }

}
