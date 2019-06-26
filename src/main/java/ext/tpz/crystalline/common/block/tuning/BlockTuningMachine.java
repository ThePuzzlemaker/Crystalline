package ext.tpz.crystalline.common.block.tuning;

import ext.tpz.crystalline.api.CStatic;
import ext.tpz.crystalline.client.tesrs.TESRTuningMachine;
import ext.tpz.crystalline.common.Crystalline;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;

public class BlockTuningMachine extends Block implements ITileEntityProvider {

    public static final PropertyDirection FACING = PropertyDirection.create("facing");
    public static final PropertyBool OPEN = PropertyBool.create("open");

    public static final int GUI_ID = 1;

    public BlockTuningMachine() {
        super(Material.IRON);
        setUnlocalizedName("crystalline.tuningmachine").setRegistryName(CStatic.MODID, "tuningmachine").setHardness(1.0f).setResistance(1.0f).setHarvestLevel("pickaxe", 1);
        setDefaultState(blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH).withProperty(OPEN, false));
        setCreativeTab(Crystalline.tab);
    }

    @Nullable
    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TETuningMachine();
    }

    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (world.isRemote)
            return true;

        TileEntity te = world.getTileEntity(pos);
        if (!(te instanceof TETuningMachine))
            return false;
        player.openGui(Crystalline.instance, GUI_ID, world, pos.getX(), pos.getY(), pos.getZ());
        world.setBlockState(pos, state.withProperty(OPEN, true), 2);
        return true;
    }

    public static EnumFacing getFacingFromEntity(BlockPos clickedBlock, EntityLivingBase entity) {
        return EnumFacing.getFacingFromVector(
                (float) (entity.posX - clickedBlock.getX()),
                (float) (entity.posY - clickedBlock.getY()),
                (float) (entity.posZ - clickedBlock.getZ()));
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, FACING, OPEN);
    }

    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
        worldIn.setBlockState(pos, state.withProperty(FACING, getFacingFromEntity(pos, placer)).withProperty(OPEN, false), 2);
    }

    // looks like these are necessary ._.
    @Override
    public int getMetaFromState(IBlockState state) {
        return state.getValue(FACING).getIndex() + (state.getValue(OPEN) ? 8 : 0);
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        return getDefaultState()
                .withProperty(FACING, EnumFacing.getFront(meta & 7))
                .withProperty(OPEN, (meta & 8) != 0);
    }

    @Override
    public BlockRenderLayer getBlockLayer() {
        return BlockRenderLayer.CUTOUT;
    }

    @SideOnly(Side.CLIENT)
    public void initModel() {
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), 0, new ModelResourceLocation(getRegistryName(), "inventory"));
        ClientRegistry.bindTileEntitySpecialRenderer(TETuningMachine.class, new TESRTuningMachine());
    }

    @Override
    public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
        TileEntity tileentity = worldIn.getTileEntity(pos);
        if (tileentity instanceof TETuningMachine)
        {
            TETuningMachine te = (TETuningMachine)tileentity;
            for (int i = 0; i < te.getItemStackHandler().getSlots(); i++ ) {
                EntityItem entityItem = new EntityItem(worldIn, pos.getX(), pos.getY(), pos.getZ(), te.getItemStackHandler().getStackInSlot(i));
                worldIn.spawnEntity(entityItem);
            }
        }
        super.breakBlock(worldIn, pos, state);
    }
}
