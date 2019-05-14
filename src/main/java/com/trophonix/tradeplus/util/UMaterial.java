package com.trophonix.tradeplus.util;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.Potion;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.potion.PotionType;

import java.util.*;

/*
    UMaterial Version: 4

    This software is created and owned by RandomHashTags, and is licensed under the GNU Affero General Public License v3.0 (https://choosealicense.com/licenses/agpl-3.0/)
    You can only find this software at https://gitlab.com/RandomHashTags/umaterial
    You can find RandomHashTags (me) at
        Discord - RandomHashTags#1948
        Email - imrandomhashtags@gmail.com
        GitHub - https://github.com/RandomHashTags
        GitLab - https://gitlab.com/RandomHashTags
        MCMarket - https://www.mc-market.org/members/20858/
        PayPal - imrandomhashtags@gmail.com
        SpigotMC - https://www.spigotmc.org/members/randomhashtags.76364/
        Twitch - https://www.twitch.tv/randomhashtags
        Twitter - https://twitter.com/irandomhashtags
 */
public enum UMaterial {
    /*
        <item>(1.8.8, 1.9.4, 1.10.2, 1.11.2, 1.12.2, 1.13.2, 1.14.0)
        1.8.8 = http://docs.codelanx.com/Bukkit/1.8/org/bukkit/Material.html
        1.13.2 = https://hub.spigotmc.org/javadocs/bukkit/org/bukkit/Material.html
        visual = https://www.digminecraft.com/lists/item_id_list_pc.php
        Special blocks = https://www.planetminecraft.com/blog/secret-blocks-vanilla/
    */
    ACACIA_BOAT("BOAT", null, null, null, "BOAT_ACACIA", "ACACIA_BOAT"),
    ACACIA_BUTTON("WOOD_BUTTON", null, null, null, null, "ACACIA_BUTTON"),
    ACACIA_DOOR("ACACIA_DOOR_ITEM", null, null, null, null, "ACACIA_DOOR"),
    ACACIA_FENCE("ACACIA_FENCE", 0),
    ACACIA_FENCE_GATE("ACACIA_FENCE_GATE", 0),
    ACACIA_LEAVES("LEAVES_2", null, null, null, null, "ACACIA_LEAVES"),
    ACACIA_LOG("LOG_2", null, null, null, null, "ACACIA_LOG"),
    ACACIA_PLANKS(4, "WOOD", null, null, null, null, "ACACIA_PLANKS"),
    ACACIA_PRESSURE_PLATE("WOOD_PLATE", null, null, null, null, "ACACIA_PRESSURE_PLATE"),
    ACACIA_SAPLING(4, "SAPLING", null, null, null, null, "ACACIA_SAPLING"),
    ACACIA_SIGN(null, null, null, null, null, null, "ACACIA_SIGN"),
    ACACIA_SLAB(4, "WOOD_STEP", null, null, null, null, "ACACIA_SLAB"),
    ACACIA_STAIRS("ACACIA_STAIRS"),
    ACACIA_TRAPDOOR("TRAP_DOOR", null, null, null, null, "ACACIA_TRAPDOOR"),
    ACACIA_WALL_SIGN(null, null, null, null, null, null, "ACACIA_WALL_SIGN"),
    ACACIA_WOOD(12, "LOG_2", null, null, null, null, "ACACIA_WOOD"),
    ACTIVATOR_RAIL("ACTIVATOR_RAIL"),
    AIR("AIR"),
    ALLIUM(2, "RED_ROSE", null, null, null, null, "ALLIUM"),
    ANDESITE(5, "STONE", null, null, null, null, "ANDESITE"),
    ANDESITE_SLAB(null, null, null, null, null, null, "ANDESITE_SLAB"),
    ANDESITE_STAIRS(null, null, null, null, null, null, "ANDESITE_STAIRS"),
    ANDESITE_WALL(null, null, null, null, null, null, "ANDESITE_WALL"),
    ANVIL("ANVIL"),
    APPLE("APPLE"),
    ARMOR_STAND("ARMOR_STAND"),
    ARROW("ARROW"),
    ATTACHED_MELON_STEM(null, null, null, null, null, "ATTACHED_MELON_STEM"),
    ATTACHED_PUMPKIN_STEM(null, null, null, null, null, "ATTACHED_PUMPKIN_STEM"),
    AZURE_BLUET(3, "RED_ROSE", null, null, null, null, "AZURE_BLUET"),
    BAMBOO(null, null, null, null, null, null, "BAMBOO"),
    BAKED_POTATO("BAKED_POTATO"),
    BARREL(null, null, null, null, null, null, "BARREL"),
    BARRIER("BARRIER"),
    BAT_SPAWN_EGG(65, "MONSTER_EGG", null, null, null, null, "BAT_SPAWN_EGG"),
    BEACON("BEACON"),
    BEDROCK("BEDROCK"),
    BEEF("RAW_BEEF", null, null, null, null, "BEEF"),
    BEETROOT(null, null, null, null, "BEETROOT"),
    BEETROOT_SEEDS(null, null, null, null, "BEETROOT_SEEDS"),
    BEETROOT_SOUP(null, null, null, null, "BEETROOT_SOUP"),
    BEETROOTS(null, null, null, null, null, "BEETROOTS"),
    BELL(null, null, null, null, null, null, "BELL"),
    BIRCH_BOAT("BOAT", null, null, null, "BOAT_BIRCH", "BIRCH_BOAT"),
    BIRCH_BUTTON("WOOD_BUTTON", null, null, null, null, "BIRCH_BUTTON"),
    BIRCH_DOOR("BIRCH_DOOR_ITEM", null, null, null, null, "BIRCH_DOOR"),
    BIRCH_FENCE("BIRCH_FENCE"),
    BIRCH_FENCE_GATE("BIRCH_FENCE_GATE"),
    BIRCH_LEAVES(2, "LEAVES", null, null, null, null, "BIRCH_LEAVES"),
    BIRCH_LOG(2, "LOG", null, null, null, null, "BIRCH_LOG"),
    BIRCH_PLANKS(2, "WOOD", null, null, null, null, "BIRCH_PLANKS"),
    BIRCH_PRESSURE_PLATE("WOOD_PLATE", null, null, null, null, "BIRCH_PRESSURE_PLATE"),
    BIRCH_SAPLING(2, "SAPLING", null, null, null, null, "BIRCH_SAPLING"),
    BIRCH_SIGN(null, null, null, null, null, null, "BIRCH_SIGN"),
    BIRCH_SLAB(2, "WOOD_STEP", null, null, null, null, "BIRCH_SLAB"),
    BIRCH_STAIRS("BIRCH_WOOD_STAIRS", null, null, null, null, "BIRCH_STAIRS"),
    BIRCH_TRAPDOOR("TRAP_DOOR", null, null, null, null, "BIRCH_TRAPDOOR"),
    BIRCH_WALL_SIGN(null, null, null, null, null, null, "BIRCH_WALL_SIGN"),
    BIRCH_WOOD(14, "LOG", null, null, null, null, "BIRCH_WOOD"),
    BLACK_BANNER("BANNER", null, null, null, null, "BLACK_BANNER"),
    BLACK_BED(15, "BED", null, null, null, null, "BLACK_BED"),
    BLACK_CARPET(15, "CARPET", null, null, null, null, "BLACK_CARPET"),
    BLACK_CONCRETE(15, null, null, null, null, null, "BLACK_CONCRETE"),
    BLACK_CONCRETE_POWDER(15, null, null, null, null, "CONCRETE_POWDER", "BLACK_CONCRETE_POWDER"),
    BLACK_DYE(null, null, null, null, null, null, "BLACK_DYE"),
    BLACK_GLAZED_TERRACOTTA(null, null, null, null, "BLACK_GLAZED_TERRACOTTA"),
    BLACK_LEATHER_BOOTS("LEATHER_BOOTS", 15, Color.fromRGB(25, 25, 25)),
    BLACK_LEATHER_CHESTPLATE("LEATHER_CHESTPLATE", 15, Color.fromRGB(25, 25, 25)),
    BLACK_LEATHER_HELMET("LEATHER_HELMET", 15, Color.fromRGB(25, 25, 25)),
    BLACK_LEATHER_LEGGINGS("LEATHER_LEGGINGS", 15, Color.fromRGB(25, 25, 25)),
    BLACK_SHULKER_BOX(null, null, null, "BLACK_SHULKER_BOX"),
    BLACK_STAINED_GLASS(15, "STAINED_GLASS", null, null, null, null, "BLACK_STAINED_GLASS"),
    BLACK_STAINED_GLASS_PANE(15, "STAINED_GLASS_PANE", null, null, null, null, "BLACK_STAINED_GLASS_PANE"),
    BLACK_TERRACOTTA(15, "STAINED_CLAY", null, null, null, null, "BLACK_TERRACOTTA"),
    BLACK_WOOL(15, "WOOL", null, null, null, null, "BLACK_WOOL"),
    BLAST_FURNACE(null, null, null, null, null, null, "BLAST_FURNACE"),
    BLAZE_POWDER("BLAZE_POWDER"),
    BLAZE_ROD("BLAZE_ROD"),
    BLAZE_SPAWN_EGG(61, "MONSTER_EGG", null, null, null, null, "BLAZE_SPAWN_EGG"),
    BLUE_BANNER(4, "BANNER", null, null, null, null, "BLUE_BANNER"),
    BLUE_BED(11, "BED", null, null, null, null, "BLUE_BED", null),
    BLUE_CARPET(11, "CARPET", null, null, null, null, "BLUE_CARPET"),
    BLUE_CONCRETE(11, null, null, null, null, "CONCRETE", "BLUE_CONCRETE"),
    BLUE_CONCRETE_POWDER(11, null, null, null, null, "CONCRETE_POWDER", "BLUE_CONCRETE_POWDER"),
    BLUE_DYE(null, null, null, null, null, null, "BLUE_DYE"),
    BLUE_GLAZED_TERRACOTTA(null, null, null, null, "BLUE_GLAZED_TERRACOTTA"),
    BLUE_LEATHER_BOOTS("LEATHER_BOOTS", 11, Color.fromRGB(51, 76, 178)),
    BLUE_LEATHER_CHESTPLATE("LEATHER_CHESTPLATE", 11, Color.fromRGB(51, 76, 178)),
    BLUE_LEATHER_HELMET("LEATHER_HELMET", 11, Color.fromRGB(51, 76, 178)),
    BLUE_LEATHER_LEGGINGS("LEATHER_LEGGINGS", 11, Color.fromRGB(51, 76, 178)),
    BLUE_ICE(null, null, null, null, null, "BLUE_ICE"),
    BLUE_ORCHID(1, "RED_ROSE", null, null, null, null, "BLUE_ORCHID"),
    BLUE_SHULKER_BOX(null, null, null, "BLUE_SHULKER_BOX"),
    BLUE_STAINED_GLASS(11, "STAINED_GLASS", null, null, null, null, "BLUE_STAINED_GLASS"),
    BLUE_STAINED_GLASS_PANE(11, "STAINED_GLASS_PANE", null, null, null, null, "BLUE_STAINED_GLASS_PANE"),
    BLUE_TERRACOTTA(11, "STAINED_CLAY", null, null, null, null, "BLUE_TERRACOTTA"),
    BLUE_WALL_BANNER(4, "BANNER", null, null, null, null, "BLACK_WALL_BANNER"),
    BLUE_WOOL(11, "WOOL", null, null, null, null, "BLUE_WOOL"),
    BONE("BONE"),
    BONE_BLOCK(null, null, "BONE_BLOCK"),
    BONE_MEAL(15, "INK_SACK", null, null, null, null, "BONE_MEAL"),
    BOOK("BOOK"),
    BOOKSHELF("BOOKSHELF"),
    BOW("BOW"),
    BOWL("BOWL"),
    BRAIN_CORAL(null, null, null, null, null, "BRAIN_CORAL"),
    BRAIN_CORAL_BLOCK(null, null, null, null, null, "BRAIN_CORAL_BLOCK"),
    BRAIN_CORAL_FAN(null, null, null, null, null, "BRAIN_CORAL_FAN"),
    BRAIN_CORAL_WALL_FAN(null, null, null, null, null, "BRAIN_CORAL_WALL_FAN"),
    BREAD("BREAD"),
    BREWING_STAND("BREWING_STAND"),
    BREWING_STAND_ITEM("BREWING_STAND_ITEM", null, null, null, null, "BREWING_STAND"),
    BRICK("BRICK", null, null, null, "CLAY_BRICK", "BRICK"),
    BRICK_SLAB(4, "STEP", null, null, null, null, "BRICK_SLAB"),
    BRICK_STAIRS("BRICK_STAIRS"),
    BRICK_WALL(null, null, null, null, null, null, "BRICK_WALL"),
    BROWN_BANNER(3, "BANNER", null, null, null, null, "BROWN_BANNER"),
    BROWN_BED(12, "BED", null, null, null, null, "BROWN_BED"),
    BROWN_CARPET(12, "CARPET", null, null, null, null, "BROWN_CARPET"),
    BROWN_CONCRETE(12, null, null, null, null, "CONCRETE", "BROWN_CONCRETE"),
    BROWN_CONCRETE_POWDER(12, null, null, null, null, "CONCRETE_POWDER", "BROWN_CONCRETE_POWDER"),
    BROWN_DYE(null, null, null, null, null, null, "BROWN_DYE"),
    BROWN_GLAZED_TERRACOTTA(null, null, null, null, "BROWN_GLAZED_TERRACOTTA"),
    BROWN_LEATHER_BOOTS("LEATHER_BOOTS", 12, Color.fromRGB(102, 76, 51)),
    BROWN_LEATHER_CHESTPLATE("LEATHER_CHESTPLATE", 12, Color.fromRGB(102, 76, 51)),
    BROWN_LEATHER_HELMET("LEATHER_HELMET", 12, Color.fromRGB(102, 76, 51)),
    BROWN_LEATHER_LEGGINGS("LEATHER_LEGGINGS", 12, Color.fromRGB(102, 76, 51)),
    BROWN_MUSHROOM("BROWN_MUSHROOM"),
    BROWN_MUSHROOM_BLOCK("HUGE_MUSHROOM_1", null, null, null, null, "BROWN_MUSHROOM_BLOCK"),
    BROWN_SHULKER_BOX(null, null, null, "BROWN_SHULKER_BOX"),
    BROWN_STAINED_GLASS(12, "STAINED_GLASS", null, null, null, null, "BROWN_STAINED_GLASS"),
    BROWN_STAINED_GLASS_PANE(12, "STAINED_GLASS_PANE", null, null, null, null, "BROWN_STAINED_GLASS_PANE"),
    BROWN_TERRACOTTA(12, "STAINED_CLAY", null, null, null, null, "BROWN_TERRACOTTA"),
    BROWN_WOOL(12, "WOOL", null, null, null, null, "BROWN_WOOL"),
    BUBBLE_COLUMN(null, null, null, null, null, "BUBBLE_COLUMN"),
    BUBBLE_CORAL(null, null, null, null, null, "BUBBLE_CORAL"),
    BUBBLE_CORAL_BLOCK(null, null, null, null, null, "BUBBLE_CORAL_BLOCK"),
    BUBBLE_CORAL_FAN(null, null, null, null, null, "BUBBLE_CORAL_FAN"),
    BUBBLE_CORAL_WALL_FAN(null, null, null, null, null, "BUBBLE_CORAL_WALL_FAN"),
    BUCKET("BUCKET"),
    BURNING_FURNACE("BURNING_FURNACE", null, null, null, null, "LEGACY_BURNING_FURNACE"),
    CACTUS("CACTUS"),
    CACTUS_GREEN(2, "INK_SACK", null, null, null, null, "CACTUS_GREEN", "GREEN_DYE"),
    CAKE("CAKE"),
    CAKE_BLOCK("CAKE_BLOCK", null, null, null, null, "CAKE"),
    CAMPFIRE(null, null, null, null, null, null, "CAMPFIRE"),
    CARROT("CARROT"),
    CARROT_ITEM("CARROT_ITEM", null, null, null, null, "CARROT"),
    CARROT_ON_A_STICK("CARROT_STICK", null, null, null, null, "CARROT_ON_A_STICK"),
    CARTOGRAPHY_TABLE(null, null, null, null, null, null, "CARTOGRAPHY_TABLE"),
    CARVED_PUMPKIN("PUMPKIN", null, null, null, null, "CARVED_PUMPKIN"),
    CAT_SPAWN_EGG(null, null, null, null, null, null, "CAT_SPAWN_EGG"),
    CAULDRON("CAULDRON"),
    CAULDRON_ITEM("CAULDRON_ITEM", null, null, null, null, "CAULDRON"),
    CAVE_AIR("AIR", null, null, null, null, "CAVE_AIR"),
    CAVE_SPIDER_SPAWN_EGG(59, "MONSTER_EGG", null, null, null, null, "CAVE_SPIDER_SPAWN_EGG"),
    CHAIN_COMMAND_BLOCK("COMMAND", "COMMAND_CHAIN", null, null, null, "CHAIN_COMMAND_BLOCK"),
    CHAINMAIL_BOOTS("CHAINMAIL_BOOTS"),
    CHAINMAIL_CHESTPLATE("CHAINMAIL_CHESTPLATE"),
    CHAINMAIL_HELMET("CHAINMAIL_HELMET"),
    CHAINMAIL_LEGGINGS("CHAINMAIL_LEGGINGS"),
    CHARCOAL(1, "COAL", null, null, null, null, "CHARCOAL"),
    CHEST("CHEST"),
    CHEST_MINECART("STORAGE_MINECART", null, null, null, null, "CHEST_MINECART"),
    CHICKEN("RAW_CHICKEN", null, null, null, null, "CHICKEN"),
    CHICKEN_SPAWN_EGG(93, "MONSTER_EGG", null, null, null, null, "CHICKEN_SPAWN_EGG"),
    CHIPPED_ANVIL(1, "ANVIL", null, null, null, null, "CHIPPED_ANVIL"),
    CHISELED_QUARTZ_BLOCK(1, "QUARTZ_BLOCK", null, null, null, null, "CHISELED_QUARTZ_BLOCK"),
    CHISELED_RED_SANDSTONE(1, "RED_SANDSTONE", null, null, null, null, "CHISELED_RED_SANDSTONE"),
    CHISELED_SANDSTONE(1, "SANDSTONE", null, null, null, null, "CHISELED_SANDSTONE"),
    CHISELED_STONE_BRICKS(3, "SMOOTH_BRICK", null, null, null, null, "CHISELED_STONE_BRICKS"),
    CHORUS_FLOWER(null, "CHORUS_FLOWER"),
    CHORUS_FRUIT(null, "CHORUS_FRUIT"),
    CHORUS_PLANT(null, "CHORUS_PLANT"),
    CLAY("CLAY"),
    CLAY_BALL("CLAY_BALL"),
    CLOCK("WATCH", null, null, null, null, "CLOCK"),
    COAL("COAL"),
    COAL_BLOCK("COAL_BLOCK"),
    COAL_ORE("COAL_ORE"),
    COARSE_DIRT(1, "DIRT", null, null, null, null, "COARSE_DIRT"),
    COBBLESTONE("COBBLESTONE"),
    COBBLESTONE_SLAB(3, "STEP", null, null, null, null, "COBBLESTONE_SLAB"),
    COBBLESTONE_STAIRS("COBBLESTONE_STAIRS"),
    COBBLESTONE_WALL("COBBLE_WALL", null, null, null, null, "COBBLESTONE_WALL"),
    COBWEB("WEB", null, null, null, null, "COBWEB"),
    COCOA("COCOA"),
    COCOA_BEANS(3, "INK_SACK", null, null, null, null, "COCOA_BEANS"),
    COD(2, "RAW_FISH", null, null, null, null, "COD"),
    COMMAND_BLOCK("COMMAND", null, null, null, null, "COMMAND_BLOCK"),
    COMMAND_BLOCK_MINECART("COMMAND_MINECART", null, null, null, null, "COMMAND_BLOCK_MINECART"),
    COMPARATOR("REDSTONE_COMPARATOR", null, null, null, null, "COMPARATOR"),
    COMPASS("COMPASS"),
    COMPOSTER(null, null, null, null, null, null, "COMPOSTER"),
    CONDUIT(null, null, null, null, null, "CONDUIT"),
    COOKED_BEEF("COOKED_BEEF"),
    COOKED_CHICKEN("COOKED_CHICKEN"),
    COOKED_COD("COOKED_FISH", null, null, null, null, "COOKED_COD"),
    COOKED_MUTTON("COOKED_MUTTON"),
    COOKED_PORKCHOP("GRILLED_PORK", null, null, null, null, "COOKED_PORKCHOP"),
    COOKED_RABBIT("COOKED_RABBIT"),
    COOKED_SALMON(1, "COOKED_FISH", null, null, null, null, "COOKED_SALMON"),
    COOKIE("COOKIE"),
    CORNFLOWER(null, null, null, null, null, null, "CORNFLOWER"),
    COW_SPAWN_EGG(92, "MONSTER_EGG", null, null, null, null, "COW_SPAWN_EGG"),
    CRACKED_STONE_BRICKS(2, "SMOOTH_BRICK", null, null, null, null, "CRACKED_STONE_BRICKS"),
    CRAFTING_TABLE("WORKBENCH", null, null, null, null, "CRAFTING_TABLE"),
    CREEPER_HEAD(4, "SKULL_ITEM", null, null, null, null, "CREEPER_HEAD"),
    CREEPER_SPAWN_EGG(50, "MONSTER_EGG", null, null, null, null, "CREEPER_SPAWN_EGG"),
    CREEPER_WALL_HEAD(3, "SKULL", null, null, null, null, "CREEPER_WALL_HEAD"),
    CROSSBOW(null, null, null, null, null, null, "CROSSBOW"),
    CUT_RED_SANDSTONE(2, "RED_SANDSTONE", null, null, null, null, "CUT_RED_SANDSTONE"),
    CUT_RED_SANDSTONE_SLAB(null, null, null, null, null, null, "CUT_RED_SANDSTONE_SLAB"),
    CUT_RED_SANDSTONE_STAIRS(null, null, null, null, null, null, "CUT_RED_SANDSTONE_STAIRS"),
    CUT_SANDSTONE(2, "SANDSTONE", null, null, null, null, "CUT_SANDSTONE"),
    CUT_SANDSTONE_SLAB(null, null, null, null, null, null, "CUT_SANDSTONE_SLAB"),
    CUT_SANDSTONE_STAIRS(null, null, null, null, null, null, "CUT_SANDSTONE_STAIRS"),
    CYAN_BANNER(6, "BANNER", null, null, null, null, "CYAN_BANNER"),
    CYAN_BED(9, "BED", null, null, null, null, "CYAN_BED"),
    CYAN_CARPET(9, "CARPET", null, null, null, null, "CYAN_CARPET"),
    CYAN_CONCRETE(9, null, null, null, null, "CONCRETE", "CYAN_CONCRETE"),
    CYAN_CONCRETE_POWDER(9, null, null, null, null, "CONCRETE_POWDER", "CYAN_CONCRETE_POWDER"),
    CYAN_GLAZED_TERRACOTTA(null, null, null, null, "CYAN_GLAZED_TERRACOTTA"),
    CYAN_LEATHER_BOOTS("LEATHER_BOOTS", 9, Color.fromRGB(76, 127, 153)),
    CYAN_LEATHER_CHESTPLATE("LEATHER_CHESTPLATE", 9, Color.fromRGB(76, 127, 153)),
    CYAN_LEATHER_HELMET("LEATHER_HELMET", 9, Color.fromRGB(76, 127, 153)),
    CYAN_LEATHER_LEGGINGS("LEATHER_LEGGINGS", 9, Color.fromRGB(76, 127, 153)),
    CYAN_SHULKER_BOX(null, null, null, "CYAN_SHULKER_BOX"),
    CYAN_STAINED_GLASS(9, "STAINED_GLASS", null, null, null, null, "CYAN_STAINED_GLASS"),
    CYAN_STAINED_GLASS_PANE(9, "STAINED_GLASS_PANE", null, null, null, null, "CYAN_STAINED_GLASS_PANE"),
    CYAN_TERRACOTTA(9, "STAINED_CLAY", null, null, null, null, "CYAN_TERRACOTTA"),
    CYAN_WALL_BANNER(6, "BANNER", null, null, null, null, "CYAN_WALL_BANNER"),
    CYAN_WOOL(9, "WOOL", null, null, null, null, "CYAN_WOOL"),
    CYAN_DYE(6, "INK_SACK", null, null, null, null, "CYAN_DYE"),
    DAMAGED_ANVIL(2, "ANVIL", null, null, null, null, "DAMAGED_ANVIL"),
    DANDELION("YELLOW_FLOWER", null, null, null, null, "DANDELION"),
    DANDELION_YELLOW(11, "INK_SACK", null, null, null, null, "DANDELION_YELLOW", "YELLOW_DYE"),
    DARK_OAK_BOAT("BOAT", null, null, null, "BOAT_DARK_OAK", "DARK_OAK_BOAT"),
    DARK_OAK_BUTTON("WOOD_BUTTON", null, null, null, null, "DARK_OAK_BUTTON"),
    DARK_OAK_DOOR(null, null, null, null, null, "DARK_OAK_DOOR"),
    DARK_OAK_DOOR_ITEM("DARK_OAK_DOOR_ITEM", null, null, null, null, "DARK_OAK_DOOR"),
    DARK_OAK_FENCE("DARK_OAK_FENCE"),
    DARK_OAK_FENCE_GATE("DARK_OAK_FENCE_GATE"),
    DARK_OAK_LEAVES(1, "LEAVES_2", null, null, null, null, "DARK_OAK_LEAVES"),
    DARK_OAK_LOG(1, "LOG_2", null, null, null, null, "DARK_OAK_LOG"),
    DARK_OAK_PLANKS(5, "WOOD", null, null, null, null, "DARK_OAK_PLANKS"),
    DARK_OAK_PRESSURE_PLATE("WOOD_PLATE", null, null, null, null, "DARK_OAK_PRESSURE_PLATE"),
    DARK_OAK_SAPLING(5, "SAPLING", null, null, null, null, "DARK_OAK_SAPLING"),
    DARK_OAK_SLAB(5, "WOOD_STEP", null, null, null, null, "DARK_OAK_SLAB"),
    DARK_OAK_STAIRS("DARK_OAK_STAIRS"),
    DARK_OAK_TRAPDOOR("TRAP_DOOR", null, null, null, null, "DARK_OAK_TRAPDOOR"),
    DARK_OAK_WOOD(13, "LOG_2", null, null, null, null, "DARK_OAK_WOOD"),
    DARK_PRISMARINE(2, "PRISMARINE", null, null, null, null, "DARK_PRISMARINE"),
    DARK_PRISMARINE_SLAB(null, null, null, null, null, "DARK_PRISMARINE_SLAB"),
    DARK_PRISMARINE_STAIRS(null, null, null, null, null, "DARK_PRISMARINE_STAIRS"),
    DAYLIGHT_DETECTOR("DAYLIGHT_DETECTOR"),
    DEAD_BRAIN_CORAL(null, null, null, null, null, "DEAD_BRAIN_CORAL"),
    DEAD_BRAIN_CORAL_BLOCK(null, null, null, null, null, "DEAD_BRAIN_CORAL_BLOCK"),
    DEAD_BRAIN_CORAL_FAN(null, null, null, null, null, "DEAD_BRAIN_CORAL_FAN"),
    DEAD_BRAIN_CORAL_WALL_FAN(null, null, null, null, null, "DEAD_BRAIN_CORAL_WALL_FAN"),
    DEAD_BUBBLE_CORAL(null, null, null, null, null, "DEAD_BUBBLE_CORAL"),
    DEAD_BUBBLE_CORAL_BLOCK(null, null, null, null, null, "DEAD_BUBBLE_CORAL_BLOCK"),
    DEAD_BUBBLE_CORAL_FAN(null, null, null, null, null, "DEAD_BUBBLE_CORAL_FAN"),
    DEAD_BUBBLE_CORAL_WALL_FAN(null, null, null, null, null, "DEAD_BUBBLE_CORAL_WALL_FAN"),
    DEAD_BUSH("LONG_GRASS", null, null, null, null, "DEAD_BUSH"),
    DEAD_FIRE_CORAL(null, null, null, null, null, "DEAD_FIRE_CORAL"),
    DEAD_FIRE_CORAL_BLOCK(null, null, null, null, null, "DEAD_FIRE_CORAL_BLOCK"),
    DEAD_FIRE_CORAL_FAN(null, null, null, null, null, "DEAD_FIRE_CORAL_FAN"),
    DEAD_FIRE_CORAL_WALL_FAN(null, null, null, null, null, "DEAD_FIRE_CORAL_WALL_FAN"),
    DEAD_HORN_CORAL(null, null, null, null, null, "DEAD_HORN_CORAL"),
    DEAD_HORN_CORAL_BLOCK(null, null, null, null, null, "DEAD_HORN_CORAL_BLOCK"),
    DEAD_HORN_CORAL_FAN(null, null, null, null, null, "DEAD_HORN_CORAL_FAN"),
    DEAD_HORN_CORAL_WALL_FAN(null, null, null, null, null, "DEAD_HORN_CORAL_WALL_FAN"),
    DEBUG_STICK(null, null, null, null, null, "DEBUG_STICK"),
    DETECTOR_RAIL("DETECTOR_RAIL"),
    DIAMOND("DIAMOND"),
    DIAMOND_AXE("DIAMOND_AXE"),
    DIAMOND_BLOCK("DIAMOND_BLOCK"),
    DIAMOND_BOOTS("DIAMOND_BOOTS"),
    DIAMOND_CHESTPLATE("DIAMOND_CHESTPLATE"),
    DIAMOND_HELMET("DIAMOND_HELMET"),
    DIAMOND_HOE("DIAMOND_HOE"),
    DIAMOND_HORSE_ARMOR("DIAMOND_BARDING", null, null, null, null, "DIAMOND_HORSE_ARMOR"),
    DIAMOND_LEGGINGS("DIAMOND_LEGGINGS"),
    DIAMOND_ORE("DIAMOND_ORE"),
    DIAMOND_PICKAXE("DIAMOND_PICKAXE"),
    DIAMOND_SHOVEL("DIAMOND_SPADE", null, null, null, null, "DIAMOND_SHOVEL"),
    DIAMOND_SWORD("DIAMOND_SWORD"),
    DIORITE(3, "STONE", null, null, null, null, "DIORITE"),
    DIORITE_SLAB(null, null, null, null, null, null, "DIORITE_SLAB"),
    DIORITE_STAIRS(null, null, null, null, null, null, "DIORITE_STAIRS"),
    DIORITE_WALL(null, null, null, null, null, null, "DIORITE_WALL"),
    DIRT("DIRT"),
    DISPENSER("DISPENSER"),
    DOLPHIN_SPAWN_EGG(null, null, null, null, null, "DOLPHIN_SPAWN_EGG"),
    DONKEY_SPAWN_EGG(31, "MONSTER_EGG", null, null, null, null, "DONKEY_SPAWN_EGG"),
    DRAGON_BREATH(null, "DRAGONS_BREATH", null, null, null, "DRAGON_BREATH"),
    DRAGON_EGG("DRAGON_EGG"),
    DRAGON_HEAD(5, null, "SKULL", null, null, null, "DRAGON_HEAD"),
    DRAGON_HEAD_ITEM(5, null, "SKULL_ITEM", null, null, null, "DRAGON_HEAD"),
    DRAGON_WALL_HEAD(5, null, "SKULL", null, null, null, "DRAGON_WALL_HEAD"),
    DRIED_KELP(null, null, null, null, null, "DRIED_KELP"),
    DRIED_KELP_BLOCK(null, null, null, null, null, "DRIED_KELP_BLOCK"),
    DROPPER("DROPPER"),
    DROWNED_SPAWN_EGG(null, null, null, null, null, "DROWNED_SPAWN_EGG"),
    EGG("EGG"),
    ELDER_GUARDIAN_SPAWN_EGG(null, null, null, null, null, "ELDER_GUARDIAN_SPAWN_EGG"),
    ELYTRA(null, "ELYTRA"),
    EMERALD("EMERALD"),
    EMERALD_BLOCK("EMERALD_BLOCK"),
    EMERALD_ORE("EMERALD_ORE"),
    ENCHANTED_BOOK("ENCHANTED_BOOK"),
    ENCHANTED_BOOK_AQUA_AFFINITY("ENCHANTED_BOOK", Enchantment.WATER_WORKER, 1),
    ENCHANTED_BOOK_BANE_OF_ARTHROPODS_1("ENCHANTED_BOOK", Enchantment.DAMAGE_ARTHROPODS, 1),
    ENCHANTED_BOOK_BANE_OF_ARTHROPODS_2("ENCHANTED_BOOK", Enchantment.DAMAGE_ARTHROPODS, 2),
    ENCHANTED_BOOK_BANE_OF_ARTHROPODS_3("ENCHANTED_BOOK", Enchantment.DAMAGE_ARTHROPODS, 3),
    ENCHANTED_BOOK_BANE_OF_ARTHROPODS_4("ENCHANTED_BOOK", Enchantment.DAMAGE_ARTHROPODS, 4),
    ENCHANTED_BOOK_BANE_OF_ARTHROPODS_5("ENCHANTED_BOOK", Enchantment.DAMAGE_ARTHROPODS, 5),
    ENCHANTED_BOOK_BLAST_PROTECTION_1("ENCHANTED_BOOK", Enchantment.PROTECTION_EXPLOSIONS, 1),
    ENCHANTED_BOOK_BLAST_PROTECTION_2("ENCHANTED_BOOK", Enchantment.PROTECTION_EXPLOSIONS, 2),
    ENCHANTED_BOOK_BLAST_PROTECTION_3("ENCHANTED_BOOK", Enchantment.PROTECTION_EXPLOSIONS, 3),
    ENCHANTED_BOOK_BLAST_PROTECTION_4("ENCHANTED_BOOK", Enchantment.PROTECTION_EXPLOSIONS, 4),
    ENCHANTED_BOOK_CHANNELING("ENCHANTED_BOOK", Enchantment.getByName(Enchantment.getByName("CHANNELING") != null ? "CHANNELING" : "PROTECTION_EXPLOSIONS"), 1),
    ENCHANTED_BOOK_CURSE_OF_BINDING("ENCHANTED_BOOK", Enchantment.getByName(Enchantment.getByName("BINDING_CURSE") != null ? "BINDING_CURSE" : "PROTECTION_EXPLOSIONS"), 1),
    ENCHANTED_BOOK_CURSE_OF_VANISHING("ENCHANTED_BOOK", Enchantment.getByName(Enchantment.getByName("VANISHING_CURSE") != null ? "VANISHING_CURSE" : "PROTECTION_EXPLOSIONS"), 1),
    ENCHANTED_BOOK_DEPTH_STRIDER_1("ENCHANTED_BOOK", Enchantment.DEPTH_STRIDER, 1),
    ENCHANTED_BOOK_DEPTH_STRIDER_2("ENCHANTED_BOOK", Enchantment.DEPTH_STRIDER, 2),
    ENCHANTED_BOOK_DEPTH_STRIDER_3("ENCHANTED_BOOK", Enchantment.DEPTH_STRIDER, 3),
    ENCHANTED_BOOK_EFFICIENCY_1("ENCHANTED_BOOK", Enchantment.DIG_SPEED, 1),
    ENCHANTED_BOOK_EFFICIENCY_2("ENCHANTED_BOOK", Enchantment.DIG_SPEED, 2),
    ENCHANTED_BOOK_EFFICIENCY_3("ENCHANTED_BOOK", Enchantment.DIG_SPEED, 3),
    ENCHANTED_BOOK_EFFICIENCY_4("ENCHANTED_BOOK", Enchantment.DIG_SPEED, 4),
    ENCHANTED_BOOK_EFFICIENCY_5("ENCHANTED_BOOK", Enchantment.DIG_SPEED, 5),
    ENCHANTED_BOOK_FEATHER_FALLING_1("ENCHANTED_BOOK", Enchantment.PROTECTION_FALL, 1),
    ENCHANTED_BOOK_FEATHER_FALLING_2("ENCHANTED_BOOK", Enchantment.PROTECTION_FALL, 2),
    ENCHANTED_BOOK_FEATHER_FALLING_3("ENCHANTED_BOOK", Enchantment.PROTECTION_FALL, 3),
    ENCHANTED_BOOK_FEATHER_FALLING_4("ENCHANTED_BOOK", Enchantment.PROTECTION_FALL, 4),
    ENCHANTED_BOOK_FIRE_ASPECT_1("ENCHANTED_BOOK", Enchantment.FIRE_ASPECT, 1),
    ENCHANTED_BOOK_FIRE_ASPECT_2("ENCHANTED_BOOK", Enchantment.FIRE_ASPECT, 2),
    ENCHANTED_BOOK_FIRE_PROTECTION_1("ENCHANTED_BOOK", Enchantment.PROTECTION_FIRE, 1),
    ENCHANTED_BOOK_FIRE_PROTECTION_2("ENCHANTED_BOOK", Enchantment.PROTECTION_FIRE, 2),
    ENCHANTED_BOOK_FIRE_PROTECTION_3("ENCHANTED_BOOK", Enchantment.PROTECTION_FIRE, 3),
    ENCHANTED_BOOK_FIRE_PROTECTION_4("ENCHANTED_BOOK", Enchantment.PROTECTION_FIRE, 4),
    ENCHANTED_BOOK_FLAME("ENCHANTED_BOOK", Enchantment.ARROW_FIRE, 1),
    ENCHANTED_BOOK_FORTUNE_1("ENCHANTED_BOOK", Enchantment.LOOT_BONUS_BLOCKS, 1),
    ENCHANTED_BOOK_FORTUNE_2("ENCHANTED_BOOK", Enchantment.LOOT_BONUS_BLOCKS, 2),
    ENCHANTED_BOOK_FORTUNE_3("ENCHANTED_BOOK", Enchantment.LOOT_BONUS_BLOCKS, 3),
    ENCHANTED_BOOK_FROST_WALKER_1("ENCHANTED_BOOK", Enchantment.getByName(Enchantment.getByName("FROST_WALKER") != null ? "FROST_WALKER" : "LOOT_BONUS_BLOCKS"), 1),
    ENCHANTED_BOOK_FROST_WALKER_2("ENCHANTED_BOOK", Enchantment.getByName(Enchantment.getByName("FROST_WALKER") != null ? "FROST_WALKER" : "LOOT_BONUS_BLOCKS"), 2),
    ENCHANTED_BOOK_IMPALING_1("ENCHANTED_BOOK", Enchantment.getByName(Enchantment.getByName("IMPALING") != null ? "IMPALING" : "LOOT_BONUS_BLOCKS"), 1),
    ENCHANTED_BOOK_IMPALING_2("ENCHANTED_BOOK", Enchantment.getByName(Enchantment.getByName("IMPALING") != null ? "IMPALING" : "LOOT_BONUS_BLOCKS"), 2),
    ENCHANTED_BOOK_IMPALING_3("ENCHANTED_BOOK", Enchantment.getByName(Enchantment.getByName("IMPALING") != null ? "IMPALING" : "LOOT_BONUS_BLOCKS"), 3),
    ENCHANTED_BOOK_IMPALING_4("ENCHANTED_BOOK", Enchantment.getByName(Enchantment.getByName("IMPALING") != null ? "IMPALING" : "LOOT_BONUS_BLOCKS"), 4),
    ENCHANTED_BOOK_IMPALING_5("ENCHANTED_BOOK", Enchantment.getByName(Enchantment.getByName("IMPALING") != null ? "IMPALING" : "LOOT_BONUS_BLOCKS"), 5),
    ENCHANTED_BOOK_INFINITY("ENCHANTED_BOOK", Enchantment.ARROW_INFINITE, 1),
    ENCHANTED_BOOK_KNOCKBACK_1("ENCHANTED_BOOK", Enchantment.KNOCKBACK, 1),
    ENCHANTED_BOOK_KNOCKBACK_2("ENCHANTED_BOOK", Enchantment.KNOCKBACK, 2),
    ENCHANTED_BOOK_LOOTING_1("ENCHANTED_BOOK", Enchantment.LOOT_BONUS_MOBS, 1),
    ENCHANTED_BOOK_LOOTING_2("ENCHANTED_BOOK", Enchantment.LOOT_BONUS_MOBS, 2),
    ENCHANTED_BOOK_LOOTING_3("ENCHANTED_BOOK", Enchantment.LOOT_BONUS_MOBS, 3),
    ENCHANTED_BOOK_LOYALTY_1("ENCHANTED_BOOK", Enchantment.getByName(Enchantment.getByName("LOYALTY") != null ? "LOYALTY" : "LOOT_BONUS_MOBS"), 1),
    ENCHANTED_BOOK_LOYALTY_2("ENCHANTED_BOOK", Enchantment.getByName(Enchantment.getByName("LOYALTY") != null ? "LOYALTY" : "LOOT_BONUS_MOBS"), 2),
    ENCHANTED_BOOK_LOYALTY_3("ENCHANTED_BOOK", Enchantment.getByName(Enchantment.getByName("LOYALTY") != null ? "LOYALTY" : "LOOT_BONUS_MOBS"), 3),
    ENCHANTED_BOOK_LUCK_OF_THE_SEA_1("ENCHANTED_BOOK", Enchantment.LUCK, 1),
    ENCHANTED_BOOK_LUCK_OF_THE_SEA_2("ENCHANTED_BOOK", Enchantment.LUCK, 2),
    ENCHANTED_BOOK_LUCK_OF_THE_SEA_3("ENCHANTED_BOOK", Enchantment.LUCK, 3),
    ENCHANTED_BOOK_LURE_1("ENCHANTED_BOOK", Enchantment.LURE, 1),
    ENCHANTED_BOOK_LURE_2("ENCHANTED_BOOK", Enchantment.LURE, 2),
    ENCHANTED_BOOK_LURE_3("ENCHANTED_BOOK", Enchantment.LURE, 3),
    ENCHANTED_BOOK_MENDING("ENCHANTED_BOOK", Enchantment.getByName(Enchantment.getByName("MENDING") != null ? "MENDING" : "LURE"), 1),
    ENCHANTED_BOOK_MULTI_SHOT("ENCHANTED_BOOK", Enchantment.getByName(Enchantment.getByName("MULTI_SHOT") != null ? "MULTI_SHOT" : "LURE"), 1),
    ENCHANTED_BOOK_PIERCING_1("ENCHANTED_BOOK", Enchantment.getByName(Enchantment.getByName("PIERCING") != null ? "PIERCING" : "LURE"), 1),
    ENCHANTED_BOOK_PIERCING_2("ENCHANTED_BOOK", Enchantment.getByName(Enchantment.getByName("PIERCING") != null ? "PIERCING" : "LURE"), 2),
    ENCHANTED_BOOK_PIERCING_3("ENCHANTED_BOOK", Enchantment.getByName(Enchantment.getByName("PIERCING") != null ? "PIERCING" : "LURE"), 3),
    ENCHANTED_BOOK_PIERCING_4("ENCHANTED_BOOK", Enchantment.getByName(Enchantment.getByName("PIERCING") != null ? "PIERCING" : "LURE"), 4),
    ENCHANTED_BOOK_POWER_1("ENCHANTED_BOOK", Enchantment.ARROW_DAMAGE, 1),
    ENCHANTED_BOOK_POWER_2("ENCHANTED_BOOK", Enchantment.ARROW_DAMAGE, 2),
    ENCHANTED_BOOK_POWER_3("ENCHANTED_BOOK", Enchantment.ARROW_DAMAGE, 3),
    ENCHANTED_BOOK_POWER_4("ENCHANTED_BOOK", Enchantment.ARROW_DAMAGE, 4),
    ENCHANTED_BOOK_POWER_5("ENCHANTED_BOOK", Enchantment.ARROW_DAMAGE, 5),
    ENCHANTED_BOOK_PROJECTILE_PROTECTION_1("ENCHANTED_BOOK", Enchantment.PROTECTION_PROJECTILE, 1),
    ENCHANTED_BOOK_PROJECTILE_PROTECTION_2("ENCHANTED_BOOK", Enchantment.PROTECTION_PROJECTILE, 2),
    ENCHANTED_BOOK_PROJECTILE_PROTECTION_3("ENCHANTED_BOOK", Enchantment.PROTECTION_PROJECTILE, 3),
    ENCHANTED_BOOK_PROJECTILE_PROTECTION_4("ENCHANTED_BOOK", Enchantment.PROTECTION_PROJECTILE, 4),
    ENCHANTED_BOOK_PROTECTION_1("ENCHANTED_BOOK", Enchantment.PROTECTION_ENVIRONMENTAL, 1),
    ENCHANTED_BOOK_PROTECTION_2("ENCHANTED_BOOK", Enchantment.PROTECTION_ENVIRONMENTAL, 2),
    ENCHANTED_BOOK_PROTECTION_3("ENCHANTED_BOOK", Enchantment.PROTECTION_ENVIRONMENTAL, 3),
    ENCHANTED_BOOK_PROTECTION_4("ENCHANTED_BOOK", Enchantment.PROTECTION_ENVIRONMENTAL, 4),
    ENCHANTED_BOOK_PUNCH_1("ENCHANTED_BOOK", Enchantment.ARROW_KNOCKBACK, 1),
    ENCHANTED_BOOK_PUNCH_2("ENCHANTED_BOOK", Enchantment.ARROW_KNOCKBACK, 2),
    ENCHANTED_BOOK_QUICK_CHARGE_1("ENCHANTED_BOOK", Enchantment.getByName(Enchantment.getByName("QUICK_CHARGE") != null ? "QUICK_CHARGE" : "ARROW_KNOCKBACK"), 1),
    ENCHANTED_BOOK_QUICK_CHARGE_2("ENCHANTED_BOOK", Enchantment.getByName(Enchantment.getByName("QUICK_CHARGE") != null ? "QUICK_CHARGE" : "ARROW_KNOCKBACK"), 2),
    ENCHANTED_BOOK_QUICK_CHARGE_3("ENCHANTED_BOOK", Enchantment.getByName(Enchantment.getByName("QUICK_CHARGE") != null ? "QUICK_CHARGE" : "ARROW_KNOCKBACK"), 3),
    ENCHANTED_BOOK_RESPIRATION_1("ENCHANTED_BOOK", Enchantment.OXYGEN, 1),
    ENCHANTED_BOOK_RESPIRATION_2("ENCHANTED_BOOK", Enchantment.OXYGEN, 2),
    ENCHANTED_BOOK_RESPIRATION_3("ENCHANTED_BOOK", Enchantment.OXYGEN, 3),
    ENCHANTED_BOOK_RIPTIDE_1("ENCHANTED_BOOK", Enchantment.getByName(Enchantment.getByName("RIPTIDE") != null ? "RIPTIDE" : "OXYGEN"), 1),
    ENCHANTED_BOOK_RIPTIDE_2("ENCHANTED_BOOK", Enchantment.getByName(Enchantment.getByName("RIPTIDE") != null ? "RIPTIDE" : "OXYGEN"), 2),
    ENCHANTED_BOOK_RIPTIDE_3("ENCHANTED_BOOK", Enchantment.getByName(Enchantment.getByName("RIPTIDE") != null ? "RIPTIDE" : "OXYGEN"), 3),
    ENCHANTED_BOOK_SHARPNESS_1("ENCHANTED_BOOK", Enchantment.DAMAGE_ALL, 1),
    ENCHANTED_BOOK_SHARPNESS_2("ENCHANTED_BOOK", Enchantment.DAMAGE_ALL, 2),
    ENCHANTED_BOOK_SHARPNESS_3("ENCHANTED_BOOK", Enchantment.DAMAGE_ALL, 3),
    ENCHANTED_BOOK_SHARPNESS_4("ENCHANTED_BOOK", Enchantment.DAMAGE_ALL, 4),
    ENCHANTED_BOOK_SHARPNESS_5("ENCHANTED_BOOK", Enchantment.DAMAGE_ALL, 5),
    ENCHANTED_BOOK_SILK_TOUCH("ENCHANTED_BOOK", Enchantment.SILK_TOUCH, 1),
    ENCHANTED_BOOK_SMITE_1("ENCHANTED_BOOK", Enchantment.DAMAGE_UNDEAD, 1),
    ENCHANTED_BOOK_SMITE_2("ENCHANTED_BOOK", Enchantment.DAMAGE_UNDEAD, 2),
    ENCHANTED_BOOK_SMITE_3("ENCHANTED_BOOK", Enchantment.DAMAGE_UNDEAD, 3),
    ENCHANTED_BOOK_SMITE_4("ENCHANTED_BOOK", Enchantment.DAMAGE_UNDEAD, 4),
    ENCHANTED_BOOK_SMITE_5("ENCHANTED_BOOK", Enchantment.DAMAGE_UNDEAD, 5),
    ENCHANTED_BOOK_SWEEPING_EDGE_1("ENCHANTED_BOOK", Enchantment.getByName(Enchantment.getByName("SWEEPING_EDGE") != null ? "SWEEPING_EDGE" : "DAMAGE_UNDEAD"), 1),
    ENCHANTED_BOOK_SWEEPING_EDGE_2("ENCHANTED_BOOK", Enchantment.getByName(Enchantment.getByName("SWEEPING_EDGE") != null ? "SWEEPING_EDGE" : "DAMAGE_UNDEAD"), 2),
    ENCHANTED_BOOK_SWEEPING_EDGE_3("ENCHANTED_BOOK", Enchantment.getByName(Enchantment.getByName("SWEEPING_EDGE") != null ? "SWEEPING_EDGE" : "DAMAGE_UNDEAD"), 3),
    ENCHANTED_BOOK_THORNS_1("ENCHANTED_BOOK", Enchantment.THORNS, 1),
    ENCHANTED_BOOK_THORNS_2("ENCHANTED_BOOK", Enchantment.THORNS, 2),
    ENCHANTED_BOOK_THORNS_3("ENCHANTED_BOOK", Enchantment.THORNS, 3),
    ENCHANTED_BOOK_UNBREAKING_1("ENCHANTED_BOOK", Enchantment.DURABILITY, 1),
    ENCHANTED_BOOK_UNBREAKING_2("ENCHANTED_BOOK", Enchantment.DURABILITY, 2),
    ENCHANTED_BOOK_UNBREAKING_3("ENCHANTED_BOOK", Enchantment.DURABILITY, 3),
    ENCHANTED_GOLDEN_APPLE(1, "GOLDEN_APPLE", null, null, null, null, "ENCHANTED_GOLDEN_APPLE"),
    ENCHANTING_TABLE("ENCHANTMENT_TABLE", null, null, null, null, "ENCHANTING_TABLE"),
    END_CRYSTAL(null, null, null, "END_CRYSTAL"),
    END_GATEWAY(null, "END_GATEWAY"),
    END_PORTAL(null, null, null, null, null, "END_PORTAL"),
    END_PORTAL_FRAME("ENDER_PORTAL_FRAME", null, null, null, null, "END_PORTAL_FRAME"),
    END_ROD(null, "END_ROD"),
    END_STONE("ENDER_STONE", null, null, null, null, "END_STONE"),
    END_STONE_BRICKS(null, "END_BRICKS", null, null, null, "END_STONE_BRICKS"),
    END_STONE_BRICK_SLAB(null, null, null, null, null, null, "END_STONE_BRICK_SLAB"),
    END_STONE_BRICK_STAIRS(null, null, null, null, null, null, "END_STONE_BRICK_STAIRS"),
    END_STONE_BRICK_WALL(null, null, null, null, null, null, "END_STONE_BRICK_WALL"),
    ENDER_CHEST("ENDER_CHEST"),
    ENDER_EYE("EYE_OF_ENDER", null, null, null, null, "ENDER_EYE"),
    ENDER_PEARL("ENDER_PEARL"),
    ENDERMAN_SPAWN_EGG(58, "MONSTER_EGG", null, null, null, null, "ENDERMAN_SPAWN_EGG"),
    ENDERMITE_SPAWN_EGG(67, "MONSTER_EGG", null, null, null, null, "ENDERMITE_SPAWN_EGG"),
    EVOKER_SPAWN_EGG(34, null, null, null, "MONSTER_EGG", null, "EVOKER_SPAWN_EGG"),
    EXPERIENCE_BOTTLE("EXP_BOTTLE", null, null, null, null, "EXPERIENCE_BOTTLE"),
    FARMLAND("SOIL", null, null, null, null, "FARMLAND"),
    FEATHER("FEATHER"),
    FERMENTED_SPIDER_EYE("FERMENTED_SPIDER_EYE"),
    FERN(2, "LONG_GRASS", null, null, null, null, "FERN"),
    FILLED_MAP("MAP", null, null, null, null, "FILLED_MAP"),
    FIRE("FIRE"),
    FIRE_CHARGE("FIREBALL", null, null, null, null, "FIRE_CHARGE"),
    FIRE_CORAL(null, null, null, null, null, "FIRE_CORAL"),
    FIRE_CORAL_BLOCK(null, null, null, null, null, "FIRE_CORAL_BLOCK"),
    FIRE_CORAL_FAN(null, null, null, null, null, "FIRE_CORAL_FAN"),
    FIRE_CORAL_WALL_FAN(null, null, null, null, null, "FIRE_CORAL_WALL_FAN"),
    FIREWORK_ROCKET("FIREWORK", null, null, null, null, "FIREWORK_ROCKET"),
    FIREWORK_STAR("FIREWORK_CHARGE", null, null, null, null, "FIREWORK_STAR"),
    FISHING_ROD("FISHING_ROD"),
    FLETCHING_TABLE("FLETCHING_TABLE"),
    FLINT("FLINT"),
    FLINT_AND_STEEL("FLINT_AND_STEEL"),
    FLOWER_POT("FLOWER_POT"),
    FLOWER_POT_ITEM("FLOWER_POT_ITEM", null, null, null, null, "FLOWER_POT"),
    FOX_SPAWN_EGG(null, null, null, null, null, null, "FOX_SPAWN_EGG"),
    FROSTED_ICE(null, "FROSTED_ICE"),
    FURNACE("FURNACE"),
    FURNACE_MINECART("POWERED_MINECART", null, null, null, null, "FURNACE_MINECART"),
    GHAST_SPAWN_EGG(56, "MONSTER_EGG", null, null, null, null, "GHAST_SPAWN_EGG"),
    GHAST_TEAR("GHAST_TEAR"),
    GLASS("GLASS"),
    GLASS_PANE("THIN_GLASS", null, null, null, null, "GLASS_PANE"),
    GLASS_BOTTLE("GLASS_BOTTLE"),
    GLISTERING_MELON_SLICE("SPECKLED_MELON", null, null, null, null, "GLISTERING_MELON_SLICE"),
    GLOWING_REDSTONE_ORE("GLOWING_REDSTONE_ORE", null, null, null, null, "LEGACY_GLOWING_REDSTONE_ORE"),
    GLOWSTONE("GLOWSTONE"),
    GLOWSTONE_DUST("GLOWSTONE_DUST"),
    GOLD_BLOCK("GOLD_BLOCK"),
    GOLD_INGOT("GOLD_INGOT"),
    GOLD_NUGGET("GOLD_NUGGET"),
    GOLD_ORE("GOLD_ORE"),
    GOLDEN_APPLE("GOLDEN_APPLE"),
    GOLDEN_AXE("GOLD_AXE", null, null, null, null, "GOLDEN_AXE"),
    GOLDEN_BOOTS("GOLD_BOOTS", null, null, null, null, "GOLDEN_BOOTS"),
    GOLDEN_CARROT("GOLDEN_CARROT"),
    GOLDEN_CHESTPLATE("GOLD_CHESTPLATE", null, null, null, null, "GOLDEN_CHESTPLATE"),
    GOLDEN_HELMET("GOLD_HELMET", null, null, null, null, "GOLDEN_HELMET"),
    GOLDEN_HOE("GOLD_HOE", null, null, null, null, "GOLDEN_HOE"),
    GOLDEN_HORSE_ARMOR("GOLD_BARDING", null, null, null, null, "GOLDEN_HORSE_ARMOR"),
    GOLDEN_LEGGINGS("GOLD_LEGGINGS", null, null, null, null, "GOLDEN_LEGGINGS"),
    GOLDEN_PICKAXE("GOLD_PICKAXE", null, null, null, null, "GOLDEN_PICKAXE"),
    GOLDEN_SHOVEL("GOLD_SPADE", null, null, null, null, "GOLDEN_SHOVEL"),
    GOLDEN_SWORD("GOLD_SWORD", null, null, null, null, "GOLDEN_SWORD"),
    GRANITE(1, "STONE", null, null, null, null, "GRANITE"),
    GRANITE_SLAB(null, null, null, null, null, null, "GRANITE_SLAB"),
    GRANITE_STAIRS(null, null, null, null, null, null, "GRANITE_STAIRS"),
    GRANITE_WALL(null, null, null, null, null, null, "GRANITE_WALL"),
    GRASS("GRASS"),
    GRASS_BLOCK("GRASS", null, null, null, null, "GRASS_BLOCK"),
    GRASS_PATH(null, "GRASS_PATH"),
    GRAVEL("GRAVEL"),
    GRAY_BANNER(8, "BANNER", null, null, null, null, "GRAY_BANNER"),
    GRAY_BED(7, "BED", null, null, null, null, "GRAY_BED"),
    GRAY_CARPET(7, "CARPET", null, null, null, null, "GRAY_CARPET"),
    GRAY_CONCRETE(7, null, null, null, null, "CONCRETE", "GRAY_CONCRETE"),
    GRAY_CONCRETE_POWDER(7, null, null, null, null, "CONCRETE_POWDER", "GRAY_CONCRETE_POWDER"),
    GRAY_DYE(8, "INK_SACK", null, null, null, null, "GRAY_DYE"),
    GRAY_GLAZED_TERRACOTTA(null, null, null, null, "GRAY_GLAZED_TERRACOTTA"),
    GRAY_LEATHER_BOOTS("LEATHER_BOOTS", 7, Color.fromRGB(76, 76, 76)),
    GRAY_LEATHER_CHESTPLATE("LEATHER_CHESTPLATE", 7, Color.fromRGB(76, 76, 76)),
    GRAY_LEATHER_HELMET("LEATHER_HELMET", 7, Color.fromRGB(76, 76, 76)),
    GRAY_LEATHER_LEGGINGS("LEATHER_LEGGINGS", 7, Color.fromRGB(76, 76, 76)),
    GRAY_SHULKER_BOX(null, null, null, "GRAY_SHULKER_BOX"),
    GRAY_STAINED_GLASS(7, "STAINED_GLASS", null, null, null, null, "GRAY_STAINED_GLASS"),
    GRAY_STAINED_GLASS_PANE(7, "STAINED_GLASS_PANE", null, null, null, null, "GRAY_STAINED_GLASS_PANE"),
    GRAY_TERRACOTTA(7, "STAINED_CLAY", null, null, null, null, "GRAY_TERRACOTTA"),
    GRAY_WALL_BANNER(8, "BANNER", null, null, null, null, "GRAY_WALL_BANNER"),
    GRAY_WOOL(7, "WOOL", null, null, null, null, "GRAY_WOOL"),
    GREEN_BANNER(2, "BANNER", null, null, null, null, "GREEN_BANNER"),
    GREEN_BED(13, "BED", null, null, null, null, "GREEN_BED"),
    GREEN_CARPET(13, "CARPET", null, null, null, null, "GREEN_CARPET"),
    GREEN_CONCRETE(13, null, null, null, null, "CONCRETE", "GREEN_CONCRETE"),
    GREEN_CONCRETE_POWDER(13, null, null, null, null, "CONCRETE_POWDER", "GREEN_CONCRETE_POWDER"),
    GREEN_GLAZED_TERRACOTTA(null, null, null, null, "GREEN_GLAZED_TERRACOTTA"),
    GREEN_LEATHER_BOOTS("LEATHER_BOOTS", 13, Color.fromRGB(102, 127, 51)),
    GREEN_LEATHER_CHESTPLATE("LEATHER_CHESTPLATE", 13, Color.fromRGB(102, 127, 51)),
    GREEN_LEATHER_HELMET("LEATHER_HELMET", 13, Color.fromRGB(102, 127, 51)),
    GREEN_LEATHER_LEGGINGS("LEATHER_LEGGINGS", 13, Color.fromRGB(102, 127, 51)),
    GREEN_SHULKER_BOX(null, null, null, "GREEN_SHULKER_BOX"),
    GREEN_STAINED_GLASS(13, "STAINED_GLASS", null, null, null, null, "GREEN_STAINED_GLASS"),
    GREEN_STAINED_GLASS_PANE(13, "STAINED_GLASS_PANE", null, null, null, null, "GREEN_STAINED_GLASS_PANE"),
    GREEN_TERRACOTTA(13, "STAINED_CLAY", null, null, null, null, "GREEN_TERRACOTTA"),
    GREEN_WALL_BANNER(2, "BANNER", null, null, null, null, "GREEN_WALL_BANNER"),
    GREEN_WOOL(13, "WOOL", null, null, null, null, "GREEN_WOOL"),
    GRINDSTONE(null, null, null, null, null, null, "GRINDSTONE"),
    GUARDIAN_SPAWN_EGG(68, "MONSTER_EGG", null, null, null, null, "GUARDIAN_SPAWN_EGG"),
    GUNPOWDER("SULPHUR", null, null, null, null, "GUNPOWDER"),
    HAY_BLOCK("HAY_BLOCK"),
    HEART_OF_THE_SEA(null, null, null, null, null, "HEART_OF_THE_SEA"),
    HEAVY_WEIGHTED_PRESSURE_PLATE("IRON_PLATE", null, null, null, null, "HEAVY_WEIGHTED_PRESSURE_PLATE"),
    HOPPER("HOPPER"),
    HOPPER_MINECART("HOPPER_MINECART"),
    HORN_CORAL(null, null, null, null, null, "HORN_CORAL"),
    HORN_CORAL_BLOCK(null, null, null, null, null, "HORN_CORAL_BLOCK"),
    HORN_CORAL_FAN(null, null, null, null, null, "HORN_CORAL_FAN"),
    HORN_CORAL_WALL_FAN(null, null, null, null, null, "HORN_CORAL_WALL_FAN"),
    HORSE_SPAWN_EGG(100, "MONSTER_EGG", null, null, null, null, "HORSE_SPAWN_EGG"),
    HUSK_SPAWN_EGG(23, null, null, null, "MONSTER_EGG", null, "HUSK_SPAWN_EGG"),
    ICE("ICE"),
    INFESTED_CHISELED_STONE_BRICKS(5, "MONSTER_EGGS", null, null, null, null, "INFESTED_CHISELED_STONE_BRICKS"),
    INFESTED_COBBLESTONE(1, "MONSTER_EGGS", null, null, null, null, "INFESTED_COBBLESTONE"),
    INFESTED_CRACKED_STONE_BRICKS(4, "MONSTER_EGGS", null, null, null, null, "INFESTED_CRACKED_STONE_BRICKS"),
    INFESTED_MOSSY_STONE_BRICKS(3, "MONSTER_EGGS", null, null, null, null, "INFESTED_MOSSY_STONE_BRICKS"),
    INFESTED_STONE("MONSTER_EGGS", null, null, null, null, "INFESTED_STONE"),
    INFESTED_STONE_BRICKS(2, "MONSTER_EGGS", null, null, null, null, "INFESTED_STONE_BRICKS"),
    INK_SAC("INK_SACK", null, null, null, null, "INK_SAC", "BLACK_DYE"),
    IRON_AXE("IRON_AXE"),
    IRON_BARS("IRON_FENCE", null, null, null, null, "IRON_BARS"),
    IRON_BLOCK("IRON_BLOCK"),
    IRON_BOOTS("IRON_BOOTS"),
    IRON_CHESTPLATE("IRON_CHESTPLATE"),
    IRON_DOOR("IRON_DOOR"),
    IRON_DOOR_BLOCK("IRON_DOOR_BLOCK", null, null, null, null, "IRON_DOOR"),
    IRON_HELMET("IRON_HELMET"),
    IRON_HOE("IRON_HOE"),
    IRON_HORSE_ARMOR("IRON_BARDING", null, null, null, null, "IRON_HORSE_ARMOR"),
    IRON_INGOT("IRON_INGOT"),
    IRON_LEGGINGS("IRON_LEGGINGS"),
    IRON_NUGGET(null, null, null, "IRON_NUGGET"),
    IRON_ORE("IRON_ORE"),
    IRON_PICKAXE("IRON_PICKAXE"),
    IRON_SHOVEL("IRON_SPADE", null, null, null, null, "IRON_SHOVEL"),
    IRON_SWORD("IRON_SWORD"),
    IRON_TRAPDOOR("IRON_TRAPDOOR"),
    ITEM_FRAME("ITEM_FRAME"),
    JACK_O_LANTERN("JACK_O_LANTERN"),
    JIGSAW(null, null, null, null, null, null, "JIGSAW"),
    JUKEBOX("JUKEBOX"),
    JUNGLE_BOAT("BOAT", null, null, null, "BOAT_JUNGLE", "JUNGLE_BOAT"),
    JUNGLE_BUTTON("WOOD_BUTTON", null, null, null, null, "JUNGLE_BUTTON"),
    JUNGLE_DOOR("JUNGLE_DOOR"),
    JUNGLE_DOOR_ITEM("JUNGLE_DOOR_ITEM", null, null, null, null, "JUNGLE_DOOR"),
    JUNGLE_FENCE("JUNGLE_FENCE"),
    JUNGLE_FENCE_GATE("JUNGLE_FENCE_GATE"),
    JUNGLE_LEAVES(3, "LEAVES", null, null, null, null, "JUNGLE_LEAVES"),
    JUNGLE_LOG(3, "LOG", null, null, null, null, "JUNGLE_LOG"),
    JUNGLE_PLANKS(3, "WOOD", null, null, null, null, "JUNGLE_PLANKS"),
    JUNGLE_PRESSURE_PLATE("WOOD_PLATE", null, null, null, null, "JUNGLE_PRESSURE_PLATE"),
    JUNGLE_SAPLING(3, "SAPLING", null, null, null, null, "JUNGLE_SAPLING"),
    JUNGLE_SIGN(null, null, null, null, null, null, "JUNGLE_SIGN"),
    JUNGLE_SLAB(3, "WOOD_STEP", null, null, null, null, "JUNGLE_SLAB"),
    JUNGLE_STAIRS("JUNGLE_WOOD_STAIRS", null, null, null, null, "JUNGLE_STAIRS"),
    JUNGLE_TRAPDOOR("TRAP_DOOR", null, null, null, null, "JUNGLE_TRAPDOOR"),
    JUNGLE_WALL_SIGN(null, null, null, null, null, null, "JUNGLE_WALL_SIGN"),
    JUNGLE_WOOD(15, "LOG", null, null, null, null, "JUNGLE_WOOD"),
    KELP(null, null, null, null, null, "KELP"),
    KELP_PLANT(null, null, null, null, null, "KELP_PLANT"),
    KNOWLEDGE_BOOK(null, null, null, null, "KNOWLEDGE_BOOK"),
    LADDER("LADDER"),
    LANTERN(null, null, null, null, null, null, "LANTERN"),
    LAPIS_BLOCK("LAPIS_BLOCK"),
    LAPIS_LAZULI(4, "INK_SACK", null, null, null, null, "LAPIS_LAZULI"),
    LAPIS_ORE("LAPIS_ORE"),
    LARGE_FERN(3, "DOUBLE_PLANT", null, null, null, null, "LARGE_FERN"),
    LAVA("LAVA"),
    LAVA_BUCKET("LAVA_BUCKET"),
    LEAD("LEASH", null, null, null, null, "LEAD"),
    LEATHER("LEATHER"),
    LEATHER_BOOTS("LEATHER_BOOTS"),
    LEATHER_CHESTPLATE("LEATHER_CHESTPLATE"),
    LEATHER_HELMET("LEATHER_HELMET"),
    LEATHER_HORSE_ARMOR("LEATHER_HORSE_ARMOR"),
    LEATHER_LEGGINGS("LEATHER_LEGGINGS"),
    LECTERN(null, null, null, null, null, null, "LECTERN"),
    LEVER("LEVER"),
    LIGHT_BLUE_BANNER(12, "BANNER", null, null, null, null, "LIGHT_BLUE_BANNER"),
    LIGHT_BLUE_BED(3, "BED", null, null, null, null, "LIGHT_BLUE_BED"),
    LIGHT_BLUE_CARPET(3, "CARPET", null, null, null, null, "LIGHT_BLUE_CARPET"),
    LIGHT_BLUE_CONCRETE(3, null, null, null, null, "CONCRETE", "LIGHT_BLUE_CONCRETE"),
    LIGHT_BLUE_CONCRETE_POWDER(3, null, null, null, null, "CONCRETE_POWDER", "LIGHT_BLUE_CONCRETE_POWDER"),
    LIGHT_BLUE_DYE(12, "INK_SACK", null, null, null, null, "LIGHT_BLUE_DYE"),
    LIGHT_BLUE_GLAZED_TERRACOTTA(null, null, null, null, "LIGHT_BLUE_GLAZED_TERRACOTTA"),
    LIGHT_BLUE_SHULKER_BOX(null, null, null, "LIGHT_BLUE_SHULKER_BOX"),
    LIGHT_BLUE_LEATHER_BOOTS("LEATHER_BOOTS", 3, Color.fromRGB(102, 153, 216)),
    LIGHT_BLUE_LEATHER_CHESTPLATE("LEATHER_CHESTPLATE", 3, Color.fromRGB(102, 153, 216)),
    LIGHT_BLUE_LEATHER_HELMET("LEATHER_HELMET", 3, Color.fromRGB(102, 153, 216)),
    LIGHT_BLUE_LEATHER_LEGGINGS("LEATHER_LEGGINGS", 3, Color.fromRGB(102, 153, 216)),
    LIGHT_BLUE_STAINED_GLASS(3, "STAINED_GLASS", null, null, null, null, "LIGHT_BLUE_STAINED_GLASS"),
    LIGHT_BLUE_STAINED_GLASS_PANE(3, "STAINED_GLASS_PANE", null, null, null, null, "LIGHT_BLUE_STAINED_GLASS_PANE"),
    LIGHT_BLUE_TERRACOTTA(3, "STAINED_CLAY", null, null, null, null, "LIGHT_BLUE_TERRACOTTA"),
    LIGHT_BLUE_WALL_BANNER(12, "BANNER", null, null, null, null, "LIGHT_BLUE_WALL_BANNER"),
    LIGHT_BLUE_WOOL(3, "WOOL", null, null, null, null, "LIGHT_BLUE_WOOL"),
    LIGHT_GRAY_BANNER(7, "BANNER", null, null, null, null, "LIGHT_GRAY_BANNER"),
    LIGHT_GRAY_BED(8, "BED", null, null, null, null, "LIGHT_GRAY_BED"),
    LIGHT_GRAY_CARPET(8, "CARPET", null, null, null, null, "LIGHT_GRAY_CARPET"),
    LIGHT_GRAY_CONCRETE(8, null, null, null, null, "CONCRETE", "LIGHT_GRAY_CONCRETE"),
    LIGHT_GRAY_CONCRETE_POWDER(8, null, null, null, null, "CONCRETE_POWDER", "LIGHT_GRAY_CONCRETE_POWDER"),
    LIGHT_GRAY_DYE(7, "INK_SACK", null, null, null, null, "LIGHT_GRAY_DYE"),
    LIGHT_GRAY_GLAZED_TERRACOTTA(null, null, null, null, "SILVER_GLAZED_TERRACOTTA", "LIGHT_GRAY_GLAZED_TERRACOTTA"),
    LIGHT_GRAY_LEATHER_BOOTS("LEATHER_BOOTS", 8, Color.fromRGB(153, 153, 153)),
    LIGHT_GRAY_LEATHER_CHESTPLATE("LEATHER_CHESTPLATE", 8, Color.fromRGB(153, 153, 153)),
    LIGHT_GRAY_LEATHER_HELMET("LEATHER_HELMET", 8, Color.fromRGB(153, 153, 153)),
    LIGHT_GRAY_LEATHER_LEGGINGS("LEATHER_LEGGINGS", 8, Color.fromRGB(153, 153, 153)),
    LIGHT_GRAY_SHULKER_BOX(null, null, null, "SILVER_SHULKER_BOX", null, "LIGHT_GRAY_SHULKER_BOX"),
    LIGHT_GRAY_STAINED_GLASS(8, "STAINED_GLASS", null, null, null, null, "LIGHT_GRAY_STAINED_GLASS"),
    LIGHT_GRAY_STAINED_GLASS_PANE(8, "STAINED_GLASS_PANE", null, null, null, null, "LIGHT_GRAY_STAINED_GLASS_PANE"),
    LIGHT_GRAY_TERRACOTTA(8, "STAINED_CLAY", null, null, null, null, "LIGHT_GRAY_TERRACOTTA"),
    LIGHT_GRAY_WALL_BANNER(7, "BANNER", null, null, null, null, "LIGHT_GRAY_WALL_BANNER"),
    LIGHT_GRAY_WOOL(8, "WOOL", null, null, null, null, "LIGHT_GRAY_WOOL"),
    LIGHT_WEIGHTED_PRESSURE_PLATE("GOLD_PLATE", null, null, null, null, "LIGHT_WEIGHTED_PRESSURE_PLATE"),
    LILAC(1, "DOUBLE_PLANT", null, null, null, null, "LILAC"),
    LILY_OF_THE_VALLEY(null, null, null, null, null, null, "LILY_OF_THE_VALLEY"),
    LILY_PAD("WATER_LILY", null, null, null, null, "LILY_PAD"),
    LIME_BANNER(10, "BANNER", null, null, null, null, "LIME_BANNER"),
    LIME_BED(5, "BED", null, null, null, null, "LIME_BED"),
    LIME_CARPET(5, "CARPET", null, null, null, null, "LIME_CARPET"),
    LIME_CONCRETE(10, null, null, null, null, "CONCRETE", "LIME_CONCRETE"),
    LIME_CONCRETE_POWDER(5, null, null, null, null, "CONCRETE_POWDER", "LIME_CONCRETE_POWDER"),
    LIME_DYE(10, "INK_SACK", null, null, null, null, "LIME_DYE"),
    LIME_GLAZED_TERRACOTTA(null, null, null, null, "LIME_GLAZED_TERRACOTTA"),
    LIME_LEATHER_BOOTS("LEATHER_BOOTS", 5, Color.fromRGB(127, 204, 25)),
    LIME_LEATHER_CHESTPLATE("LEATHER_CHESTPLATE", 5, Color.fromRGB(127, 204, 25)),
    LIME_LEATHER_HELMET("LEATHER_HELMET", 5, Color.fromRGB(127, 204, 25)),
    LIME_LEATHER_LEGGINGS("LEATHER_LEGGINGS", 5, Color.fromRGB(127, 204, 25)),
    LIME_SHULKER_BOX(null, null, null, "LIME_SHULKER_BOX"),
    LIME_STAINED_GLASS(5, "STAINED_GLASS", null, null, null, null, "LIME_STAINED_GLASS"),
    LIME_STAINED_GLASS_PANE(5, "STAINED_GLASS_PANE", null, null, null, null, "LIME_STAINED_GLASS_PANE"),
    LIME_TERRACOTTA(5, "STAINED_CLAY", null, null, null, null, "LIME_TERRACOTTA"),
    LIME_WALL_BANNER(10, "BANNER", null, null, null, null, "LIME_WALL_BANNER"),
    LIME_WOOL(5, "WOOL", null, null, null, null, "LIME_WOOL"),
/*    LINGERING_POTION_AWKWARD(new UPotion(PotionBase.LINGERING, PotionType.valueOf(!Bukkit.getVersion().contains("1.8") ? "AWKWARD" : "WATER"), false, false), 0, null, "LINGERING_POTION"),
    LINGERING_POTION_FIRE_RESISTANCE(new UPotion(PotionBase.LINGERING, PotionType.FIRE_RESISTANCE, false, false), 0, null, "LINGERING_POTION"),
    LINGERING_POTION_FIRE_RESISTANCE_EXTENDED(new UPotion(PotionBase.LINGERING, PotionType.FIRE_RESISTANCE, true, false), 0, null, "LINGERING_POTION"),
    LINGERING_POTION_HARMING_1(new UPotion(PotionBase.LINGERING, PotionType.INSTANT_DAMAGE, false, false), 0, null, "LINGERING_POTION"),
    LINGERING_POTION_HARMING_2(new UPotion(PotionBase.LINGERING, PotionType.INSTANT_DAMAGE, false, true), 0, null, "LINGERING_POTION"),
    LINGERING_POTION_HEALING_1(new UPotion(PotionBase.LINGERING, PotionType.INSTANT_HEAL, false, false), 0, null, "LINGERING_POTION"),
    LINGERING_POTION_HEALING_2(new UPotion(PotionBase.LINGERING, PotionType.INSTANT_HEAL, false, true), 0, null, "LINGERING_POTION"),
    LINGERING_POTION_INVISIBILITY(new UPotion(PotionBase.LINGERING, PotionType.INVISIBILITY, false, false), 0, null, "LINGERING_POTION"),
    LINGERING_POTION_INVISIBILITY_EXTENDED(new UPotion(PotionBase.LINGERING, PotionType.INVISIBILITY, true, false), 0, null, "LINGERING_POTION"),
    LINGERING_POTION_LEAPING_1(new UPotion(PotionBase.LINGERING, PotionType.JUMP, false, false), 0, null, "LINGERING_POTION"),
    LINGERING_POTION_LEAPING_1_EXTENDED(new UPotion(PotionBase.LINGERING, PotionType.JUMP, true, false), 0, null, "LINGERING_POTION"),
    LINGERING_POTION_LEAPING_2(new UPotion(PotionBase.LINGERING, PotionType.JUMP, false, true), 0, null, "LINGERING_POTION"),
    LINGERING_POTION_LUCK(new UPotion(PotionBase.LINGERING, !Bukkit.getVersion().contains("1.8") ? PotionType.valueOf("LUCK") : PotionType.WATER, false, false), 0, null, "LINGERING_POTION"),
    LINGERING_POTION_MUNDANE(new UPotion(PotionBase.LINGERING, PotionType.valueOf(!Bukkit.getVersion().contains("1.8") ? "MUNDANE" : "WATER"), false, false), 0, null, "LINGERING_POTION"),
    LINGERING_POTION_NIGHT_VISION(new UPotion(PotionBase.LINGERING, PotionType.NIGHT_VISION, false, false), 0, null, "LINGERING_POTION"),
    LINGERING_POTION_NIGHT_VISION_EXTENDED(new UPotion(PotionBase.LINGERING, PotionType.NIGHT_VISION, true, false), 0, null, "LINGERING_POTION"),
    LINGERING_POTION_POISON_1(new UPotion(PotionBase.LINGERING, PotionType.POISON, false, false), 0, null, "LINGERING_POTION"),
    LINGERING_POTION_POISON_1_EXTENDED(new UPotion(PotionBase.LINGERING, PotionType.POISON, true, false), 0, null, "LINGERING_POTION"),
    LINGERING_POTION_POISON_2(new UPotion(PotionBase.LINGERING, PotionType.POISON, false, true), 0, null, "LINGERING_POTION"),
    LINGERING_POTION_REGENERATION_1(new UPotion(PotionBase.LINGERING, PotionType.REGEN, false, false), 0, null, "LINGERING_POTION"),
    LINGERING_POTION_REGENERATION_1_EXTENDED(new UPotion(PotionBase.LINGERING, PotionType.REGEN, true, false), 0, null, "LINGERING_POTION"),
    LINGERING_POTION_REGENERATION_2(new UPotion(PotionBase.LINGERING, PotionType.REGEN, false, true), 0, null, "LINGERING_POTION"),
    LINGERING_POTION_SLOW_FALLING(new UPotion(PotionBase.LINGERING, !Bukkit.getVersion().contains("1.8") && !Bukkit.getVersion().contains("1.9") && !Bukkit.getVersion().contains("1.10") && !Bukkit.getVersion().contains("1.11") && !Bukkit.getVersion().contains("1.12") ? PotionType.valueOf("SLOW_FALLING") : PotionType.WATER, false, false), 0, null, "LINGERING_POTION"),
    LINGERING_POTION_SLOW_FALLING_EXTENDED(new UPotion(PotionBase.LINGERING, !Bukkit.getVersion().contains("1.8") && !Bukkit.getVersion().contains("1.9") && !Bukkit.getVersion().contains("1.10") && !Bukkit.getVersion().contains("1.11") && !Bukkit.getVersion().contains("1.12") ? PotionType.valueOf("SLOW_FALLING") : PotionType.WATER, true, false), 0, null, "LINGERING_POTION"),
    LINGERING_POTION_SLOWNESS_1(new UPotion(PotionBase.LINGERING, PotionType.SLOWNESS, false, false), 0, null, "LINGERING_POTION"),
    LINGERING_POTION_SLOWNESS_1_EXTENDED(new UPotion(PotionBase.LINGERING, PotionType.SLOWNESS, true, false), 0, null, "LINGERING_POTION"),
    LINGERING_POTION_SLOWNESS_2(new UPotion(PotionBase.LINGERING, PotionType.SLOWNESS, false, true), 0, null, "LINGERING_POTION"),
    LINGERING_POTION_STRENGTH_1(new UPotion(PotionBase.LINGERING, PotionType.STRENGTH, false, false), 0, null, "LINGERING_POTION"),
    LINGERING_POTION_STRENGTH_1_EXTENDED(new UPotion(PotionBase.LINGERING, PotionType.STRENGTH, true, false), 0, null, "LINGERING_POTION"),
    LINGERING_POTION_STRENGTH_2(new UPotion(PotionBase.LINGERING, PotionType.STRENGTH, false, true), 0, null, "LINGERING_POTION"),
    LINGERING_POTION_SWIFTNESS_1(new UPotion(PotionBase.LINGERING, PotionType.SPEED, false, false), 0, null, "LINGERING_POTION"),
    LINGERING_POTION_SWIFTNESS_1_EXTENDED(new UPotion(PotionBase.LINGERING, PotionType.SPEED, true, false), 0, null, "LINGERING_POTION"),
    LINGERING_POTION_SWIFTNESS_2(new UPotion(PotionBase.LINGERING, PotionType.SPEED, false, true), 0, null, "LINGERING_POTION"),
    LINGERING_POTION_THICK(new UPotion(PotionBase.LINGERING, PotionType.valueOf(!Bukkit.getVersion().contains("1.8") ? "THICK" : "WATER"), false, false), 0, null, "LINGERING_POTION"),
    LINGERING_POTION_TURTLE_MASTER_1(new UPotion(PotionBase.LINGERING, !Bukkit.getVersion().contains("1.8") && !Bukkit.getVersion().contains("1.9") && !Bukkit.getVersion().contains("1.10") && !Bukkit.getVersion().contains("1.11") && !Bukkit.getVersion().contains("1.12") ? PotionType.valueOf("TURTLE_MASTER") : PotionType.WATER, false, false), 0, null, "LINGERING_POTION"),
    LINGERING_POTION_TURTLE_MASTER_1_EXTENDED(new UPotion(PotionBase.LINGERING, !Bukkit.getVersion().contains("1.8") && !Bukkit.getVersion().contains("1.9") && !Bukkit.getVersion().contains("1.10") && !Bukkit.getVersion().contains("1.11") && !Bukkit.getVersion().contains("1.12") ? PotionType.valueOf("TURTLE_MASTER") : PotionType.WATER, true, false), 0, null, "LINGERING_POTION"),
    LINGERING_POTION_TURTLE_MASTER_2(new UPotion(PotionBase.LINGERING, !Bukkit.getVersion().contains("1.8") && !Bukkit.getVersion().contains("1.9") && !Bukkit.getVersion().contains("1.10") && !Bukkit.getVersion().contains("1.11") && !Bukkit.getVersion().contains("1.12") ? PotionType.valueOf("TURTLE_MASTER") : PotionType.WATER, false, true), 0, null, "LINGERING_POTION"),
    LINGERING_POTION_WATER(new UPotion(PotionBase.LINGERING, PotionType.WATER, false, false), 0, null, "LINGERING_POTION"),
    LINGERING_POTION_WATER_BREATHING(new UPotion(PotionBase.LINGERING, PotionType.WATER_BREATHING, false, false), 0, null, "LINGERING_POTION"),
    LINGERING_POTION_WATER_BREATHING_EXTENDED(new UPotion(PotionBase.LINGERING, PotionType.WATER_BREATHING, true, false), 0, null, "LINGERING_POTION"),
    LINGERING_POTION_WEAKNESS(new UPotion(PotionBase.LINGERING, PotionType.WEAKNESS, false, false), 0, null, "LINGERING_POTION"),
    LINGERING_POTION_WEAKNESS_EXTENDED(new UPotion(PotionBase.LINGERING, PotionType.WEAKNESS, true, false), 0, null, "LINGERING_POTION"),
    */
    LLAMA_SPAWN_EGG(103, null, null, null, "MONSTER_EGG", null, "LLAMA_SPAWN_EGG"),
    LOOM(null, null, null, null, null, null, "LOOM"),
    MAGENTA_BANNER(13, "BANNER", null, null, null, null, "MAGENTA_BANNER"),
    MAGENTA_BED(2, "BED", null, null, null, null, "MAGENTA_BED"),
    MAGENTA_CARPET(2, "CARPET", null, null, null, null, "MAGENTA_CARPET"),
    MAGENTA_CONCRETE(2, null, null, null, null, "CONCRETE", "MAGENTA_CONCRETE"),
    MAGENTA_CONCRETE_POWDER(2, null, null, null, null, "CONCRETE_POWDER", "MAGENTA_CONCRETE_POWDER"),
    MAGENTA_DYE(13, "INK_SACK", null, null, null, null, "MAGENTA_DYE"),
    MAGENTA_GLAZED_TERRACOTTA(null, null, null, null, "MAGENTA_GLAZED_TERRACOTTA"),
    MAGENTA_LEATHER_BOOTS("LEATHER_BOOTS", 2, Color.fromRGB(178, 76, 216)),
    MAGENTA_LEATHER_CHESTPLATE("LEATHER_CHESTPLATE", 2, Color.fromRGB(178, 76, 216)),
    MAGENTA_LEATHER_HELMET("LEATHER_HELMET", 2, Color.fromRGB(178, 76, 216)),
    MAGENTA_LEATHER_LEGGINGS("LEATHER_LEGGINGS", 2, Color.fromRGB(178, 76, 216)),
    MAGENTA_SHULKER_BOX(null, null, null, "MAGENTA_SHULKER_BOX"),
    MAGENTA_STAINED_GLASS(2, "STAINED_GLASS", null, null, null, null, "MAGENTA_STAINED_GLASS"),
    MAGENTA_STAINED_GLASS_PANE(2, "STAINED_GLASS_PANE", null, null, null, null, "MAGENTA_STAINED_GLASS_PANE"),
    MAGENTA_TERRACOTTA(2, "STAINED_CLAY", null, null, null, null, "MAGENTA_TERRACOTTA"),
    MAGENTA_WALL_BANNER(13, "BANNER", null, null, null, null, "MAGENTA_WALL_BANNER"),
    MAGENTA_WOOL(2, "WOOL", null, null, null, null, "MAGENTA_WOOL"),
    MAGMA_BLOCK(null, null, "MAGMA", null, null, "MAGMA_BLOCK"),
    MAGMA_CREAM("MAGMA_CREAM"),
    MAGMA_CUBE_SPAWN_EGG(62, "MONSTER_EGG", null, null, null, null, "MAGMA_CUBE_SPAWN_EGG"),
    MAP("EMPTY_MAP", null, null, null, null, "MAP"),
    MELON("MELON_BLOCK", null, null, null, null, "MELON"),
    MELON_SEEDS("MELON_SEEDS"),
    MELON_SLICE("MELON", null, null, null, null, "MELON_SLICE"),
    MELON_STEM("MELON_STEM"),
    MILK_BUCKET("MILK_BUCKET"),
    MINECART("MINECART"),
    MOOSHROOM_SPAWN_EGG(96, "MONSTER_EGG", null, null, null, null, "MOOSHROOM_SPAWN_EGG"),
    MOSSY_COBBLESTONE("MOSSY_COBBLESTONE"),
    MOSSY_COBBLESTONE_SLAB(null, null, null, null, null, null, "MOSSY_COBBLESTONE_SLAB"),
    MOSSY_COBBLESTONE_STAIRS(null, null, null, null, null, null, "MOSSY_COBBLESTONE_STAIRS"),
    MOSSY_COBBLESTONE_WALL(1, "COBBLE_WALL", null, null, null, null, "MOSSY_COBBLESTONE_WALL"),
    MOSSY_STONE_BRICKS(1, "SMOOTH_BRICK", null, null, null, null, "MOSSY_STONE_BRICKS"),
    MOSSY_STONE_BRICK_SLAB(null, null, null, null, null, null, "MOSSY_STONE_BRICK_SLAB"),
    MOSSY_STONE_BRICK_STAIRS(null, null, null, null, null, null, "MOSSY_STONE_BRICK_STAIRS"),
    MOSSY_STONE_BRICK_WALL(null, null, null, null, null, null, "MOSSY_STONE_BRICK_WALL"),
    MOVING_PISTON(null, null, null, null, null, "MOVING_PISTON"),
    MULE_SPAWN_EGG(32, "MONSTER_EGG", null, null, null, null, "MULE_SPAWN_EGG"),
    MUSHROOM_STEM(null, null, null, null, null, "MUSHROOM_STEM"),
    MUSHROOM_STEW("MUSHROOM_SOUP", null, null, null, null, "MUSHROOM_STEW"),
    MUSIC_DISC_11("RECORD_11", null, null, null, null, "MUSIC_DISC_11"),
    MUSIC_DISC_13("GOLD_RECORD", null, null, null, null, "MUSIC_DISC_13"),
    MUSIC_DISC_BLOCKS("RECORD_3", null, null, null, null, "MUSIC_DISC_BLOCKS"),
    MUSIC_DISC_CAT("GREEN_RECORD", null, null, null, null, "MUSIC_DISC_CAT"),
    MUSIC_DISC_CHIRP("RECORD_4", null, null, null, null, "MUSIC_DISC_CHIRP"),
    MUSIC_DISC_FAR("RECORD_5", null, null, null, null, "MUSIC_DISC_FAR"),
    MUSIC_DISC_MALL("RECORD_6", null, null, null, null, "MUSIC_DISC_MALL"),
    MUSIC_DISC_MELLOHI("RECORD_7", null, null, null, null, "MUSIC_DISC_MELLOHI"),
    MUSIC_DISC_STAL("RECORD_8", null, null, null, null, "MUSIC_DISC_STAL"),
    MUSIC_DISC_STRAD("RECORD_9", null, null, null, null, "MUSIC_DISC_STRAD"),
    MUSIC_DISC_WAIT("RECORD_12", null, null, null, null, "MUSIC_DISC_WAIT"),
    MUSIC_DISC_WARD("RECORD_10", null, null, null, null, "MUSIC_DISC_WARD"),
    MUTTON("MUTTON"),
    MYCELIUM("MYCEL", null, null, null, null, "MYCELIUM"),
    NAME_TAG("NAME_TAG"),
    NAUTILUS_SHELL(null, null, null, null, null, "NAUTILUS_SHELL"),
    NETHER_BRICK("NETHER_BRICK_ITEM", null, null, null, null, "NETHER_BRICK"),
    NETHER_BRICK_FENCE("NETHER_FENCE", null, null, null, null, "NETHER_BRICK_FENCE"),
    NETHER_BRICK_SLAB(6, "STEP", null, null, null, null, "NETHER_BRICK_SLAB"),
    NETHER_BRICK_STAIRS("NETHER_BRICK_STAIRS"),
    NETHER_BRICK_WALL(null, null, null, null, null, null, "NETHER_BRICK_WALL"),
    NETHER_BRICKS("NETHER_BRICK", null, null, null, null, "NETHER_BRICKS"),
    NETHER_PORTAL("PORTAL", null, null, null, null, "NETHER_PORTAL"),
    NETHER_QUARTZ_ORE("QUARTZ_ORE", null, null, null, null, "NETHER_QUARTZ_ORE"),
    NETHER_STAR("NETHER_STAR"),
    NETHER_WART("NETHER_STALK", null, null, null, null, "NETHER_WART"),
    NETHER_WART_BLOCK("NETHER_WARTS", null, null, null, null, "NETHER_WART_BLOCK"),
    NETHERRACK("NETHERRACK"),
    NOTE_BLOCK("NOTE_BLOCK"),
    OAK_BOAT("BOAT", null, null, null, null, "OAK_BOAT"),
    OAK_BUTTON("WOOD_BUTTON", null, null, null, null, "OAK_BUTTON"),
    OAK_DOOR("WOOD_DOOR", null, null, null, null, "OAK_DOOR"),
    OAK_DOOR_ITEM("WOOD_DOOR", null, null, null, null, "OAK_DOOR"),
    OAK_FENCE("FENCE", null, null, null, null, "OAK_FENCE"),
    OAK_FENCE_GATE("FENCE_GATE", null, null, null, null, "OAK_FENCE_GATE"),
    OAK_LEAVES("LEAVES", null, null, null, null, "OAK_LEAVES"),
    OAK_LOG("LOG", null, null, null, null, "OAK_LOG"),
    OAK_PLANKS("WOOD", null, null, null, null, "OAK_PLANKS"),
    OAK_PRESSURE_PLATE("WOOD_PLATE", null, null, null, null, "OAK_PRESSURE_PLATE"),
    OAK_SAPLING("SAPLING", null, null, null, null, "OAK_SAPLING"),
    OAK_SIGN("SIGN", null, null, null, null, null, "OAK_SIGN"),
    OAK_SLAB("WOOD_STEP", null, null, null, null, "OAK_SLAB"),
    OAK_STAIRS("WOOD_STAIRS", null, null, null, null, "OAK_STAIRS"),
    OAK_TRAPDOOR("TRAP_DOOR", null, null, null, null, "OAK_TRAPDOOR"),
    OAK_WOOD(12, "LOG", null, null, null, null, "OAK_WOOD"),
    OBSERVER(null, null, null, "OBSERVER"),
    OBSIDIAN("OBSIDIAN"),
    OCELOT_SPAWN_EGG(98, "MONSTER_EGG", null, null, null, null, "OCELOT_SPAWN_EGG"),
    ORANGE_BANNER(14, "BANNER", null, null, null, null, "ORANGE_BANNER"),
    ORANGE_BED(1, "BED", null, null, null, null, "ORANGE_BED"),
    ORANGE_CARPET(1, "CARPET", null, null, null, null, "ORANGE_CARPET"),
    ORANGE_CONCRETE(1, null, null, null, null, "CONCRETE", "ORANGE_CONCRETE"),
    ORANGE_CONCRETE_POWDER(1, null, null, null, null, "CONCRETE_POWDER", "ORANGE_CONCRETE_POWDER"),
    ORANGE_DYE(14, "INK_SACK", null, null, null, null, "ORANGE_DYE"),
    ORANGE_GLAZED_TERRACOTTA(null, null, null, null, "ORANGE_GLAZED_TERRACOTTA"),
    ORANGE_LEATHER_BOOTS("LEATHER_BOOTS", 1, Color.fromRGB(216, 127, 51)),
    ORANGE_LEATHER_CHESTPLATE("LEATHER_CHESTPLATE", 1, Color.fromRGB(216, 127, 51)),
    ORANGE_LEATHER_HELMET("LEATHER_HELMET", 1, Color.fromRGB(216, 127, 51)),
    ORANGE_LEATHER_LEGGINGS("LEATHER_LEGGINGS", 1, Color.fromRGB(216, 127, 51)),
    ORANGE_SHULKER_BOX(null, null, null, "ORANGE_SHULKER_BOX"),
    ORANGE_STAINED_GLASS(1, "STAINED_GLASS", null, null, null, null, "ORANGE_STAINED_GLASS"),
    ORANGE_STAINED_GLASS_PANE(1, "STAINED_GLASS_PANE", null, null, null, null, "ORANGE_STAINED_GLASS_PANE"),
    ORANGE_TERRACOTTA(1, "STAINED_CLAY", null, null, null, null, "ORANGE_TERRACOTTA"),
    ORANGE_TULIP(5, "RED_ROSE", null, null, null, null, "ORANGE_TULIP"),
    ORANGE_WALL_BANNER(14, "BANNER", null, null, null, null, "ORANGE_WALL_BANNER"),
    ORANGE_WOOL(1, "WOOL", null, null, null, null, "ORANGE_WOOL"),
    OXEYE_DAISY(8, "RED_ROSE", null, null, null, null, "OXEYE_DAISY"),
    PACKED_ICE("PACKED_ICE"),
    PAINTING("PAINTING"),
    PANDA_SPAWN_EGG(null, null, null, null, null, null, "PANDA_SPAWN_EGG"),
    PAPER("PAPER"),
    PARROT_SPAWN_EGG(105, null, null, null, null, "MONSTER_EGG", "PARROT_SPAWN_EGG"),
    PEONY(5, "DOUBLE_PLANT", null, null, null, null, "PEONY"),
    PETRIFIED_OAK_SLAB(null, null, null, null, null, "PETRIFIED_OAK_SLAB"),
    PHANTOM_MEMBRANE(null, null, null, null, null, "PHANTOM_MEMBRANE"),
    PHANTOM_SPAWN_EGG(null, null, null, null, null, "PHANTOM_SPAWN_EGG"),
    PIG_SPAWN_EGG(90, "MONSTER_EGG", null, null, null, null, "PIG_SPAWN_EGG"),
    PILLAGER_SPAWN_EGG(null, null, null, null, null, null, "PILLAGER_SPAWN_EGG"),
    PINK_BANNER(9, "BANNER", null, null, null, null, "PINK_BANNER"),
    PINK_BED(6, "BED", null, null, null, null, "PINK_BED"),
    PINK_CARPET(6, "CARPET", null, null, null, null, "PINK_CARPET"),
    PINK_CONCRETE(6, null, null, null, null, "CONCRETE", "PINK_CONCRETE"),
    PINK_CONCRETE_POWDER(6, null, null, null, null, "CONCRETE_POWDER", "PINK_CONCRETE_POWDER"),
    PINK_DYE(9, "INK_SACK", null, null, null, null, "PINK_DYE"),
    PINK_GLAZED_TERRACOTTA(null, null, null, null, "PINK_GLAZED_TERRACOTTA"),
    PINK_LEATHER_BOOTS("LEATHER_BOOTS", 6, Color.fromRGB(242, 127, 165)),
    PINK_LEATHER_CHESTPLATE("LEATHER_CHESTPLATE", 6, Color.fromRGB(242, 127, 165)),
    PINK_LEATHER_HELMET("LEATHER_HELMET", 6, Color.fromRGB(242, 127, 165)),
    PINK_LEATHER_LEGGINGS("LEATHER_LEGGINGS", 6, Color.fromRGB(242, 127, 165)),
    PINK_SHULKER_BOX(null, null, null, "PINK_SHULKER_BOX"),
    PINK_STAINED_GLASS(6, "STAINED_GLASS", null, null, null, null, "PINK_STAINED_GLASS"),
    PINK_STAINED_GLASS_PANE(6, "STAINED_GLASS_PANE", null, null, null, null, "PINK_STAINED_GLASS_PANE"),
    PINK_TERRACOTTA(6, "STAINED_CLAY", null, null, null, null, "PINK_TERRACOTTA"),
    PINK_TULIP(7, "RED_ROSE", null, null, null, null, "PINK_TULIP"),
    PINK_WALL_BANNER(9, "BANNER", null, null, null, null, "PINK_WALL_BANNER"),
    PINK_WOOL(6, "WOOL", null, null, null, null, "PINK_WOOL"),
    PISTON("PISTON_BASE", null, null, null, null, "PISTON"),
    PISTON_HEAD("PISTON_EXTENSION", null, null, null, null, "PISTON_HEAD"),
    PLAYER_HEAD(3, "SKULL", null, null, null, null, "PLAYER_HEAD"),
    PLAYER_HEAD_ITEM(3, "SKULL_ITEM", null, null, null, null, "PLAYER_HEAD"),
    PLAYER_WALL_HEAD(3, "SKULL", null, null, null, null, "PLAYER_WALL_HEAD"),
    PODZOL(2, "DIRT", null, null, null, null, "PODZOL"),
    POISONOUS_POTATO("POISONOUS_POTATO"),
    POLAR_BEAR_SPAWN_EGG(102, null, null, "MONSTER_EGG", null, null, "POLAR_BEAR_SPAWN_EGG"),
    POLISHED_ANDESITE(6, "STONE", null, null, null, null, "POLISHED_ANDESITE"),
    POLISHED_ANDESITE_SLAB(null, null, null, null, null, null, "POLISHED_ANDESITE_SLAB"),
    POLISHED_ANDESITE_STAIRS(null, null, null, null, null, null, "POLISHED_ANDESITE_STAIRS"),
    POLISHED_DIORITE(4, "STONE", null, null, null, null, "POLISHED_DIORITE"),
    POLISHED_DIORITE_SLAB(null, null, null, null, null, null, "POLISHED_DIORITE_SLAB"),
    POLISHED_DIORITE_STAIRS(null, null, null, null, null, null, "POLISHED_DIORITE_STAIRS"),
    POLISHED_GRANITE(2, "STONE", null, null, null, null, "POLISHED_GRANITE"),
    POLISHED_GRANITE_SLAB(null, null, null, null, null, null, "POLISHED_GRANITE_SLAB"),
    POLISHED_GRANITE_STAIRS(null, null, null, null, null, null, "POLISHED_GRANITE_STAIRS"),
    POPPED_CHORUS_FRUIT(null, "CHORUS_FRUIT_POPPED", null, null, null, "POPPED_CHORUS_FRUIT"),
    POPPY("RED_ROSE", null, null, null, null, "POPPY"),
    PORKCHOP("PORK", null, null, null, null, "PORKCHOP"),
    POTATO_ITEM("POTATO_ITEM", null, null, null, null, "POTATO"),
    POTATOES("POTATO", null, null, null, null, "POTATOES"),
    POTION("POTION"),
    /*POTION_AWKWARD("POTION", !Bukkit.getVersion().contains("1.8") ? 0 : 16, new UPotion(PotionBase.NORMAL, PotionType.valueOf(!Bukkit.getVersion().contains("1.8") ? "AWKWARD" : "WATER"), false, false)),
    POTION_FIRE_RESISTANCE("POTION", 0, new UPotion(PotionBase.NORMAL, PotionType.FIRE_RESISTANCE, false, false)),
    POTION_FIRE_RESISTANCE_EXTENDED("POTION", 0, new UPotion(PotionBase.NORMAL, PotionType.FIRE_RESISTANCE, true, false)),
    POTION_HARMING_1("POTION", 0, new UPotion(PotionBase.NORMAL, PotionType.INSTANT_DAMAGE, false, false)),
    POTION_HARMING_2("POTION", 0, new UPotion(PotionBase.NORMAL, PotionType.INSTANT_DAMAGE, false, true)),
    POTION_HEALING_1("POTION", 0, new UPotion(PotionBase.NORMAL, PotionType.INSTANT_HEAL, false, false)),
    POTION_HEALING_2("POTION", 0, new UPotion(PotionBase.NORMAL, PotionType.INSTANT_HEAL, false, true)),
    POTION_INVISIBILITY("POTION", 0, new UPotion(PotionBase.NORMAL, PotionType.INVISIBILITY, false, false)),
    POTION_INVISIBILITY_EXTENDED("POTION", 0, new UPotion(PotionBase.NORMAL, PotionType.INVISIBILITY, true, false)),
    POTION_LEAPING_1("POTION", 0, new UPotion(PotionBase.NORMAL, PotionType.JUMP, false, false)),
    POTION_LEAPING_1_EXTENDED("POTION", 0, new UPotion(PotionBase.NORMAL, PotionType.JUMP, true, false)),
    POTION_LEAPING_2("POTION", 0, new UPotion(PotionBase.NORMAL, PotionType.JUMP, false, true)),
    POTION_LUCK("POTION", 0, new UPotion(PotionBase.NORMAL, !Bukkit.getVersion().contains("1.8") ? PotionType.valueOf("LUCK") : PotionType.WATER, false, false)),
    POTION_MUNDANE("POTION", 0, new UPotion(PotionBase.NORMAL, PotionType.valueOf(!Bukkit.getVersion().contains("1.8") ? "MUNDANE" : "WATER"), false, false)),
    POTION_NIGHT_VISION("POTION", 0, new UPotion(PotionBase.NORMAL, PotionType.NIGHT_VISION, false, false)),
    POTION_NIGHT_VISION_EXTENDED("POTION", 0, new UPotion(PotionBase.NORMAL, PotionType.NIGHT_VISION, true, false)),
    POTION_POISON_1("POTION", 0, new UPotion(PotionBase.NORMAL, PotionType.POISON, false, false)),
    POTION_POISON_1_EXTENDED("POTION", 0, new UPotion(PotionBase.NORMAL, PotionType.POISON, true, false)),
    POTION_POISON_2("POTION", 0, new UPotion(PotionBase.NORMAL, PotionType.POISON, false, true)),
    POTION_REGENERATION_1("POTION", 0, new UPotion(PotionBase.NORMAL, PotionType.REGEN, false, false)),
    POTION_REGENERATION_1_EXTENDED("POTION", 0, new UPotion(PotionBase.NORMAL, PotionType.REGEN, true, false)),
    POTION_REGENERATION_2("POTION", 0, new UPotion(PotionBase.NORMAL, PotionType.REGEN, false, true)),
    POTION_SLOW_FALLING("POTION", 0, new UPotion(PotionBase.NORMAL, !Bukkit.getVersion().contains("1.8") && !Bukkit.getVersion().contains("1.9") && !Bukkit.getVersion().contains("1.10") && !Bukkit.getVersion().contains("1.11") && !Bukkit.getVersion().contains("1.12") ? PotionType.valueOf("SLOW_FALLING") : PotionType.WATER, false, false)),
    POTION_SLOW_FALLING_EXTENDED("POTION", 0, new UPotion(PotionBase.NORMAL, !Bukkit.getVersion().contains("1.8") && !Bukkit.getVersion().contains("1.9") && !Bukkit.getVersion().contains("1.10") && !Bukkit.getVersion().contains("1.11") && !Bukkit.getVersion().contains("1.12") ? PotionType.valueOf("SLOW_FALLING") : PotionType.WATER, true, false)),
    POTION_SLOWNESS_1("POTION", 0, new UPotion(PotionBase.NORMAL, PotionType.SLOWNESS, false, false)),
    POTION_SLOWNESS_1_EXTENDED("POTION", 0, new UPotion(PotionBase.NORMAL, PotionType.SLOWNESS, true, false)),
    POTION_SLOWNESS_2("POTION", 0, new UPotion(PotionBase.NORMAL, PotionType.SLOWNESS, false, true)),
    POTION_STRENGTH_1("POTION", 0, new UPotion(PotionBase.NORMAL, PotionType.STRENGTH, false, false)),
    POTION_STRENGTH_1_EXTENDED("POTION", 0, new UPotion(PotionBase.NORMAL, PotionType.STRENGTH, true, false)),
    POTION_STRENGTH_2("POTION", 0, new UPotion(PotionBase.NORMAL, PotionType.STRENGTH, false, true)),
    POTION_SWIFTNESS_1("POTION", 0, new UPotion(PotionBase.NORMAL, PotionType.SPEED, false, false)),
    POTION_SWIFTNESS_1_EXTENDED("POTION", 0, new UPotion(PotionBase.NORMAL, PotionType.SPEED, true, false)),
    POTION_SWIFTNESS_2("POTION", 0, new UPotion(PotionBase.NORMAL, PotionType.SPEED, false, true)),
    POTION_THICK("POTION", 0, new UPotion(PotionBase.NORMAL, PotionType.valueOf(!Bukkit.getVersion().contains("1.8") ? "THICK" : "WATER"), false, false)),
    POTION_TURTLE_MASTER_1("POTION", 0, new UPotion(PotionBase.NORMAL, !Bukkit.getVersion().contains("1.8") && !Bukkit.getVersion().contains("1.9") && !Bukkit.getVersion().contains("1.10") && !Bukkit.getVersion().contains("1.11") && !Bukkit.getVersion().contains("1.12") ? PotionType.valueOf("TURTLE_MASTER") : PotionType.WATER, false, false)),
    POTION_TURTLE_MASTER_1_EXTENDED("POTION", 0, new UPotion(PotionBase.NORMAL, !Bukkit.getVersion().contains("1.8") && !Bukkit.getVersion().contains("1.9") && !Bukkit.getVersion().contains("1.10") && !Bukkit.getVersion().contains("1.11") && !Bukkit.getVersion().contains("1.12") ? PotionType.valueOf("TURTLE_MASTER") : PotionType.WATER, true, false)),
    POTION_TURTLE_MASTER_2("POTION", 0, new UPotion(PotionBase.NORMAL, !Bukkit.getVersion().contains("1.8") && !Bukkit.getVersion().contains("1.9") && !Bukkit.getVersion().contains("1.10") && !Bukkit.getVersion().contains("1.11") && !Bukkit.getVersion().contains("1.12") ? PotionType.valueOf("TURTLE_MASTER") : PotionType.WATER, false, true)),
    POTION_WATER("POTION", 0, new UPotion(PotionBase.NORMAL, PotionType.WATER, false, false)),
    POTION_WATER_BREATHING("POTION", 0, new UPotion(PotionBase.NORMAL, PotionType.WATER_BREATHING, false, false)),
    POTION_WATER_BREATHING_EXTENDED("POTION", 0, new UPotion(PotionBase.NORMAL, PotionType.WATER_BREATHING, true, false)),
    POTION_WEAKNESS("POTION", 0, new UPotion(PotionBase.NORMAL, PotionType.WEAKNESS, false, false)),
    POTION_WEAKNESS_EXTENDED("POTION", 0, new UPotion(PotionBase.NORMAL, PotionType.WEAKNESS, true, false)),*/
    POTTED_ACACIA_SAPLING(null, null, null, null, null, "POTTED_ACACIA_SAPLING"),
    POTTED_ALLIUM(null, null, null, null, null, "POTTED_ALLIUM"),
    POTTED_AZURE_BLUET(null, null, null, null, null, "POTTED_AZURE_BLUET"),
    POTTED_BIRCH_SAPLING(null, null, null, null, null, "POTTED_BIRCH_SAPLING"),
    POTTED_BLUE_ORCHID(null, null, null, null, null, "POTTED_BLUE_ORCHID"),
    POTTED_BROWN_MUSHROOM(null, null, null, null, null, "POTTED_BROWN_MUSHROOM"),
    POTTED_CACTUS(null, null, null, null, null, "POTTED_CACTUS"),
    POTTED_CORNFLOWER(null, null, null, null, null, null, "POTTED_CORNFLOWER"),
    POTTED_DANDELION(null, null, null, null, null, "POTTED_DANDELION"),
    POTTED_DARK_OAK_SAPLING(null, null, null, null, null, "POTTED_DARK_OAK_SAPLING"),
    POTTED_DEAD_BUSH(null, null, null, null, null, "POTTED_DEAD_BUSH"),
    POTTED_FERN(null, null, null, null, null, "POTTED_FERN"),
    POTTED_JUNGLE_SAPLING(null, null, null, null, null, "POTTED_JUNGLE_SAPLING"),
    POTTED_LILY_OF_THE_VALLEY(null, null, null, null, null, null, "POTTED_LILY_OF_THE_VALLEY"),
    POTTED_OAK_SAPLING(null, null, null, null, null, "POTTED_OAK_SAPLING"),
    POTTED_ORANGE_TULIP(null, null, null, null, null, "POTTED_ORANGE_TULIP"),
    POTTED_OXEYE_DAISY(null, null, null, null, null, "POTTED_OXEYE_DAISY"),
    POTTED_PINK_TULIP(null, null, null, null, null, "POTTED_PINK_TULIP"),
    POTTED_POPPY(null, null, null, null, null, "POTTED_POPPY"),
    POTTED_RED_MUSHROOM(null, null, null, null, null, "POTTED_RED_MUSHROOM"),
    POTTED_RED_TULIP(null, null, null, null, null, "POTTED_RED_TULIP"),
    POTTED_SPRUCE_SAPLING(null, null, null, null, null, "POTTED_SPRUCE_SAPLING"),
    POTTED_WHITE_TULIP(null, null, null, null, null, "POTTED_WHITE_TULIP"),
    POTTED_WITHER_ROSE(null, null, null, null, null, null, "POTTED_WITHER_ROSE"),
    POWERED_RAIL("POWERED_RAIL"),
    PRISMARINE("PRISMARINE"),
    PRISMARINE_BRICK_SLAB(null, null, null, null, null, "PRISMARINE_BRICK_SLAB"),
    PRISMARINE_BRICK_STAIRS(null, null, null, null, null, "PRISMARINE_BRICK_STAIRS"),
    PRISMARINE_BRICKS(1, "PRISMARINE", null, null, null, null, "PRISMARINE_BRICKS"),
    PRISMARINE_CRYSTALS("PRISMARINE_CRYSTALS"),
    PRISMARINE_SHARD("PRISMARINE_SHARD"),
    PRISMARINE_SLAB(null, null, null, null, null, "PRISMARINE_SLAB"),
    PRISMARINE_STAIRS(null, null, null, null, null, "PRISMARINE_STAIRS"),
    PRISMARINE_WALL(null, null, null, null, null, null, "PRISMARINE_WALL"),
    PUFFERFISH(3, "RAW_FISH", null, null, null, null, "PUFFERFISH"),
    PUFFERFISH_BUCKET(null, null, null, null, null, "PUFFERFISH_BUCKET"),
    PUFFERFISH_SPAWN_EGG(null, null, null, null, null, "PUFFERFISH_SPAWN_EGG"),
    PUMPKIN("PUMPKIN"),
    PUMPKIN_PIE("PUMPKIN_PIE"),
    PUMPKIN_SEEDS("PUMPKIN_SEEDS"),
    PUMPKIN_STEM("PUMPKIN_STEM"),
    PURPLE_BANNER(5, "BANNER", null, null, null, null, "PURPLE_BANNER"),
    PURPLE_BED(10, "BED", null, null, null, null, "PURPLE_BED"),
    PURPLE_CARPET(10, "CARPET", null, null, null, null, "PURPLE_CARPET"),
    PURPLE_CONCRETE(10, null, null, null, null, "CONCRETE", "PURPLE_CONCRETE"),
    PURPLE_CONCRETE_POWDER(10, null, null, null, null, "CONCRETE_POWDER", "PURPLE_CONCRETE_POWDER"),
    PURPLE_DYE(5, "INK_SACK", null, null, null, null, "PURPLE_DYE"),
    PURPLE_GLAZED_TERRACOTTA(null, null, null, null, "PURPLE_GLAZED_TERRACOTTA"),
    PURPLE_LEATHER_BOOTS("LEATHER_BOOTS", 10, Color.fromRGB(127, 63, 178)),
    PURPLE_LEATHER_CHESTPLATE("LEATHER_CHESTPLATE", 10, Color.fromRGB(127, 63, 178)),
    PURPLE_LEATHER_HELMET("LEATHER_HELMET", 10, Color.fromRGB(127, 63, 178)),
    PURPLE_LEATHER_LEGGINGS("LEATHER_LEGGINGS", 10, Color.fromRGB(127, 63, 178)),
    PURPLE_SHULKER_BOX(null, null, null, "PURPLE_SHULKER_BOX"),
    PURPLE_STAINED_GLASS(10, "STAINED_GLASS", null, null, null, null, "PURPLE_STAINED_GLASS"),
    PURPLE_STAINED_GLASS_PANE(10, "STAINED_GLASS_PANE", null, null, null, null, "PURPLE_STAINED_GLASS_PANE"),
    PURPLE_TERRACOTTA(10, "STAINED_CLAY", null, null, null, null, "PURPLE_TERRACOTTA"),
    PURPLE_WALL_BANNER(5, "BANNER", null, null, null, null, "PURPLE_WALL_BANNER"),
    PURPLE_WOOL(10, "WOOL", null, null, null, null, "PURPLE_WOOL"),
    PURPUR_BLOCK(null, "PURPUR_BLOCK"),
    PURPUR_PILLAR(null, "PURPUR_PILLAR"),
    PURPUR_SLAB(null, "PURPUR_SLAB"),
    PURPUR_STAIRS(null, "PURPUR_STAIRS"),
    QUARTZ("QUARTZ"),
    QUARTZ_BLOCK("QUARTZ_BLOCK"),
    QUARTZ_PILLAR(2, "QUARTZ_BLOCK", null, null, null, null, "QUARTZ_PILLAR"),
    QUARTZ_SLAB(7, "STEP", null, null, null, null, "QUARTZ_SLAB"),
    QUARTZ_STAIRS("QUARTZ_STAIRS"),
    RABBIT("RABBIT"),
    RABBIT_FOOT("RABBIT_FOOT"),
    RABBIT_HIDE("RABBIT_HIDE"),
    RABBIT_SPAWN_EGG(101, "MONSTER_EGG", null, null, null, null, "RABBIT_SPAWN_EGG"),
    RABBIT_STEW("RABBIT_STEW"),
    RAIL("RAILS", null, null, null, null, "RAIL"),
    RAVAGER_SPAWN_EGG(null, null, null, null, null, null, "RAVAGER_SPAWN_EGG"),
    RED_BANNER(1, "BANNER", null, null, null, null, "RED_BANNER"),
    RED_BED(14, "BED", null, null, null, null, "RED_BED"),
    RED_CARPET(14, "CARPET", null, null, null, null, "RED_CARPET"),
    RED_CONCRETE(14, null, null, null, null, "CONCRETE", "RED_CONCRETE"),
    RED_CONCRETE_POWDER(14, null, null, null, null, "CONCRETE_POWDER", "RED_CONCRETE_POWDER"),
    RED_GLAZED_TERRACOTTA(null, null, null, null, "RED_GLAZED_TERRACOTTA"),
    RED_LEATHER_BOOTS("LEATHER_BOOTS", 14, Color.fromRGB(153, 51, 51)),
    RED_LEATHER_CHESTPLATE("LEATHER_CHESTPLATE", 14, Color.fromRGB(153, 51, 51)),
    RED_LEATHER_HELMET("LEATHER_HELMET", 14, Color.fromRGB(153, 51, 51)),
    RED_LEATHER_LEGGINGS("LEATHER_LEGGINGS", 14, Color.fromRGB(153, 51, 51)),
    RED_MUSHROOM("RED_MUSHROOM"),
    RED_MUSHROOM_BLOCK(14, "HUGE_MUSHROOM_2", null, null, null, null, "RED_MUSHROOM_BLOCK"),
    RED_NETHER_BRICKS(null, null, "RED_NETHER_BRICK", null, null, "RED_NETHER_BRICKS"),
    RED_NETHER_BRICK_SLAB(null, null, null, null, null, null, "RED_NETHER_BRICK_SLAB"),
    RED_NETHER_BRICK_STAIRS(null, null, null, null, null, null, "RED_NETHER_BRICK_STAIRS"),
    RED_NETHER_BRICK_WALL(null, null, null, null, null, null, "RED_NETHER_BRICK_WALL"),
    RED_SAND(1, "SAND", null, null, null, null, "RED_SAND"),
    RED_SANDSTONE("RED_SANDSTONE"),
    RED_SANDSTONE_SLAB("STONE_SLAB2", null, null, null, null, "RED_SANDSTONE_SLAB"),
    RED_SANDSTONE_STAIRS("RED_SANDSTONE_STAIRS"),
    RED_SANDSTONE_WALL(null, null, null, null, null, null, "RED_SANDSTONE_WALL"),
    RED_SHULKER_BOX(null, null, null, "RED_SHULKER_BOX"),
    RED_STAINED_GLASS(14, "STAINED_GLASS", null, null, null, null, "RED_STAINED_GLASS"),
    RED_STAINED_GLASS_PANE(14, "STAINED_GLASS_PANE", null, null, null, null, "RED_STAINED_GLASS_PANE"),
    RED_TERRACOTTA(14, "STAINED_CLAY", null, null, null, null, "RED_TERRACOTTA"),
    RED_TULIP(4, "RED_ROSE", null, null, null, null, "RED_TULIP"),
    RED_WALL_BANNER(1, "BANNER", null, null, null, null, "RED_WALL_BANNER"),
    RED_WOOL(14, "WOOL", null, null, null, null, "RED_WOOL"),
    REDSTONE("REDSTONE"),
    REDSTONE_BLOCK("REDSTONE_BLOCK"),
    REDSTONE_LAMP("REDSTONE_LAMP_OFF", null, null, null, null, "REDSTONE_LAMP"),
    REDSTONE_LAMP_ON("REDSTONE_LAMP_ON", null, null, null, null, "REDSTONE_LAMP"),
    REDSTONE_ORE("REDSTONE_ORE"),
    REDSTONE_TORCH("REDSTONE_TORCH_OFF", null, null, null, null, "REDSTONE_TORCH"),
    REDSTONE_TORCH_ON("REDSTONE_TORCH_ON", null, null, null, null, "REDSTONE_TORCH"),
    REDSTONE_WALL_TORCH("REDSTONE_TORCH_OFF", null, null, null, null, "REDSTONE_WALL_TORCH"),
    REDSTONE_WIRE("REDSTONE_WIRE"),
    REPEATER("DIODE", null, null, null, null, "REPEATER"),
    REPEATING_COMMAND_BLOCK("COMMAND", "COMMAND_REPEATING", null, null, null, "REPEATING_COMMAND_BLOCK"),
    ROSE_BUSH(4, "DOUBLE_PLANT", null, null, null, null, "ROSE_BUSH"),
    ROSE_RED(1, "INK_SACK", null, null, null, null, "ROSE_RED", "RED_DYE"),
    ROTTEN_FLESH("ROTTEN_FLESH"),
    SADDLE("SADDLE"),
    SALMON(1, "RAW_FISH", null, null, null, null, "SALMON"),
    SALMON_BUCKET(null, null, null, null, null, "SALMON_BUCKET"),
    SALMON_SPAWN_EGG(null, null, null, null, null, "SALMON_SPAWN_EGG"),
    SAND("SAND"),
    SANDSTONE("SANDSTONE"),
    SANDSTONE_SLAB(1, "STEP", null, null, null, null, "SANDSTONE_SLAB"),
    SANDSTONE_STAIRS("SANDSTONE_STAIRS"),
    SANDSTONE_WALL(null, null, null, null, null, null, "SANDSTONE_WALL"),
    SCAFFOLDING(null, null, null, null, null, null, "SCAFFOLDING"),
    SCUTE(null, null, null, null, null, "SCUTE"),
    SEA_LANTERN("SEA_LANTERN"),
    SEA_PICKLE(null, null, null, null, null, "SEA_PICKLE"),
    SEAGRASS(null, null, null, null, null, "SEAGRASS"),
    SHEARS("SHEARS"),
    SHEEP_SPAWN_EGG(91, "MONSTER_EGG", null, null, null, null, "SHEEP_SPAWN_EGG"),
    SHIELD(null, "SHIELD"),
    SHULKER_BOX(null, null, null, "WHITE_SHULKER_BOX"),
    SHULKER_SHELL(null, null, null, "SHULKER_SHELL"),
    SHULKER_SPAWN_EGG(69, null, null, null, "MONSTER_EGG", null, "SHULKER_SPAWN_EGG"),
    SIGN_POST("SIGN_POST", null, null, null, null, null, "LEGACY_SIGN_POST"),
    SILVERFISH_SPAWN_EGG(60, "MONSTER_EGG", null, null, null, null, "SILVERFISH_SPAWN_EGG"),
    SKELETON_HORSE_SPAWN_EGG(null, null, null, null, null, "SKELETON_HORSE_SPAWN_EGG"),
    SKELETON_SKULL("SKULL", null, null, null, null, "SKELETON_SKULL"),
    SKELETON_SKULL_ITEM("SKULL_ITEM", null, null, null, null, "SKELETON_SKULL"),
    SKELETON_SPAWN_EGG(51, "MONSTER_EGG", null, null, null, null, "SKELETON_SPAWN_EGG"),
    SKELETON_WALL_SKULL("SKULL", null, null, null, null, "SKELETON_WALL_SKULL"),
    SLIME_BALL("SLIME_BALL"),
    SLIME_BLOCK("SLIME_BLOCK"),
    SLIME_SPAWN_EGG(55, "MONSTER_EGG", null, null, null, null, "SLIME_SPAWN_EGG"),
    SMITHING_TABLE(null, null, null, null, null, null, "SMITHING_TABLE"),
    SMOKER(null, null, null, null, null, null, "SMOKER"),
    SMOOTH_QUARTZ(null, null, null, null, null, "SMOOTH_QUARTZ"),
    SMOOTH_QUARTZ_SLAB(null, null, null, null, null, null, "SMOOTH_QUARTZ_SLAB"),
    SMOOTH_QUARTZ_STAIRS(null, null, null, null, null, null, "SMOOTH_QUARTZ_STAIRS"),
    SMOOTH_RED_SANDSTONE(8, "DOUBLE_STONE_SLAB2", null, null, null, null, "SMOOTH_RED_SANDSTONE"),
    SMOOTH_RED_SANDSTONE_SLAB(null, null, null, null, null, null, "SMOOTH_RED_SANDSTONE_SLAB"),
    SMOOTH_RED_SANDSTONE_STAIRS(null, null, null, null, null, null, "SMOOTH_RED_SANDSTONE_STAIRS"),
    SMOOTH_SANDSTONE(9, "DOUBLE_STEP", null, null, null, null, "SMOOTH_SANDSTONE"),
    SMOOTH_SANDSTONE_SLAB(null, null, null, null, null, null, "SMOOTH_SANDSTONE_SLAB"),
    SMOOTH_SANDSTONE_STAIRS(null, null, null, null, null, null, "SMOOTH_SANDSTONE_STAIRS"),
    SMOOTH_STONE(8, "DOUBLE_STEP", null, null, null, null, "SMOOTH_STONE"),
    SNOW("SNOW"),
    SNOW_BLOCK("SNOW_BLOCK"),
    SNOWBALL("SNOW_BALL", null, null, null, null, "SNOWBALL"),
    SOUL_SAND("SOUL_SAND"),
    SPAWN_EGG("MONSTER_EGG", null, null, null, null, "LEGACY_MONSTER_EGG"),
    SPAWNER("MOB_SPAWNER", null, null, null, null, "SPAWNER"),
    SPECTRAL_ARROW("ARROW", "SPECTRAL_ARROW"),
    SPIDER_EYE("SPIDER_EYE"),
    SPIDER_SPAWN_EGG(52, "MONSTER_EGG", null, null, null, null, "SPIDER_SPAWN_EGG"),
    SPLASH_POTION(null, "SPLASH_POTION"),
    /*SPLASH_POTION_AWKWARD(new UPotion(PotionBase.SPLASH, PotionType.valueOf(!Bukkit.getVersion().contains("1.8") ? "AWKWARD" : "WATER"), false, false), 0, null, "SPLASH_POTION"),
    SPLASH_POTION_FIRE_RESISTANCE(new UPotion(PotionBase.SPLASH, PotionType.FIRE_RESISTANCE, false, false), 0, null, "SPLASH_POTION"),
    SPLASH_POTION_FIRE_RESISTANCE_EXTENDED(new UPotion(PotionBase.SPLASH, PotionType.FIRE_RESISTANCE, true, false), 0, null, "SPLASH_POTION"),
    SPLASH_POTION_HARMING_1(new UPotion(PotionBase.SPLASH, PotionType.INSTANT_DAMAGE, false, false), 0, null, "SPLASH_POTION"),
    SPLASH_POTION_HARMING_2(new UPotion(PotionBase.SPLASH, PotionType.INSTANT_DAMAGE, false, true), 0, null, "SPLASH_POTION"),
    SPLASH_POTION_HEALING_1(new UPotion(PotionBase.SPLASH, PotionType.INSTANT_HEAL, false, false), 0, null, "SPLASH_POTION"),
    SPLASH_POTION_HEALING_2(new UPotion(PotionBase.SPLASH, PotionType.INSTANT_HEAL, false, true), 0, null, "SPLASH_POTION"),
    SPLASH_POTION_INVISIBILITY(new UPotion(PotionBase.SPLASH, PotionType.INVISIBILITY, false, false), 0, null, "SPLASH_POTION"),
    SPLASH_POTION_INVISIBILITY_EXTENDED(new UPotion(PotionBase.SPLASH, PotionType.INVISIBILITY, true, false), 0, null, "SPLASH_POTION"),
    SPLASH_POTION_LEAPING_1(new UPotion(PotionBase.SPLASH, PotionType.JUMP, false, false), 0, null, "SPLASH_POTION"),
    SPLASH_POTION_LEAPING_1_EXTENDED(new UPotion(PotionBase.SPLASH, PotionType.JUMP, true, false), 0, null, "SPLASH_POTION"),
    SPLASH_POTION_LEAPING_2(new UPotion(PotionBase.SPLASH, PotionType.JUMP, false, true), 0, null, "SPLASH_POTION"),
    SPLASH_POTION_LUCK(new UPotion(PotionBase.SPLASH, !Bukkit.getVersion().contains("1.8") ? PotionType.valueOf("LUCK") : PotionType.WATER, false, false), 0, null, "SPLASH_POTION"),
    SPLASH_POTION_MUNDANE(new UPotion(PotionBase.SPLASH, PotionType.valueOf(!Bukkit.getVersion().contains("1.8") ? "MUNDANE" : "WATER"), false, false), 0, null, "SPLASH_POTION"),
    SPLASH_POTION_NIGHT_VISION(new UPotion(PotionBase.SPLASH, PotionType.NIGHT_VISION, false, false), 0, null, "SPLASH_POTION"),
    SPLASH_POTION_NIGHT_VISION_EXTENDED(new UPotion(PotionBase.SPLASH, PotionType.NIGHT_VISION, true, false), 0, null, "SPLASH_POTION"),
    SPLASH_POTION_POISON_1(new UPotion(PotionBase.SPLASH, PotionType.POISON, false, false), 0, null, "SPLASH_POTION"),
    SPLASH_POTION_POISON_1_EXTENDED(new UPotion(PotionBase.SPLASH, PotionType.POISON, true, false), 0, null, "SPLASH_POTION"),
    SPLASH_POTION_POISON_2(new UPotion(PotionBase.SPLASH, PotionType.POISON, false, true), 0, null, "SPLASH_POTION"),
    SPLASH_POTION_REGENERATION_1(new UPotion(PotionBase.SPLASH, PotionType.REGEN, false, false), 0, null, "SPLASH_POTION"),
    SPLASH_POTION_REGENERATION_1_EXTENDED(new UPotion(PotionBase.SPLASH, PotionType.REGEN, true, false), 0, null, "SPLASH_POTION"),
    SPLASH_POTION_REGENERATION_2(new UPotion(PotionBase.SPLASH, PotionType.REGEN, false, true), 0, null, "SPLASH_POTION"),
    SPLASH_POTION_SLOW_FALLING(new UPotion(PotionBase.SPLASH, !Bukkit.getVersion().contains("1.8") && !Bukkit.getVersion().contains("1.9") && !Bukkit.getVersion().contains("1.10") && !Bukkit.getVersion().contains("1.11") && !Bukkit.getVersion().contains("1.12") ? PotionType.valueOf("SLOW_FALLING") : PotionType.WATER, false, false), 0, null, "SPLASH_POTION"),
    SPLASH_POTION_SLOW_FALLING_EXTENDED(new UPotion(PotionBase.SPLASH, !Bukkit.getVersion().contains("1.8") && !Bukkit.getVersion().contains("1.9") && !Bukkit.getVersion().contains("1.10") && !Bukkit.getVersion().contains("1.11") && !Bukkit.getVersion().contains("1.12") ? PotionType.valueOf("SLOW_FALLING") : PotionType.WATER, true, false), 0, null, "SPLASH_POTION"),
    SPLASH_POTION_SLOWNESS_1(new UPotion(PotionBase.SPLASH, PotionType.SLOWNESS, false, false), 0, null, "SPLASH_POTION"),
    SPLASH_POTION_SLOWNESS_1_EXTENDED(new UPotion(PotionBase.SPLASH, PotionType.SLOWNESS, true, false), 0, null, "SPLASH_POTION"),
    SPLASH_POTION_SLOWNESS_2(new UPotion(PotionBase.SPLASH, PotionType.SLOWNESS, false, true), 0, null, "SPLASH_POTION"),
    SPLASH_POTION_STRENGTH_1(new UPotion(PotionBase.SPLASH, PotionType.STRENGTH, false, false), 0, null, "SPLASH_POTION"),
    SPLASH_POTION_STRENGTH_1_EXTENDED(new UPotion(PotionBase.SPLASH, PotionType.STRENGTH, true, false), 0, null, "SPLASH_POTION"),
    SPLASH_POTION_STRENGTH_2(new UPotion(PotionBase.SPLASH, PotionType.STRENGTH, false, true), 0, null, "SPLASH_POTION"),
    SPLASH_POTION_SWIFTNESS_1(new UPotion(PotionBase.SPLASH, PotionType.SPEED, false, false), 0, null, "SPLASH_POTION"),
    SPLASH_POTION_SWIFTNESS_1_EXTENDED(new UPotion(PotionBase.SPLASH, PotionType.SPEED, true, false), 0, null, "SPLASH_POTION"),
    SPLASH_POTION_SWIFTNESS_2(new UPotion(PotionBase.SPLASH, PotionType.SPEED, false, true), 0, null, "SPLASH_POTION"),
    SPLASH_POTION_THICK(new UPotion(PotionBase.SPLASH, PotionType.valueOf(!Bukkit.getVersion().contains("1.8") ? "THICK" : "WATER"), false, false), 0, null, "SPLASH_POTION"),
    SPLASH_POTION_TURTLE_MASTER_1(new UPotion(PotionBase.SPLASH, !Bukkit.getVersion().contains("1.8") && !Bukkit.getVersion().contains("1.9") && !Bukkit.getVersion().contains("1.10") && !Bukkit.getVersion().contains("1.11") && !Bukkit.getVersion().contains("1.12") ? PotionType.valueOf("TURTLE_MASTER") : PotionType.WATER, false, false), 0, null, "SPLASH_POTION"),
    SPLASH_POTION_TURTLE_MASTER_1_EXTENDED(new UPotion(PotionBase.SPLASH, !Bukkit.getVersion().contains("1.8") && !Bukkit.getVersion().contains("1.9") && !Bukkit.getVersion().contains("1.10") && !Bukkit.getVersion().contains("1.11") && !Bukkit.getVersion().contains("1.12") ? PotionType.valueOf("TURTLE_MASTER") : PotionType.WATER, true, false), 0, null, "SPLASH_POTION"),
    SPLASH_POTION_TURTLE_MASTER_2(new UPotion(PotionBase.SPLASH, !Bukkit.getVersion().contains("1.8") && !Bukkit.getVersion().contains("1.9") && !Bukkit.getVersion().contains("1.10") && !Bukkit.getVersion().contains("1.11") && !Bukkit.getVersion().contains("1.12") ? PotionType.valueOf("TURTLE_MASTER") : PotionType.WATER, false, true), 0, null, "SPLASH_POTION"),
    SPLASH_POTION_WATER(new UPotion(PotionBase.SPLASH, PotionType.WATER, false, false), 0, null, "SPLASH_POTION"),
    SPLASH_POTION_WATER_BREATHING(new UPotion(PotionBase.SPLASH, PotionType.WATER_BREATHING, false, false), 0, null, "SPLASH_POTION"),
    SPLASH_POTION_WATER_BREATHING_EXTENDED(new UPotion(PotionBase.SPLASH, PotionType.WATER_BREATHING, true, false), 0, null, "SPLASH_POTION"),
    SPLASH_POTION_WEAKNESS(new UPotion(PotionBase.SPLASH, PotionType.WEAKNESS, false, false), 0, null, "SPLASH_POTION"),
    SPLASH_POTION_WEAKNESS_EXTENDED(new UPotion(PotionBase.SPLASH, PotionType.WEAKNESS, true, false), 0, null, "SPLASH_POTION"),
    */
    SPRUCE_SIGN(null, null, null, null, null, null, "SPRUCE_SIGN"),
    SPRUCE_WALL_SIGN(null, null, null, null, null, null, "SPRUCE_WALL_SIGN"),
    SPONGE("SPONGE"),
    SPRUCE_BOAT("BOAT", null, null, null, "BOAT_SPRUCE", "SPRUCE_BOAT"),
    SPRUCE_BUTTON("WOOD_BUTTON", null, null, null, null, "SPRUCE_BUTTON"),
    SPRUCE_DOOR("SPRUCE_DOOR"),
    SPRUCE_DOOR_ITEM("SPRUCE_DOOR_ITEM", null, null, null, null, "SPRUCE_DOOR"),
    SPRUCE_FENCE("SPRUCE_FENCE"),
    SPRUCE_FENCE_GATE("SPRUCE_FENCE_GATE"),
    SPRUCE_LEAVES(1, "LEAVES", null, null, null, null, "SPRUCE_LEAVES"),
    SPRUCE_LOG(1, "LOG", null, null, null, null, "SPRUCE_LOG"),
    SPRUCE_PLANKS(1, "WOOD", null, null, null, null, "SPRUCE_PLANKS"),
    SPRUCE_PRESSURE_PLATE("WOOD_PLATE", null, null, null, null, "SPRUCE_PRESSURE_PLATE"),
    SPRUCE_SAPLING(1, "SAPLING", null, null, null, null, "SPRUCE_SAPLING"),
    SPRUCE_SLAB(1, "WOOD_STEP", null, null, null, null, "SPRUCE_SLAB"),
    SPRUCE_STAIRS("SPRUCE_WOOD_STAIRS", null, null, null, null, "SPRUCE_STAIRS"),
    SPRUCE_TRAPDOOR("TRAP_DOOR", null, null, null, null, "SPRUCE_TRAPDOOR"),
    SPRUCE_WOOD(13, "LOG", null, null, null, null, "SPRUCE_WOOD"),
    STONECUTTER(null, null, null, null, null, null, "STONECUTTER"),
    SQUID_SPAWN_EGG(94, "MONSTER_EGG", null, null, null, null, "SQUID_SPAWN_EGG"),
    STICK("STICK"),
    STICKY_PISTON("PISTON_STICKY_BASE", null, null, null, null, "STICKY_PISTON"),
    STONE("STONE"),
    STONE_AXE("STONE_AXE"),
    STONE_BRICKS("SMOOTH_BRICK", null, null, null, null, "STONE_BRICKS"),
    STONE_BRICK_SLAB(5, "STEP", null, null, null, null, "STONE_BRICK_SLAB"),
    STONE_BRICK_STAIRS("SMOOTH_STAIRS", null, null, null, null, "STONE_BRICK_STAIRS"),
    STONE_BRICK_WALL(null, null, null, null, null, null, "STONE_BRICK_WALL"),
    STONE_BUTTON("STONE_BUTTON"),
    STONE_HOE("STONE_HOE"),
    STONE_PICKAXE("STONE_PICKAXE"),
    STONE_PRESSURE_PLATE("STONE_PLATE", null, null, null, null, "STONE_PRESSURE_PLATE"),
    STONE_SHOVEL("STONE_SPADE", null, null, null, null, "STONE_SHOVEL"),
    STONE_SLAB("STEP", null, null, null, null, "STONE_SLAB"),
    STONE_STAIRS(null, null, null, null, null, null, "STONE_STAIRS"),
    STONE_SWORD("STONE_SWORD"),
    STRAY_SPAWN_EGG(null, null, "MONSTER_EGG", null, null, "STRAY_SPAWN_EGG"),
    STRING("STRING"),
    STRIPPED_ACACIA_LOG(null, null, null, null, null, "STRIPPED_ACACIA_LOG"),
    STRIPPED_ACACIA_WOOD(null, null, null, null, null, "STRIPPED_ACACIA_WOOD"),
    STRIPPED_BIRCH_LOG(null, null, null, null, null, "STRIPPED_BIRCH_LOG"),
    STRIPPED_BIRCH_WOOD(null, null, null, null, null, "STRIPPED_BIRCH_WOOD"),
    STRIPPED_DARK_OAK_LOG(null, null, null, null, null, "STRIPPED_DARK_OAK_LOG"),
    STRIPPED_DARK_OAK_WOOD(null, null, null, null, null, "STRIPPED_DARK_OAK_WOOD"),
    STRIPPED_JUNGLE_LOG(null, null, null, null, null, "STRIPPED_JUNGLE_LOG"),
    STRIPPED_JUNGLE_WOOD(null, null, null, null, null, "STRIPPED_JUNGLE_WOOD"),
    STRIPPED_OAK_LOG(null, null, null, null, null, "STRIPPED_OAK_LOG"),
    STRIPPED_OAK_WOOD(null, null, null, null, null, "STRIPPED_OAK_WOOD"),
    STRIPPED_SPRUCE_LOG(null, null, null, null, null, "STRIPPED_SPRUCE_LOG"),
    STRIPPED_SPRUCE_WOOD(null, null, null, null, null, "STRIPPED_SPRUCE_WOOD"),
    STRUCTURE_BLOCK(null, "STRUCTURE_BLOCK"),
    SUGAR("SUGAR"),
    SUGAR_CANE_ITEM("SUGAR_CANE"),
    SUNFLOWER("DOUBLE_PLANT", null, null, null, null, "SUNFLOWER"),
    SUSPICIOUS_STEW(null, null, null, null, null, null, "SUSPICIOUS_STEW"),
    SWEET_BERRIES(null, null, null, null, null, null, "SWEET_BERRIES"),
    SWEET_BERRY_BUSH(null, null, null, null, null, null, "SWEET_BERRY_BUSH"),
    TALL_GRASS(1, "LONG_GRASS", null, null, null, null, "TALL_GRASS"),
    TALL_GRASS_BOTTOM(2, "DOUBLE_PLANT", null, null, null, null, "TALL_GRASS"),
    TALL_GRASS_TOP(10, 2, "DOUBLE_PLANT", null, null, null, null, "TALL_GRASS"),
    TALL_SEAGRASS(null, null, null, null, null, "TALL_SEAGRASS"),
    TERRACOTTA("HARD_CLAY", null, null, null, null, "TERRACOTTA"),
    TIPPED_ARROW("ARROW", null, null, null, "TIPPED_ARROW"),
    /*TIPPED_ARROW_FIRE_RESISTANCE(new UPotion(PotionBase.ARROW, PotionType.FIRE_RESISTANCE, false, false), 0, "ARROW", "TIPPED_ARROW"),
    TIPPED_ARROW_FIRE_RESISTANCE_EXTENDED(new UPotion(PotionBase.ARROW, PotionType.FIRE_RESISTANCE, true, false), 0, "ARROW", "TIPPED_ARROW"),
    TIPPED_ARROW_HARMING_1(new UPotion(PotionBase.ARROW, PotionType.INSTANT_DAMAGE, false, false), 0, "ARROW", "TIPPED_ARROW"),
    TIPPED_ARROW_HARMING_2(new UPotion(PotionBase.ARROW, PotionType.INSTANT_DAMAGE, false, true), 0, "ARROW", "TIPPED_ARROW"),
    TIPPED_ARROW_HEALING_1(new UPotion(PotionBase.ARROW, PotionType.INSTANT_HEAL, false, false), 0, "ARROW", "TIPPED_ARROW"),
    TIPPED_ARROW_HEALING_2(new UPotion(PotionBase.ARROW, PotionType.INSTANT_HEAL, false, true), 0, "ARROW", "TIPPED_ARROW"),
    TIPPED_ARROW_INVISIBILITY(new UPotion(PotionBase.ARROW, PotionType.INVISIBILITY, false, false), 0, "ARROW", "TIPPED_ARROW"),
    TIPPED_ARROW_INVISIBILITY_EXTENDED(new UPotion(PotionBase.ARROW, PotionType.INVISIBILITY, true, false), 0, "ARROW", "TIPPED_ARROW"),
    TIPPED_ARROW_LEAPING_1(new UPotion(PotionBase.ARROW, PotionType.JUMP, false, false), 0, "ARROW", "TIPPED_ARROW"),
    TIPPED_ARROW_LEAPING_1_EXTENDED(new UPotion(PotionBase.ARROW, PotionType.JUMP, true, false), 0, "ARROW", "TIPPED_ARROW"),
    TIPPED_ARROW_LEAPING_2(new UPotion(PotionBase.ARROW, PotionType.JUMP, false, true), 0, "ARROW", "TIPPED_ARROW"),
    TIPPED_ARROW_LUCK(new UPotion(PotionBase.ARROW, PotionType.valueOf(!Bukkit.getVersion().contains("1.8") ? "LUCK" : "JUMP"), false, false), 0, "ARROW", "TIPPED_ARROW"),
    TIPPED_ARROW_NIGHT_VISION(new UPotion(PotionBase.ARROW, PotionType.NIGHT_VISION, false, false), 0, "ARROW", "TIPPED_ARROW"),
    TIPPED_ARROW_NIGHT_VISION_EXTENDED(new UPotion(PotionBase.ARROW, PotionType.NIGHT_VISION, true, false), 0, "ARROW", "TIPPED_ARROW"),
    TIPPED_ARROW_POISON_1(new UPotion(PotionBase.ARROW, PotionType.POISON, false, false), 0, "ARROW", "TIPPED_ARROW"),
    TIPPED_ARROW_POISON_1_EXTENDED(new UPotion(PotionBase.ARROW, PotionType.POISON, true, false), 0, "ARROW", "TIPPED_ARROW"),
    TIPPED_ARROW_POISON_2(new UPotion(PotionBase.ARROW, PotionType.POISON, false, true), 0, "ARROW", "TIPPED_ARROW"),
    TIPPED_ARROW_REGENERATION_1(new UPotion(PotionBase.ARROW, PotionType.REGEN, false, false), 0, "ARROW", "TIPPED_ARROW"),
    TIPPED_ARROW_REGENERATION_1_EXTENDED(new UPotion(PotionBase.ARROW, PotionType.REGEN, true, false), 0, "ARROW", "TIPPED_ARROW"),
    TIPPED_ARROW_REGENERATION_2(new UPotion(PotionBase.ARROW, PotionType.REGEN, false, true), 0, "ARROW", "TIPPED_ARROW"),
    TIPPED_ARROW_SLOW_FALLING(new UPotion(PotionBase.ARROW, PotionType.valueOf(!Bukkit.getVersion().contains("1.8") && !Bukkit.getVersion().contains("1.9") && !Bukkit.getVersion().contains("1.10") && !Bukkit.getVersion().contains("1.11") && !Bukkit.getVersion().contains("1.12") ? "SLOW_FALLING" : "SLOWNESS"), false, false), 0, "ARROW", "TIPPED_ARROW"),
    TIPPED_ARROW_SLOW_FALLING_EXTENDED(new UPotion(PotionBase.ARROW, PotionType.valueOf(!Bukkit.getVersion().contains("1.8") && !Bukkit.getVersion().contains("1.9") && !Bukkit.getVersion().contains("1.10") && !Bukkit.getVersion().contains("1.11") && !Bukkit.getVersion().contains("1.12") ? "SLOW_FALLING" : "SLOWNESS"), true, false), 0, "ARROW", "TIPPED_ARROW"),
    TIPPED_ARROW_SLOWNESS_1(new UPotion(PotionBase.ARROW, PotionType.SLOWNESS, false, false), 0, "ARROW", "TIPPED_ARROW"),
    TIPPED_ARROW_SLOWNESS_1_EXTENDED(new UPotion(PotionBase.ARROW, PotionType.SLOWNESS, true, false), 0, "ARROW", "TIPPED_ARROW"),
    TIPPED_ARROW_SLOWNESS_2(new UPotion(PotionBase.ARROW, PotionType.SLOWNESS, false, true), 0, "ARROW", "TIPPED_ARROW"),
    TIPPED_ARROW_STRENGTH_1(new UPotion(PotionBase.ARROW, PotionType.STRENGTH, false, false), 0, "ARROW", "TIPPED_ARROW"),
    TIPPED_ARROW_STRENGTH_1_EXTENDED(new UPotion(PotionBase.ARROW, PotionType.STRENGTH, true, false), 0, "ARROW", "TIPPED_ARROW"),
    TIPPED_ARROW_STRENGTH_2(new UPotion(PotionBase.ARROW, PotionType.STRENGTH, false, true), 0, "ARROW", "TIPPED_ARROW"),
    TIPPED_ARROW_SWIFTNESS_1(new UPotion(PotionBase.ARROW, PotionType.SPEED, false, false), 0, "ARROW", "TIPPED_ARROW"),
    TIPPED_ARROW_SWIFTNESS_1_EXTENDED(new UPotion(PotionBase.ARROW, PotionType.SPEED, true, false), 0, "ARROW", "TIPPED_ARROW"),
    TIPPED_ARROW_SWIFTNESS_2(new UPotion(PotionBase.ARROW, PotionType.SPEED, false, true), 0, "ARROW", "TIPPED_ARROW"),
    TIPPED_ARROW_TURTLE_MASTER_1(new UPotion(PotionBase.ARROW, PotionType.valueOf(!Bukkit.getVersion().contains("1.8") && !Bukkit.getVersion().contains("1.9") && !Bukkit.getVersion().contains("1.10") && !Bukkit.getVersion().contains("1.11") && !Bukkit.getVersion().contains("1.12") ? "TURTLE_MASTER" : "SLOWNESS"), false, false), 0, "ARROW", "TIPPED_ARROW"),
    TIPPED_ARROW_TURTLE_MASTER_1_EXTENDED(new UPotion(PotionBase.ARROW, PotionType.valueOf(!Bukkit.getVersion().contains("1.8") && !Bukkit.getVersion().contains("1.9") && !Bukkit.getVersion().contains("1.10") && !Bukkit.getVersion().contains("1.11") && !Bukkit.getVersion().contains("1.12") ? "TURTLE_MASTER" : "SLOWNESS"), true, false), 0, "ARROW", "TIPPED_ARROW"),
    TIPPED_ARROW_TURTLE_MASTER_2(new UPotion(PotionBase.ARROW, PotionType.valueOf(!Bukkit.getVersion().contains("1.8") && !Bukkit.getVersion().contains("1.9") && !Bukkit.getVersion().contains("1.10") && !Bukkit.getVersion().contains("1.11") && !Bukkit.getVersion().contains("1.12") ? "TURTLE_MASTER" : "SLOWNESS"), false, true), 0, "ARROW", "TIPPED_ARROW"),
    TIPPED_ARROW_WATER_BREATHING(new UPotion(PotionBase.ARROW, PotionType.WATER_BREATHING, false, false), 0, "ARROW", "TIPPED_ARROW"),
    TIPPED_ARROW_WATER_BREATHING_EXTENDED(new UPotion(PotionBase.ARROW, PotionType.WATER_BREATHING, true, false), 0, "ARROW", "TIPPED_ARROW"),
    TIPPED_ARROW_WEAKNESS(new UPotion(PotionBase.ARROW, PotionType.WEAKNESS, false, false), 0, "ARROW", "TIPPED_ARROW"),
    TIPPED_ARROW_WEAKNESS_EXTENDED(new UPotion(PotionBase.ARROW, PotionType.WEAKNESS, true, false), 0, "ARROW", "TIPPED_ARROW"),
    */
    TNT("TNT"),
    TNT_MINECART("EXPLOSIVE_MINECART", null, null, null, null, "TNT_MINECART"),
    TORCH("TORCH"),
    TOTEM_OF_UNDYING(null, null, null, "TOTEM", null, "TOTEM_OF_UNDYING"),
    TRADER_LLAMA_SPAWN_EGG(null, null, null, null, null, null, "TRADER_LLAMA_SPAWN_EGG"),
    TRAPPED_CHEST("TRAPPED_CHEST"),
    WANDERING_TRADER_LLAMA_SPAWN_EGG(null, null, null, null, null, null, "WANDERING_TRADER_LLAMA_SPAWN_EGG"),
    TRIDENT(null, null, null, null, null, "TRIDENT"),
    TRIPWIRE("TRIPWIRE"),
    TRIPWIRE_HOOK("TRIPWIRE_HOOK"),
    TROPICAL_FISH("RAW_FISH", null, null, null, null, "TROPICAL_FISH"),
    TROPICAL_FISH_BUCKET(null, null, null, null, null, "TROPICAL_FISH_BUCKET"),
    TROPICAL_FISH_SPAWN_EGG(null, null, null, null, null, "TROPICAL_FISH_SPAWN_EGG"),
    TUBE_CORAL(null, null, null, null, null, "TUBE_CORAL"),
    TUBE_CORAL_BLOCK(null, null, null, null, null, "TUBE_CORAL_BLOCK"),
    TUBE_CORAL_FAN(null, null, null, null, null, "TUBE_CORAL_FAN"),
    TUBE_CORAL_WALL_FAN(null, null, null, null, null, "TUBE_CORAL_WALL_FAN"),
    TURTLE_EGG(null, null, null, null, null, "TURTLE_EGG"),
    TURTLE_HELMET(null, null, null, null, null, "TURTLE_HELMET"),
    TURTLE_SPAWN_EGG(null, null, null, null, null, "TURTLE_SPAWN_EGG"),
    VEX_SPAWN_EGG(35, null, null, null, "MONSTER_EGG", null, "VEX_SPAWN_EGG"),
    VILLAGER_SPAWN_EGG(120, "MONSTER_EGG", null, null, null, null, "VILLAGER_SPAWN_EGG"),
    VINDICATOR_SPAWN_EGG(36, null, null, null, "MONSTER_EGG", null, "VINDICATOR_SPAWN_EGG"),
    VINE("VINE", 0),
    VOID_AIR("AIR", null, null, null, null, "VOID_AIR"),
    WALL_SIGN("WALL_SIGN", null, null, null, null, null, "OAK_WALL_SIGN"),
    WALL_TORCH(null, null, null, null, null, "WALL_TORCH"),
    WATER("WATER"),
    WATER_BUCKET("WATER_BUCKET"),
    WET_SPONGE(1, "SPONGE", null, null, null, null, "WET_SPONGE"),
    WHEAT("WHEAT"),
    WHEAT_SEEDS("SEEDS", null, null, null, null, "WHEAT_SEEDS"),
    WHITE_BANNER(15, "BANNER", null, null, null, null, "WHITE_BANNER"),
    WHITE_BED("BED", null, null, null, null, "WHITE_BED"),
    WHITE_CARPET("CARPET", null, null, null, null, "WHITE_CARPET"),
    WHITE_CONCRETE(null, null, null, null, "CONCRETE", "WHITE_CONCRETE"),
    WHITE_CONCRETE_POWDER(null, null, null, null, "CONCRETE_POWDER", "WHITE_CONCRETE_POWDER"),
    WHITE_DYE("WHITE_DYE"),
    WHITE_GLAZED_TERRACOTTA(null, null, null, null, "WHITE_GLAZED_TERRACOTTA"),
    WHITE_LEATHER_BOOTS("LEATHER_BOOTS", 0, Color.fromRGB(255, 255, 255)),
    WHITE_LEATHER_CHESTPLATE("LEATHER_CHESTPLATE", 0, Color.fromRGB(255, 255, 255)),
    WHITE_LEATHER_HELMET("LEATHER_HELMET", 0, Color.fromRGB(255, 255, 255)),
    WHITE_LEATHER_LEGGINGS("LEATHER_LEGGINGS", 0, Color.fromRGB(255, 255, 255)),
    WITHER_ROSE(null, null, null, null, null, null, "WITHER_ROSE"),
    WHITE_SHULKER_BOX(null, null, null, "WHITE_SHULKER_BOX"),
    WHITE_STAINED_GLASS("STAINED_GLASS", null, null, null, null, "WHITE_STAINED_GLASS"),
    WHITE_STAINED_GLASS_PANE("STAINED_GLASS_PANE", null, null, null, null, "WHITE_STAINED_GLASS_PANE"),
    WHITE_TERRACOTTA("STAINED_CLAY", null, null, null, null, "WHITE_TERRACOTTA"),
    WHITE_TULIP(6, "RED_ROSE", null, null, null, null, "WHITE_TULIP"),
    WHITE_WALL_BANNER("BANNER", null, null, null, null, "WHITE_WALL_BANNER"),
    WHITE_WOOL("WOOL", null, null, null, null, "WHITE_WOOL"),
    WITCH_SPAWN_EGG(66, "MONSTER_EGG", null, null, null, null, "WITCH_SPAWN_EGG"),
    WITHER_SKELETON_SKULL(1, "SKULL", null, null, null, null, "WITHER_SKELETON_SKULL"),
    WITHER_SKELETON_SKULL_ITEM(1, "SKULL_ITEM", null, null, null, null, "WITHER_SKELETON_SKULL"),
    WITHER_SKELETON_SPAWN_EGG(null, null, null, null, null, "WITHER_SKELETON_SPAWN_EGG"),
    WITHER_SKELETON_WALL_SKULL(1, "SKULL", null, null, null, null, "WITHER_SKELETON_WALL_SKULL"),
    WOLF_SPAWN_EGG(95, "MONSTER_EGG", null, null, null, null, "WOLF_SPAWN_EGG"),
    WOODEN_AXE("WOOD_AXE", null, null, null, null, "WOODEN_AXE"),
    WOODEN_HOE("WOOD_HOE", null, null, null, null, "WOODEN_HOE"),
    WOODEN_PICKAXE("WOOD_PICKAXE", null, null, null, null, "WOODEN_PICKAXE"),
    WOODEN_SHOVEL("WOOD_SPADE", null, null, null, null, "WOODEN_SHOVEL"),
    WOODEN_SWORD("WOOD_SWORD", null, null, null, null, "WOODEN_SWORD"),
    WRITABLE_BOOK("BOOK_AND_QUILL", null, null, null, null, "WRITABLE_BOOK"),
    WRITTEN_BOOK("WRITTEN_BOOK"),
    YELLOW_BANNER(11, "BANNER", null, null, null, null, "YELLOW_BANNER"),
    YELLOW_BED(4, "BED", null, null, null, null, "YELLOW_BED"),
    YELLOW_CARPET(4, "CARPET", null, null, null, null, "YELLOW_CARPET"),
    YELLOW_CONCRETE(4, null, null, null, null, "CONCRETE", "YELLOW_CONCRETE"),
    YELLOW_CONCRETE_POWDER(4, null, null, null, null, "CONCRETE_POWDER", "YELLOW_CONCRETE_POWDER"),
    YELLOW_GLAZED_TERRACOTTA(null, null, null, null, "YELLOW_GLAZED_TERRACOTTA"),
    YELLOW_LEATHER_BOOTS("LEATHER_BOOTS", 4, Color.fromRGB(229, 229, 51)),
    YELLOW_LEATHER_CHESTPLATE("LEATHER_CHESTPLATE", 4, Color.fromRGB(229, 229, 51)),
    YELLOW_LEATHER_HELMET("LEATHER_HELMET", 4, Color.fromRGB(229, 229, 51)),
    YELLOW_LEATHER_LEGGINGS("LEATHER_LEGGINGS", 4, Color.fromRGB(229, 229, 51)),
    YELLOW_SHULKER_BOX(null, null, null, "YELLOW_SHULKER_BOX"),
    YELLOW_STAINED_GLASS(4, "STAINED_GLASS", null, null, null, null, "YELLOW_STAINED_GLASS"),
    YELLOW_STAINED_GLASS_PANE(4, "STAINED_GLASS_PANE", null, null, null, null, "YELLOW_STAINED_GLASS_PANE"),
    YELLOW_TERRACOTTA(4, "STAINED_CLAY", null, null, null, null, "YELLOW_TERRACOTTA"),
    YELLOW_WALL_BANNER(11, "BANNER", null, null, null, null, "YELLOW_WALL_BANNER"),
    YELLOW_WOOL(4, "WOOL", null, null, null, null, "YELLOW_WOOL"),
    ZOMBIE_HEAD(2, "SKULL", null, null, null, null, "ZOMBIE_HEAD"),
    ZOMBIE_HEAD_ITEM(2, "SKULL_ITEM", null, null, null, null, "ZOMBIE_HEAD"),
    ZOMBIE_HORSE_SPAWN_EGG(null, null, null, null, null, "ZOMBIE_HORSE_SPAWN_EGG"),
    ZOMBIE_PIGMAN_SPAWN_EGG(57, "MONSTER_EGG", null, null, null, null, "ZOMBIE_PIGMAN_SPAWN_EGG"),
    ZOMBIE_SPAWN_EGG(54, "MONSTER_EGG", null, null, null, null, "ZOMBIE_SPAWN_EGG"),
    ZOMBIE_VILLAGER_SPAWN_EGG(null, null, null, null, null, "ZOMBIE_VILLAGER_SPAWN_EGG"),
    ZOMBIE_WALL_HEAD(2, "SKULL", null, null, null, null, "ZOMBIE_WALL_HEAD"),
    ;
    private static final HashMap<String, UMaterial> inUMemory = new HashMap<>();
    private static final HashMap<String, ItemStack> inMemory = new HashMap<>();
    private static final String VERSION = Bukkit.getVersion();
    private final String version = Bukkit.getVersion();
    private String[] names = new String[7];
    private ItemStack is;
    private Material material;
    private int data;
    UMaterial(String ...names) {
        this(0, names);
    }
    UMaterial(String name, int data) {
        names[0] = name;
        this.data = data;
        final Material m = Material.matchMaterial(name);
        material = m;
        if (material != null) is = version.contains("1.8") || version.contains("1.9") || version.contains("1.10") || version.contains("1.11") || version.contains("1.12") ? new ItemStack(m, 1, (byte) data) : new ItemStack(m);
    }
    UMaterial(String name, int spoofedData, Color color) {
        names[0] = name;
        this.data = spoofedData;
        final Material m = Material.matchMaterial(name);
        material = m;
        if (material != null) is = new ItemStack(m);
        final LeatherArmorMeta lam = (LeatherArmorMeta) is.getItemMeta();
        lam.setColor(color);
        is.setItemMeta(lam);
    }
    /*UMaterial(String name, int data, UPotion pd) {
        doPotion(setItemStack(name, data), pd);
    }
    UMaterial(UPotion pd, int data, String ...names) {
        doPotion(setItemStack(data, names), pd);
    }*/
    UMaterial(String name, Enchantment enchant, int level) {
        doEnchantmentBook(setItemStack(0, name), enchant, level);
    }
    UMaterial(int data, String ...names) {
        this.names = names;
        this.data = data;
        final Material m = getMaterialName();
        material = m;
        if (material != null) is = version.contains("1.8") || version.contains("1.9") || version.contains("1.10") || version.contains("1.11") || version.contains("1.12") ? new ItemStack(m, 1, (short) data, (byte) data) : new ItemStack(m);
    }
    UMaterial(int data, int returnData, String... names) {
        this.names = names;
        this.data = data;
        final Material m = getMaterialName();
        material = m;
        if (material != null) is = version.contains("1.8") || version.contains("1.9") || version.contains("1.10") || version.contains("1.11") || version.contains("1.12") ? new ItemStack(m, 1, (short) returnData, (byte) returnData) : new ItemStack(m);
    }
    /*private void doPotion(ItemStack m, UPotion up) {
        this.is = up.getItemStack();
    }*/
    private void doEnchantmentBook(ItemStack m, Enchantment enchant, int level) {
        final EnchantmentStorageMeta sm = (EnchantmentStorageMeta) m.getItemMeta();
        sm.addStoredEnchant(enchant, level, true);
        m.setItemMeta(sm);
        this.is = m;
    }
    public Material getMaterial() { return material; }
    public ItemStack getItemStack() { return is.clone(); }
    public byte getData() { return (byte) data; }
    // 0 = 1.8.8
    // 1 = 1.9.4
    // 2 = 1.10.2
    // 3 = 1.11.2
    // 4 = 1.12.2
    // 5 = 1.13.2
    // 6 = 1.14.0
    private Material getMaterialName() {
        final int ver = version.contains("1.8") ? 0 : version.contains("1.9") ? 1 : version.contains("1.10") ? 2 : version.contains("1.11") ? 3 : version.contains("1.12") ? 4 : version.contains("1.13") ? 5 : version.contains("1.14") ? 6 : names.length-1;
        int realver = names.length <= ver ? names.length-1 : ver;
        if(names[realver] == null) {
            boolean did = false;
            for(int i = realver; i >= 0; i--) {
                if(!did && names[i] != null) {
                    realver = i;
                    did = true;
                }
            }
        }
        final String t = names[realver], t2 = names.length > ver ? names[ver] : names[names.length-1];
        return Material.matchMaterial(t != null ? t : t2 != null ? t2 : "AIR");
    }
    private ItemStack setItemStack(String name, int data) {
        names[0] = name;
        this.data = data;
        final Material m = getMaterialName();
        material = m;
        return version.contains("1.8") || version.contains("1.9") || version.contains("1.10") || version.contains("1.11") || version.contains("1.12") ? new ItemStack(m, 1, (byte) data) : new ItemStack(m);
    }
    private ItemStack setItemStack(int data, String ...names) {
        this.names = names;
        this.data = data;
        final Material m = getMaterialName();
        material = m;
        return version.contains("1.8") || version.contains("1.9") || version.contains("1.10") || version.contains("1.11") || version.contains("1.12") ? new ItemStack(m, 1, (byte) data) : new ItemStack(m);
    }
    public static ItemStack getEnchantmentBook(Enchantment enchant, int level, int amount) {
        final LinkedHashMap<Enchantment, Integer> e = new LinkedHashMap<>();
        e.put(enchant, level);
        return getEnchantmentBook(e, amount);
    }
    public static ItemStack getEnchantmentBook(LinkedHashMap<Enchantment, Integer> enchants, int amount) {
        final ItemStack s = new ItemStack(Material.ENCHANTED_BOOK, amount);
        final EnchantmentStorageMeta sm = (EnchantmentStorageMeta) s;
        for(Enchantment enchant : enchants.keySet())
            sm.addStoredEnchant(enchant, enchants.get(enchant), true);
        s.setItemMeta(sm);
        return s;
    }
    public static ItemStack getColoredLeather(Material m, int amount, int red, int green, int blue) {
        final ItemStack i = new ItemStack(m, amount);
        final LeatherArmorMeta lam = (LeatherArmorMeta) i.getItemMeta();
        lam.setColor(Color.fromRGB(red, green, blue));
        i.setItemMeta(lam);
        return i;
    }
    @Deprecated
    public static UMaterial match(String name, byte data) {
        if (data == -1) return match(name);
        name = name.toUpperCase();
        if(inUMemory.keySet().contains(name + data)) return inUMemory.get(name + data);
        for(UMaterial u : UMaterial.values()) {
            for(String n : u.names) {
                if(n != null && n.equalsIgnoreCase(name) && u.getData() == data) {
                    inUMemory.put(name + data, u);
                    return u;
                }
            }
        }
        return match(name);
    }
    @Deprecated
    public static ItemStack valueOf(String name, byte data) {
        name = name.toUpperCase();
        if(inMemory.keySet().contains(name + data)) return inMemory.get(name + data).clone();
        for(UMaterial u : UMaterial.values()) {
            for(String n : u.names) {
                if(n != null && n.equals(name)) {
                    final ItemStack i = u.getItemStack();
                    if(u.getData() == data) {
                        inMemory.put(name + data, i);
                        return i;
                    }
                }
            }
        }
        return null;
    }
    public static UMaterial matchSpawnEgg(ItemStack egg) {
        if(egg != null) {
            final String v = Bukkit.getVersion();
            if(v.contains("1.8")) {
                return match("MONSTER_EGG", egg.getData().getData());
            } else if(v.contains("1.9") || v.contains("1.10") || v.contains("1.11") || v.contains("1.12")) {
                final String id = egg.hasItemMeta() ? egg.getItemMeta().toString().split("id=")[1].split("}")[0].toUpperCase() : "PIG";
                return match(id + "_SPAWN_EGG");
            } else {
                return match(egg.getType().name());
            }
        }
        return null;
    }
    public static UMaterial matchEnchantedBook(ItemStack book) {
        if(book != null && book.getItemMeta() instanceof EnchantmentStorageMeta) {
            final EnchantmentStorageMeta m = (EnchantmentStorageMeta) book.getItemMeta();
            final Map<Enchantment, Integer> s = m.getStoredEnchants();
            if(s.size() == 1) {
                final Enchantment e = (Enchantment) s.keySet().toArray()[0];
                final int l = s.get(e);
                final String n = e.getName(), name =
                        n.equals("PROTECTION_ENVIRONMENTAL") ? "PROTECTION"
                                : n.equals("PROTECTION_FIRE") ? "FIRE_PROTECTION"
                                : n.equals("PROTECTION_FALL") ? "FEATHER_FALLING"
                                : n.equals("PROTECTION_EXPLOSIONS") ? "BLAST_PROTECTION"
                                : n.equals("PROTECTION_PROJECTILE") ? "PROJECTILE_PROTECTION"
                                : n.equals("OXYGEN") ? "AQUA_AFFINITY"
                                : n.equals("WATER_WORKER") ? "RESPIRATION"
                                : n.equals("DAMAGE_ALL") ? "SHARPNESS"
                                : n.equals("DAMAGE_UNDEAD") ? "SMITE"
                                : n.equals("DAMAGE_ARTHROPODS") ? "BANE_OF_ARTHROPODS"
                                : n.equals("LOOT_BONUS_MOBS") ? "LOOTING"
                                : n.equals("SWEEPING_EDGE") ? "SWEEPING"
                                : n.equals("DIG_SPEED") ? "EFFICIENCY"
                                : n.equals("DURABILITY") ? "UNBREAKING"
                                : n.equals("LOOT_BONUS_BLOCKS") ? "FORTUNE"
                                : n.equals("ARROW_DAMAGE") ? "POWER"
                                : n.equals("ARROW_KNOCKBACK") ? "PUNCH"
                                : n.equals("ARROW_FIRE") ? "FLAME"
                                : n.equals("ARROW_INFINITE") ? "INFINITY"
                                : n.equals("LUCK") ? "LUCK_OF_THE_SEA"
                                : n;
                return match("ENCHANTED_BOOK_" + (e.getMaxLevel() != 1 ? name + "_" + l : name));
            }
        }
        return null;
    }
    /*public static UMaterial matchPotion(ItemStack potion, PotionBase base) {
        if(potion != null && potion.getItemMeta() instanceof PotionMeta) {
            final String v = Bukkit.getVersion();
            final PotionMeta p = (PotionMeta) potion.getItemMeta();
            final List<PotionEffect> ce = p.getCustomEffects();
            if(ce.size() == 0) {
                final PotionEffectType t;
                final int l, max;
                final boolean extended;
                if(v.contains("1.8")) {
                    final Potion po = Potion.fromItemStack(potion);
                    t = ((PotionEffect) po.getEffects().toArray()[0]).getType();
                    l = po.getLevel();
                    max = po.getType().getMaxLevel();
                    extended = po.hasExtendedDuration();
                } else {
                    final org.bukkit.potion.PotionData pd = p.getBasePotionData();
                    final PotionType type = pd.getType();
                    t = type.getEffectType();
                    l = type.isUpgradeable() && pd.isUpgraded() ? 2 : 1;
                    max = type.getMaxLevel();
                    extended = type.isExtendable() && pd.isExtended();
                }
                final String a = t.getName(), T = base.equals(PotionBase.NORMAL) ? "POTION_" : base.equals(PotionBase.SPLASH) ? "SPLASH_POTION_" : base.equals(PotionBase.LINGERING) ? "LINGERING_POTION_" : null,
                        type = a.equals("SPEED") ? "SWIFTNESS"
                                : a.equals("SLOW") ? "SLOWNESS"
                                : a.equals("INCREASE_DAMAGE") ? "STRENGTH"
                                : a.equals("HEAL") ? "HEALING"
                                : a.equals("HARM") ? "HARMING"
                                : a.equals("JUMP") ? "LEAPING"
                                : a;
                final String g = T + type + (l <= max ? "_" + l : "") + (extended ? "_EXTENDED" : "");
                return T != null ? match(g) : null;
            }
        }
        return null;
    }*/
    @Deprecated
    public static UMaterial match(ItemStack item) {
        if(item != null) {
            final UMaterial u = match(item.getType().name(), item.getData().getData());
            final String un = u.name();
            return un.contains("SPAWN_EGG") ? matchSpawnEgg(item) : un.contains("ENCHANTED_BOOK") ? matchEnchantedBook(item) : u;
        }
        return null;
    }
    public static UMaterial match(String name) {
        final byte d = name.contains(":") ? Byte.parseByte(name.split(":")[1]) : -1;
        name = (name.contains(":") ? name.split(":")[0] : name).toUpperCase();
        if(inUMemory.containsKey(name + d)) return inUMemory.get(name + d);
        for(UMaterial u : UMaterial.values()) {
            if(u.name().equals(name)) {
                inUMemory.put(name, u);
                return u;
            }
            final byte D = u.getData();
            if (d == -1 || D == d) {
                final String[] names = u.names;
                for(String n : names) {
                    if(n != null && n.equals(name)) {
                        for(String na : names) inUMemory.put(na + d, u);
                        return u;
                    }
                }
            }
        }
        return null;
    }


}

