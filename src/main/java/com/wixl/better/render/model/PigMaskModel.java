package com.wixl.better.render.model;

import com.google.common.collect.ImmutableList;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.entity.LivingEntity;

public class PigMaskModel<T extends LivingEntity> extends BipedEntityModel<T> {
	ModelPart piglet;

	public PigMaskModel() {
		super(1.0F);

		this.textureWidth = 64;
		this.textureHeight = 64;
		this.head = new ModelPart(this, 0, 0);
		this.head.setPivot(0.0F, 0.0F, 0.0F);
		this.head.addCuboid(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, 1.0F);
		this.helmet = new ModelPart(this, 0, 0);

		piglet = new ModelPart(this, 16, 16);
		piglet.setPivot(0.0f, 0.0f, 0.0f);
		piglet.addCuboid(-2.0F, -4.0F, -6.5F, 4.0F, 3.0F, 1.0F, 0.5f);

		this.head.addChild(piglet);
	}

	@Override
	protected Iterable<ModelPart> getBodyParts() {
		return ImmutableList.of();
	}
}
