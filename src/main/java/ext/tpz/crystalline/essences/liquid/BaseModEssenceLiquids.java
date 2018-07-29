package ext.tpz.crystalline.essences.liquid;

import ext.tpz.crystalline.api.essence.liquid.IEssenceLiquid;
import ext.tpz.crystalline.util.Reference;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class BaseModEssenceLiquids {

    @GameRegistry.ObjectHolder(Reference.MODID + ":essence.liquid.null")
    public static IEssenceLiquid essence_liquid_null = new EssenceLiquidNull();

    public static void register(RegistryEvent.Register<IEssenceLiquid> e) {
        e.getRegistry().register(essence_liquid_null);
    }

}
