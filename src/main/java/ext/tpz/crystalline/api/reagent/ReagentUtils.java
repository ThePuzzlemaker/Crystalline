package ext.tpz.crystalline.api.reagent;

import net.minecraft.util.ResourceLocation;

public class ReagentUtils {

    public static IReagent from(String id) {
        return ReagentRegistry.getRegistry().getValue(new ResourceLocation(id));
    }

    public static String to(IReagent reagent) {
        return reagent.getRegistryName().getResourceDomain() + ":" + reagent.getRegistryName().getResourcePath();
    }

}
