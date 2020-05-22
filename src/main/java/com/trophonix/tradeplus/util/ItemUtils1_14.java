package com.trophonix.tradeplus.util;

import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ItemUtils1_14 {

  public static int getCustomModelData(ItemStack stack) {
    if (!stack.hasItemMeta()) return 0;
    return stack.getItemMeta().getCustomModelData();
  }

  public static void applyCustomModelData(ItemMeta meta, int customModelData) {
    meta.setCustomModelData(customModelData);
  }

  public static String getName(Enchantment enchantment) {
    return enchantment.getKey().getKey();
  }

  public static Enchantment getEnchantment(String key) {
    return Enchantment.getByKey(NamespacedKey.minecraft(key));
  }

}
