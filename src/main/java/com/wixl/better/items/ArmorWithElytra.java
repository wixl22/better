package com.wixl.better.items;

import net.fabricmc.fabric.api.object.builder.v1.client.model.FabricModelPredicateProviderRegistry;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.*;
import net.minecraft.util.Identifier;

public class ArmorWithElytra extends MbArmorItem {
	public ArmorWithElytra(ArmorMaterial material, EquipmentSlot slot, Settings settings) {
		super(material, slot, settings);
		FabricModelPredicateProviderRegistry.register(new Identifier("broken"), (stack, world, entity) -> isUsable(stack) ? 0.0F : 1.0F);
	}

	public static boolean isUsable(ItemStack stack) {
		return stack.getDamage() < stack.getMaxDamage() - 1;
	}
}
