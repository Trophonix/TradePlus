package com.trophonix.tradeplus.util;

import com.google.common.base.Preconditions;
import com.trophonix.tradeplus.TradePlus;
import lombok.Getter;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ItemFactory {

  private short damage = 0;
  private Material material;
  @Getter private int amount = 1;
  private byte data = 0;
  private String display;
  private List<String> lore;
  private List flags;

  public ItemFactory(Material material) {
        this.material = material;
    }

  public ItemFactory(String parsable, Material fallback) {
    Preconditions.checkNotNull(parsable, "Material cannot be null.");
    parsable = parsable.toUpperCase().replace(" ", "_");
    UMaterial uMat = UMaterial.match(parsable);
    if (uMat == null) {
      material = fallback;
      TradePlus.getPlugin(TradePlus.class).getLogger().warning("Unknown material [" + parsable + "]." + (Sounds.version >= 113 ? " Make sure you've updated to the new 1.13 standard. Numerical item IDs are no longer supported. Using fallback: " + fallback.name() : ""));
    } else {
      material = uMat.getMaterial();
      data = uMat.getData();
    }
  }

  public ItemFactory(String parsable) {
    Preconditions.checkNotNull(parsable, "Material cannot be null.");
    UMaterial uMat = UMaterial.match(parsable.toUpperCase().replace(" ", "_"));
    Preconditions.checkNotNull(uMat, "Unknown material [%s]", parsable);
    Preconditions.checkArgument(uMat.getMaterial() != null, "Unknown material [%s]. Make sure item exists in your version!", parsable);
    material = uMat.getMaterial();
    data = uMat.getData();
  }

  public ItemFactory(ItemStack stack) {
    damage = stack.getDurability();
    material = stack.getType();
    amount = stack.getAmount();
    data = stack.getData().getData();
    if (stack.hasItemMeta()) {
      ItemMeta meta = stack.getItemMeta();
      if (meta.hasDisplayName()) {
        display = meta.getDisplayName();
      }
      if (meta.hasLore()) {
        lore = meta.getLore();
      }
      if (Sounds.version != 17) flags = new ArrayList<>(meta.getItemFlags());
    }
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
    if (!item.hasItemMeta()) {
      return item;
    }
    ItemMeta meta = item.getItemMeta();
    if (meta != null) {
      try {
        for (int i = 0; i < replace.length - 1; i += 2) {
          String toReplace = replace[i];
          String replaceWith = replace[i + 1];
          if (meta.hasDisplayName()) {
            meta.setDisplayName(meta.getDisplayName().replace(toReplace, replaceWith));
          }
          if (meta.hasLore()) {
            List<String> lore = meta.getLore();
            assert lore != null;
            for (int j = 0; j < lore.size(); j++) {
              lore.set(j, lore.get(j).replace(toReplace, replaceWith));
            }
            meta.setLore(lore);
          }
        }
      } catch (Exception ignored) { }
    }
    item.setItemMeta(meta);
    return item;
  }

  public ItemFactory replace(String... replace) {
    for (int i = 0; i < replace.length - 1; i += 2) {
      display = display.replace(replace[i], replace[i + 1]);
      if (lore != null) {
        int n = i;
        lore = lore.stream().map(str -> str.replace(replace[n], replace[n + 1])).collect(Collectors.toList());
      }
    }
    return this;
  }

  public ItemStack build() {
    ItemStack itemStack;
    if (Sounds.version < 113 && data != (byte)0) {
      itemStack = new ItemStack(material, amount, damage, data);
    } else {
      itemStack = new ItemStack(material, amount, damage);
    }
    ItemMeta itemMeta = itemStack.getItemMeta();
    if (display != null) {
      itemMeta.setDisplayName(display);
    }
    itemMeta.setLore(lore);
    if (flags != null) itemMeta.addItemFlags(((List<org.bukkit.inventory.ItemFlag>)flags).toArray(new org.bukkit.inventory.ItemFlag[0]));
    itemStack.setItemMeta(itemMeta);
    return itemStack;
  }

  public ItemFactory copy() {
    ItemFactory copy = new ItemFactory(material);
    copy.damage = damage;
    copy.amount = amount;
    copy.data = data;
    copy.display = display;
    copy.lore = lore;
    copy.flags = flags;
    return copy;
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
    if (lore == null) {
      return lore(null);
    }
    for (int i = 0; i < lore.size(); i++) {
      lore.set(i, ChatColor.translateAlternateColorCodes(colorChar, lore.get(i)));
    }
    this.lore = lore;
    return this;
  }

  public ItemFactory flag(String flag) {
    if (Sounds.version == 17) return this;
    if (flags == null) flags = new ArrayList<org.bukkit.inventory.ItemFlag>();
    flags.add(org.bukkit.inventory.ItemFlag.valueOf(flag));
    return this;
  }

}
