package com.wixl.better.render.model;

import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.entity.LivingEntity;

public class FoxArmorModel<T extends LivingEntity> extends BipedEntityModel<T> {
	ModelPart month;
	ModelPart leftEar;
	ModelPart rightEar;

	public FoxArmorModel() {
		super(1.0F);

		this.textureWidth = 64;
		this.textureHeight = 64;
		this.head = new ModelPart(this, 0, 0);
		this.head.setPivot(0.0F, 0.0F, 0.0F);
		this.head.addCuboid(-5.0F, -10.0F, -5.0F, 10, 10, 10, 0.0F);
		this.helmet = new ModelPart(this, 0, 0);


		this.month = new ModelPart(this, 40, 0);
		this.month.setPivot(0.0f, 0.0f, 0.0f);
		this.month.addCuboid(-2.0F, -7.0F, -8.0F, 4, 3, 3, 0.0f);
		this.leftEar = new ModelPart(this, 40, 10);
		this.leftEar.setPivot(0.0f, 0.0f, 0.0f);
		this.leftEar.addCuboid(-5.0F, -13.0F, 0.0000F, 3, 3, 1, 0.0f);
		this.rightEar = new ModelPart(this, 40, 10);
		this.rightEar.setPivot(0.0f, 0.0f, 0.0f);
		this.rightEar.addCuboid(2.0F, -13.0F, 0.0000F, 3, 3, 1, 0.0f);

		this.head.addChild(month);
		this.head.addChild(leftEar);
		this.head.addChild(rightEar);

		this.torso = new ModelPart(this, 16, 20);
		this.torso.addCuboid(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, 0);
		this.torso.setPivot(0.0F, 0.0F, 0.0F);
		this.rightArm = new ModelPart(this, 40, 20);
		this.rightArm.addCuboid(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0);
		this.rightArm.setPivot(-5.0F, 2.0F, 0.0F);
		this.leftArm = new ModelPart(this, 40, 20);
		this.leftArm.mirror = true;
		this.leftArm.addCuboid(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0);
		this.leftArm.setPivot(5.0F, 2.0F, 0.0F);
		this.rightLeg = new ModelPart(this, 0, 20);
		this.rightLeg.addCuboid(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0);
		this.rightLeg.setPivot(-1.9F, 12.0F, 0.0F);
		this.leftLeg = new ModelPart(this, 0, 20);
		this.leftLeg.mirror = true;
		this.leftLeg.addCuboid(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0);
		this.leftLeg.setPivot(1.9F, 12.0F, 0.0F);
	}
}
