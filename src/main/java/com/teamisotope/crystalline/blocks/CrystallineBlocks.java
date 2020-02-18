package com.teamisotope.crystalline.blocks;

import com.teamisotope.crystalline.Crystalline;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.ObjectHolder;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class CrystallineBlocks {

    @ObjectHolder("crystalline:exampleblock")
    public static ExampleBlock exampleblock;

    @SubscribeEvent
    public static void onBlocksRegistry(final RegistryEvent.Register<Block> blockRegistryEvent) {
        IForgeRegistry<Block> registry = blockRegistryEvent.getRegistry();
        registry.register(new ExampleBlock());
    }

    @SubscribeEvent
    public static void onItemsRegistry(final RegistryEvent.Register<Item> itemRegistryEvent) {
        IForgeRegistry<Item> registry = itemRegistryEvent.getRegistry();
        registry.register(newBlockItem(exampleblock));
    }

    private static Item newBlockItem(Block block) {
        return new BlockItem(block, new Item.Properties()).setRegistryName(block.getRegistryName());
    }

}
