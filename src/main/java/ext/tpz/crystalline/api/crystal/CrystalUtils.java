package ext.tpz.crystalline.api.crystal;

import net.minecraft.util.ResourceLocation;

public class CrystalUtils {

    public static ICrystal from(String id) {
        return CrystalRegistry.getRegistry().getValue(new ResourceLocation(id));
    }

    public static String to(ICrystal crystal) {
        return crystal.getRegistryName().toString();
    }

}
