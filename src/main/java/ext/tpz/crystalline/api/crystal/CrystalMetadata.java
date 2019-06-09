package ext.tpz.crystalline.api.crystal;

import ext.tpz.crystalline.api.util.SerializableMetadata;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

import java.util.UUID;

public class CrystalMetadata implements SerializableMetadata {

    private ICrystal crystal;
    private int frequency;
    private UUID bound;
    private int potential;

    public CrystalMetadata(ICrystal crystal, UUID bound, int frequency, int potential) {
        this.crystal = crystal;
        this.bound = bound;
        this.frequency = frequency;
        this.potential = potential;
    }
    public CrystalMetadata(ICrystal crystal, UUID bound) {
        this(crystal, bound, 0, 100);
    }
    public CrystalMetadata(ICrystal crystal) {
        this(crystal, null, 0, 100);
    }
    public CrystalMetadata(ICrystal crystal, int frequency, int potential) {
        this(crystal, null, frequency, potential);
    }
    public CrystalMetadata(ICrystal crystal, int potential) {
        this(crystal, null, 0, potential);
    }
    public CrystalMetadata() {
        this(null, null, 0, 100);
    }

    @Override
    public NBTTagCompound serialize() {
        NBTTagCompound nbt = new NBTTagCompound();
        if (crystal != null)
            nbt.setString("id", crystal.serialize());
        if (bound != null)
            nbt.setUniqueId("bound", bound);
        nbt.setInteger("potential", potential);
        nbt.setInteger("frequency", frequency);
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
            if (nbt.hasKey("frequency"))
                this.frequency = nbt.getInteger("frequency");
        }
        return this;
    }

    public void setCrystal(ICrystal crystal) {
        this.crystal = crystal;
    }

    public void setBound(UUID bound) {
        this.bound = bound;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    public void setPotential(int potential) {
        this.potential = potential;
    }

    public ICrystal getCrystal() {
        return crystal;
    }

    public int getFrequency() {
        return frequency;
    }

    public UUID getBound() {
        return bound;
    }

    public int getPotential() {
        return potential;
    }

}
