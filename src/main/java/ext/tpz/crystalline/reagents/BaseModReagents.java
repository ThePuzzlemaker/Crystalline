package ext.tpz.crystalline.reagents;

import ext.tpz.crystalline.api.reagent.IReagent;
import ext.tpz.crystalline.util.Reference;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class BaseModReagents {

    @GameRegistry.ObjectHolder(Reference.MODID + ":reagent.basic")
    public static IReagent reagent_basic = new ReagentBasic();

    @GameRegistry.ObjectHolder(Reference.MODID + ":reagent.null")
    public static IReagent reagent_null = new ReagentNull();

    @GameRegistry.ObjectHolder(Reference.MODID + ":reagent.advanced")
    public static IReagent reagent_advanced = new ReagentAdvanced();

    @GameRegistry.ObjectHolder(Reference.MODID + ":reagent.extreme")
    public static IReagent reagent_extreme = new ReagentExtreme();

    @GameRegistry.ObjectHolder(Reference.MODID + ":reagent.rift")
    public static IReagent reagent_rift = new ReagentRift();

    @GameRegistry.ObjectHolder(Reference.MODID + ":reagent.universe")
    public static IReagent reagent_universe = new ReagentUniverse();

    public static void register(RegistryEvent.Register<IReagent> e) {
        e.getRegistry().register(BaseModReagents.reagent_null);
        e.getRegistry().register(BaseModReagents.reagent_basic);
        e.getRegistry().register(BaseModReagents.reagent_advanced);
        e.getRegistry().register(BaseModReagents.reagent_extreme);
        e.getRegistry().register(BaseModReagents.reagent_rift);
        e.getRegistry().register(BaseModReagents.reagent_universe);
    }

}
