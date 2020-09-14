package com.wixl.better.util;

import net.minecraft.block.BlockState;

public interface BreakValidator {
	boolean canBreak(BlockState state);
}