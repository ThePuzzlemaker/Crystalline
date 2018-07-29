package ext.tpz.crystalline.block.tileentity;

import ext.tpz.crystalline.essences.powder.BaseModEssencePowders;
import ext.tpz.crystalline.item.CrystallineItems;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;

public class TEDistillationBasin extends TileEntity implements ITickable {

    private ItemStack stack = ItemStack.EMPTY;
    public float rot = 0.0f;
    private int timer = 0;

    public int getTimer() {
        return timer;
    }

    public void setTimer(int timer) {
        this.timer = timer;
        markDirty();
        if (world != null) {
            IBlockState state = world.getBlockState(getPos());
            world.notifyBlockUpdate(getPos(), state, state, 3);
        }
    }



    public ItemStack getStack() {
        return stack;
    }

    public void setStack(ItemStack stack) {
        this.stack = stack;
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
        if (compound.hasKey("item")) {
            stack = new ItemStack(compound.getCompoundTag("item"));
        } else {
            stack = ItemStack.EMPTY;
        }
        if (compound.hasKey("timer")) {
            timer = compound.getInteger("timer");
        } else {
            timer = 0;
        }
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);
        if (!stack.isEmpty()) {
            NBTTagCompound tagCompound = new NBTTagCompound();
            stack.writeToNBT(tagCompound);
            compound.setTag("item", tagCompound);
        }
        if (timer >= 0) {
            compound.setInteger("timer", timer);
        }
        return compound;
    }

    /*
        knowledge: diamond
        administration: spectral arrow
        cleansing: quartz
        life: golden apple
        universe: pure universe essence
        unknown: missingno essence
        rift: pure rift essence
     */

    @Override
    public void update() {
        if (!world.isRemote) {
            ItemStack universe = new ItemStack(CrystallineItems.essence_powder);
            ItemStack rift = new ItemStack(CrystallineItems.essence_powder);
            TEDistillationBasin te = (TEDistillationBasin) world.getTileEntity(pos);
            if (!te.getStack().isEmpty()) {
                if (getTimer() + 1 < 1200) {
                    ItemStack s = te.getStack();
                    if (s.getItem() == Items.GLOWSTONE_DUST && s.getCount() == 8) {
                        setTimer((getTimer() + 1));
                    } else if (s.getItem() == Items.DIAMOND && s.getCount() == 4) {
                        setTimer((getTimer() + 1));
                    } else if (s.getItem() == Items.QUARTZ && s.getCount() == 8) {
                        setTimer((getTimer() + 1));
                    } else if (s.getItem() == Items.GOLDEN_APPLE && s.getCount() == 4) {
                        setTimer((getTimer() + 1));
                    } else if (s.getItem() == CrystallineItems.essence_powder && CrystallineItems.essence_powder.getType(stack) == BaseModEssencePowders.essence_powder_rift  && s.getCount() == 8) {
                        setTimer((getTimer() + 1));
                    } else if (s.getItem() == CrystallineItems.essence_powder && CrystallineItems.essence_powder.getType(stack) == BaseModEssencePowders.essence_powder_rift && s.getCount() == 8) {
                        setTimer((getTimer() + 1));
                    } else {
                        setTimer(0);
                    }
                } else {
                    ItemStack s = te.getStack();
                    ItemStack res = new ItemStack(CrystallineItems.essence_bottle);
                    if (s.getItem() == Items.GLOWSTONE_DUST && s.getCount() == 8) {
                        CrystallineItems.essence_bottle.setType(res, EnumCrystalTypes.ADMINISTRATION);
                        te.setStack(res);
                    } else if (s.getItem() == Items.DIAMOND && s.getCount() == 4) {
                        CrystallineItems.essence_bottle.setType(res, EnumCrystalTypes.KNOWLEDGE);
                        te.setStack(res);
                    } else if (s.getItem() == Items.QUARTZ && s.getCount() == 8) {
                        CrystallineItems.essence_bottle.setType(res, EnumCrystalTypes.CLEANSING);
                        te.setStack(res);
                    } else if (s.getItem() == Items.GOLDEN_APPLE && s.getCount() == 4) {
                        CrystallineItems.essence_bottle.setType(res, EnumCrystalTypes.LIFE);
                        te.setStack(res);
                    } else if (s.getItem() == CrystallineItems.essence_powder && CrystallineItems.essence_powder.getType(stack) == BaseModEssencePowders.essence_powder_rift && s.getCount() == 8) {
                        CrystallineItems.essence_bottle.setType(res, EnumCrystalTypes.UNIVERSE);
                        te.setStack(res);
                    } else if (s.getItem() == CrystallineItems.essence_powder && CrystallineItems.essence_powder.getType(stack) == BaseModEssencePowders.essence_powder_rift  && s.getCount() == 8) {
                        CrystallineItems.essence_bottle.setType(res, EnumCrystalTypes.RIFT);
                        te.setStack(res);
                    }
                    setTimer(0);
                }
            }
        }
    }
}
