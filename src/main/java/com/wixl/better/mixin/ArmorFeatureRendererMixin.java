package com.wixl.better.mixin;

import com.wixl.better.util.HookClient;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.feature.ArmorFeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.DyeableItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import java.util.Map;

@Mixin(ArmorFeatureRenderer.class)
public abstract class ArmorFeatureRendererMixin<T extends LivingEntity, M extends BipedEntityModel<T>, A extends BipedEntityModel<T>> extends FeatureRenderer<T, M> {
	@Shadow
	protected static Map<String, Identifier> ARMOR_TEXTURE_CACHE;

	public ArmorFeatureRendererMixin(FeatureRendererContext<T, M> context) {
		super(context);
	}

	@Shadow
	protected abstract void setVisible(A bipedModel, EquipmentSlot slot);

	@Shadow
	public abstract boolean usesSecondLayer(EquipmentSlot slot);

	private void renderArmor(MatrixStack matrices, VertexConsumerProvider vertexConsumers, T entity, EquipmentSlot slot, int light, A armorModel) {
		ItemStack itemStack = entity.getEquippedStack(slot);
		if (itemStack.getItem() instanceof ArmorItem) {
			ArmorItem armorItem = (ArmorItem) itemStack.getItem();
			if (armorItem.getSlotType() == slot) {
				armorModel = getArmorModelHook(entity, itemStack, slot, armorModel);
				this.getContextModel().setAttributes(armorModel);
				armorModel.child = entity.isBaby();
				this.setVisible(armorModel, slot);
				boolean bl2 = itemStack.hasGlint();
				if (armorItem instanceof DyeableItem) {
					int i = ((DyeableItem) armorItem).getColor(itemStack);
					float f = (float) (i >> 16 & 255) / 255.0F;
					float g = (float) (i >> 8 & 255) / 255.0F;
					float h = (float) (i & 255) / 255.0F;
					this.renderArmorModel(matrices, vertexConsumers, light, bl2, armorModel, f, g, h, this.getArmorResource(entity, itemStack, slot, (String) null));
					this.renderArmorModel(matrices, vertexConsumers, light, bl2, armorModel, 1.0F, 1.0F, 1.0F, this.getArmorResource(entity, itemStack, slot, "overlay"));
				} else {
					this.renderArmorModel(matrices, vertexConsumers, light, bl2, armorModel, 1.0F, 1.0F, 1.0F, this.getArmorResource(entity, itemStack, slot, (String) null));
				}

			}
		}
	}

	public void renderArmorModel(MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, boolean glint, A armorModel, float red, float green, float blue, Identifier armorTexture) {
		VertexConsumer vertexConsumer = ItemRenderer.getArmorVertexConsumer(vertexConsumers, RenderLayer.getArmorCutoutNoCull(armorTexture), false, glint);
		armorModel.render(matrices, vertexConsumer, light, OverlayTexture.DEFAULT_UV, red, green, blue, 1.0F);
	}


	public A getArmorModelHook(T entity, ItemStack itemStack, EquipmentSlot slot, A model) {
		return HookClient.getArmorModel(entity,itemStack, slot, model);
	}

	public Identifier getArmorResource(net.minecraft.entity.Entity entity, ItemStack stack, EquipmentSlot slot, String type) {
		ArmorItem item = (ArmorItem) stack.getItem();
		String texture = item.getMaterial().getName();
		String domain = "minecraft";
		int idx = texture.indexOf(':');
		if (idx != -1) {
			domain = texture.substring(0, idx);
			texture = texture.substring(idx + 1);
		}
		String s1 = String.format("%s:textures/models/armor/%s_layer_%d%s.png", domain, texture, (usesSecondLayer(slot) ? 2 : 1), type == null ? "" : String.format("_%s", type));

		s1 = HookClient.getArmorTexture(entity, stack, s1, slot, type);
		Identifier resourcelocation = ARMOR_TEXTURE_CACHE.get(s1);

		if (resourcelocation == null) {
			resourcelocation = new Identifier(s1);
			ARMOR_TEXTURE_CACHE.put(s1, resourcelocation);
		}

		return resourcelocation;
	}
	/*=================================== FORGE END ===========================================*/

}