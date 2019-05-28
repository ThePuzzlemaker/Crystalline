package ext.tpz.crystalline.api.essence.liquid;

import ext.tpz.crystalline.api.crystal.CrystalRegistry;
import ext.tpz.crystalline.api.crystal.ICrystal;
import ext.tpz.crystalline.api.essence.powder.EssencePowderRegistry;
import ext.tpz.crystalline.api.essence.powder.IEssencePowder;
import net.minecraft.util.ResourceLocation;

import java.util.ArrayList;

public class EssenceLiquidUtils {

    public static IEssenceLiquid from(String id) {
        return EssenceLiquidRegistry.getRegistry().getValue(new ResourceLocation(id));
    }

    public static String to(IEssenceLiquid liquid) {
        return liquid.getRegistryName().toString();
    }

    public static String[] dump() {
        ArrayList<String> registryNames = new ArrayList<>();
        if (CrystalRegistry.getRegistry() != null) {
            for (IEssenceLiquid c : EssenceLiquidRegistry.getRegistry()) {
                registryNames.add(to(c));
            }
        }
        String[] rNs = new String[registryNames.size()];
        registryNames.toArray(rNs);
        return rNs;
    }

}
