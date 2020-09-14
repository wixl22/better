package com.wixl.better.mixin;

import com.wixl.better.blocks.MbBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.SugarCaneBlock;
import net.minecraft.fluid.FluidState;
import net.minecraft.tag.FluidTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.WorldView;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(SugarCaneBlock.class)
public class SugarCaneBlockMixin {

	@Overwrite
	public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
		BlockState blockState = world.getBlockState(pos.down());
		if (blockState.isOf(Blocks.SUGAR_CANE)) {
			return true;
		} else {
			if (blockState.isOf(Blocks.GRASS_BLOCK) || blockState.isOf(MbBlocks.GRASSY_DIRT) || blockState.isOf(Blocks.DIRT) || blockState.isOf(Blocks.COARSE_DIRT) || blockState.isOf(Blocks.PODZOL) || blockState.isOf(Blocks.SAND) || blockState.isOf(Blocks.RED_SAND)) {
				BlockPos blockPos = pos.down();

				for (Direction direction : Direction.Type.HORIZONTAL) {
					BlockState blockState2 = world.getBlockState(blockPos.offset(direction));
					FluidState fluidState = world.getFluidState(blockPos.offset(direction));
					if (fluidState.isIn(FluidTags.WATER) || blockState2.isOf(Blocks.FROSTED_ICE)) {
						return true;
					}
				}
			}

			return false;
		}
	}
}
