package com.wixl.better.mixin;

import com.wixl.better.items.DualWield;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.entity.model.AnimalModel;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.Arm;
import net.minecraft.util.Hand;
import net.minecraft.util.math.MathHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;


@Mixin(BipedEntityModel.class)
public abstract class BipedEntityModelMixin<T extends LivingEntity> extends AnimalModel<T> {
	@Shadow
	public ModelPart head;

	@Shadow
	public ModelPart helmet;

	@Shadow
	public ModelPart torso;
	@Shadow
	public ModelPart rightArm;
	@Shadow
	public ModelPart leftArm;
	@Shadow
	public ModelPart rightLeg;
	@Shadow
	public ModelPart leftLeg;
	@Shadow
	public BipedEntityModel.ArmPose leftArmPose;
	@Shadow
	public BipedEntityModel.ArmPose rightArmPose;
	@Shadow
	public boolean sneaking;


	@Shadow
	protected abstract float lerpAngle(float from, float to, float position);

	@Shadow
	protected abstract ModelPart getArm(Arm arm);

	@Shadow
	protected abstract Arm getPreferredArm(T entity);

	@Shadow
	public abstract float method_2807(float f);

	@Shadow
	public float leaningPitch;

	@Shadow
	public abstract void method_30155(T livingEntity);

	@Shadow
	public abstract void method_30154(T livingEntity);


	@Overwrite
	public void method_29353(T livingEntity, float f) {
		if (this.handSwingProgress > 0.0F) {
			Arm arm = this.getPreferredArm(livingEntity);
			ModelPart modelPart = this.getArm(arm);
			float g = this.handSwingProgress;


			ModelPart var10000;
			this.rightArm.pivotZ = MathHelper.sin(this.torso.yaw) * 5.0F;
			this.rightArm.pivotX = -MathHelper.cos(this.torso.yaw) * 5.0F;
			this.leftArm.pivotZ = -MathHelper.sin(this.torso.yaw) * 5.0F;
			this.leftArm.pivotX = MathHelper.cos(this.torso.yaw) * 5.0F;
			var10000 = this.rightArm;
			var10000.yaw += this.torso.yaw;
			var10000 = this.leftArm;
			var10000.yaw += this.torso.yaw;
			var10000 = this.leftArm;
			var10000.pitch += this.torso.yaw;
			g = 1.0F - this.handSwingProgress;
			g *= g;
			g *= g;
			g = 1.0F - g;
			float h = MathHelper.sin(g * 3.1415927F);
			float i = MathHelper.sin(this.handSwingProgress * 3.1415927F) * -(this.head.pitch - 0.7F) * 0.75F;

			if (livingEntity.getMainHandStack().getItem() instanceof DualWield && livingEntity.getOffHandStack().isEmpty()){
				ModelPart modelPart2 = this.getArm(arm.getOpposite());

				modelPart2.pitch = (float)((double)modelPart.pitch - ((double)h * 1.2D + (double)i));
				modelPart2.yaw += this.torso.yaw * 2.0F;
				modelPart2.roll -= MathHelper.sin(this.handSwingProgress * 3.1415927F) * -0.4F;
			}
			else{
				this.torso.yaw = MathHelper.sin(MathHelper.sqrt(g) * 6.2831855F) * 0.2F;
				if (arm == Arm.LEFT) {
					this.torso.yaw *= -1.0F;
				}
			}
			modelPart.pitch = (float)((double)modelPart.pitch - ((double)h * 1.2D + (double)i));
			modelPart.yaw += this.torso.yaw * 2.0F;
			modelPart.roll += MathHelper.sin(this.handSwingProgress * 3.1415927F) * -0.4F;
		}
	}
}
