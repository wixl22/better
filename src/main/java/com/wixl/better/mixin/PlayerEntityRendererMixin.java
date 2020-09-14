package com.wixl.better.mixin;

import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.entity.PlayerEntityRenderer;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerEntityRenderer.class)
public class PlayerEntityRendererMixin {
	@Inject(method = "getArmPose", at = @At(value = "HEAD"), cancellable = true)
	private static void getArmPose(AbstractClientPlayerEntity abstractClientPlayerEntity, Hand hand, CallbackInfoReturnable<BipedEntityModel.ArmPose> callbackInfo) {
		ItemStack itemStack3 = abstractClientPlayerEntity.getStackInHand(hand);
		if (!itemStack3.isEmpty()) {
			BipedEntityModel.ArmPose armPose = BipedEntityModel.ArmPose.ITEM;
			if (abstractClientPlayerEntity.getActiveHand() != hand) {
				callbackInfo.setReturnValue(armPose);
				callbackInfo.cancel();
			}
		}
	}
}
