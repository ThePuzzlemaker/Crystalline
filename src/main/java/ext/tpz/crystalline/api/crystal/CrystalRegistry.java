package ext.tpz.crystalline.api.crystal;

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
public class CrystalRegistry {

    private static IForgeRegistry<ICrystal> REGISTRY = null;

    @SubscribeEvent(priority = EventPriority.NORMAL)
    public static void registerRegistry(@Nonnull RegistryEvent.NewRegistry event) {
        REGISTRY = new RegistryBuilder<ICrystal>()
                .setName(new ResourceLocation(Reference.MODID, "registry.crystal"))
                .setType(ICrystal.class)
                .setIDRange(0, Integer.MAX_VALUE - 1)
                .create();
    }

    public static IForgeRegistry<ICrystal> getRegistry() {
        if (REGISTRY != null)
            return REGISTRY;
        return null;
    }



}
