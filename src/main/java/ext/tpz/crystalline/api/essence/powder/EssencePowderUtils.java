package ext.tpz.crystalline.api.essence.powder;

import net.minecraft.util.ResourceLocation;

public class EssencePowderUtils {

    public static IEssencePowder from(String id) {
        return EssencePowderRegistry.getRegistry().getValue(new ResourceLocation(id));
    }

    public static String to(IEssencePowder powder) {
        return powder.getRegistryName().getResourceDomain() + ":" + powder.getRegistryName().getResourcePath();
    }

}
