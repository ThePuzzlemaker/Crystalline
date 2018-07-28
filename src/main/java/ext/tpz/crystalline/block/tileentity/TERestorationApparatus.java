package ext.tpz.crystalline.block.tileentity;

import ext.tpz.crystalline.item.CrystallineItems;
import ext.tpz.crystalline.item.EnumCrystalTypes;
import ext.tpz.crystalline.item.EnumReagentTypes;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;

public class TERestorationApparatus extends TileEntity implements ITickable {

    private ItemStack essenceStack = ItemStack.EMPTY;
    private ItemStack crystalStack = ItemStack.EMPTY;
    public float rotE = 0.0f;
    public float rotC = 0.0f;

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
        if (compound.hasKey("timer")) {
            timer = compound.getInteger("timer");
        } else {
            timer = 0;
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
        if (timer >= 0) {
            compound.setInteger("timer", timer);
        }
        return compound;
    }

    @Override
    public void update() {
        if (!world.isRemote) {
            TERestorationApparatus te = (TERestorationApparatus) world.getTileEntity(pos);
            if (!te.getCrystalStack().isEmpty() && !te.getEssenceStack().isEmpty()) {
                ItemStack essence = te.getEssenceStack();
                ItemStack crystal = te.getCrystalStack();
                if (CrystallineItems.crystal.getPotential(crystal) < 100 && CrystallineItems.crystal.getPotential(crystal) > 0) {
                    if (CrystallineItems.essence_bottle.getType(essence).equals(CrystallineItems.crystal.getType(crystal))) {
                        if (getTimer() + 1 < 1200) {
                            setTimer(getTimer() + 1);
                        } else {
                            CrystallineItems.crystal.setPotential(crystal, 100);
                            CrystallineItems.crystal.setDrained(crystal, false);
                            te.setEssenceStack(ItemStack.EMPTY);
                            setTimer(0);
                        }
                    }
                } else if (CrystallineItems.crystal.getPotential(crystal) == 0) {
                    if (getTimer() + 1 < 1200) {
                        setTimer(getTimer() + 1);
                    } else {
                        CrystallineItems.crystal.setPotential(crystal, 100);
                        CrystallineItems.crystal.setType(crystal, CrystallineItems.essence_bottle.getType(essence));
                        CrystallineItems.crystal.setDrained(crystal, false);
                        EnumReagentTypes reagent = EnumReagentTypes.NONE;
                        switch (CrystallineItems.essence_bottle.getType(essence)) {
                            case "void":
                                reagent = EnumReagentTypes.NONE; break;
                            case "cleansing":
                                reagent = EnumReagentTypes.BASIC; break;
                            case "administration":
                                reagent = EnumReagentTypes.EXTREME; CrystallineItems.crystal.setBound(crystal, CrystallineItems.crystal.getBound(crystal)); break;
                            case "life":
                                reagent = EnumReagentTypes.ADVANCED; CrystallineItems.crystal.setBound(crystal, CrystallineItems.crystal.getBound(crystal)); break;
                            case "knowledge":
                                reagent = EnumReagentTypes.NONE; break;
                            case "rift":
                                reagent = EnumReagentTypes.RIFT; break;
                            case "universe":
                                reagent = EnumReagentTypes.UNIVERSE; break;
                            case "artificial":
                                reagent = EnumReagentTypes.NONE; break;
                            case "unknown":
                                reagent = EnumReagentTypes.NONE; break;
                            default:
                                reagent = EnumReagentTypes.NONE; break;
                        }
                        CrystallineItems.crystal.setReagent(crystal, reagent);
                        te.setEssenceStack(ItemStack.EMPTY);
                        setTimer(0);
                    }
                }
            }
        }
    }

}
