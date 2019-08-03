package com.teamisotope.crystalline.api.resonance;

import com.teamisotope.crystalline.api.crystal.CrystalRegistry;
import com.teamisotope.crystalline.api.crystal.ICrystal;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.concurrent.ThreadLocalRandom;

@Mod.EventBusSubscriber
public class ResonanceHandler {

    @SubscribeEvent
    public static void onWorldGeneration(WorldEvent.Load e) {
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
