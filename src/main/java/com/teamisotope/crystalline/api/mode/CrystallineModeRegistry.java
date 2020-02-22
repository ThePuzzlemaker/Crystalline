package com.teamisotope.crystalline.api.mode;

import com.teamisotope.crystalline.common.Crystalline;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.RegistryBuilder;

import javax.annotation.Nonnull;

@Mod.EventBusSubscriber(modid = Crystalline.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class CrystallineModeRegistry {

    private static IForgeRegistry<IMode> REGISTRY = null;

    @SubscribeEvent(priority = EventPriority.NORMAL)
    public static void registerRegistry(@Nonnull RegistryEvent.NewRegistry event) {
        REGISTRY = new RegistryBuilder<IMode>()
                .setName(new ResourceLocation(Crystalline.MODID, "registry.mode"))
                .setType(IMode.class)
                .setIDRange(0, Integer.MAX_VALUE - 1)
                .create();
    }

    public static IForgeRegistry<IMode> getRegistry() {
        if (REGISTRY != null)
            return REGISTRY;
        return null;
    }

}
