package com.teamisotope.crystalline.api.serialization.builtin;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.util.INBTSerializable;

import java.util.UUID;

public class CrystalData implements INBTSerializable<CompoundNBT> {

    private ResourceLocation crystal;
    private UUID boundTo;
    private int potential;
    private ResourceLocation currentMode;

    public CrystalData() {
        this(null, null, 100, null);
    }

    public CrystalData(ResourceLocation crystal, UUID boundTo, int potential, ResourceLocation currentMode) {
        this.crystal = crystal;
        this.boundTo = boundTo;
        this.potential = potential;
        this.currentMode = currentMode;
    }

    @Override
    public CompoundNBT serializeNBT() {
        CompoundNBT nbt = new CompoundNBT();
        nbt.putString("crystal", getCrystal().toString());
        nbt.putString("boundTo", getBoundTo().toString());
        nbt.putInt("potential", getPotential());
        nbt.putString("currentMode", getCurrentMode().toString());
        return nbt;
    }

    @Override
    public void deserializeNBT(CompoundNBT nbt) {
        if (nbt.contains("crystal"))
            setCrystal(new ResourceLocation(nbt.getString("crystal")));
        if (nbt.contains("boundTo"))
            setBoundTo(UUID.fromString(nbt.getString("boundTo")));
        if (nbt.contains("potential"))
            setPotential(nbt.getInt("potential"));
        if (nbt.contains("currentMode"))
            setCrystal(new ResourceLocation(nbt.getString("currentMode")));
    }

    public ResourceLocation getCrystal() {
        return crystal;
    }

    public CrystalData setCrystal(ResourceLocation crystal) {
        this.crystal = crystal;
        return this;
    }

    public UUID getBoundTo() {
        return boundTo;
    }

    public CrystalData setBoundTo(UUID boundTo) {
        this.boundTo = boundTo;
        return this;
    }

    public int getPotential() {
        return potential;
    }

    public CrystalData setPotential(int potential) {
        this.potential = potential;
        return this;
    }

    public ResourceLocation getCurrentMode() {
        return currentMode;
    }

    public CrystalData setCurrentMode(ResourceLocation currentMode) {
        this.currentMode = currentMode;
        return this;
    }

}
