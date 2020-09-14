package com.wixl.better.mixin;

import com.google.common.collect.Lists;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnchantmentLevelEntry;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.registry.Registry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import java.util.Iterator;
import java.util.List;

@Mixin(EnchantmentHelper.class)
public class EnchantmentHelperMixin {

	@Overwrite
	public static List<EnchantmentLevelEntry> getPossibleEntries(int power, ItemStack stack, boolean treasureAllowed) {
		List<EnchantmentLevelEntry> list = Lists.newArrayList();
		Item item = stack.getItem();
		boolean bl = stack.getItem() == Items.BOOK;
		Iterator var6 = Registry.ENCHANTMENT.iterator();

		while(true) {
			while(true) {
				Enchantment enchantment;
				do {
					do {
						do {
							if (!var6.hasNext()) {
								return list;
							}

							enchantment = (Enchantment)var6.next();
						} while(enchantment.isTreasure() && !treasureAllowed);
					} while(!enchantment.isAvailableForRandomSelection());
				} while(!enchantment.isAcceptableItem(stack) && !bl);

				for(int i = enchantment.getMaxLevel(); i > enchantment.getMinLevel() - 1; --i) {
					if (power >= enchantment.getMinPower(i) && power <= enchantment.getMaxPower(i)) {
						list.add(new EnchantmentLevelEntry(enchantment, i));
						break;
					}
				}
			}
		}
	}

}
