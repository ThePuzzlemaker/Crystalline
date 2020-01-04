package com.teamisotope.crystalline.common.block;

import com.teamisotope.crystalline.api.CStatic;
import com.teamisotope.crystalline.common.block.am.BlockArcanumMinerale;
import com.teamisotope.crystalline.common.block.distillation.BlockDistillationApparatus;
import com.teamisotope.crystalline.common.block.tuning.BlockTuningMachine;
import com.teamisotope.crystalline.common.block.tuning.TETuningMachine;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@Mod.EventBusSubscriber
public class CBlocks {

    @GameRegistry.ObjectHolder(CStatic.MODID + ":tuningmachine")
    public static BlockTuningMachine tuningMachine;

    @GameRegistry.ObjectHolder(CStatic.MODID + ":arcanumminerale")
    public static BlockArcanumMinerale arcanumMinerale;

    @GameRegistry.ObjectHolder(CStatic.MODID + ":distillationapparatus")
    public static BlockDistillationApparatus distillationApparatus;

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> e) {
        e.getRegistry().register(new BlockTuningMachine());
        e.getRegistry().register(new BlockArcanumMinerale());
        e.getRegistry().register(new BlockDistillationApparatus());
        GameRegistry.registerTileEntity(TETuningMachine.class, new ResourceLocation(CStatic.MODID, "_tuningmachine"));
    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> e) {
        e.getRegistry().register(new ItemBlock(tuningMachine).setRegistryName(tuningMachine.getRegistryName()));
        e.getRegistry().register(new ItemBlock(arcanumMinerale).setRegistryName(arcanumMinerale.getRegistryName()));
        e.getRegistry().register(new ItemBlock(distillationApparatus).setRegistryName(distillationApparatus.getRegistryName()));
    }

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public static void registerModels(ModelRegistryEvent e) {
        tuningMachine.initModel();
        arcanumMinerale.initModel();
        distillationApparatus.initModel();
    }



}