/*

  TROPHONIX has modified this part of the code, disabling the UPotion class
  and all uses of it due to version incompatability issues and a lack of
  need for them.

 */

/*class UPotion {
    private static final String v = Bukkit.getVersion();
    private final ItemStack potion;
    private final Object potiondata;
    public UPotion(UMaterial.PotionBase base, PotionType type, boolean extended, boolean upgraded) {
        final String bn = base.name();
        if(v.contains("1.8")) {
            potion = type.name().equals("WATER") ? new Potion(type).toItemStack(1) : new Potion(type, upgraded ? 2 : 1, bn.equals("SPLASH")).toItemStack(1);
            potiondata = potion.getItemMeta();
        } else {
            final ItemStack is = new ItemStack(Material.matchMaterial(bn.equals("NORMAL") ? "POTION" : bn.equals("ARROW") ? v.contains("1.8") || v.contains("1.9") || v.contains("1.11") ? "ARROW" : "TIPPED_ARROW" : bn + "_POTION"));
            final boolean a = !is.getType().equals(Material.ARROW);
            org.bukkit.inventory.meta.PotionMeta pm = null;
            org.bukkit.potion.PotionData pd = null;
            if(a) {
                pm = (org.bukkit.inventory.meta.PotionMeta) is.getItemMeta();
                pd = new org.bukkit.potion.PotionData(type, type.isExtendable() && extended, type.isUpgradeable() && upgraded);
            }
            potiondata = pd;
            if(a) {
                pm.setBasePotionData(pd);
                is.setItemMeta(pm);
            }
            potion = is;
        }
    }
    public ItemStack getItemStack() { return potion.clone(); }
    public Object getPotionData() { return potiondata; }
}*/
/*
class USpawner extends ItemStack {
    private EntityType type;
    private final ItemStack is;
    private final SpawnerMeta sm;
    public USpawner(EntityType type) {
        this.type = type;
        final ItemStack is = UMaterial.SPAWNER.getItemStack();
        final SpawnerMeta sm = (SpawnerMeta) is.getItemMeta();
        sm.setType(type);
        this.sm = sm;
        this.is = is;
    }
    public void setType(EntityType type) { this.type = type; }
    public ItemStack getItemStack() { return is.clone(); }
    public SpawnerMeta getSpawnerMeta() { return sm; }
}
class UCreatureSpawner {
    private CreatureSpawner cs;
    private EntityType type;
    public UCreatureSpawner(Block b) {
        if(b.getType().name().contains("SPAWNER")) {
            cs = (CreatureSpawner) b.getState();
            this.type = cs.getSpawnedType();
        }
    }
    public UCreatureSpawner(EntityType type) {
        this.type = type;
    }
    public CreatureSpawner getCreatureSpawner() { return cs; }
    private ItemStack getItemStack(EntityType type) { return new USpawner(type).getItemStack(); }
    public void setType(EntityType type) {
        this.type = type;
        if(cs != null) {
            cs.setSpawnedType(type);
            cs.update();
        }
    }
}
interface SpawnerMeta extends ItemMeta {
    void setType(EntityType type);
    EntityType getType();
    SpawnerMeta clone();
}*/
