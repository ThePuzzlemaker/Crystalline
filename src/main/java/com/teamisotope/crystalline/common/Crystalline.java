package com.teamisotope.crystalline.common;

import com.teamisotope.crystalline.common.setup.*;
import com.teamisotope.crystalline.common.util.Config;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLPaths;
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

        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.Common.CONFIG_SPEC);
        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, Config.Client.CONFIG_SPEC);
        ModLoadingContext.get().registerConfig(ModConfig.Type.SERVER, Config.Server.CONFIG_SPEC);

        Config.loadConfig(Config.Common.CONFIG_SPEC, FMLPaths.CONFIGDIR.get().resolve("crystalline-common.toml"));
        Config.loadConfig(Config.Client.CONFIG_SPEC, FMLPaths.CONFIGDIR.get().resolve("crystalline-client.toml"));
        Config.loadConfig(Config.Server.CONFIG_SPEC, FMLPaths.CONFIGDIR.get().resolve("crystalline-server.toml"));

        Registration.init();

    }

    private void setup(final FMLCommonSetupEvent event) {
        setup.init();
        proxy.init();
    }

}
