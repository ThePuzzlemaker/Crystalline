package ext.tpz.crystalline.api.resonance;

import ext.tpz.crystalline.api.crystal.ICrystal;

import java.util.HashMap;

public class WorldResonance {

    private HashMap<ICrystal, Resonance> resonances;

    public static final WorldResonance INSTANCE = new WorldResonance();

    public WorldResonance() {
        this.resonances = new HashMap<>();
    }

    public HashMap<ICrystal, Resonance> getResonances() {
        return resonances;
    }

    public Resonance getResonance(ICrystal c) {
        return resonances.get(c);
    }

}
