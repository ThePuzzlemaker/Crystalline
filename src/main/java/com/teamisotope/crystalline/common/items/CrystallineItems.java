package com.teamisotope.crystalline.common.items;

import com.teamisotope.crystalline.common.Crystalline;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.ObjectHolder;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class CrystallineItems {

    @ObjectHolder("crystalline:arcanummineralecrystal")
    public static Item arcanummineralecrystal;

    @SubscribeEvent
    public static void onItemsRegistry(final RegistryEvent.Register<Item> itemRegistryEvent) {
        IForgeRegistry<Item> registry = itemRegistryEvent.getRegistry();
        Item.Properties mainGroup = new Item.Properties().group(Crystalline.setup.crystallineMainGroup);
        registry.register(new Item(mainGroup).setRegistryName("arcanummineralecrystal"));
    }

}
