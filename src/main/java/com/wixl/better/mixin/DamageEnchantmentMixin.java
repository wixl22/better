package com.wixl.better.mixin;

import com.wixl.better.items.DualWieldTool;
import net.minecraft.enchantment.DamageEnchantment;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(DamageEnchantment.class)
public class DamageEnchantmentMixin {
	@Inject(method = "isAcceptableItem", at = @At("RETURN"), cancellable = true)
	public void isAcceptableItem(ItemStack stack, CallbackInfoReturnable<Boolean> callback) {
		boolean isAcceptable = callback.getReturnValue();
		if (!isAcceptable){
			callback.setReturnValue(stack.getItem() instanceof DualWieldTool);
		}
	}
}