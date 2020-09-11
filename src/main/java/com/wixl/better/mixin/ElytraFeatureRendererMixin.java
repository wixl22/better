package com.wixl.better.mixin;

import com.wixl.better.items.ArmorWithElytra;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.PlayerModelPart;
import net.minecraft.client.render.entity.feature.ElytraFeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.ElytraEntityModel;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(ElytraFeatureRenderer.class)
public class ElytraFeatureRendererMixin<T extends LivingEntity, M extends EntityModel<T>> extends FeatureRenderer<T, M> {
	private static final Identifier SKIN = new Identifier("textures/entity/elytra.png");
	private final ElytraEntityModel<T> elytra = new ElytraEntityModel();

	public ElytraFeatureRendererMixin(FeatureRendererContext<T, M> context) {
		super(context);
	}

	@Override
	public void render(MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i, T livingEntity, float f, float g, float h, float j, float k, float l) {
		ItemStack itemStack = livingEntity.getEquippedStack(EquipmentSlot.CHEST);
		Item item = itemStack.getItem();
		if (item == Items.ELYTRA || item instanceof ArmorWithElytra) {
			Identifier identifier4;
			if (livingEntity instanceof AbstractClientPlayerEntity) {
				AbstractClientPlayerEntity abstractClientPlayerEntity = (AbstractClientPlayerEntity)livingEntity;
				if (abstractClientPlayerEntity.canRenderElytraTexture() && abstractClientPlayerEntity.getElytraTexture() != null) {
					identifier4 = abstractClientPlayerEntity.getElytraTexture();
				} else if (abstractClientPlayerEntity.canRenderCapeTexture() && abstractClientPlayerEntity.getCapeTexture() != null && abstractClientPlayerEntity.isPartVisible(PlayerModelPart.CAPE)) {
					identifier4 = abstractClientPlayerEntity.getCapeTexture();
				} else {
					identifier4 = SKIN;
				}
			} else {
				identifier4 = SKIN;
			}

			matrixStack.push();
			matrixStack.translate(0.0D, 0.0D, 0.125D);
			this.getContextModel().copyStateTo(this.elytra);
			this.elytra.setAngles(livingEntity, f, g, j, k, l);
			VertexConsumer vertexConsumer = ItemRenderer.getArmorVertexConsumer(vertexConsumerProvider, RenderLayer.getArmorCutoutNoCull(identifier4), false, itemStack.hasGlint());
			this.elytra.render(matrixStack, vertexConsumer, i, OverlayTexture.DEFAULT_UV, 1.0F, 1.0F, 1.0F, 1.0F);
			matrixStack.pop();
		}
	}
}
