package com.wixl.better.blocks;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.tool.attribute.v1.FabricToolTags;
import net.minecraft.block.*;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.state.property.Properties;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.util.function.ToIntFunction;

public class MbBlocks {
	public static final Block GLASS_DOOR = register("glass_door", new MbDoorBlock(FabricBlockSettings.of(Material.GLASS).strength(0.3F, 0.3F).sounds(BlockSoundGroup.GLASS).nonOpaque()));
	public static final Block GRASSY_DIRT = register("grassy_dirt", new GrassyDirt(FabricBlockSettings.of(Material.SOIL, MaterialColor.DIRT).strength(0.3F, 0.3F).breakByTool(FabricToolTags.SHOVELS, 0).ticksRandomly().strength(0.5f, 0.5f).sounds(BlockSoundGroup.GRAVEL)));
	public static final Block CRACKED_ANDESITE = register("cracked_andesite", new Block(FabricBlockSettings.of(Material.STONE, MaterialColor.STONE).breakByTool(FabricToolTags.PICKAXES, 0).strength(1.5F, 6.0F)));

	private static Block register(String key, Block block) {
		return Registry.register(Registry.BLOCK, new Identifier("better", key), block);
	}

	public static void init(){
		System.out.println("init better Blocks!");
	}

	private static ToIntFunction<BlockState> createLightLevelFromBlockState(int litLevel) {
		return (blockState) -> (Boolean)blockState.get(Properties.LIT) ? litLevel : 0;
	}
}
