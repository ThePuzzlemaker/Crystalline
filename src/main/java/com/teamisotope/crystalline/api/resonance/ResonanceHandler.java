package com.teamisotope.crystalline.api.resonance;

import com.teamisotope.crystalline.api.crystal.CrystalRegistry;
import com.teamisotope.crystalline.api.crystal.ICrystal;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

@Mod.EventBusSubscriber
public class ResonanceHandler {

    @SubscribeEvent
    public static void onWorldLoad(WorldEvent.Load e) {
        WorldResonance rl = WorldResonance.INSTANCE;
        if (CrystalRegistry.getRegistry() != null) {
            for (ICrystal c : CrystalRegistry.getRegistry().getValuesCollection()) {
                Random r = e.getWorld().rand;
                int difference = r.nextInt(39062 - 9765 + 1) + 9765; // Difference between two numbers can be anywhere from ~= 1/256 of the spectrum or ~= 1/1024 of the spectrum
                int lower = r.nextInt(9999999 - difference + 1);
                int upper = lower + difference;
                int exact = r.nextInt(upper - lower + 1) + lower;
                rl.getResonances().put(c, new Resonance(lower, upper, exact));
            }
        }
    }



}
