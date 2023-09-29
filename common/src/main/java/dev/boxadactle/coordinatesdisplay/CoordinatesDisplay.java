package dev.boxadactle.coordinatesdisplay;

import dev.boxadactle.boxlib.config.BConfigClass;
import dev.boxadactle.boxlib.config.BConfigHandler;
import dev.boxadactle.boxlib.util.ClientUtils;
import dev.boxadactle.boxlib.util.ModLogger;
import dev.boxadactle.boxlib.util.WorldUtils;
import dev.boxadactle.coordinatesdisplay.config.screen.ConfigScreen;
import dev.boxadactle.coordinatesdisplay.config.screen.HudPositionScreen;
import dev.boxadactle.coordinatesdisplay.hud.Hud;
import dev.boxadactle.coordinatesdisplay.config.ModConfig;
import dev.boxadactle.coordinatesdisplay.position.Position;

public class CoordinatesDisplay {

	public static final String MOD_NAME = "CoordinatesDisplay";

	public static final String MOD_ID = "coordinatesdisplay";

	public static final String VERSION = "4.0.0";

	public static final String VERSION_STRING = MOD_NAME + " v" + VERSION;


	// wiki pages
	public static final String WIKI = "https://boxadactle.dev/wiki/coordinates-display/";
	public static String WIKI_VISUAL = WIKI + "#visual";
	public static String WIKI_RENDER = WIKI + "#rendering";
	public static String WIKI_COLOR = WIKI + "#color";
	public static String WIKI_DEATHPOS = WIKI + "#deathpos";
	public static String WIKI_TEXTS = WIKI + "#text";


	public static final ModLogger LOGGER = new ModLogger(MOD_NAME);

	public static boolean shouldConfigGuiOpen = false;
	public static boolean shouldCoordinatesGuiOpen = false;
	public static boolean shouldHudPositionGuiOpen = false;

	public static boolean shouldHudRender = true;

	public static BConfigClass<ModConfig> CONFIG;

	public static Hud HUD;

	public static void init() {

		CONFIG = BConfigHandler.registerConfig(ModConfig.class);

		HUD = new Hud();
	}

	public static ModConfig getConfig() {
		return CONFIG.get();
	}

	public static void tick() {
		if (shouldConfigGuiOpen) {
			ClientUtils.setScreen(new ConfigScreen(null));
			shouldConfigGuiOpen = false;
		}

		if (shouldCoordinatesGuiOpen) {
			Position pos = Position.of(WorldUtils.getCamera());

			ClientUtils.setScreen(new CoordinatesScreen(pos));

			shouldCoordinatesGuiOpen = false;
		}

		if (shouldHudPositionGuiOpen) {
			ClientUtils.setScreen(new HudPositionScreen(null));
			shouldHudPositionGuiOpen = false;
		}
	}

	public static class BiomeColors {

		public static int getBiomeColor(String name, int defaultColor) {

			return switch (name) {
				case "Eroded Badlands", "Badlands", "Badlands Mountains", "Hot Desert", "Frozen Wetlands", "Badlands Mountains River", "Badlands Buttes", "Eroded Badlands Buttes", "Cracked Badlands Plateau" -> 0xb55a26;
				case "Bamboo Jungle","Bamboo Jungle Flats","Bamboo Ponds" -> 0x2be625;
				case "Snowy Beach", "Snowy Plains", "Snowy Slopes", "Snowy Taiga", "Basalt Deltas", "Snowy Meadows" -> 0xadadad;
				case "Beach" -> 0xc5c93a;
				case "Birch Forest", "Old Growth Birch Forest" ,"Birch Forest Flats", "Birch Forest Hills" -> 0xdecc7a;
				case "Cold Ocean" -> 0x738ae6;
				case "Crimson Forest", "Nether Wastes" -> 0xad201d;
				case "Dark Forest" -> 0x452309;
				case "River", "Ocean", "Deep Cold Ocean", "River Arid", "River Swamp", "River Sakura" -> 0x161d78;
				case "Swamp", "Wetlands", "Mangrove Swamp", "Marsh" -> 0x4c5714;
				case "Deep Dark" -> 0x03273d;
				case "Deep Frozen Ocean","Iceberg Ocean" -> 0x1e4054;
				case "Deep Lukewarm Ocean" -> 0x235b63;
				case "Deep Ocean" -> 0x15115c;
				case "End Barrens", "End Highlands", "End Midlands", "Small End Islands", "Desert", "Palm Forest", "Archipelago", "Rocky Archipelago", "Sandstone Archipelago","Palm Beach", "Yellowstone", "Desert Flats", "Desert Pillars", "Desert Spikes", "Sandstone Wetlands", "Arid Highlands", "Xeric Mountains", "Xeric Mountains River" -> 0xb3ac30;
				case "Dripstone Caves" -> 0x665f50;
				case "Flower Forest", "Forest", "Lush Caves", "Meadow", "Flowering Flats", "Flowering Forest", "Flowering Forest Hills", "Forest Hills", "Forest Flats" -> 0x32701c;
				case "Frozen Ocean", "Frozen Peaks", "Frozen River", "Ice Spikes" -> 0x34c4c9;
				case "Grove", "Jagged Peaks" -> 0xacb0a7;
				case "Jungle","Jungle Flats","Cerros De Mavecure","Sparse Jungle Flats","" -> 0x85c41f;
				case "Lukewarm Ocean" -> 0x3d9ba8;
				case "Mushroom Fields","Mushroom Hills", "Mushroom Coast", "Mushroom Mountains" -> 0x4c4654;
				case "Old Growth Pine Taiga", "Old Growth Spruce Forest" ,"Taiga","Taiga Hills","Taiga Flats","Redwood Forest Hills","Redwood Forest" -> 0x3b230d;
				case "Plains", "Sunflower Plains", "Plains2", "Sparse Flats", "Lush Sea Caves"-> 0x4dd115;
				case "Savanna", "Savanna Plateau", "Savanna Flats", "Steppe", "Oak Savanna", "Oak Savanna Zero", "Savanna Overhangs", "Savanna Hills", "Savanna Low Hills" -> 0x5c701c;
				case "Cherry Grove", "Sakura Flats", "Sakura Mountains", "Sakura River" -> 0xd863e0;
				case "Mountains", "Snowy Mountains","Monsoon Mountains", "Large Monsoon Mountains", "Snowy Eroded Terraced Mountains", "Snowy Mountains River", "Temperate Mountains", "Mountains River", "Temperate Mountains River", "Temperate Alpha Mountains" -> 0x6f6f6f;
				case "Active Volcano Base", "Active Volcano Base Edge", "Active Volcano Pit", "Active Volcano Pit Edge" -> 0xA55200;
				case "Caldera Volcano Base", "Caldera Volcano Base Edge", "Caldera Volcano Pit", "Caldera Volcano Pit Edge" -> 0x4E968E;
				default -> defaultColor;
			};

		}

	}

}
