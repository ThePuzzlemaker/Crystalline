package com.teamisotope.crystalline.common.setup;

import com.teamisotope.crystalline.common.Crystalline;
import net.minecraftforge.client.model.obj.OBJLoader;

public class ClientProxy implements IProxy {

    static {
        OBJLoader.INSTANCE.addDomain(Crystalline.MODID);
    }

    @Override
    public void init() {

    }

}
