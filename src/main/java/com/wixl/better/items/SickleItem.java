package com.wixl.better.items;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.HoeItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolMaterial;

public class SickleItem extends MbHoeItem implements DualWield {
	protected SickleItem(ToolMaterial material, float attackDamage, float attackSpeed, Settings settings) {
		super(material, attackDamage, attackSpeed, settings);
	}

	@Override
	public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
		stack.damage(1, attacker, e -> {
			e.sendEquipmentBreakStatus(EquipmentSlot.MAINHAND);
			e.sendEquipmentBreakStatus(EquipmentSlot.OFFHAND);
		});
		return true;
	}

}
