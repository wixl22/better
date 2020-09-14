package com.wixl.better.util;

import com.wixl.better.items.MbArmorItem;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;


public class HookClient {
	public static String getArmorTexture(Entity entity, ItemStack armor, String _default, EquipmentSlot slot, String type)
	{
		Item item = armor.getItem();
		if (item instanceof MbArmorItem) {
			String result = ((MbArmorItem) item).getArmorTexture(armor, entity, slot, type);
			return result != null ? result : _default;
		}
		return _default;
	}

	public static <T extends LivingEntity, A extends BipedEntityModel<T>> A getArmorModel(LivingEntity entityLiving, ItemStack itemStack, EquipmentSlot slot, A _default)
	{
		A model = ((MbArmorItem) itemStack.getItem()).getArmorModel(entityLiving, itemStack, slot, _default);
		return model == null ? _default : model;
	}
}
