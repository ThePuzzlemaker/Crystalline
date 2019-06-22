package ext.tpz.crystalline.common.block;

import ext.tpz.crystalline.api.CStatic;
import ext.tpz.crystalline.common.block.tuning.BlockTuningMachine;
import ext.tpz.crystalline.common.block.tuning.TETuningMachine;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
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

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> e) {
        e.getRegistry().register(new BlockTuningMachine());
        GameRegistry.registerTileEntity(TETuningMachine.class, new ResourceLocation(CStatic.MODID, "_tuningmachine"));
    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> e) {
        e.getRegistry().register(new ItemBlock(tuningMachine).setRegistryName(tuningMachine.getRegistryName()));
    }

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public static void registerModels(ModelRegistryEvent e) {
        tuningMachine.initModel();
    }



}
