package com.teamisotope.crystalline.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraftforge.common.ToolType;

public class ArcanumMinerale extends Block {

    public ArcanumMinerale() {
        super(Properties.create(Material.ROCK)
                .hardnessAndResistance(3.0f, 15.0f)
                .harvestTool(ToolType.PICKAXE)
                .harvestLevel(3)
        );
    }

}
