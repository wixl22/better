package com.wixl.better.mixin;

import com.wixl.better.enchantments.MbEnchantments;
import com.wixl.better.util.BlockBreaker;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.MiningToolItem;
import net.minecraft.item.PickaxeItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;

import java.util.Set;

import static net.minecraft.enchantment.EnchantmentHelper.getEquipmentLevel;

@Mixin(PickaxeItem.class)
public class PickaxeItemMixin extends MiningToolItem {
	protected PickaxeItemMixin(float attackDamage, float attackSpeed, ToolMaterial material, Set<Block> effectiveBlocks, Settings settings) {
		super(attackDamage, attackSpeed, material, effectiveBlocks, settings);
	}

	@Override
	public boolean canMine(BlockState state, World world, BlockPos blockPos, PlayerEntity player) {
		if (getEquipmentLevel(MbEnchantments.GIGANTIC, player) > 0) {
			if (player.isSneaking()) {
				return super.canMine(state, world, blockPos, player);
			}

			float originHardness = world.getBlockState(blockPos).getHardness(null, null);

			// only do a 3x3 break if the player's tool is effective on the block they are breaking
			// this makes it so breaking gravel doesn't break nearby stone
			if (player.getMainHandStack().isEffectiveOn(world.getBlockState(blockPos))) {
				BlockBreaker.breakInRadius(world, player, 1, (breakState) -> {
					double hardness = breakState.getHardness(null, null);
					boolean isEffective = player.getMainHandStack().isEffectiveOn(breakState);
					boolean verifyHardness = hardness < originHardness * 5 && hardness > 0;
					return isEffective && verifyHardness;
				}, true);
			}
		}
		return super.canMine(state, world, blockPos, player);
	}
}
