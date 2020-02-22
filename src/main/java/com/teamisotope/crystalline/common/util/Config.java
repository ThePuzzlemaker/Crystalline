package com.teamisotope.crystalline.common.util;

import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import com.electronwill.nightconfig.core.io.WritingMode;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;

import java.nio.file.Path;

@Mod.EventBusSubscriber
public class Config {

    public static void loadConfig(ForgeConfigSpec spec, Path path) {

        final CommentedFileConfig configData = CommentedFileConfig.builder(path)
                .sync()
                .autosave()
                .writingMode(WritingMode.REPLACE)
                .build();

        configData.load();
        spec.setConfig(configData);
    }

    @SubscribeEvent
    public static void onLoad(final ModConfig.Loading configEvent) {

    }

    @SubscribeEvent
    public static void onReload(final ModConfig.ConfigReloading configEvent) {
    }

    public static class Common {
        public static ForgeConfigSpec CONFIG_SPEC;
        private static final ForgeConfigSpec.Builder CONFIG_BUILDER = new ForgeConfigSpec.Builder();
        public static String CATEGORY_GENERAL = "general";
        public static String CATEGORY_OREGEN = "oregen";
        static {
            CONFIG_BUILDER.comment("General settings").push(CATEGORY_GENERAL);
            CONFIG_BUILDER.pop();

            CONFIG_BUILDER.comment("Ore generation settings").push(CATEGORY_OREGEN);
            OreGen.setupConfig(CONFIG_BUILDER);
            CONFIG_BUILDER.pop();

            CONFIG_SPEC = CONFIG_BUILDER.build();
        }

        public static class OreGen {
            public static String CATEGORY_ARCANUMMINNERALE = "arcanumminerale";

            public static ForgeConfigSpec.IntValue AM_VEINSIZE;
            public static ForgeConfigSpec.IntValue AM_MINY;
            public static ForgeConfigSpec.IntValue AM_MAXY;
            public static ForgeConfigSpec.IntValue AM_COUNT;

            private static void setupConfig(ForgeConfigSpec.Builder builder) {
                builder.comment("Arcanum Minerale ore generation settings").push(CATEGORY_ARCANUMMINNERALE);
                AM_VEINSIZE = builder.comment("Vein size for Arcanum Minerale ore generation")
                        .defineInRange("amVeinSize", 10, 1, 20);
                AM_MINY = builder.comment("Minimum Y-value for Arcanum Minerale ore generation")
                        .defineInRange("amMinY", 0, 0, 255);
                AM_MAXY = builder.comment("Maximum Y-value for Arcanum Minerale ore generation")
                        .defineInRange("amMaxY", 20, 0, 255);
                AM_COUNT = builder.comment("Number of times to generate per chunk for Arcanum Minerale ore generation")
                        .defineInRange("amCount", 7, 1, 20);
                builder.pop();
            }
        }
    }

    public static class Client {
        public static ForgeConfigSpec CONFIG_SPEC;
        private static final ForgeConfigSpec.Builder CONFIG_BUILDER = new ForgeConfigSpec.Builder();

        static {
            CONFIG_SPEC = CONFIG_BUILDER.build();
        }
    }

    public static class Server {
        public static ForgeConfigSpec CONFIG_SPEC;
        private static final ForgeConfigSpec.Builder CONFIG_BUILDER = new ForgeConfigSpec.Builder();

        static {
            CONFIG_SPEC = CONFIG_BUILDER.build();
        }
    }

}
