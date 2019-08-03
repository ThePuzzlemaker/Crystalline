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

    private int currentFrequency;
    private int currentDifference;


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
        this.currentDifference = compound.hasKey("currentDifference") ? compound.getInteger("currentDifference") : 0;
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);
        compound.setTag("items", itemStackHandler.serializeNBT());
        compound.setInteger("currentFrequency", currentFrequency);
        compound.setInteger("currentDifference", currentDifference);
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
                    meta.setFrequency(this.getCurrentFrequency());
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

    @Override
    public boolean shouldRefresh(World world, BlockPos pos, IBlockState oldState, IBlockState newSate) {
        return false;
    }
}