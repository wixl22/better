package com.wixl.better.mixin;

import net.fabricmc.fabric.api.object.builder.v1.client.model.FabricModelPredicateProviderRegistry;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.ItemCooldownManager;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(SwordItem.class)
public class SwordItemMixin extends ToolItem {
	public SwordItemMixin(ToolMaterial material, Settings settings) {
		super(material, settings);
	}

	@Inject(at = @At("RETURN"), method = "<init>")
	public void init(ToolMaterial material, int attackDamage, float attackSpeed, Item.Settings settings, CallbackInfo info) {
		FabricModelPredicateProviderRegistry.register(new Identifier("blocking"), (stack, world, entity) -> entity != null && entity.isUsingItem() && entity.getActiveItem() == stack ? 1.0F : 0.0F);
	}

	public int getMaxUseTime(ItemStack stack) {
		return 10;
	}

	public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
		if (user instanceof PlayerEntity) {
			PlayerEntity playerEntity = (PlayerEntity) user;
			float timeLeft = playerEntity.getItemCooldownManager().getCooldownProgress(stack.getItem(), 0f);
			if (timeLeft <= 0) {
				playerEntity.getItemCooldownManager().set(stack.getItem(), 20);
			}
		}
		return super.finishUsing(stack, world, user);
	}

	public UseAction getUseAction(ItemStack stack) {
		return UseAction.BLOCK;
	}

	public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
		ItemStack itemStack = user.getStackInHand(hand);
		ItemCooldownManager itemCooldownManager = user.getItemCooldownManager();
		Item item = itemStack.getItem();
		if (itemCooldownManager.getCooldownProgress(item, 0) > 0) {
			return TypedActionResult.fail(itemStack);
		}

		user.setCurrentHand(hand);
		return TypedActionResult.consume(itemStack);
	}
}
