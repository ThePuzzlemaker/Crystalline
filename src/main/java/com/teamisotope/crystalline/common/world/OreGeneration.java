package com.teamisotope.crystalline.common.world;

import com.teamisotope.crystalline.common.setup.Registration;
import com.teamisotope.crystalline.common.util.Config;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.placement.CountRangeConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.registries.ForgeRegistries;

public class OreGeneration {

    public static void setupOreGeneration() {
        for (Biome biome : ForgeRegistries.BIOMES) {
            // Arcanum Minerale
            biome.addFeature(
                    GenerationStage.Decoration.UNDERGROUND_ORES,
                    Feature.ORE.withConfiguration(
                            new OreFeatureConfig(
                                    OreFeatureConfig.FillerBlockType.NATURAL_STONE,
                                    Registration.arcanumminerale.get().getDefaultState(),
                                    Config.Common.OreGen.AM_VEINSIZE.get())
                    ).withPlacement(
                            Placement.COUNT_RANGE.configure(
                                new CountRangeConfig(
                                        Config.Common.OreGen.AM_COUNT.get(),
                                        Config.Common.OreGen.AM_MINY.get(),
                                        0,
                                       Config.Common.OreGen.AM_MAXY.get()
                                )
                            )
                    )
            );
        }
    }

}
