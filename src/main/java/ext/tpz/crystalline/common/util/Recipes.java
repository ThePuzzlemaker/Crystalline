package ext.tpz.crystalline.common.util;

import ext.tpz.crystalline.common.block.CBlocks;
import ext.tpz.crystalline.common.item.CItems;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.registry.GameRegistry;

@Mod.EventBusSubscriber
public class Recipes {

    public static void init() {
        GameRegistry.addSmelting(CBlocks.arcanumMineralis, new ItemStack(CItems.arcanumMineralisCrystal), 1.5f);
    }

}
