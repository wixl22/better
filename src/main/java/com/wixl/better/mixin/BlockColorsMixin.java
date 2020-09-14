package com.wixl.better.mixin;

import com.google.common.collect.ImmutableSet;
import com.wixl.better.blocks.MbBlocks;
import net.minecraft.block.*;
import net.minecraft.block.enums.DoubleBlockHalf;
import net.minecraft.client.color.block.BlockColors;
import net.minecraft.client.color.world.BiomeColors;
import net.minecraft.client.color.world.FoliageColors;
import net.minecraft.client.color.world.GrassColors;
import net.minecraft.state.property.Property;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BlockColors.class)
public class BlockColorsMixin {
	@Inject(method = "create", at = @At("RETURN"), cancellable = true)
	private static void colorInit(CallbackInfoReturnable<BlockColors> info)
	{
		BlockColors colors = info.getReturnValue();

		colors.registerColorProvider((state, view, pos, tintIndex) -> {
			return view != null && pos != null ? BiomeColors.getGrassColor(view, pos) : GrassColors.getColor(0.5D, 1.0D);
		}, MbBlocks.GRASSY_DIRT);

		info.setReturnValue(colors);
	}

}
