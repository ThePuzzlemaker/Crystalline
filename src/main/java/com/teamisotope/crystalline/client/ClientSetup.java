package com.teamisotope.crystalline.client;


import com.teamisotope.crystalline.client.crystalmodel.CrystalModelLoader;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoaderRegistry;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

public class ClientSetup {

    public void clientSetup(FMLClientSetupEvent event) {
        ModelLoaderRegistry.registerLoader(new ResourceLocation("crystalline:crystal_loader"), CrystalModelLoader.INSTANCE);
    }

}
