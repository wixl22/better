package com.wixl.better.blocks;

import net.minecraft.block.BlockState;
import net.minecraft.block.DoorBlock;
import net.minecraft.block.Material;
import net.minecraft.block.enums.DoorHinge;
import net.minecraft.block.enums.DoubleBlockHalf;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

public class MbDoorBlock extends DoorBlock {
	protected MbDoorBlock(Settings settings) {
		super(settings);
		this.setDefaultState(this.stateManager.getDefaultState().with(FACING, Direction.NORTH).with(OPEN, false).with(HINGE, DoorHinge.LEFT).with(POWERED, false).with(HALF, DoubleBlockHalf.LOWER));
	}

	@Override
	public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
		state = state.cycle(OPEN);
		world.setBlockState(pos, state, 10);
		world.syncWorldEvent(player, state.get(OPEN) ? this.getCloseSoundEventId() : this.getOpenSoundEventId(), pos, 0);
		return ActionResult.SUCCESS;
	}

	private int getOpenSoundEventId() {
		return this.material == Material.METAL ? 1011 : 1012;
	}

	private int getCloseSoundEventId() {
		return this.material == Material.METAL ? 1005 : 1006;
	}
}
