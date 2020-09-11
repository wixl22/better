package com.wixl.better;

import com.wixl.better.blocks.MbBlocks;
import com.wixl.better.items.MbItems;
import net.fabricmc.api.ModInitializer;

public class Better implements ModInitializer {
	@Override
	public void onInitialize() {
		MbBlocks.init();
		MbItems.init();
	}
}
