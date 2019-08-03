package com.teamisotope.crystalline.api.insanity.capability;

import com.teamisotope.crystalline.api.CStatic;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.concurrent.Callable;

public class InsanityCapability implements IInsanity, ICapabilitySerializable<NBTTagCompound> {

    @CapabilityInject(IInsanity.class)
    public static Capability<IInsanity> CAPABILITY_INSANITY = null;

    public static final ResourceLocation CAPABILITY_INSANITY_RL = new ResourceLocation(CStatic.MODID, "capability.insanity");

    private int insanity;

    public InsanityCapability() {
        this.insanity = 0;
    }

    @Override
    public int getInsanity() {
        return insanity;
    }

    @Override
    public void setInsanity(int toSet) {
        this.insanity = toSet;
    }

    @Override
    public void addInsanity(int toAdd) {
        this.insanity += toAdd;
    }

    @Override
    public void subInsanity(int toSub) {
        this.insanity -= toSub;
    }

    @Override
    public NBTTagCompound serializeNBT() {
        return (NBTTagCompound) CAPABILITY_INSANITY.writeNBT(this, EnumFacing.UP);
    }

    @Override
    public void deserializeNBT(NBTTagCompound nbt) {
        CAPABILITY_INSANITY.readNBT(this, EnumFacing.UP, nbt);
    }

    @Override
    public boolean hasCapability(@Nonnull Capability<?> capability, @Nullable EnumFacing facing) {
        return capability == CAPABILITY_INSANITY;
    }

    @Nullable
    @Override
    public <T> T getCapability(@Nonnull Capability<T> capability, @Nullable EnumFacing facing) {
        if (capability == CAPABILITY_INSANITY)
            return CAPABILITY_INSANITY.cast(this);
        return null;
    }

    public static class Storage implements Capability.IStorage<IInsanity> {
        @Nullable
        @Override
        public NBTBase writeNBT(Capability<IInsanity> capability, IInsanity instance, EnumFacing side) {
            NBTTagCompound nbt = new NBTTagCompound();
            nbt.setInteger("insanity", instance.getInsanity());
            return nbt;
        }

        @Override
        public void readNBT(Capability<IInsanity> capability, IInsanity instance, EnumFacing side, NBTBase nbt) {
            if (nbt instanceof NBTTagCompound) {
                int insanity = ((NBTTagCompound) nbt).getInteger("insanity");
                instance.setInsanity(insanity);
            } else {
                instance.setInsanity(0);
            }
        }
    }

    public static class Factory implements Callable<IInsanity> {
        @Override
        public IInsanity call() {
            return new InsanityCapability();
        }
    }


}
