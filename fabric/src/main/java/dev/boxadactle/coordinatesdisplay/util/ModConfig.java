package dev.boxadactle.coordinatesdisplay.util;

import dev.boxadactle.boxlib.config.BConfig;
import dev.boxadactle.boxlib.config.BConfigFile;

@BConfigFile("coordinates-display")
public class ModConfig implements BConfig {

    public boolean visible = true;
    public boolean decimalRounding = false;

    public RenderMode renderMode = RenderMode.DEFAULT;
    public int hudX = 0;
    public int hudY = 0;
    public float hudScale = 1.0f;
    public boolean hudTextShadow = true;

    public boolean renderBackground = true;
    public boolean renderChunkData = true;
    public boolean renderDirection = true;
    public boolean renderBiome = true;
    public boolean biomeColors = true;
    public boolean renderDirectionInt = true;
    public boolean renderMCVersion = true;

    public int definitionColor = 0x55FF55;
    public int dataColor = 0xFFFFFF;
    public int deathPosColor = 0x55FFFF;
    public int backgroundColor = 0x405c5c5c;

    public boolean displayPosOnDeathScreen = true;
    public boolean showDeathPosInChat = true;

    public int padding = 5;
    public int textPadding = 15;

    public String posChatMessage = "{x} {y} {z}";
    public String copyPosMessage = "{x}, {y}, {z}";
    public boolean shouldRoundWhenCopying = true;
    public TeleportMode teleportMode = TeleportMode.EXECUTE;

    public enum RenderMode {
        DEFAULT,
        MINIMUM,
        MAXIMUM,
        LINE
    }

    public enum TeleportMode {
        EXECUTE,
        TP,
        BARITONE
    }

}
