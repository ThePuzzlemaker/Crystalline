package com.teamisotope.crystalline.api.crystal;

import com.teamisotope.crystalline.api.util.ISerializableMetadata;
import com.teamisotope.crystalline.common.base.crystal.CrystalInit;
import net.minecraft.nbt.NBTTagCompound;

import java.util.UUID;

public class CrystalMetadata implements ISerializableMetadata {

    private ICrystal crystal;
    private UUID bound;
    private int potential;

    public CrystalMetadata(ICrystal crystal, UUID bound, int potential) {
        this.crystal = crystal;
        this.bound = bound;
        this.potential = potential;
    }
    public CrystalMetadata(ICrystal crystal, UUID bound) {
        this(crystal, bound, 100);
    }
    public CrystalMetadata(ICrystal crystal) {
        this(crystal, null, 100);
    }
    public CrystalMetadata(ICrystal crystal, int potential) {
        this(crystal, null, potential);
    }
    public CrystalMetadata() {
        this(null, null, 100);
    }

    @Override
    public NBTTagCompound serialize() {
        NBTTagCompound nbt = new NBTTagCompound();
        if (crystal != null)
            nbt.setString("id", crystal.serialize());
        if (bound != null)
            nbt.setUniqueId("bound", bound);
        nbt.setInteger("potential", potential);
        return nbt;
    }

    @Override
    public CrystalMetadata deserialize(NBTTagCompound nbt) {
        if (nbt != null) {
            if (nbt.hasKey("id"))
                this.crystal = CrystalRegistry.deserialize(nbt.getString("id"));
            if (nbt.hasKey("bound"))
                this.bound = nbt.getUniqueId("bound");
            if (nbt.hasKey("potential"))
                this.potential = nbt.getInteger("potential");
        }
        return this;
    }

    public void setCrystal(ICrystal crystal) {
        this.crystal = crystal;
    }

    public void setBound(UUID bound) {
        this.bound = bound;
    }

    public void setPotential(int potential) {
        this.potential = potential;
    }

    public ICrystal getCrystal() {
        return crystal;
    }

    public UUID getBound() {
        return bound;
    }

    public int getPotential() {
        return potential;
    }

}
