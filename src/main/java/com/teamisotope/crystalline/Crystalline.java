package com.teamisotope.crystalline;

import com.teamisotope.crystalline.setup.ClientProxy;
import com.teamisotope.crystalline.setup.CrystallineSetup;
import com.teamisotope.crystalline.setup.IProxy;
import com.teamisotope.crystalline.setup.ServerProxy;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(Crystalline.MODID)
public class Crystalline {

    public static final String MODID = "crystalline";

    // Dead for now, may eventually be used, if I don't end up using it in release, it'll be removed.
    public static IProxy proxy = DistExecutor.runForDist(() -> () -> new ClientProxy(), () -> () -> new ServerProxy());

    public static CrystallineSetup setup = new CrystallineSetup();

    public static final Logger LOGGER = LogManager.getLogger("Crystalline");

    public Crystalline() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
    }

    private void setup(final FMLCommonSetupEvent event) {
        setup.init();
        proxy.init();
    }

}
