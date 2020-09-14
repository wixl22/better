package com.wixl.better.enchantments;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class MbEnchantments {
	public static final Enchantment GIGANTIC = register("gigantic", new GiganticEnchantment(Enchantment.Rarity.RARE, EquipmentSlot.MAINHAND));

	private static Enchantment register(String key, Enchantment enchantment) {
		return Registry.register(Registry.ENCHANTMENT, new Identifier("better", key), enchantment);
	}

	public static void init(){
		System.out.println("init enchants");
	}
}
