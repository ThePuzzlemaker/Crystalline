package ext.tpz.crystalline.potion;

import net.minecraft.potion.Potion;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

@Mod.EventBusSubscriber
public class Potions {

    @GameRegistry.ObjectHolder("crystalline:dirtshield")
    public static PotionDirtshield potionDirtshield;

    @SubscribeEvent
    public static void registerPotions(RegistryEvent.Register<Potion> e) {
        e.getRegistry().register(new PotionDirtshield());
    }

}
