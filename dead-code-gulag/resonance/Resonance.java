package com.teamisotope.crystalline.api.resonance;

public class Resonance {

    private int lower;
    private int upper;
    private int exact;

    public Resonance(int lower, int upper, int exact) {
        this.lower = lower;
        this.upper = upper;
        this.exact = exact;
    }

    public int getLower() {
        return lower;
    }

    public int getUpper() {
        return upper;
    }

    public int getExact() {
        return exact;
    }

}
