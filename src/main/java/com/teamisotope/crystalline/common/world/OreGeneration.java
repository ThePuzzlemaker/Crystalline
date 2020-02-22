package com.teamisotope.crystalline.common.world;

import com.teamisotope.crystalline.common.blocks.CrystallineBlocks;
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
                    Biome.createDecoratedFeature(
                            Feature.ORE,
                            new OreFeatureConfig(
                                    OreFeatureConfig.FillerBlockType.NATURAL_STONE,
                                    CrystallineBlocks.arcanumminerale.getDefaultState(),
                                    Config.Common.OreGen.AM_VEINSIZE.get()),
                            Placement.COUNT_RANGE,
                            new CountRangeConfig(
                                    Config.Common.OreGen.AM_COUNT.get(),
                                    Config.Common.OreGen.AM_MINY.get(),
                                    0,
                                    Config.Common.OreGen.AM_MAXY.get()
                            )
                    )
            );
        }
    }

}
