package com.wixl.better.paintings;

import net.minecraft.entity.decoration.painting.PaintingMotive;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class MbPaintings extends PaintingMotive {

	public static final PaintingMotive TWILIGHT_WITH_FIZZLEPOP = register("twilight_with_fizzlepop", 64, 48);
	public static final PaintingMotive STEEVE2 = register("steeve2", 64, 64);
	public static final PaintingMotive STEEVE = register("steeve", 128, 64);
	public static final PaintingMotive SPACE = register("space", 32, 16);
	public static final PaintingMotive SKULL_CAVE = register("skull_cave", 96, 48);
	public static final PaintingMotive RAPUNZEL = register("rapunzel", 64, 32);
	public static final PaintingMotive RAINBOW_DASH = register("rainbow_dash", 64, 64);
	public static final PaintingMotive EASTER = register("easter", 96, 64);
	public static final PaintingMotive CREEPER = register("creeper", 64, 96);
	public static final PaintingMotive CHARGED_CREEPER = register("charged_creeper", 32, 64);
	public static final PaintingMotive BREWING = register("breewing", 32, 64);
	public static final PaintingMotive ABSTRACT_WOMAN = register("abstract_woman", 16, 32);

	public MbPaintings(int width, int height) {
		super(width, height);
	}
	private static PaintingMotive register(String key, int width, int height) {
		return Registry.register(Registry.PAINTING_MOTIVE, new Identifier("better", key), new PaintingMotive(width, height));
	}

	public static void init(){
		System.out.println("init paintings");
	}
}
