package ext.tpz.crystalline.api.essence.powder;

import ext.tpz.crystalline.api.crystal.CrystalRegistry;
import ext.tpz.crystalline.api.essence.liquid.EssenceLiquidRegistry;
import ext.tpz.crystalline.api.essence.liquid.IEssenceLiquid;
import net.minecraft.util.ResourceLocation;

import java.util.ArrayList;

public class EssencePowderUtils {

    public static IEssencePowder from(String id) {
        return EssencePowderRegistry.getRegistry().getValue(new ResourceLocation(id));
    }

    public static String to(IEssencePowder powder) {
        return powder.getRegistryName().toString();
    }

    public static String[] dump() {
        ArrayList<String> registryNames = new ArrayList<>();
        if (CrystalRegistry.getRegistry() != null) {
            for (IEssencePowder c : EssencePowderRegistry.getRegistry()) {
                registryNames.add(to(c));
            }
        }
        String[] rNs = new String[registryNames.size()];
        registryNames.toArray(rNs);
        return rNs;
    }

}
