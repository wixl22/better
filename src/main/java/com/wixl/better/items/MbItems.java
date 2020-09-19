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
	public static final Item NICOMYRN_INGOT = register("nicomyrn_ingot", new Item((new Item.Settings()).group(ItemGroup.MATERIALS)));
	public static final Item NICOMYRN_ORE = register(MbBlocks.NICOMYRN_ORE, ItemGroup.BUILDING_BLOCKS);
	public static final Item NICOMYRN_BLOCK = register(MbBlocks.NICOMYRN_BLOCK, ItemGroup.BUILDING_BLOCKS);
	public static final Item CRACKED_ANDESITE = register(MbBlocks.CRACKED_ANDESITE, ItemGroup.BUILDING_BLOCKS);


	public static final Item IRON_SWORD = register("iron_sword", new SwordItem(ToolMaterials.IRON, 3, -2.4F, (new Item.Settings()).group(ItemGroup.COMBAT)));
	public static final Item DIAMOND_SWORD = register("diamond_sword", new SwordItem(ToolMaterials.DIAMOND, 4, -2.4F, (new Item.Settings()).group(ItemGroup.COMBAT).rarity(Rarity.UNCOMMON)));
	public static final Item DIAMOND_PICKAXE = register("diamond_pickaxe", new MbPickaxeItem(ToolMaterials.DIAMOND, 2, -2.8F, (new Item.Settings()).group(ItemGroup.TOOLS).rarity(Rarity.UNCOMMON)));
	public static final Item CHAINMAIL_CHESTPLATE_WITH_ELYTRA = register("chainmail_chestplate_with_elytra", new ArmorWithElytra(ArmorMaterials.CHAIN, EquipmentSlot.CHEST, (new Item.Settings()).group(ItemGroup.COMBAT).rarity(Rarity.UNCOMMON)));
	public static final Item IRON_CHESTPLATE_WITH_ELYTRA = register("iron_chestplate_with_elytra", new ArmorWithElytra(ArmorMaterials.IRON, EquipmentSlot.CHEST, (new Item.Settings()).group(ItemGroup.COMBAT).rarity(Rarity.UNCOMMON)));
	public static final Item DIAMOND_CHESTPLATE_WITH_ELYTRA = register("diamond_chestplate_with_elytra", new ArmorWithElytra(ArmorMaterials.DIAMOND, EquipmentSlot.CHEST, (new Item.Settings()).group(ItemGroup.COMBAT).rarity(Rarity.UNCOMMON)));
	public static final Item GOLDEN_CHESTPLATE_WITH_ELYTRA = register("golden_chestplate_with_elytra", new ArmorWithElytra(ArmorMaterials.GOLD, EquipmentSlot.CHEST, (new Item.Settings()).group(ItemGroup.COMBAT).rarity(Rarity.UNCOMMON)));
	public static final Item NETHERITE_CHESTPLATE_WITH_ELYTRA = register("netherite_chestplate_with_elytra", new ArmorWithElytra(ArmorMaterials.NETHERITE, EquipmentSlot.CHEST, (new Item.Settings()).group(ItemGroup.COMBAT).fireproof().rarity(Rarity.UNCOMMON)));
	public static final Item NICOMYRN_CHESTPLATE_WITH_ELYTRA = register("nicomyrn_chestplate_with_elytra", new ArmorWithElytra(MbArmorMaterials.NICOMYRN, EquipmentSlot.CHEST, (new Item.Settings()).group(ItemGroup.COMBAT).rarity(Rarity.UNCOMMON)));

	public static final Item GLASS_DOOR = register(new TallBlockItem(MbBlocks.GLASS_DOOR, (new Item.Settings()).group(ItemGroup.REDSTONE)));

	public static final Item MUSIC_DISC_DEMON_SLAYER = register("music_disc_demon_slayer", new MbMusicDiscItem(13, MbSoundEvents.MUSIC_DISC_DEMON_SLAYER, (new Item.Settings()).maxCount(1).group(ItemGroup.MISC).rarity(Rarity.RARE)));
	public static final Item MUSIC_DISC_SEVEN_NATION_ARMY = register("music_disc_seven_nation_army", new MbMusicDiscItem(14, MbSoundEvents.MUSIC_DISC_SEVEN_NATION_ARMY, (new Item.Settings()).maxCount(1).group(ItemGroup.MISC).rarity(Rarity.RARE)));
	public static final Item MUSIC_DISC_DONT_MINE_AT_NIGHT = register("music_disc_dont_mine_at_night", new MbMusicDiscItem(15, MbSoundEvents.MUSIC_DISC_DONT_MINE_AT_NIGHT, (new Item.Settings()).maxCount(1).group(ItemGroup.MISC).rarity(Rarity.RARE)));
	public static final Item MUSIC_DISC_GOOD_MORNING_WORLD = register("music_disc_good_morning_world", new MbMusicDiscItem(16, MbSoundEvents.MUSIC_DISC_GOOD_MORNING_WORLD, (new Item.Settings()).maxCount(1).group(ItemGroup.MISC).rarity(Rarity.RARE)));

	public static final Item NIGHTMARE_BITE = register("nightmare_bite", new SickleItem(ToolMaterials.DIAMOND, 3, -2F, (new Item.Settings()).group(ItemGroup.COMBAT).rarity(Rarity.RARE)));
	public static final Item SICKLE = register("sickle", new SickleItem(ToolMaterials.IRON, 1, -1.4F, (new Item.Settings()).group(ItemGroup.COMBAT).rarity(Rarity.RARE)));

	//
