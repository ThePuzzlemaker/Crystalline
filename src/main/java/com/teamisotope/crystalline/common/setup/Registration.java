package com.teamisotope.crystalline.common.setup;

import com.teamisotope.crystalline.common.Crystalline;
import com.teamisotope.crystalline.common.blocks.ArcanumMinerale;
import com.teamisotope.crystalline.common.items.dynamic.CrystalItem;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class Registration {

    private static final DeferredRegister<Block> BLOCKS = new DeferredRegister<>(ForgeRegistries.BLOCKS, Crystalline.MODID);
    private static final DeferredRegister<Item> ITEMS = new DeferredRegister<>(ForgeRegistries.ITEMS, Crystalline.MODID);

    public static void init() {
        BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
        ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }

    public static final RegistryObject<ArcanumMinerale> arcanumminerale = BLOCKS.register("arcanumminerale", ArcanumMinerale::new);
    public static final RegistryObject<Item> arcanumminerale_item = ITEMS.register("arcanumminerale", () -> new BlockItem(arcanumminerale.get(), new Item.Properties().group(Crystalline.setup.crystallineMainGroup)));

    public static final RegistryObject<Item> arcanummineralecrystal = ITEMS.register("arcanummineralecrystal", () -> new Item(new Item.Properties().group(Crystalline.setup.crystallineMainGroup)));
    public static final RegistryObject<CrystalItem> crystal = ITEMS.register("crystal", CrystalItem::new);

}
