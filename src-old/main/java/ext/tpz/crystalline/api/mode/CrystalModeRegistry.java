package ext.tpz.crystalline.api.mode;

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
public class CrystalModeRegistry {

    private static IForgeRegistry<ICrystalMode> REGISTRY = null;

    @SubscribeEvent(priority = EventPriority.NORMAL)
    public static void registerRegistry(@Nonnull RegistryEvent.NewRegistry event) {
        REGISTRY = new RegistryBuilder<ICrystalMode>()
                .setName(new ResourceLocation(Reference.MODID, "registry.modes"))
                .setType(ICrystalMode.class)
                .setIDRange(0, Integer.MAX_VALUE - 1)
                .create();
    }

    public static IForgeRegistry<ICrystalMode> getRegistry() {
        if (REGISTRY != null)
            return REGISTRY;
        return null;
    }



}
