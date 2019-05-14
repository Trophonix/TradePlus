package com.trophonix.tradeplus.util;

import com.google.common.base.Preconditions;
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
    Preconditions.checkNotNull(parsable, "Material cannot be null.");
    parsable = parsable.toUpperCase().replace(" ", "_");
    UMaterial uMat = UMaterial.match(parsable);
    if (uMat == null) {
      this.material = fallback;
      TradePlus.getPlugin(TradePlus.class).getLogger().warning("Unknown material [" + parsable + "]." + (Sounds.version >= 113 ? " Make sure you've updated to the new 1.13 standard. Numerical item IDs are no longer supported. Using fallback: " + fallback.name() : ""));
    } else {
      this.material = uMat.getMaterial();
      this.data = uMat.getData();
    }
  }

  public ItemFactory(String parsable) {
    Preconditions.checkNotNull(parsable, "Material cannot be null.");
    UMaterial uMat = UMaterial.match(parsable);
    Preconditions.checkNotNull(uMat, "Unknown material [%s]", parsable);
    Preconditions.checkArgument(uMat.getMaterial() != null, "Unknown material [%s]. Make sure item exists in your version!", parsable);
    this.material = uMat.getMaterial();
    this.data = uMat.getData();
  }

  static ItemStack getPlayerSkull(String name, String displayName) {
    ItemStack skull = UMaterial.PLAYER_HEAD_ITEM.getItemStack();
    Preconditions.checkNotNull(skull, "Failed to load skull.");
    skull.getData().setData((byte)3);
    SkullMeta meta = (SkullMeta) skull.getItemMeta();
    meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', displayName));
    meta.setOwner(name);
    skull.setItemMeta(meta);
    return skull;
  }

  public static ItemStack replaceInMeta(ItemStack item, String... replace) {
    item = item.clone();
    if (!item.hasItemMeta()) return item;
    ItemMeta meta = item.getItemMeta();
    if (meta != null) {
      try {
        for (int i = 0; i < replace.length; i += 2) {
          String toReplace = replace[i];
          String replaceWith = replace[i + 1];
          if (meta.hasDisplayName()) {
            meta.setDisplayName(meta.getDisplayName().replace(toReplace, replaceWith));
          }
          if (meta.hasLore()) {
            List<String> lore = meta.getLore();
            for (int j = 0; j < lore.size(); j++)
              lore.set(j, lore.get(j).replace(toReplace, replaceWith));
            meta.setLore(lore);
          }
        }
      } catch (Exception ignored) { }
    }
    item.setItemMeta(meta);
    return item;
  }

  public ItemStack build() {
    ItemStack itemStack;
    if (Sounds.version < 113 && data != (byte)0) {
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
