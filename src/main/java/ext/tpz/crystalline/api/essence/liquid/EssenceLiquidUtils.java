package ext.tpz.crystalline.api.essence.liquid;

import ext.tpz.crystalline.api.essence.powder.EssencePowderRegistry;
import ext.tpz.crystalline.api.essence.powder.IEssencePowder;
import net.minecraft.util.ResourceLocation;

public class EssenceLiquidUtils {

    public static IEssenceLiquid from(String id) {
        return EssenceLiquidRegistry.getRegistry().getValue(new ResourceLocation(id));
    }

    public static String to(IEssenceLiquid liquid) {
        return liquid.getRegistryName().toString();
    }

}
