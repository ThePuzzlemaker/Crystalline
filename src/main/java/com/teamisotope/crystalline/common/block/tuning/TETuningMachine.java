package com.teamisotope.crystalline.common.block.tuning;

import com.teamisotope.crystalline.api.crystal.CrystalMetadata;
import com.teamisotope.crystalline.api.resonance.Resonance;
import com.teamisotope.crystalline.api.resonance.WorldResonance;
import com.teamisotope.crystalline.common.item.CItems;
import com.teamisotope.crystalline.common.item.dyn.ItemCrystal;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nullable;

public class TETuningMachine extends TileEntity {

    public static final int SIZE = 2;

    private int currentFrequency = 0;
    private int currentDifference = -1;

    private int upper = -1;
    private int lower = -1;
    private int exact = -1;


    private ItemStackHandler itemStackHandler = new ItemStackHandler(SIZE) {
        @Override
        protected void onContentsChanged(int slot) {
            TETuningMachine.this.markDirty();
            IBlockState state = getWorld().getBlockState(getPos());
            getWorld().notifyBlockUpdate(getPos(), state, state, 3);
        }
    };

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        if (compound.hasKey("items"))
            itemStackHandler.deserializeNBT((NBTTagCompound) compound.getTag("items"));
        this.currentFrequency = compound.hasKey("currentFrequency") ? compound.getInteger("currentFrequency") : 0;
        this.currentDifference = compound.hasKey("currentDifference") ? compound.getInteger("currentDifference") : -1;
        this.upper = compound.hasKey("upper") ? compound.getInteger("upper") : -1;
        this.lower = compound.hasKey("lower") ? compound.getInteger("lower") : -1;
        this.exact = compound.hasKey("exact") ? compound.getInteger("exact") : -1;
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);
        compound.setTag("items", itemStackHandler.serializeNBT());
        compound.setInteger("currentFrequency", currentFrequency);
        compound.setInteger("currentDifference", currentDifference);
        compound.setInteger("upper", upper);
        compound.setInteger("lower", lower);
        compound.setInteger("exact", exact);
        return compound;
    }

    public boolean canInteractWith(EntityPlayer playerIn) {
        return !isInvalid() && playerIn.getDistanceSq(pos.add(0.5D, 0.5D, 0.5D)) < 64D;
    }

    @Override
    public boolean hasCapability(Capability<?> capability, @Nullable EnumFacing facing) {
        return capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY || super.hasCapability(capability, facing);
    }

    @Nullable
    @Override
    public <T> T getCapability(Capability<T> capability, @Nullable EnumFacing facing) {
        return capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY ? CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.cast(itemStackHandler) : super.getCapability(capability, facing);
    }

    public void markClosed() {
        BlockPos pos = this.getPos();
        World world = this.getWorld();
        IBlockState state = world.getBlockState(pos);
        IBlockState newState = state.withProperty(BlockTuningMachine.OPEN, false);
        world.setBlockState(pos, newState, 2);
    }

    public ItemStackHandler getItemStackHandler() {
        return itemStackHandler;
    }

    public void tune(ItemStack crystal) {
        if (crystal.getItem() == CItems.crystal) {
            CrystalMetadata meta = ItemCrystal.get(crystal);
            if (meta != null) {
                if (meta.getCrystal() != null) {
                    if (itemStackHandler.getStackInSlot(1) == ItemStack.EMPTY) {
                        meta.setFrequency(this.getCurrentFrequency());
                        ItemCrystal.set(crystal, meta);
                        itemStackHandler.setStackInSlot(1, crystal);
                        itemStackHandler.setStackInSlot(0, ItemStack.EMPTY);
                    }
                }
            }
        }
    }



    public void test(ItemStack crystal) {
        if (crystal.getItem() == CItems.crystal) {
            CrystalMetadata meta = ItemCrystal.get(crystal);
            if (meta != null) {
                if (meta.getCrystal() != null) {
                    Resonance r = WorldResonance.INSTANCE.getResonance(meta.getCrystal());
                    if (r != null) {
                        this.setCurrentDifference(r.getExact() - this.getCurrentFrequency());
                        this.setUpper(r.getUpper());
                        this.setLower(r.getLower());
                    }
                }
            }
        }
    }

    @Override
    public NBTTagCompound getUpdateTag() {
        return this.writeToNBT(new NBTTagCompound());
    }

    @Nullable
    @Override
    public SPacketUpdateTileEntity getUpdatePacket() {
        NBTTagCompound nbtTag = new NBTTagCompound();
        this.writeToNBT(nbtTag);
        return new SPacketUpdateTileEntity(getPos(), 1, nbtTag);
    }

    @Override
    public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt) {
        this.readFromNBT(pkt.getNbtCompound());
    }

    public int getCurrentFrequency() {
        return currentFrequency;
    }

    public void setCurrentFrequency(int currentFrequency) {
        this.currentFrequency = currentFrequency;
        this.markDirty();
        IBlockState state = getWorld().getBlockState(getPos());
        getWorld().notifyBlockUpdate(getPos(), state, state, 3);
    }

    public int getCurrentDifference() {
        return currentDifference;
    }

    public void setCurrentDifference(int currentDifference) {
        this.currentDifference = currentDifference;
        this.markDirty();
        IBlockState state = getWorld().getBlockState(getPos());
        getWorld().notifyBlockUpdate(getPos(), state, state, 3);
    }

    public int getUpper() { return upper; }

    public int getLower() { return lower; }

    public int getExact() { return exact; }

    public void setUpper(int upper) {
        this.upper = upper;
        this.markDirty();
        IBlockState state = getWorld().getBlockState(getPos());
        getWorld().notifyBlockUpdate(getPos(), state, state, 3);
    }

    public void setLower(int lower) {
        this.lower = lower;
        this.markDirty();
        IBlockState state = getWorld().getBlockState(getPos());
        getWorld().notifyBlockUpdate(getPos(), state, state, 3);
    }

    public void setExact(int exact) {
        this.exact = exact;
        this.markDirty();
        IBlockState state = getWorld().getBlockState(getPos());
        getWorld().notifyBlockUpdate(getPos(), state, state, 3);
    }

    @Override
    public boolean shouldRefresh(World world, BlockPos pos, IBlockState oldState, IBlockState newSate) {
        return false;
    }
}
