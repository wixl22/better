package com.wixl.better.mixin;

import com.google.common.base.MoreObjects;
import com.wixl.better.items.DualWieldTool;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.item.HeldItemRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.util.math.Vector3f;
import net.minecraft.item.CrossbowItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Hand;
import net.minecraft.util.math.MathHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(HeldItemRenderer.class)
public class HeldItemRendererMixin {

	@Shadow
	private ItemStack mainHand;
	@Shadow
	private ItemStack offHand;
	@Shadow
	private float equipProgressMainHand;
	@Shadow
	private float prevEquipProgressMainHand;
	@Shadow
	private float equipProgressOffHand;
	@Shadow
	private float prevEquipProgressOffHand;


	@Shadow
	private void renderFirstPersonItem(AbstractClientPlayerEntity player, float tickDelta, float pitch, Hand hand, float swingProgress, ItemStack item, float equipProgress, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light) {
	}

	@Overwrite
	public void renderItem(float tickDelta, MatrixStack matrices, VertexConsumerProvider.Immediate vertexConsumers, ClientPlayerEntity player, int light) {
		float f = player.getHandSwingProgress(tickDelta);
		Hand hand = (Hand) MoreObjects.firstNonNull(player.preferredHand, Hand.MAIN_HAND);
		float g = MathHelper.lerp(tickDelta, player.prevPitch, player.pitch);
		boolean bl = true;
		boolean bl2 = true;
		ItemStack itemStack3;
		if (player.isUsingItem()) {
			itemStack3 = player.getActiveItem();
			if (itemStack3.getItem() == Items.BOW || itemStack3.getItem() == Items.CROSSBOW) {
				bl = player.getActiveHand() == Hand.MAIN_HAND;
				bl2 = !bl;
			}

			Hand hand2 = player.getActiveHand();
			if (hand2 == Hand.MAIN_HAND) {
				ItemStack itemStack2 = player.getOffHandStack();
				if (itemStack2.getItem() == Items.CROSSBOW && CrossbowItem.isCharged(itemStack2)) {
					bl2 = false;
				}
			}
		} else {
			itemStack3 = player.getMainHandStack();
			ItemStack itemStack4 = player.getOffHandStack();
			if (itemStack3.getItem() == Items.CROSSBOW && CrossbowItem.isCharged(itemStack3)) {
				bl2 = !bl;
			}

			if (itemStack4.getItem() == Items.CROSSBOW && CrossbowItem.isCharged(itemStack4)) {
				bl = !itemStack3.isEmpty();
				bl2 = !bl;
			}
		}

		float h = MathHelper.lerp(tickDelta, player.lastRenderPitch, player.renderPitch);
		float i = MathHelper.lerp(tickDelta, player.lastRenderYaw, player.renderYaw);
		matrices.multiply(Vector3f.POSITIVE_X.getDegreesQuaternion((player.getPitch(tickDelta) - h) * 0.1F));
		matrices.multiply(Vector3f.POSITIVE_Y.getDegreesQuaternion((player.getYaw(tickDelta) - i) * 0.1F));
		float m;
		float l;
		if (bl) {
			l = hand == Hand.MAIN_HAND ? f : 0.0F;
			m = 1.0F - MathHelper.lerp(tickDelta, this.prevEquipProgressMainHand, this.equipProgressMainHand);
			this.renderFirstPersonItem(player, tickDelta, g, Hand.MAIN_HAND, l, this.mainHand, m, matrices, vertexConsumers, light);
		}

		if (bl2) {
			Item mainHandItem = player.getMainHandStack().getItem();
			if (mainHandItem instanceof DualWieldTool && player.getOffHandStack().getItem() == mainHandItem) {
				l = f;
			} else {
				l = hand == Hand.OFF_HAND ? f : 0.0F;
			}

			m = 1.0F - MathHelper.lerp(tickDelta, this.prevEquipProgressOffHand, this.equipProgressOffHand);
			this.renderFirstPersonItem(player, tickDelta, g, Hand.OFF_HAND, l, this.offHand, m, matrices, vertexConsumers, light);
		}

		vertexConsumers.draw();
	}
}
