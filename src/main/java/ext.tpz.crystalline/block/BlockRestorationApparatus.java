package ext.tpz.crystalline.block;

import ext.tpz.crystalline.Crystalline;
import ext.tpz.crystalline.block.tileentity.TEDistillationBasin;
import ext.tpz.crystalline.block.tileentity.TERestorationApparatus;
import ext.tpz.crystalline.block.tileentity.TESRDistillationBasin;
import ext.tpz.crystalline.block.tileentity.TESRRestorationApparatus;
import ext.tpz.crystalline.item.CrystallineItems;
import ext.tpz.crystalline.util.Reference;
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
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;

public class BlockRestorationApparatus extends Block implements ITileEntityProvider {

    AxisAlignedBB BB = new AxisAlignedBB(0.0625 * 5, 0, 0.0625 * 5, 0.0625 * 11, 0.0625 * 11, 0.0625 * 11);

    public BlockRestorationApparatus() {
        super(Material.WOOD);
        setUnlocalizedName(Reference.MODID + ".restoration_apparatus").setRegistryName("restoration_apparatus").setCreativeTab(Crystalline.tab).setHardness(1.0f).setResistance(1.0f);
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

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
        return BB;
    }

    @Nullable
    @Override
    public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos) {
        return BB;
    }

    @SideOnly(Side.CLIENT)
    public void initTESR() {
        ClientRegistry.bindTileEntitySpecialRenderer(TERestorationApparatus.class, new TESRRestorationApparatus());
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TERestorationApparatus();
    }

    private TERestorationApparatus getTE(World world, BlockPos pos) {
        return (TERestorationApparatus) world.getTileEntity(pos);
    }

    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player,
                                    EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ) {
        if (!world.isRemote) {
            TERestorationApparatus te = getTE(world, pos);
            if (te.getCrystalStack().isEmpty()) {
                if (!player.getHeldItem(hand).isEmpty()) {
                    if (player.getHeldItem(hand).getItem() == CrystallineItems.crystal) {
                        te.setCrystalStack(player.getHeldItem(hand));
                        player.inventory.setInventorySlotContents(player.inventory.currentItem, ItemStack.EMPTY);
                        // Make sure the client knows about the changes in the player inventory
                        player.openContainer.detectAndSendChanges();
                    }
                }
            } else {
                if (player.isSneaking()) {
                    if (player.getHeldItem(hand).isEmpty()) {
                        ItemStack stack = te.getCrystalStack();
                        te.setCrystalStack(ItemStack.EMPTY);
                        if (!player.inventory.addItemStackToInventory(stack)) {
                            EntityItem entityItem = new EntityItem(world, pos.getX(), pos.getY()+1, pos.getZ(), stack);
                            world.spawnEntity(entityItem);
                        } else {
                            player.openContainer.detectAndSendChanges();
                        }
                    }
                }
            }
            if (te.getEssenceStack().isEmpty()) {
                if (!player.getHeldItem(hand).isEmpty()) {
                    if (player.getHeldItem(hand).getItem() == CrystallineItems.essence_bottle) {
                        te.setEssenceStack(player.getHeldItem(hand));
                        player.inventory.setInventorySlotContents(player.inventory.currentItem, ItemStack.EMPTY);
                        // Make sure the client knows about the changes in the player inventory
                        player.openContainer.detectAndSendChanges();
                    }
                }
            } else {
                if (player.getHeldItem(hand).isEmpty()) {
                    ItemStack stack = te.getEssenceStack();
                    te.setEssenceStack(ItemStack.EMPTY);
                    if (!player.inventory.addItemStackToInventory(stack)) {
                        EntityItem entityItem = new EntityItem(world, pos.getX(), pos.getY()+1, pos.getZ(), stack);
                        world.spawnEntity(entityItem);
                    } else {
                        player.openContainer.detectAndSendChanges();
                    }
                }
            }

        }

        // Return true also on the client to make sure that MC knows we handled this and will not try to place
        // a block on the client
        return true;
    }

    @Override
    public void breakBlock(World world, BlockPos pos, IBlockState state) {
        TERestorationApparatus te = getTE(world, pos);
        EntityItem entityItem = new EntityItem(world, pos.getX(), pos.getY()+1, pos.getZ(), te.getCrystalStack());
        world.spawnEntity(entityItem);
        EntityItem entityItemEssence = new EntityItem(world, pos.getX(), pos.getY()+1, pos.getZ(), te.getEssenceStack());
        world.spawnEntity(entityItemEssence);
    }

}