//	public static final Item PIG_MASK = register("pig_mask", new MbArmorItem(MbArmorMaterials.PIG_MASK, EquipmentSlot.HEAD, (new Item.Settings()).group(ItemGroup.COMBAT)));
//
//	public static final Item FOX_HELMET = register("fox_helmet", new MbArmorItem(MbArmorMaterials.FOX, EquipmentSlot.HEAD, (new Item.Settings()).group(ItemGroup.COMBAT)));
//	public static final Item FOX_CHESTPLATE = register("fox_chestplate", new MbArmorItem(MbArmorMaterials.FOX, EquipmentSlot.CHEST, (new Item.Settings()).group(ItemGroup.COMBAT)));
//	public static final Item FOX_BOOTS = register("fox_boots", new MbArmorItem(MbArmorMaterials.FOX, EquipmentSlot.FEET, (new Item.Settings()).group(ItemGroup.COMBAT)));
//

//	public static final Item NICOMYRN_SWORD = register("nicomyrn_sword", new SwordItem(MbToolMaterials.NICOMYRN, 4, -2.2F, (new Item.Settings()).group(ItemGroup.COMBAT)));
//	public static final Item NICOMYRN_SHOVEL = register("nicomyrn_shovel", new ShovelItem(MbToolMaterials.NICOMYRN, 2, -3.0F, (new Item.Settings()).group(ItemGroup.TOOLS)));
//	public static final Item NICOMYRN_PICKAXE = register("nicomyrn_pickaxe", new MbPickaxeItem(MbToolMaterials.NICOMYRN, 1, -2.8F, (new Item.Settings()).group(ItemGroup.TOOLS)));
//	public static final Item NICOMYRN_AXE = register("nicomyrn_axe", new MbAxeItem(MbToolMaterials.NICOMYRN, 5, -2.8F, (new Item.Settings()).group(ItemGroup.TOOLS)));
//	public static final Item NICOMYRN_HOE = register("nicomyrn_hoe", new MbHoeItem(MbToolMaterials.NICOMYRN, -4, 0.0F, (new Item.Settings()).group(ItemGroup.TOOLS)));
//
//	public static final Item NICOMYRN_HELMET = register("nicomyrn_helmet", new MbArmorItem(MbArmorMaterials.NICOMYRN, EquipmentSlot.HEAD, (new Item.Settings()).group(ItemGroup.COMBAT)));
//	public static final Item NICOMYRN_CHESTPLATE = register("nicomyrn_chestplate", new MbArmorItem(MbArmorMaterials.NICOMYRN, EquipmentSlot.CHEST, (new Item.Settings()).group(ItemGroup.COMBAT)));
//	public static final Item NICOMYRN_LEGGINGS = register("nicomyrn_leggings", new MbArmorItem(MbArmorMaterials.NICOMYRN, EquipmentSlot.LEGS, (new Item.Settings()).group(ItemGroup.COMBAT)));
//	public static final Item NICOMYRN_BOOTS = register("nicomyrn_boots", new MbArmorItem(MbArmorMaterials.NICOMYRN, EquipmentSlot.FEET, (new Item.Settings()).group(ItemGroup.COMBAT)));
//

//	public static final Item LONGBOW = register("longbow", new LongBowItem(new Item.Settings().group(ItemGroup.COMBAT).maxDamage(384)));



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
