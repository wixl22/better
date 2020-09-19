package com.wixl.better.items;

import com.wixl.better.blocks.MbBlocks;
import com.wixl.better.materials.MbArmorMaterials;
import com.wixl.better.sound.MbSoundEvents;
import net.minecraft.block.Block;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.*;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import net.minecraft.util.registry.Registry;

public class MbItems {
	public static final Item CRACKED_ANDESITE = register(MbBlocks.CRACKED_ANDESITE, ItemGroup.BUILDING_BLOCKS);

	public static final Item LONGBOW = register("longbow", new LongBowItem(new Item.Settings().group(ItemGroup.COMBAT).maxDamage(384)));

	public static final Item SICKLE = register("sickle", new SickleItem(ToolMaterials.IRON, -0.76F, (2f-4.0f), (new Item.Settings()).group(ItemGroup.COMBAT).rarity(Rarity.RARE)));
	public static final Item NIGHTMARE_BITE = register("nightmare_bite", new SickleItem(ToolMaterials.DIAMOND, -1.82f, (2f-4.0f), (new Item.Settings()).group(ItemGroup.COMBAT).rarity(Rarity.EPIC)));
	public static final Item THE_LAST_LAUGH = register("the_last_laugh", new SickleItem(ToolMaterials.DIAMOND, -1.72f, (2f-4.0f), (new Item.Settings()).group(ItemGroup.COMBAT).rarity(Rarity.EPIC)));

	public static final Item DAGGER = register("dagger", new DaggerItem(ToolMaterials.IRON, -0.89f, (2.5f-4.0f), (new Item.Settings()).group(ItemGroup.COMBAT).rarity(Rarity.RARE)));
	public static final Item FANG_OF_FROST = register("fang_of_frost", new DaggerItem(ToolMaterials.DIAMOND, -1.45f, (2.5f-4.0f), (new Item.Settings()).group(ItemGroup.COMBAT).rarity(Rarity.EPIC)));
	public static final Item MOON_DAGGER = register("moon_dagger", new DaggerItem(ToolMaterials.DIAMOND, -1.86f, (2.5f-4.0f), (new Item.Settings()).group(ItemGroup.COMBAT).rarity(Rarity.EPIC)));

	public static final Item IRON_SWORD = register("iron_sword", new SwordItem(ToolMaterials.IRON, 3, (1.6f-4.0f), (new Item.Settings()).group(ItemGroup.COMBAT)));
	public static final Item DIAMOND_SWORD = register("diamond_sword", new SwordItem(ToolMaterials.DIAMOND, 4, (1.6f-4.0f), (new Item.Settings()).group(ItemGroup.COMBAT).rarity(Rarity.UNCOMMON)));
	public static final Item DIAMOND_PICKAXE = register("diamond_pickaxe", new MbPickaxeItem(ToolMaterials.DIAMOND, 2, (1.3f-4.0f), (new Item.Settings()).group(ItemGroup.TOOLS).rarity(Rarity.UNCOMMON)));

	public static final Item CHAINMAIL_CHESTPLATE_WITH_ELYTRA = register("chainmail_chestplate_with_elytra", new ArmorWithElytra(ArmorMaterials.CHAIN, EquipmentSlot.CHEST, (new Item.Settings()).group(ItemGroup.COMBAT).rarity(Rarity.UNCOMMON)));
	public static final Item IRON_CHESTPLATE_WITH_ELYTRA = register("iron_chestplate_with_elytra", new ArmorWithElytra(ArmorMaterials.IRON, EquipmentSlot.CHEST, (new Item.Settings()).group(ItemGroup.COMBAT).rarity(Rarity.UNCOMMON)));
	public static final Item DIAMOND_CHESTPLATE_WITH_ELYTRA = register("diamond_chestplate_with_elytra", new ArmorWithElytra(ArmorMaterials.DIAMOND, EquipmentSlot.CHEST, (new Item.Settings()).group(ItemGroup.COMBAT).rarity(Rarity.UNCOMMON)));
	public static final Item GOLDEN_CHESTPLATE_WITH_ELYTRA = register("golden_chestplate_with_elytra", new ArmorWithElytra(ArmorMaterials.GOLD, EquipmentSlot.CHEST, (new Item.Settings()).group(ItemGroup.COMBAT).rarity(Rarity.UNCOMMON)));
	public static final Item NETHERITE_CHESTPLATE_WITH_ELYTRA = register("netherite_chestplate_with_elytra", new ArmorWithElytra(ArmorMaterials.NETHERITE, EquipmentSlot.CHEST, (new Item.Settings()).group(ItemGroup.COMBAT).fireproof().rarity(Rarity.UNCOMMON)));

