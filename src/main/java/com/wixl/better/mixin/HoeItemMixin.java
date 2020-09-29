package com.wixl.better.mixin;

import com.wixl.better.blocks.MbBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.item.HoeItem;
import net.minecraft.item.Item;
import net.minecraft.item.ToolMaterial;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Map;

@Mixin(HoeItem.class)
public class HoeItemMixin {
	@Shadow
	protected static Map<Block, BlockState> TILLED_BLOCKS;

	@Inject(at = @At("RETURN"), method = "<init>")
	public void init(ToolMaterial material, int attackDamage, float attackSpeed, Item.Settings settings, CallbackInfo info) {
		TILLED_BLOCKS.put(MbBlocks.GRASSY_DIRT,  Blocks.FARMLAND.getDefaultState());
	}
}
