package dev.boxadactle.coordinatesdisplay.hud.renderer;

import dev.boxadactle.boxlib.math.mathutils.NumberFormatter;
import dev.boxadactle.coordinatesdisplay.CoordinatesDisplay;
import dev.boxadactle.boxlib.math.geometry.Rect;
import dev.boxadactle.boxlib.math.geometry.Vec2;
import dev.boxadactle.boxlib.math.geometry.Vec3;
import dev.boxadactle.boxlib.util.ClientUtils;
import dev.boxadactle.boxlib.util.GuiUtils;
import dev.boxadactle.boxlib.util.RenderUtils;
import dev.boxadactle.coordinatesdisplay.ModUtil;
import dev.boxadactle.coordinatesdisplay.hud.HudRenderer;
import dev.boxadactle.coordinatesdisplay.position.Position;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;

public class DefaultRenderer extends HudRenderer {

    public DefaultRenderer() {
        super("hud.coordinatesdisplay.");
    }

    private int calculateWidth(int p, int tp, Component xtext, Component ytext, Component ztext, Component chunkx, Component chunkz, Component direction, Component cCount, Component biome, Component version) {
        int a = GuiUtils.getLongestLength(xtext, ytext, ztext);
        int b = GuiUtils.getLongestLength(chunkx, chunkz);
        int c = a + (CoordinatesDisplay.CONFIG.get().renderChunkData ? b + tp : 0);

        int d = 0;
        if (CoordinatesDisplay.CONFIG.get().renderDirection) {
            if (GuiUtils.getLongestLength(direction) > d) d = GuiUtils.getLongestLength(direction);
        }
        if (CoordinatesDisplay.CONFIG.get().renderBiome) {
            if (GuiUtils.getLongestLength(biome) > d) d = GuiUtils.getLongestLength(biome);
        }
        if (CoordinatesDisplay.CONFIG.get().renderCCount) {
            if (GuiUtils.getLongestLength(cCount) > d) d = GuiUtils.getLongestLength(cCount);
        }
        if (CoordinatesDisplay.CONFIG.get().renderMCVersion) {
            if (GuiUtils.getLongestLength(version) > d) d = GuiUtils.getLongestLength(version);
        }

        return p + Math.max(c, d) + p;
    }

    private int calculateHeight(int th, int p, int tp) {
        int a = th * 3;

        int b = 0;
        if (CoordinatesDisplay.CONFIG.get().renderDirection) {
            b += th;
        }
        if (CoordinatesDisplay.CONFIG.get().renderCCount) {
            b += th;
        }
        if (CoordinatesDisplay.CONFIG.get().renderBiome) {
            b += th;
        }
        if (CoordinatesDisplay.CONFIG.get().renderMCVersion) {
            b += th;
        }

        boolean c = (CoordinatesDisplay.CONFIG.get().renderDirection || CoordinatesDisplay.CONFIG.get().renderCCount || CoordinatesDisplay.CONFIG.get().renderBiome || CoordinatesDisplay.CONFIG.get().renderMCVersion);

        return p + a + (c ? tp : 0) + b + p;
    }

