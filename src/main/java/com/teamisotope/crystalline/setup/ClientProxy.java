package com.teamisotope.crystalline.setup;

import com.teamisotope.crystalline.Crystalline;
import net.minecraftforge.client.model.obj.OBJLoader;

public class ClientProxy implements IProxy {

    static {
        OBJLoader.INSTANCE.addDomain(Crystalline.MODID);
    }

    @Override
    public void init() {

    }

}
