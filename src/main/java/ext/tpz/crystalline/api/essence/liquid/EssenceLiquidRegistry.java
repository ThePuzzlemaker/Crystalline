package ext.tpz.crystalline.api.essence.liquid;

import ext.tpz.crystalline.api.essence.powder.IEssencePowder;
import ext.tpz.crystalline.util.Reference;
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
public class EssenceLiquidRegistry {

    private static IForgeRegistry<IEssenceLiquid> REGISTRY = null;

    @SubscribeEvent(priority = EventPriority.NORMAL)
    public static void registerRegistry(@Nonnull RegistryEvent.NewRegistry event) {
        REGISTRY = new RegistryBuilder<IEssenceLiquid>()
                .setName(new ResourceLocation(Reference.MODID, "registry.essence.liquid"))
                .setType(IEssenceLiquid.class)
                .setIDRange(0, Integer.MAX_VALUE - 1)
                .create();
    }

    public static IForgeRegistry<IEssenceLiquid> getRegistry() {
        if (REGISTRY != null)
            return REGISTRY;
        return null;
    }



}