    @Override
    protected Rect<Integer> renderOverlay(GuiGraphics guiGraphics, int x, int y, Position pos) {
        NumberFormatter<Double> formatter = new NumberFormatter<>(CoordinatesDisplay.CONFIG.get().decimalPlaces);
        Vec3<Double> player = pos.position.getPlayerPos();
        Vec2<Integer> chunkPos = pos.position.getChunkPos();

        Component xtext = GuiUtils.colorize(translation(
                "x",
                GuiUtils.colorize(
                        Component.literal(formatter.formatDecimal(player.getX())),
                        config().dataColor
                )
        ), config().definitionColor);

        Component ytext = GuiUtils.colorize(translation(
                "y",
                GuiUtils.colorize(
                        Component.literal(formatter.formatDecimal(player.getY())),
                        config().dataColor
                )
        ), config().definitionColor);

        Component ztext = GuiUtils.colorize(translation(
                "z",
                GuiUtils.colorize(
                        Component.literal(formatter.formatDecimal(player.getZ())),
                        config().dataColor
                )
        ), config().definitionColor);



        Component chunkx = GuiUtils.colorize(translation(
                "chunk.x",
                GuiUtils.colorize(
                        Component.literal(Integer.toString(chunkPos.getX())),
                        config().dataColor
                )
        ), config().definitionColor);

        Component chunkz = GuiUtils.colorize(translation(
                "chunk.z",
                GuiUtils.colorize(
                        Component.literal(Integer.toString(chunkPos.getY())),
                        config().dataColor
                )
        ), config().definitionColor);



        Component direction = translation(
                "direction",
                GuiUtils.colorize(
                        translation(ModUtil.getDirectionFromYaw(pos.headRot.wrapYaw())),
                        config().definitionColor
                ),
                config().renderDirectionInt ? GuiUtils.colorize(
                        GuiUtils.parentheses(Component.literal(formatter.formatDecimal(pos.headRot.wrapYaw()))),
                        config().dataColor
                ) : Component.literal("")
        );

        Component cCounter = GuiUtils.colorize(translation(
                "c",
                GuiUtils.colorize(
                        Component.literal(ModUtil.getCCounterString()),
                        config().dataColor)
        ), config().definitionColor);

        String biomestring = ModUtil.getBiomestring(pos);
        Component biome = GuiUtils.colorize(translation(
                "biome",
                GuiUtils.colorize(
                        Component.literal(biomestring),
                        CoordinatesDisplay.CONFIG.get().biomeColors ?
                                CoordinatesDisplay.BiomeColors.getBiomeColor(biomestring, CoordinatesDisplay.CONFIG.get().dataColor) :
                                CoordinatesDisplay.CONFIG.get().dataColor
                )
        ), config().definitionColor);

        Component mcversion = GuiUtils.colorize(translation(
                "version",
                GuiUtils.colorize(
                        Component.literal(ClientUtils.getGameVersion()),
                        config().dataColor
                )
        ), config().definitionColor);

        int p = config().padding;
        int tp = config().textPadding;
        int th = GuiUtils.getTextRenderer().lineHeight;

        int w = calculateWidth(p, tp, xtext, ytext, ztext, chunkx, chunkz, direction, cCounter, biome, mcversion);
        int h = calculateHeight(th, p, tp);

        if (config().renderBackground) {
            RenderUtils.drawSquare(guiGraphics, x, y, w, h, config().backgroundColor);
        }

        // required
        drawInfo(guiGraphics, xtext, x + p, y + p, CoordinatesDisplay.CONFIG.get().definitionColor);
        drawInfo(guiGraphics, ytext, x + p, y + p + th, CoordinatesDisplay.CONFIG.get().definitionColor);
        drawInfo(guiGraphics, ztext, x + p, y + p + (th * 2), CoordinatesDisplay.CONFIG.get().definitionColor);

        if (config().renderChunkData) {
            drawInfo(guiGraphics, chunkx, x + p + GuiUtils.getLongestLength(xtext, ytext, ztext) + tp, y + p, CoordinatesDisplay.CONFIG.get().definitionColor);
            drawInfo(guiGraphics, chunkz, x + p + GuiUtils.getLongestLength(xtext, ytext, ztext) + tp, y + p + (th), CoordinatesDisplay.CONFIG.get().definitionColor);
        }

        int offset = 0;

        if (config().renderDirection) {
            drawInfo(guiGraphics, direction, x + p, y + p + (th * 3) + tp + (offset += th), CoordinatesDisplay.CONFIG.get().definitionColor);
        }

        if (config().renderCCount) {
            drawInfo(guiGraphics, cCounter, x + p, y + p + (th * 3) + tp + (offset += th), CoordinatesDisplay.CONFIG.get().definitionColor);
        }

        if (config().renderBiome) {
            drawInfo(guiGraphics, biome, x + p, y + p + (th * 3) + tp + (offset += th), CoordinatesDisplay.CONFIG.get().definitionColor);
        }

        if (config().renderMCVersion) {
            drawInfo(guiGraphics, mcversion, x + p, y + p + (th * 3) + tp + (offset += th), CoordinatesDisplay.CONFIG.get().dataColor);
        }


        return new Rect<>(x, y, w, h);
    }

}
