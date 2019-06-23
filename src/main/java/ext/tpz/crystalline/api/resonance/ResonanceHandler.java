package ext.tpz.crystalline.api.resonance;

import ext.tpz.crystalline.api.crystal.CrystalRegistry;
import ext.tpz.crystalline.api.crystal.ICrystal;
import ext.tpz.crystalline.api.insanity.capability.InsanityCapability;
import ext.tpz.crystalline.api.insanity.capability.InsanityUtils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

@Mod.EventBusSubscriber
public class ResonanceHandler {

    @SubscribeEvent
    public static void onWorldGeneration(WorldEvent.CreateSpawnPosition e) {
        WorldResonance rl = WorldResonance.INSTANCE;
        if (CrystalRegistry.getRegistry() != null) {
            for (ICrystal c : CrystalRegistry.getRegistry().getValuesCollection()) {
                ThreadLocalRandom r = ThreadLocalRandom.current();
                r.setSeed(e.getWorld().getSeed());
                int difference = r.nextInt(9765, 39062+1); // Difference between two numbers can be anywhere from ~= 1/256 of the spectrum or ~= 1/1024 of the spectrum
                int lower = r.nextInt(0, 9999999-difference+1);
                int upper = lower + difference;
                int exact = r.nextInt(lower, upper+1);
                rl.getResonances().put(c, new Resonance(lower, upper, exact));
            }
        }
    }



}
