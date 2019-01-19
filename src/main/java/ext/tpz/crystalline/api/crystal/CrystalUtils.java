package ext.tpz.crystalline.api.crystal;

import net.minecraft.util.ResourceLocation;

import java.util.ArrayList;

public class CrystalUtils {

    public static ICrystal from(String id) {
        return CrystalRegistry.getRegistry().getValue(new ResourceLocation(id));
    }

    public static String to(ICrystal crystal) {
        return crystal.getRegistryName().toString();
    }

    public static String[] dump() {
        ArrayList<String> registryNames = new ArrayList<>();
        if (CrystalRegistry.getRegistry() != null) {
            for (ICrystal c : CrystalRegistry.getRegistry()) {
                registryNames.add(to(c));
            }
        }
        String[] rNs = new String[registryNames.size()];
        registryNames.toArray(rNs);
        return rNs;
    }

}
