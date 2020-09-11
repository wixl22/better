package com.wixl.better.materials;

import com.wixl.better.items.MbItems;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Lazy;

import java.util.function.Supplier;

public enum MbArmorMaterials implements ArmorMaterial {
	NICOMYRN("nycomyrn", 40, new int[]{4, 8, 10, 4}, 25, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 5.0F, 0, () -> {
		return Ingredient.ofItems(MbItems.NICOMYRN_INGOT);
	}),

	MINING_HELMET("mining_helmet", 15, new int[]{2, 5, 6, 2}, 9, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 0.0F, 0, () ->
	{
		return Ingredient.ofItems(Items.IRON_INGOT);
	}),

	PIG_MASK("pig_mask", 5, new int[]{1, 2, 3, 1}, 15, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 0.0F, 0.0F, () ->
	{
		return Ingredient.ofItems(Items.LEATHER);
	}),

	FOX("fox", 5, new int[]{1, 2, 3, 1}, 15, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 0.0F, 0.0F, () ->
	{
		return Ingredient.ofItems(Items.LEATHER);
	});

	private static final int[] BASE_DURABILITY = new int[]{13, 15, 16, 11};
	private final String name;
	private final int durabilityMultiplier;
	private final int[] protectionAmounts;
	private final int enchantability;
	private final SoundEvent equipSound;
	private final float toughness;
	private final float knockbackResistance;
	private final Lazy<Ingredient> repairIngredientSupplier;

	MbArmorMaterials(String name, int durabilityMultiplier, int[] protectionAmounts, int enchantability, SoundEvent equipSound, float toughness, float knockbackResistance, Supplier<Ingredient> supplier) {
		this.name = name;
		this.durabilityMultiplier = durabilityMultiplier;
		this.protectionAmounts = protectionAmounts;
		this.enchantability = enchantability;
		this.equipSound = equipSound;
		this.toughness = toughness;
		this.knockbackResistance = knockbackResistance;
		this.repairIngredientSupplier = new Lazy(supplier);
	}

	public int getDurability(EquipmentSlot slot) {
		return BASE_DURABILITY[slot.getEntitySlotId()] * this.durabilityMultiplier;
	}

	public int getProtectionAmount(EquipmentSlot slot) {
		return this.protectionAmounts[slot.getEntitySlotId()];
	}

	public int getEnchantability() {
		return this.enchantability;
	}

	public SoundEvent getEquipSound() {
		return this.equipSound;
	}

	public Ingredient getRepairIngredient() {
		return (Ingredient) this.repairIngredientSupplier.get();
	}

	@Environment(EnvType.CLIENT)
	public String getName() {
		return this.name;
	}

	public float getToughness() {
		return this.toughness;
	}

	@Override
	public float getKnockbackResistance() {
		return knockbackResistance;
	}
}
