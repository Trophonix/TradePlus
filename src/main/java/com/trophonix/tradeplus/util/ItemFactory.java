package com.trophonix.tradeplus.util;

import com.google.common.base.Preconditions;
import com.trophonix.tradeplus.TradePlus;
import lombok.Getter;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ItemFactory {

 @Getter
 private ItemStack stack;

  public ItemFactory(Material material) {
    this.stack = new ItemStack(material);
  }

  public ItemFactory(String parsable, Material fallback) {
    if (parsable == null) {
      this.stack = new ItemStack(fallback);
    } else {
      byte data = -1;
      if (parsable.contains(":")) {
        String[] split = parsable.split(":");
        data = Byte.parseByte(split[1]);
        parsable = split[0];
      }
      parsable = parsable.toUpperCase().replace(" ", "_");

      Material mat = Material.getMaterial(parsable);
      if (mat == null) {
        mat = fallback;
        TradePlus.getPlugin(TradePlus.class)
            .getLogger()
            .warning(
                "Unknown material ["
                    + parsable
                    + "]."
                    + (Sounds.version >= 113
                        ? " Make sure you've updated to the new 1.13 standard. Numerical item IDs are no longer supported. Using fallback: "
                            + fallback.name()
                        : ""));
      }

      if (data > 0) {
        this.stack = new ItemStack(mat, 1, data, data);
      } else {
        this.stack = new ItemStack(mat);
      }
    }
  }

  public ItemFactory damage(short damage) {
    stack.setDurability(damage);
    return this;
  }

  public ItemFactory(String parsable) {
    this(parsable, Material.PAPER);
    //    Preconditions.checkNotNull(parsable, "Material cannot be null.");
    //    byte data = -1;
    //    if (parsable.contains(":")) {
    //      String[] split = parsable.split(":");
    //      data = Byte.parseByte(split[1]);
    //      parsable = split[0];
    //    }
    //    parsable = parsable.toUpperCase().replace(" ", "_");
    //    Material mat = Material.getMaterial(parsable);
    //    this.material = Preconditions.checkNotNull(mat, "Unknown material [%s]", parsable);
    //    ;
    //    this.data = data;
  }

  public ItemFactory(ItemStack stack) {
    this.stack = stack.clone();
  }

  public ItemFactory(ConfigurationSection yml, String key) {
    this.stack = yml.getItemStack(key);
    if (stack != null && stack.hasItemMeta()) {
      ItemMeta meta = stack.getItemMeta();
      String displayName = null;
      List<String> lore = null;

      if (meta.hasDisplayName()) {
        displayName = MsgUtils.color(meta.getDisplayName());
      }

      if (meta.hasLore()) {
        lore =
            meta.getLore().stream()
                .map(s -> MsgUtils.color(s))
                .collect(Collectors.toList());
      }

      meta.setDisplayName(displayName);
      meta.setLore(lore);

      stack.setItemMeta(meta);
    }
  }

  public ItemFactory save(ConfigurationSection yml, String key) {
    ItemStack stack = this.stack.clone();
    ItemMeta meta = stack.getItemMeta();
    if (meta != null) {
        if (meta.hasDisplayName()) meta.setDisplayName(meta.getDisplayName().replace(ChatColor.COLOR_CHAR, '&'));
        if (meta.hasLore()) meta.setLore(meta.getLore().stream().map(s -> s.replace(ChatColor.COLOR_CHAR, '&')).collect(Collectors.toList()));
    }
    yml.set(key, stack);
    return this;
  }

  static ItemStack getPlayerSkull(Player player, String displayName) {
    ItemStack skull =
        new ItemStack(Material.getMaterial(Sounds.version > 112 ? "PLAYER_HEAD" : "SKULL_ITEM"));
    Preconditions.checkNotNull(skull, "Failed to load skull.");
    if (Sounds.version < 113) skull.getData().setData((byte) 3);
    SkullMeta meta = (SkullMeta) skull.getItemMeta();
    meta.setDisplayName(MsgUtils.color(displayName));
    if (Sounds.version >= 112) meta.setOwningPlayer(player);
    else meta.setOwner(player.getName());
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
      } catch (Exception ignored) {
      }
    }
    item.setItemMeta(meta);
    return item;
  }

  public ItemFactory replace(String... replace) {
    if (stack.hasItemMeta()) {
      ItemMeta meta = stack.getItemMeta();
      String display = meta.getDisplayName();
      List<String> lore = meta.getLore();
      for (int i = 0; i < replace.length - 1; i += 2) {
        if (display != null) display = display.replace(replace[i], replace[i + 1]);
        if (lore != null) {
          int n = i;
          lore =
              lore.stream()
                  .map(str -> str.replace(replace[n], replace[n + 1]))
                  .collect(Collectors.toList());
        }
      }
      meta.setDisplayName(display);
      meta.setLore(lore);
      stack.setItemMeta(meta);
    }
    return this;
  }

  public ItemStack build() {
    return stack.clone();
  }

  public ItemFactory copy() {
    return new ItemFactory(stack);
  }

  public ItemFactory amount(int amount) {
    this.stack.setAmount(amount);
    return this;
  }

  public ItemFactory display(String display) {
    ItemMeta meta = stack.getItemMeta();
    if (display.contains("%NEWLINE%")) {
      String[] split = display.split("%NEWLINE%");
      display = split[0];
      List<String> lore = new ArrayList<>();
      for (int i = 1; i < split.length; i++) {
        lore.add(split[i]);
      }
      this.lore(lore);
    }
    meta.setDisplayName(MsgUtils.color(display));
    stack.setItemMeta(meta);
    return this;
  }

  public ItemFactory display(char colorChar, String display) {
    return display(ChatColor.translateAlternateColorCodes(colorChar, display));
  }

  public ItemFactory lore(List<String> lore) {
    for (int i = 0; i < lore.size(); i++) {
        String line = lore.get(i);
        if (line != null) {
            line = MsgUtils.color(line);
            lore.set(i, line);
        }
    }
    ItemMeta meta = stack.getItemMeta();
    List<String> current = meta.getLore();
    if (current == null) current = new ArrayList<>();
    current.addAll(lore);
    meta.setLore(current);
    stack.setItemMeta(meta);
    return this;
  }

  public ItemFactory lore(char colorChar, List<String> lore) {
    if (lore == null) {
      return this;
    }
    for (int i = 0; i < lore.size(); i++) {
      lore.set(i, ChatColor.translateAlternateColorCodes(colorChar, lore.get(i)));
    }
    return this.lore(lore);
  }

  public ItemFactory flag(String flag) {
    if (Sounds.version == 17) return this;
    ItemMeta meta = stack.getItemMeta();
    meta.addItemFlags(org.bukkit.inventory.ItemFlag.valueOf(flag));
    stack.setItemMeta(meta);
    return this;
  }

  public ItemFactory customModelData(int customModelData) {
    if (Sounds.version < 114) return this;
    ItemMeta meta = stack.getItemMeta();
    ItemUtils1_14.applyCustomModelData(meta, customModelData);
    stack.setItemMeta(meta);
    return this;
  }

  public int getAmount() {
    return stack.getAmount();
  }
}
