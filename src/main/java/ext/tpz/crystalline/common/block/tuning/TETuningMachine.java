package ext.tpz.crystalline.common.block.tuning;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
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

import static ext.tpz.crystalline.common.block.tuning.BlockTuningMachine.OPEN;

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
        world.setBlockState(pos, state.withProperty(OPEN, false), 2);
    }

    public ItemStackHandler getItemStackHandler() {
        return itemStackHandler;
    }

    public void tune(ItemStack crystal) {

    }

    public void test(ItemStack crystal) {
        if (crystal.getItem() == Items.DIAMOND) {
            this.setCurrentDifference(0);
        } else {
            this.setCurrentDifference(5100);
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


}
