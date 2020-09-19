package com.wixl.better.mixin;

import com.google.common.base.MoreObjects;
import com.wixl.better.items.DualWield;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.item.HeldItemRenderer;
import net.minecraft.client.render.model.json.ModelTransformation;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.util.math.Vector3f;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.CrossbowItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Arm;
import net.minecraft.util.Hand;
import net.minecraft.util.math.MathHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(HeldItemRenderer.class)
public abstract class HeldItemRendererMixin {

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
		float l;
		if (bl) {
			l = hand == Hand.MAIN_HAND ? f : 0.0F;
			this.renderFirstPersonItem(player, tickDelta, g, Hand.MAIN_HAND, l, this.mainHand, 0, matrices, vertexConsumers, light);
		}

		if (bl2) {
			Item mainHandItem = player.getMainHandStack().getItem();
			if (mainHandItem instanceof DualWield && player.getOffHandStack().isEmpty()) {
				l = f;
			} else {
				l = hand == Hand.OFF_HAND ? f : 0.0F;
			}

			this.renderFirstPersonItem(player, tickDelta, g, Hand.OFF_HAND, l, this.offHand, 0, matrices, vertexConsumers, light);
		}

		vertexConsumers.draw();
	}
	@Shadow
	public abstract void renderArmHoldingItem(MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, float equipProgress, float swingProgress, Arm arm);
	@Shadow
	public abstract void renderMapInBothHands(MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, float pitch, float equipProgress, float swingProgress);

	@Shadow
	private MinecraftClient client;

	@Shadow
	public abstract void renderMapInOneHand(MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, float equipProgress, Arm arm, float swingProgress, ItemStack stack);

	@Shadow
	public abstract void applyEquipOffset(MatrixStack matrices, Arm arm, float equipProgress);

	@Shadow
	public abstract void renderItem(LivingEntity entity, ItemStack stack, ModelTransformation.Mode renderMode, boolean leftHanded, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light);

	@Shadow
	public abstract void applySwingOffset(MatrixStack matrices, Arm arm, float swingProgress);

	@Shadow
	public abstract void applyEatOrDrinkTransformation(MatrixStack matrices, float tickDelta, Arm arm, ItemStack stack);

		@Overwrite
	private void renderFirstPersonItem(AbstractClientPlayerEntity player, float tickDelta, float pitch, Hand hand, float swingProgress, ItemStack item, float equipProgress, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light) {
		boolean bl = hand == Hand.MAIN_HAND;
		Arm arm = bl ? player.getMainArm() : player.getMainArm().getOpposite();
		matrices.push();
		if (item.isEmpty()) {
			if (bl){
				if (!player.isInvisible()) {
					this.renderArmHoldingItem(matrices, vertexConsumers, light, equipProgress, swingProgress, arm);
				}
			}
			else{
				if (!this.mainHand.isEmpty() && this.mainHand.getItem() instanceof DualWield) {
					float aa = -0.4F * MathHelper.sin(MathHelper.sqrt(swingProgress) * 3.1415927F);
					float u = 0.2F * MathHelper.sin(MathHelper.sqrt(swingProgress) * 6.2831855F);
					float v = -0.2F * MathHelper.sin(swingProgress * 3.1415927F);
					boolean bl4 = arm == Arm.RIGHT;
					int ad = bl4 ? 1 : -1;
					matrices.translate((float) ad * aa, u, v);
					this.applyEquipOffset(matrices, arm, equipProgress);
					this.applySwingOffset(matrices, arm, swingProgress);
					this.renderItem(player, ((DualWield) mainHand.getItem()).getItemToShowInOffhand(), bl4 ? ModelTransformation.Mode.FIRST_PERSON_RIGHT_HAND : ModelTransformation.Mode.FIRST_PERSON_LEFT_HAND, !bl4, matrices, vertexConsumers, light);
				}
			}
		} else if (item.getItem() == Items.FILLED_MAP) {
			if (bl && this.offHand.isEmpty()) {
				this.renderMapInBothHands(matrices, vertexConsumers, light, pitch, equipProgress, swingProgress);
			} else {
				this.renderMapInOneHand(matrices, vertexConsumers, light, equipProgress, arm, swingProgress, item);
			}
		} else {
			boolean bl4;
			float v;
			float w;
			float x;
			float y;
			if (item.getItem() == Items.CROSSBOW) {
				bl4 = CrossbowItem.isCharged(item);
				boolean bl3 = arm == Arm.RIGHT;
				int i = bl3 ? 1 : -1;
				if (player.isUsingItem() && player.getItemUseTimeLeft() > 0 && player.getActiveHand() == hand) {
					this.applyEquipOffset(matrices, arm, equipProgress);
					matrices.translate((double)((float)i * -0.4785682F), -0.0943870022892952D, 0.05731530860066414D);
					matrices.multiply(Vector3f.POSITIVE_X.getDegreesQuaternion(-11.935F));
					matrices.multiply(Vector3f.POSITIVE_Y.getDegreesQuaternion((float)i * 65.3F));
					matrices.multiply(Vector3f.POSITIVE_Z.getDegreesQuaternion((float)i * -9.785F));
					v = (float)item.getMaxUseTime() - ((float)this.client.player.getItemUseTimeLeft() - tickDelta + 1.0F);
					w = v / (float)CrossbowItem.getPullTime(item);
					if (w > 1.0F) {
						w = 1.0F;
					}

					if (w > 0.1F) {
						x = MathHelper.sin((v - 0.1F) * 1.3F);
						y = w - 0.1F;
						float k = x * y;
						matrices.translate((double)(k * 0.0F), (double)(k * 0.004F), (double)(k * 0.0F));
					}

					matrices.translate((double)(w * 0.0F), (double)(w * 0.0F), (double)(w * 0.04F));
					matrices.scale(1.0F, 1.0F, 1.0F + w * 0.2F);
					matrices.multiply(Vector3f.NEGATIVE_Y.getDegreesQuaternion((float)i * 45.0F));
				} else {
					v = -0.4F * MathHelper.sin(MathHelper.sqrt(swingProgress) * 3.1415927F);
					w = 0.2F * MathHelper.sin(MathHelper.sqrt(swingProgress) * 6.2831855F);
					x = -0.2F * MathHelper.sin(swingProgress * 3.1415927F);
					matrices.translate((double)((float)i * v), (double)w, (double)x);
					this.applyEquipOffset(matrices, arm, equipProgress);
					this.applySwingOffset(matrices, arm, swingProgress);
					if (bl4 && swingProgress < 0.001F) {
						matrices.translate((double)((float)i * -0.641864F), 0.0D, 0.0D);
						matrices.multiply(Vector3f.POSITIVE_Y.getDegreesQuaternion((float)i * 10.0F));
					}
				}

				this.renderItem(player, item, bl3 ? ModelTransformation.Mode.FIRST_PERSON_RIGHT_HAND : ModelTransformation.Mode.FIRST_PERSON_LEFT_HAND, !bl3, matrices, vertexConsumers, light);
			} else {
				bl4 = arm == Arm.RIGHT;
				int o;
				float u;
				if (player.isUsingItem() && player.getItemUseTimeLeft() > 0 && player.getActiveHand() == hand) {
					o = bl4 ? 1 : -1;
					switch(item.getUseAction()) {
						case NONE:
							this.applyEquipOffset(matrices, arm, equipProgress);
							break;
						case EAT:
						case DRINK:
							this.applyEatOrDrinkTransformation(matrices, tickDelta, arm, item);
							this.applyEquipOffset(matrices, arm, equipProgress);
							break;
						case BLOCK:
							this.applyEquipOffset(matrices, arm, equipProgress);
							break;
						case BOW:
							this.applyEquipOffset(matrices, arm, equipProgress);
							matrices.translate((double)((float)o * -0.2785682F), 0.18344387412071228D, 0.15731531381607056D);
							matrices.multiply(Vector3f.POSITIVE_X.getDegreesQuaternion(-13.935F));
							matrices.multiply(Vector3f.POSITIVE_Y.getDegreesQuaternion((float)o * 35.3F));
							matrices.multiply(Vector3f.POSITIVE_Z.getDegreesQuaternion((float)o * -9.785F));
							u = (float)item.getMaxUseTime() - ((float)this.client.player.getItemUseTimeLeft() - tickDelta + 1.0F);
							v = u / 20.0F;
							v = (v * v + v * 2.0F) / 3.0F;
							if (v > 1.0F) {
								v = 1.0F;
							}

							if (v > 0.1F) {
								w = MathHelper.sin((u - 0.1F) * 1.3F);
								x = v - 0.1F;
								y = w * x;
								matrices.translate((double)(y * 0.0F), (double)(y * 0.004F), (double)(y * 0.0F));
							}

							matrices.translate((double)(v * 0.0F), (double)(v * 0.0F), (double)(v * 0.04F));
							matrices.scale(1.0F, 1.0F, 1.0F + v * 0.2F);
							matrices.multiply(Vector3f.NEGATIVE_Y.getDegreesQuaternion((float)o * 45.0F));
							break;
						case SPEAR:
							this.applyEquipOffset(matrices, arm, equipProgress);
							matrices.translate((double)((float)o * -0.5F), 0.699999988079071D, 0.10000000149011612D);
							matrices.multiply(Vector3f.POSITIVE_X.getDegreesQuaternion(-55.0F));
							matrices.multiply(Vector3f.POSITIVE_Y.getDegreesQuaternion((float)o * 35.3F));
							matrices.multiply(Vector3f.POSITIVE_Z.getDegreesQuaternion((float)o * -9.785F));
							u = (float)item.getMaxUseTime() - ((float)this.client.player.getItemUseTimeLeft() - tickDelta + 1.0F);
							v = u / 10.0F;
							if (v > 1.0F) {
								v = 1.0F;
							}

							if (v > 0.1F) {
								w = MathHelper.sin((u - 0.1F) * 1.3F);
								x = v - 0.1F;
								y = w * x;
								matrices.translate((double)(y * 0.0F), (double)(y * 0.004F), (double)(y * 0.0F));
							}

							matrices.translate(0.0D, 0.0D, (double)(v * 0.2F));
							matrices.scale(1.0F, 1.0F, 1.0F + v * 0.2F);
							matrices.multiply(Vector3f.NEGATIVE_Y.getDegreesQuaternion((float)o * 45.0F));
					}
				} else if (player.isUsingRiptide()) {
					this.applyEquipOffset(matrices, arm, equipProgress);
					o = bl4 ? 1 : -1;
					matrices.translate((double)((float)o * -0.4F), 0.800000011920929D, 0.30000001192092896D);
					matrices.multiply(Vector3f.POSITIVE_Y.getDegreesQuaternion((float)o * 65.0F));
					matrices.multiply(Vector3f.POSITIVE_Z.getDegreesQuaternion((float)o * -85.0F));
				} else {
					float aa = -0.4F * MathHelper.sin(MathHelper.sqrt(swingProgress) * 3.1415927F);
					u = 0.2F * MathHelper.sin(MathHelper.sqrt(swingProgress) * 6.2831855F);
					v = -0.2F * MathHelper.sin(swingProgress * 3.1415927F);
					int ad = bl4 ? 1 : -1;
					matrices.translate((double)((float)ad * aa), (double)u, (double)v);
					this.applyEquipOffset(matrices, arm, equipProgress);
					this.applySwingOffset(matrices, arm, swingProgress);
				}

				this.renderItem(player, item, bl4 ? ModelTransformation.Mode.FIRST_PERSON_RIGHT_HAND : ModelTransformation.Mode.FIRST_PERSON_LEFT_HAND, !bl4, matrices, vertexConsumers, light);
			}
		}

		matrices.pop();
	}
}