	public static final Item GLASS_DOOR = register(new TallBlockItem(MbBlocks.GLASS_DOOR, (new Item.Settings()).group(ItemGroup.REDSTONE)));

	public static final Item MUSIC_DISC_DEMON_SLAYER = register("music_disc_demon_slayer", new MbMusicDiscItem(13, MbSoundEvents.MUSIC_DISC_DEMON_SLAYER, (new Item.Settings()).maxCount(1).group(ItemGroup.MISC).rarity(Rarity.RARE)));
	public static final Item MUSIC_DISC_SEVEN_NATION_ARMY = register("music_disc_seven_nation_army", new MbMusicDiscItem(14, MbSoundEvents.MUSIC_DISC_SEVEN_NATION_ARMY, (new Item.Settings()).maxCount(1).group(ItemGroup.MISC).rarity(Rarity.RARE)));
	public static final Item MUSIC_DISC_DONT_MINE_AT_NIGHT = register("music_disc_dont_mine_at_night", new MbMusicDiscItem(15, MbSoundEvents.MUSIC_DISC_DONT_MINE_AT_NIGHT, (new Item.Settings()).maxCount(1).group(ItemGroup.MISC).rarity(Rarity.RARE)));
	public static final Item MUSIC_DISC_GOOD_MORNING_WORLD = register("music_disc_good_morning_world", new MbMusicDiscItem(16, MbSoundEvents.MUSIC_DISC_GOOD_MORNING_WORLD, (new Item.Settings()).maxCount(1).group(ItemGroup.MISC).rarity(Rarity.RARE)));

	public static final Item FOX_HELMET = register("fox_helmet", new MbArmorItem(MbArmorMaterials.FOX, EquipmentSlot.HEAD, (new Item.Settings()).group(ItemGroup.COMBAT)));
	public static final Item FOX_CHESTPLATE = register("fox_chestplate", new MbArmorItem(MbArmorMaterials.FOX, EquipmentSlot.CHEST, (new Item.Settings()).group(ItemGroup.COMBAT)));
	public static final Item FOX_BOOTS = register("fox_boots", new MbArmorItem(MbArmorMaterials.FOX, EquipmentSlot.FEET, (new Item.Settings()).group(ItemGroup.COMBAT)));


	private static Item register(Block block) {
		return register(new BlockItem(block, new Item.Settings()));
	}

	private static Item register(Block block, ItemGroup group) {
		return register(new BlockItem(block, (new Item.Settings()).group(group)));
	}

	private static Item register(BlockItem item) {
		return register((Block) item.getBlock(), (Item) item);
	}

	protected static Item register(Block block, Item iem) {
		return register(Registry.BLOCK.getId(block), iem);
	}

	private static Item register(String id, Item item) {
		return register(new Identifier("better", id), item);
	}

	private static Item register(Identifier id, Item item) {
		if (item instanceof BlockItem) {
			((BlockItem) item).appendBlocks(Item.BLOCK_ITEMS, item);
		}

		return Registry.register(Registry.ITEM, id, item);
	}

	public static void init() {
		System.out.println("better: init MbItems!");
	}
}
