package com.wixl.better.items;

import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.ItemStack;


public class MbArmorItem extends ArmorItem{
	public BipedEntityModel<LivingEntity> armorModel = null;

	public void setArmorModel(BipedEntityModel<LivingEntity> armorModel) {
		this.armorModel = armorModel;
	}

	public MbArmorItem(ArmorMaterial material, EquipmentSlot slot, Settings settings) {
		super(material, slot, settings);
	}

	public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type)
	{
		return null;
	}

	public <T extends LivingEntity, A extends BipedEntityModel<T>> A getArmorModel(LivingEntity entityLiving, ItemStack itemStack, EquipmentSlot armorSlot, A _default)
	{
		if (armorModel != null) {
			return (A) armorModel;
		}
		return null;
	}
}