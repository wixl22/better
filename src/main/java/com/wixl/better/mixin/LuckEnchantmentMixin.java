package com.wixl.better.mixin;

import com.wixl.better.items.DualWield;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.enchantment.LuckEnchantment;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(LuckEnchantment.class)
public class LuckEnchantmentMixin extends Enchantment {
	protected LuckEnchantmentMixin(Rarity rarity, EnchantmentTarget enchantmentTarget, EquipmentSlot... equipmentSlots) {
		super(rarity, enchantmentTarget, equipmentSlots);
	}

	public boolean isAcceptableItem(ItemStack stack) {
		return stack.getItem() instanceof DualWield || super.isAcceptableItem(stack);
	}
}
