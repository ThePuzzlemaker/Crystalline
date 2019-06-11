package ext.tpz.crystalline.common;

import ext.tpz.crystalline.api.CStatic;
import ext.tpz.crystalline.common.block.tuning.BlockTuningMachine;
import ext.tpz.crystalline.common.block.tuning.TETuningMachine;
import ext.tpz.crystalline.common.capabilities.CCapabilities;
import ext.tpz.crystalline.common.gui.GuiProxy;
import ext.tpz.crystalline.common.network.PacketHandler;
import net.minecraft.block.Block;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;

@Mod.EventBusSubscriber
public class CommonProxy {

    public void preInit(FMLPreInitializationEvent e) {
        CCapabilities.register();
    }

    public void init(FMLInitializationEvent e) {
        NetworkRegistry.INSTANCE.registerGuiHandler(Crystalline.instance, new GuiProxy());
        PacketHandler.registerMessages(CStatic.MODID);
    }

    public void postInit(FMLPostInitializationEvent e) {

    }

}
