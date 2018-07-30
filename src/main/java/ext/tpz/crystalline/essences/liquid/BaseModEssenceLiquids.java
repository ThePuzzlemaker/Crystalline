package ext.tpz.crystalline.essences.liquid;

import ext.tpz.crystalline.api.essence.liquid.IEssenceLiquid;
import ext.tpz.crystalline.util.Reference;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class BaseModEssenceLiquids {

    @GameRegistry.ObjectHolder(Reference.MODID + ":essence.liquid.null")
    public static IEssenceLiquid essence_liquid_null = new EssenceLiquidNull();

    @GameRegistry.ObjectHolder(Reference.MODID + ":essence.liquid.administration")
    public static IEssenceLiquid essence_liquid_administration = new EssenceLiquidAdministration();

    @GameRegistry.ObjectHolder(Reference.MODID + ":essence.liquid.knowledge")
    public static IEssenceLiquid essence_liquid_knowledge = new EssenceLiquidKnowledge();

    @GameRegistry.ObjectHolder(Reference.MODID + ":essence.liquid.life")
    public static IEssenceLiquid essence_liquid_life = new EssenceLiquidLife();

    @GameRegistry.ObjectHolder(Reference.MODID + ":essence.liquid.rift")
    public static IEssenceLiquid essence_liquid_rift = new EssenceLiquidRift();

    @GameRegistry.ObjectHolder(Reference.MODID + ":essence.liquid.universe")
    public static IEssenceLiquid essence_liquid_universe = new EssenceLiquidUniverse();

    @GameRegistry.ObjectHolder(Reference.MODID + ":essence.liquid.cleansing")
    public static IEssenceLiquid essence_liquid_cleansing = new EssenceLiquidCleansing();


    public static void register(RegistryEvent.Register<IEssenceLiquid> e) {
        e.getRegistry().register(essence_liquid_null);
        e.getRegistry().register(essence_liquid_administration);
        e.getRegistry().register(essence_liquid_knowledge);
        e.getRegistry().register(essence_liquid_life);
        e.getRegistry().register(essence_liquid_rift);
        e.getRegistry().register(essence_liquid_universe);
        e.getRegistry().register(essence_liquid_cleansing);
    }

}
