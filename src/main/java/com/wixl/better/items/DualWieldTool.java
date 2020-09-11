package com.wixl.better.items;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.Material;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class DualWieldTool extends ToolItem {
	private final float attackDamage;
	private final float attackSpeed;
	private final Multimap<EntityAttribute, EntityAttributeModifier> attributes;

	public DualWieldTool(ToolMaterial material, float attackDamage, float attackSpeed, Settings settings) {
		super(material, settings);
		this.attackSpeed = attackSpeed;
		this.attackDamage = attackDamage;
		ImmutableMultimap.Builder<EntityAttribute, EntityAttributeModifier> builder = ImmutableMultimap.builder();
		builder.put(EntityAttributes.GENERIC_ATTACK_DAMAGE, new EntityAttributeModifier(ATTACK_DAMAGE_MODIFIER_ID, "Weapon modifier", this.attackDamage, EntityAttributeModifier.Operation.ADDITION));
		builder.put(EntityAttributes.GENERIC_ATTACK_SPEED, new EntityAttributeModifier(ATTACK_SPEED_MODIFIER_ID, "Weapon modifier", this.attackSpeed, EntityAttributeModifier.Operation.ADDITION));
		this.attributes = builder.build();
	}

	public float getAttackDamage() {
		return this.attackDamage;
	}

	public boolean canMine(BlockState state, World world, BlockPos pos, PlayerEntity miner) {
		return !miner.isCreative();
	}

	public float getMiningSpeedMultiplier(ItemStack stack, BlockState state) {
		Block block = state.getBlock();
		if (block == Blocks.COBWEB) {
			return 15.0F;
		} else {
			Material material = state.getMaterial();
			return material != Material.PLANT && material != Material.REPLACEABLE_PLANT && material != Material.UNUSED_PLANT && !state.isIn(BlockTags.LEAVES) && material != Material.GOURD ? 1.0F : 1.5F;
		}
	}

	public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
		stack.damage(1, attacker, ((e) -> e.sendEquipmentBreakStatus(EquipmentSlot.MAINHAND)));
		ItemStack offHandStack = attacker.getOffHandStack();
		if (stack.getItem() == offHandStack.getItem()){
			offHandStack.damage(1, attacker, ((e) -> e.sendEquipmentBreakStatus(EquipmentSlot.OFFHAND)));
		}
		return true;
	}

	public boolean postMine(ItemStack stack, World world, BlockState state, BlockPos pos, LivingEntity miner) {
		if (state.getHardness(world, pos) != 0.0F) {
			stack.damage(2, miner, ((e) -> e.sendEquipmentBreakStatus(EquipmentSlot.MAINHAND)));
			ItemStack offHandStack = miner.getOffHandStack();
			if (stack.getItem() == offHandStack.getItem()){
				offHandStack.damage(2, miner, ((e) -> e.sendEquipmentBreakStatus(EquipmentSlot.OFFHAND)));
			}
		}
		return true;
	}

	public Multimap<EntityAttribute, EntityAttributeModifier> getAttributeModifiers(EquipmentSlot equipmentSlot) {
		return equipmentSlot == EquipmentSlot.MAINHAND ? this.attributes : super.getAttributeModifiers(equipmentSlot);
	}
}
