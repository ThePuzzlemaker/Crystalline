package com.teamisotope.crystalline.api.crystal;


import com.teamisotope.crystalline.api.CStatic;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.RegistryBuilder;

@Mod.EventBusSubscriber
public class CrystalRegistry {

    private static IForgeRegistry<ICrystal> REGISTRY = null;

    @SubscribeEvent
    public static void onRegistryEvent(RegistryEvent.NewRegistry e) {
        REGISTRY = new RegistryBuilder<ICrystal>()
                .setName(new ResourceLocation(CStatic.MODID, "registry.crystal"))
                .setType(ICrystal.class)
                .setIDRange(0, Integer.MAX_VALUE - 1)
                .create();
    }

    public static IForgeRegistry<ICrystal> getRegistry() {
        if (REGISTRY != null)
            return REGISTRY;
        return null;
    }

    public static String serialize(ICrystal crystal) {
        return crystal.serialize();
    }

    public static ICrystal deserialize(String serialized) {
        if (getRegistry() != null) {
            if (getRegistry().containsKey(new ResourceLocation(serialized))) {
                return getRegistry().getValue(new ResourceLocation(serialized));
            }
        }
        return null;
    }

}
