package com.teamisotope.crystalline.common.base.crystal;

import com.teamisotope.crystalline.api.crystal.ICrystal;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

@Mod.EventBusSubscriber
public class CrystalInit {

    @GameRegistry.ObjectHolder("crystalline:crystal.base")
    public static CBase baseCrystal;


    @SubscribeEvent
    public static void register(RegistryEvent.Register<ICrystal> e) {
        e.getRegistry().register(new CBase());
    }

}
