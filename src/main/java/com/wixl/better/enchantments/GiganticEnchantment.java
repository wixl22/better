package com.wixl.better.enchantments;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.PickaxeItem;

public class GiganticEnchantment extends Enchantment {
	public GiganticEnchantment(Enchantment.Rarity weight, EquipmentSlot... slotTypes) {
		super(weight, EnchantmentTarget.DIGGER, slotTypes);
	}

	public int getMinPower(int level) {
		return 15 + (level - 1) * 9;
	}

	public int getMaxPower(int level) {
		return super.getMinPower(level) + 50;
	}

	public int getMaxLevel() {
		return 1;
	}

	@Override
	public boolean isAcceptableItem(ItemStack stack) {
		return stack.getItem() instanceof PickaxeItem;
	}
}
