package com.teamisotope.crystalline.common;

import com.teamisotope.crystalline.api.CStatic;
import com.teamisotope.crystalline.common.capabilities.CCapabilities;
import com.teamisotope.crystalline.common.gui.GuiProxy;
import com.teamisotope.crystalline.common.network.PacketHandler;
import com.teamisotope.crystalline.common.util.OreGen;
import com.teamisotope.crystalline.common.util.Recipes;
import net.minecraftforge.client.model.obj.OBJLoader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;

@Mod.EventBusSubscriber
public class CommonProxy {

    public void preInit(FMLPreInitializationEvent e) {
        CCapabilities.register();
        OBJLoader.INSTANCE.addDomain(CStatic.MODID);
    }

    public void init(FMLInitializationEvent e) {
        NetworkRegistry.INSTANCE.registerGuiHandler(Crystalline.instance, new GuiProxy());
        PacketHandler.registerMessages(CStatic.MODID);
        Recipes.init();
        GameRegistry.registerWorldGenerator(new OreGen(), 0);
    }

    public void postInit(FMLPostInitializationEvent e) {

    }

}
