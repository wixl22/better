package com.wixl.better.blocks;

import net.minecraft.block.*;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

import java.util.Map;
import java.util.Random;

public class GrassyDirt extends Block implements Fertilizable {
	public static final IntProperty AGE = Properties.AGE_1;

	public GrassyDirt(Settings settings) {
		super(settings);
		setDefaultState(getStateManager().getDefaultState().with(AGE, 0));
	}

	public BlockState withAge(int age) {
		return this.getDefaultState().with(this.getAgeProperty(), age);
	}


	public IntProperty getAgeProperty() {
		return AGE;
	}

	@Override
	protected void appendProperties(StateManager.Builder<Block, BlockState> stateManager) {
		super.appendProperties(stateManager);
		stateManager.add(AGE);
	}

	public int getMaxAge() {
		return 1;
	}

	protected int getAge(BlockState state) {
		return state.get(this.getAgeProperty());
	}

	public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
		super.scheduledTick(state, world, pos, random);
		if (world.getLightLevel(pos.up()) >= 9) {
			grow(world, random, pos, state);
		}
	}

	@Override
	public boolean isFertilizable(BlockView world, BlockPos pos, BlockState state, boolean isClient) {
		return world.getBlockState(pos.up()).isAir();
	}

	@Override
	public boolean canGrow(World world, Random random, BlockPos pos, BlockState state) {
		return true;
	}

	@Override
	public void grow(ServerWorld world, Random random, BlockPos pos, BlockState state) {
		int i = this.getAge(state);
		if (i < this.getMaxAge()) {
			world.setBlockState(pos, this.withAge(i + 1), 2);
		} else {
			world.setBlockState(pos, Blocks.GRASS_BLOCK.getDefaultState(), 2);
		}
	}
}
