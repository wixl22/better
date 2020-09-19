package com.wixl.better;

import com.wixl.better.blocks.MbBlocks;
import com.wixl.better.items.ArmorWithElytra;
import com.wixl.better.items.MbArmorItem;
import com.wixl.better.items.MbBowItem;
import com.wixl.better.items.MbItems;
import com.wixl.better.render.model.FoxArmorModel;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.object.builder.v1.client.model.FabricModelPredicateProviderRegistry;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;

public class BetterClientModInitializer implements ClientModInitializer {
	@Override
	public void onInitializeClient() {

		FabricModelPredicateProviderRegistry.register(Items.WOODEN_SWORD, new Identifier("blocking"), (stack, world, entity) -> entity != null && entity.isUsingItem() && entity.getActiveItem() == stack ? 1.0F : 0.0F);
		FabricModelPredicateProviderRegistry.register(Items.IRON_SWORD, new Identifier("blocking"), (stack, world, entity) -> entity != null && entity.isUsingItem() && entity.getActiveItem() == stack ? 1.0F : 0.0F);
		FabricModelPredicateProviderRegistry.register(Items.STONE_SWORD, new Identifier("blocking"), (stack, world, entity) -> entity != null && entity.isUsingItem() && entity.getActiveItem() == stack ? 1.0F : 0.0F);
		FabricModelPredicateProviderRegistry.register(Items.DIAMOND_SWORD, new Identifier("blocking"), (stack, world, entity) -> entity != null && entity.isUsingItem() && entity.getActiveItem() == stack ? 1.0F : 0.0F);
		FabricModelPredicateProviderRegistry.register(Items.GOLDEN_SWORD, new Identifier("blocking"), (stack, world, entity) -> entity != null && entity.isUsingItem() && entity.getActiveItem() == stack ? 1.0F : 0.0F);
		FabricModelPredicateProviderRegistry.register(Items.NETHERITE_SWORD, new Identifier("blocking"), (stack, world, entity) -> entity != null && entity.isUsingItem() && entity.getActiveItem() == stack ? 1.0F : 0.0F);

		FabricModelPredicateProviderRegistry.register(MbItems.CHAINMAIL_CHESTPLATE_WITH_ELYTRA, new Identifier("broken"), (stack, world, entity) -> ArmorWithElytra.isUsable(stack) ? 0.0F : 1.0F);
		FabricModelPredicateProviderRegistry.register(MbItems.IRON_CHESTPLATE_WITH_ELYTRA, new Identifier("broken"), (stack, world, entity) -> ArmorWithElytra.isUsable(stack) ? 0.0F : 1.0F);
		FabricModelPredicateProviderRegistry.register(MbItems.GOLDEN_CHESTPLATE_WITH_ELYTRA, new Identifier("broken"), (stack, world, entity) -> ArmorWithElytra.isUsable(stack) ? 0.0F : 1.0F);
		FabricModelPredicateProviderRegistry.register(MbItems.DIAMOND_CHESTPLATE_WITH_ELYTRA, new Identifier("broken"), (stack, world, entity) -> ArmorWithElytra.isUsable(stack) ? 0.0F : 1.0F);
		FabricModelPredicateProviderRegistry.register(MbItems.NETHERITE_CHESTPLATE_WITH_ELYTRA, new Identifier("broken"), (stack, world, entity) -> ArmorWithElytra.isUsable(stack) ? 0.0F : 1.0F);

		FabricModelPredicateProviderRegistry.register(MbItems.LONGBOW, new Identifier("pull"), (stack, world, entity) -> {
			if (entity == null) {
				return 0.0F;
			} else {
				return entity.getActiveItem().getItem() instanceof MbBowItem ? ((MbBowItem)stack.getItem()).getPullProgressBetter(stack.getMaxUseTime() - entity.getItemUseTimeLeft()) : 0.0F;
			}
		});
		FabricModelPredicateProviderRegistry.register(MbItems.LONGBOW, new Identifier("pulling"), (stack, world, entity) -> {
			return entity != null && entity.isUsingItem() && entity.getActiveItem() == stack ? 1.0F : 0.0F;
		});

		((MbArmorItem)MbItems.FOX_HELMET).setArmorModel(new FoxArmorModel<>());
		((MbArmorItem)MbItems.FOX_BOOTS).setArmorModel(new FoxArmorModel<>());
		((MbArmorItem)MbItems.FOX_CHESTPLATE).setArmorModel(new FoxArmorModel<>());

		BlockRenderLayerMap.INSTANCE.putBlock(MbBlocks.GLASS_DOOR, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(MbBlocks.GRASSY_DIRT, RenderLayer.getCutout());
	}
}
