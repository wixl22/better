package com.wixl.better.items;


import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class LongBowItem extends MbBowItem {
	public LongBowItem(Settings settings) {
		super(settings);
	}

	@Override
	public void onStoppedUsing(ItemStack stack, World world, LivingEntity user, int remainingUseTicks) {
		shootArrow(stack, world, user, remainingUseTicks, 4.0F);
	}

	@Override
	public float getPullProgressBetter(int useTicks) {
		float f = (float) useTicks / 40.0F;
		f = (f * f + f * 2.0F) / 3.0F;
		if (f > 1.0F) {
			f = 1.0F;
		}

		return f;
	}
}
