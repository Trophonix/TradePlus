package com.trophonix.tradeplus.util;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.SkullType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ItemFactory {

    private Material material;
    private int amount = 1;
    private final short damage = 0;
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
            try {
                this.material = Material.getMaterial(Integer.parseInt(split[0]));
                material.getData();
            } catch (Exception ex) {
                this.material = Material.getMaterial(split[0]);
            }
            try {
                this.data = Byte.parseByte(split[1]);
            } catch (Exception ignored) {}
        } else {
            try {
                this.material = Material.getMaterial(Integer.parseInt(parsable));
                material.getData();
            } catch (Exception ex) {
                this.material = Material.getMaterial(parsable);
            }
        }
        if (this.material == null)
            this.material = fallback;
    }

    public ItemFactory(String parsable) {
        parsable = parsable.toUpperCase().replace(" ", "_");
        if (parsable != null) {
            if (parsable.contains(":")) {
                String[] split = parsable.split(":");
                try {
                    this.material = Material.getMaterial(Integer.parseInt(split[0]));
                } catch (Exception ex) {
                    this.material = Material.getMaterial(split[0]);
                }
                try {
                    this.data = Byte.parseByte(split[1]);
                } catch (Exception ignored) {}
            } else {
                try {
                    this.material = Material.getMaterial(Integer.parseInt(parsable));
                } catch (Exception ex) {
                    this.material = Material.getMaterial(parsable);
                }
            }
        }
    }

    public ItemStack build() {
        ItemStack itemStack = new ItemStack(material, amount, damage, data);
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

    /*public ItemFactory damage(short damage) {
        this.damage = damage;
        return this;
    }

    public ItemFactory data(byte data) {
        this.data = data;
        return this;
    }*/

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

    public static ItemStack getPlayerSkull(String name, String displayName) {
        ItemStack skull = new ItemStack(Material.SKULL_ITEM, 1, (short) SkullType.PLAYER.ordinal());
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

}
