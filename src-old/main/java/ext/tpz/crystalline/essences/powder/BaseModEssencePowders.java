package ext.tpz.crystalline.essences.powder;

import ext.tpz.crystalline.api.essence.liquid.IEssenceLiquid;
import ext.tpz.crystalline.api.essence.powder.IEssencePowder;
import ext.tpz.crystalline.essences.liquid.EssenceLiquidNull;
import ext.tpz.crystalline.common.util.Reference;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class BaseModEssencePowders {

    @GameRegistry.ObjectHolder(Reference.MODID + ":essence.powder.rift")
    public static IEssencePowder essence_powder_rift = new EssencePowderRift();

    @GameRegistry.ObjectHolder(Reference.MODID + ":essence.powder.universe")
    public static IEssencePowder essence_powder_universe = new EssencePowderUniverse();

    @GameRegistry.ObjectHolder(Reference.MODID + ":essence.powder.null")
    public static IEssencePowder essence_powder_null = new EssencePowderNull();

    public static void register(RegistryEvent.Register<IEssencePowder> e) {
        e.getRegistry().register(essence_powder_rift);
        e.getRegistry().register(essence_powder_universe);
        e.getRegistry().register(essence_powder_null);
    }

}
