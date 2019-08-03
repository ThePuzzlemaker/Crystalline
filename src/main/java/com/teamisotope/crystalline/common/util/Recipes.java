package com.teamisotope.crystalline.common.util;

import com.teamisotope.crystalline.common.block.CBlocks;
import com.teamisotope.crystalline.common.item.CItems;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.registry.GameRegistry;

@Mod.EventBusSubscriber
public class Recipes {

    public static void init() {
        GameRegistry.addSmelting(CBlocks.arcanumMineralis, new ItemStack(CItems.arcanumMineralisCrystal), 1.5f);
    }

}
