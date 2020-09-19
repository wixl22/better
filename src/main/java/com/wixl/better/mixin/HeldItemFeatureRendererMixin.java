package com.wixl.better.mixin;

import com.wixl.better.items.DualWield;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.feature.HeldItemFeatureRenderer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.ModelWithArms;
import net.minecraft.client.render.model.json.ModelTransformation;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Arm;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(HeldItemFeatureRenderer.class)
public abstract class HeldItemFeatureRendererMixin<T extends LivingEntity, M extends EntityModel<T> & ModelWithArms> extends FeatureRenderer<T, M> {

	public HeldItemFeatureRendererMixin(FeatureRendererContext<T, M> context) {
		super(context);
	}

	@Overwrite
	public void render(MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i, T livingEntity, float f, float g, float h, float j, float k, float l) {
		boolean bl = livingEntity.getMainArm() == Arm.RIGHT;
		ItemStack itemStack;
		ItemStack itemStack2;

		ItemStack mainHandStack = livingEntity.getMainHandStack();
		ItemStack offHandStack = livingEntity.getOffHandStack();
		if (offHandStack.isEmpty() && mainHandStack.getItem() instanceof DualWield) {
			offHandStack = mainHandStack;
		}

		if (bl) {
			itemStack = offHandStack;
			itemStack2 = mainHandStack;
		} else {
			itemStack = mainHandStack;
			itemStack2 = offHandStack;
		}
		if (!itemStack.isEmpty() || !itemStack2.isEmpty()) {
			matrixStack.push();
			if (this.getContextModel().child) {
				float m = 0.5F;
				matrixStack.translate(0.0D, 0.75D, 0.0D);
				matrixStack.scale(0.5F, 0.5F, 0.5F);
			}

			this.renderItem(livingEntity, itemStack2, ModelTransformation.Mode.THIRD_PERSON_RIGHT_HAND, Arm.RIGHT, matrixStack, vertexConsumerProvider, i);
			this.renderItem(livingEntity, itemStack, ModelTransformation.Mode.THIRD_PERSON_LEFT_HAND, Arm.LEFT, matrixStack, vertexConsumerProvider, i);
			matrixStack.pop();
		}
	}

	@Shadow
	public abstract void renderItem(LivingEntity entity, ItemStack stack, ModelTransformation.Mode transformationMode, Arm arm, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light);
}