package ext.tpz.crystalline.api.mode;

import ext.tpz.crystalline.api.crystal.CrystalRegistry;
import ext.tpz.crystalline.api.crystal.ICrystal;
import net.minecraft.util.ResourceLocation;

public class ModeUtils {

    public static ICrystalMode from(String id) {
        return CrystalModeRegistry.getRegistry().getValue(new ResourceLocation(id));
    }

    public static String to(ICrystalMode mode) {
        return mode.getRegistryName().getResourceDomain() + ":" + mode.getRegistryName().getResourcePath();
    }

}
