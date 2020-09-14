package com.wixl.better.sound;

import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class MbSoundEvents {
	public static final SoundEvent MUSIC_DISC_DEMON_SLAYER = register("music_disc.demon_slayer");
	public static final SoundEvent MUSIC_DISC_SEVEN_NATION_ARMY = register("music_disc.seven_nation_army");
	public static final SoundEvent MUSIC_DISC_DONT_MINE_AT_NIGHT = register("music_disc.dont_mine_at_night");
	public static final SoundEvent MUSIC_DISC_GOOD_MORNING_WORLD = register("music_disc.good_morning_world");

	private static SoundEvent register(String id) {
		Identifier sound_id = new Identifier("better", id);
		return Registry.register(Registry.SOUND_EVENT, sound_id, new SoundEvent(sound_id));
	}
}
