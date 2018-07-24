package ext.tpz.crystalline.block;

import ext.tpz.crystalline.Crystalline;
import ext.tpz.crystalline.util.Reference;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

import javax.annotation.Nullable;

public class BlockRestorationApparatus extends Block {

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
}
