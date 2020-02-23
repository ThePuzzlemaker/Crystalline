package com.teamisotope.crystalline.common.setup;

import com.teamisotope.crystalline.client.ClientSetup;
import com.teamisotope.crystalline.common.Crystalline;
import net.minecraftforge.client.model.obj.OBJLoader;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

public class ClientProxy implements IProxy {

    static {

    }

    @Override
    public void init() {
        ClientSetup setup = new ClientSetup();
        FMLJavaModLoadingContext.get().getModEventBus().addListener(setup::clientSetup);
    }

}
