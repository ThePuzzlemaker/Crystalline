package ext.tpz.crystalline.api.recipe;

import ext.tpz.crystalline.api.mode.ICrystalMode;
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
public class DistillationRegistry {

    private static IForgeRegistry<IDistillationRecipe> REGISTRY = null;

    @SubscribeEvent(priority = EventPriority.NORMAL)
    public static void registerRegistry(@Nonnull RegistryEvent.NewRegistry event) {
        REGISTRY = new RegistryBuilder<IDistillationRecipe>()
                .setName(new ResourceLocation(Reference.MODID, "registry.distillation"))
                .setType(IDistillationRecipe.class)
                .setIDRange(0, Integer.MAX_VALUE - 1)
                .create();
    }

    public static IForgeRegistry<IDistillationRecipe> getRegistry() {
        if (REGISTRY != null)
            return REGISTRY;
        return null;
    }



}
