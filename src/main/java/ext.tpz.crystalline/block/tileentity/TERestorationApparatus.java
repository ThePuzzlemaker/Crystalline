package ext.tpz.crystalline.block.tileentity;

import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

public class TERestorationApparatus extends TileEntity {

    private ItemStack essenceStack = ItemStack.EMPTY;
    private ItemStack crystalStack = ItemStack.EMPTY;
    public float rotE = 0.0f;
    public float rotC = 0.0f;

    public ItemStack getCrystalStack() {
        return crystalStack;
    }

    public ItemStack getEssenceStack() {
        return essenceStack;
    }

    public void setEssenceStack(ItemStack stack) {
        this.essenceStack = stack;
        markDirty();
        if (world != null) {
            IBlockState state = world.getBlockState(getPos());
            world.notifyBlockUpdate(getPos(), state, state, 3);
        }
    }

    public void setCrystalStack(ItemStack stack) {
        this.crystalStack = stack;
        markDirty();
        if (world != null) {
            IBlockState state = world.getBlockState(getPos());
            world.notifyBlockUpdate(getPos(), state, state, 3);
        }
    }

    @Override
    public NBTTagCompound getUpdateTag() {
        // getUpdateTag() is called whenever the chunkdata is sent to the
        // client. In contrast getUpdatePacket() is called when the tile entity
        // itself wants to sync to the client. In many cases you want to send
        // over the same information in getUpdateTag() as in getUpdatePacket().
        return writeToNBT(new NBTTagCompound());
    }

    @Override
    public SPacketUpdateTileEntity getUpdatePacket() {
        // Prepare a packet for syncing our TE to the client. Since we only have to sync the stack
        // and that's all we have we just write our entire NBT here. If you have a complex
        // tile entity that doesn't need to have all information on the client you can write
        // a more optimal NBT here.
        NBTTagCompound nbtTag = new NBTTagCompound();
        this.writeToNBT(nbtTag);
        return new SPacketUpdateTileEntity(getPos(), 1, nbtTag);
    }

    @Override
    public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity packet) {
        // Here we get the packet from the server and read it into our client side tile entity
        this.readFromNBT(packet.getNbtCompound());
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        if (compound.hasKey("crystal")) {
            crystalStack = new ItemStack(compound.getCompoundTag("crystal"));
        } else {
            crystalStack = ItemStack.EMPTY;
        }
        if (compound.hasKey("essence")) {
            essenceStack = new ItemStack(compound.getCompoundTag("essence"));
        } else {
            essenceStack = ItemStack.EMPTY;
        }
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);
        if (!essenceStack.isEmpty()) {
            NBTTagCompound tmp = new NBTTagCompound();
            essenceStack.writeToNBT(tmp);
            compound.setTag("essence", tmp);
        }
        if (!crystalStack.isEmpty()) {
            NBTTagCompound tmp2 = new NBTTagCompound();
            crystalStack.writeToNBT(tmp2);
            compound.setTag("crystal", tmp2);
        }
        return compound;
    }

}
