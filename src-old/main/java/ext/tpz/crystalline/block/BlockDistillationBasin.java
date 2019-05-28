package ext.tpz.crystalline.block;

import ext.tpz.crystalline.Crystalline;
import ext.tpz.crystalline.block.tileentity.TEDistillationBasin;
import ext.tpz.crystalline.block.tileentity.TESRDistillationBasin;
import ext.tpz.crystalline.compat.top.TOPInfoProvider;
import ext.tpz.crystalline.util.Reference;
import mcjty.theoneprobe.api.IProbeHitData;
import mcjty.theoneprobe.api.IProbeInfo;
import mcjty.theoneprobe.api.ProbeMode;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockDistillationBasin extends Block implements ITileEntityProvider, TOPInfoProvider {

    public BlockDistillationBasin() {
        super(Material.WOOD);
        setUnlocalizedName(Reference.MODID + ".distillation_basin").setRegistryName("distillation_basin").setCreativeTab(Crystalline.tab).setHardness(1.0f).setResistance(1.0f);
    }

    // I'm not sure what replaces these deprecated functions - I'm using them until they break.
    @Override
    public boolean isFullCube(IBlockState state) {
        return false;
    }

    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    @SideOnly(Side.CLIENT)
    public void initTESR() {
        ClientRegistry.bindTileEntitySpecialRenderer(TEDistillationBasin.class, new TESRDistillationBasin());
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TEDistillationBasin();
    }

    private TEDistillationBasin getTE(World world, BlockPos pos) {
        return (TEDistillationBasin) world.getTileEntity(pos);
    }

    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player,
                                    EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ) {
        if (!world.isRemote) {
            TEDistillationBasin te = getTE(world, pos);
            if (player.getHeldItem(hand).isEmpty()){
                // There is a stack in the pedestal. In this case we remove it and try to put it in the
                // players inventory if there is room
                ItemStack stack = te.getStack();
                te.setStack(ItemStack.EMPTY);
                if (!player.inventory.addItemStackToInventory(stack)) {
                    // Not possible. Throw item in the world
                    EntityItem entityItem = new EntityItem(world, pos.getX(), pos.getY()+1, pos.getZ(), stack);
                    world.spawnEntity(entityItem);
                } else {
                    player.openContainer.detectAndSendChanges();
                }
            } else if (te.getStack().isEmpty() || ((te.getStack().getCount() <= 64 && te.getStack().getItem() == player.getHeldItem(hand).getItem()) && (te.getStack().getCount() + 1) <= te.getStack().getMaxStackSize())) {
                if (!player.getHeldItem(hand).isEmpty()) {
                    // There is no item in the pedestal and the player is holding an item. We move that item
                    // to the pedestal
                    ItemStack s = player.getHeldItem(hand).copy();
                    if (te.getStack().isEmpty()) {
                        s.setCount(1);
                    } else {
                        s.setCount(te.getStack().getCount() + 1);
                    }
                    te.setStack(s);
                    if (player.getHeldItem(hand).getCount() == 1) {
                        player.inventory.setInventorySlotContents(player.inventory.currentItem, ItemStack.EMPTY);
                    } else {
                        ItemStack s2 = player.getHeldItem(hand).copy();
                        s2.setCount(player.getHeldItem(hand).getCount() - 1);
                        player.inventory.setInventorySlotContents(player.inventory.currentItem, s2);
                    }
                    // Make sure the client knows about the changes in the player inventory
                    player.openContainer.detectAndSendChanges();
                }
            }
        }

        // Return true also on the client to make sure that MC knows we handled this and will not try to place
        // a block on the client
        return true;
    }

    @Override
    public void breakBlock(World world, BlockPos pos, IBlockState state) {
        TEDistillationBasin te = getTE(world, pos);
        EntityItem entityItem = new EntityItem(world, pos.getX(), pos.getY()+1, pos.getZ(), te.getStack());
        world.spawnEntity(entityItem);
    }

    private int calculate(int tick, int max) {
        float percent = ((float)tick * 100.0f) / (float)max;
        return (int)percent;
    }

    @Override
    public void addProbeInfo(ProbeMode mode, IProbeInfo probeInfo, EntityPlayer player, World world, IBlockState state, IProbeHitData data) {
        TileEntity te = world.getTileEntity(data.getPos());
        if (te instanceof TEDistillationBasin) {
            TEDistillationBasin basin = (TEDistillationBasin) te;
            probeInfo.horizontal().item(new ItemStack(Items.CLOCK)).text(TextFormatting.GREEN + "Ticks: " + basin.getTimer() + "/1200");
            probeInfo.horizontal(probeInfo.defaultLayoutStyle().borderColor(0xffff0000)).item(new ItemStack(CrystallineBlocks.distillationBasin)).progress((calculate(basin.getTimer(), 1200)), 100, probeInfo.defaultProgressStyle().suffix("%")).horizontal();
            probeInfo.horizontal().item(basin.getStack()).text("Current Item");
        }
    }
}
