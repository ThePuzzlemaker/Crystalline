package ext.tpz.crystalline.api.mode;

import ext.tpz.crystalline.api.crystal.CrystalRegistry;
import ext.tpz.crystalline.api.crystal.ICrystal;
import ext.tpz.crystalline.api.essence.powder.EssencePowderRegistry;
import ext.tpz.crystalline.api.essence.powder.IEssencePowder;
import net.minecraft.util.ResourceLocation;

import java.util.ArrayList;

public class ModeUtils {

    public static ICrystalMode from(String id) {
        return CrystalModeRegistry.getRegistry().getValue(new ResourceLocation(id));
    }

    public static String to(ICrystalMode mode) {
        return mode.getRegistryName().toString();
    }

    public static String[] dump() {
        ArrayList<String> registryNames = new ArrayList<>();
        if (CrystalRegistry.getRegistry() != null) {
            for (ICrystalMode c : CrystalModeRegistry.getRegistry()) {
                registryNames.add(to(c));
            }
        }
        String[] rNs = new String[registryNames.size()];
        registryNames.toArray(rNs);
        return rNs;
    }

}
