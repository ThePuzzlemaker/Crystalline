package ext.tpz.crystalline.api.reagent;

import ext.tpz.crystalline.api.mode.ICrystalMode;
import ext.tpz.crystalline.common.util.Reference;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.RegistryBuilder;

import javax.annotation.Nonnull;

/*
 *
 * Thanks goes to the OG EpicSquid (@EpicSquid318)
 *
 */

@Mod.EventBusSubscriber(modid = Reference.MODID)
public class ReagentRegistry {

    private static IForgeRegistry<IReagent> REGISTRY = null;

    @SubscribeEvent(priority = EventPriority.NORMAL)
    public static void registerRegistry(@Nonnull RegistryEvent.NewRegistry event) {
        REGISTRY = new RegistryBuilder<IReagent>()
                .setName(new ResourceLocation(Reference.MODID, "registry.reagents"))
                .setType(IReagent.class)
                .setIDRange(0, Integer.MAX_VALUE - 1)
                .create();
    }

    public static IForgeRegistry<IReagent> getRegistry() {
        if (REGISTRY != null)
            return REGISTRY;
        return null;
    }



}
