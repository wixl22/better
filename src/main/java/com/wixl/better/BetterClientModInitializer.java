package com.wixl.better;

import com.wixl.better.blocks.MbBlocks;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.render.RenderLayer;

public class BetterClientModInitializer implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
//		((MbArmorItem)MbItems.MINING_HELMET).setArmorModel(new TorchHelmetModel<>());
//		((MbArmorItem)MbItems.PIG_MASK).setArmorModel(new PigMaskModel<>());
//
//		((MbArmorItem)MbItems.FOX_HELMET).setArmorModel(new FoxArmorModel<>());
//		((MbArmorItem)MbItems.FOX_BOOTS).setArmorModel(new FoxArmorModel<>());
//		((MbArmorItem)MbItems.FOX_CHESTPLATE).setArmorModel(new FoxArmorModel<>());

		BlockRenderLayerMap.INSTANCE.putBlock(MbBlocks.GLASS_DOOR, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(MbBlocks.GRASSY_DIRT, RenderLayer.getCutout());
	}
}
