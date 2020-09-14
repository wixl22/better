package com.wixl.better.mixin;

import com.wixl.better.items.ArmorWithElytra;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ElytraItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.stat.Stats;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import java.util.function.Consumer;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity {
	public LivingEntityMixin(EntityType<?> type, World world) {
		super(type, world);
	}

	public abstract byte getEquipmentBreakStatus(EquipmentSlot slot);

	@Shadow
	protected int roll;

	@Shadow
	public abstract ItemStack getEquippedStack(EquipmentSlot slot);

	@Shadow
	public abstract boolean hasStatusEffect(StatusEffect effect);

	@Overwrite
	private void initAi() {
		boolean bl = this.getFlag(7);
		if (bl && !this.onGround && !this.hasVehicle() && !this.hasStatusEffect(StatusEffects.LEVITATION)) {
			ItemStack itemStack = this.getEquippedStack(EquipmentSlot.CHEST);
			if ((itemStack.getItem() == Items.ELYTRA || itemStack.getItem() instanceof ArmorWithElytra) && ElytraItem.isUsable(itemStack)) {
				bl = true;
				if (!this.world.isClient && (this.roll + 1) % 20 == 0) {
					damage(itemStack, 1, getSelf(), ((livingEntity) -> {
						this.world.sendEntityStatus(this, getEquipmentBreakStatus(EquipmentSlot.CHEST));
					}));
				}
			} else {
				bl = false;
			}
		} else {
			bl = false;
		}

		if (!this.world.isClient) {
			this.setFlag(7, bl);
		}

	}

	public <T extends Entity> void damage(ItemStack stack, int amount, T entity, Consumer<T> breakCallback) {
		if (entity instanceof LivingEntity) {
			if (!entity.world.isClient && (!(entity instanceof PlayerEntity) || !((PlayerEntity) entity).abilities.creativeMode)) {
				if (stack.isDamageable()) {
					if (stack.damage(amount, ((LivingEntity) entity).getRandom(), entity instanceof ServerPlayerEntity ? (ServerPlayerEntity) entity : null)) {
						breakCallback.accept(entity);
						Item item = stack.getItem();
						stack.decrement(1);
						if (entity instanceof PlayerEntity) {
							((PlayerEntity) entity).incrementStat(Stats.BROKEN.getOrCreateStat(item));
						}
						stack.setDamage(0);
					}
				}
			}
		}
	}

	private PlayerEntity getSelf() {
		return (PlayerEntity) this.world.getEntityById(getEntityId());
	}

}
