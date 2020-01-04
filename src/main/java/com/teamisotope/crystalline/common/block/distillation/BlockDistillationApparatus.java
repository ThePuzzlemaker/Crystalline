package com.teamisotope.crystalline.common.block.distillation;

import com.teamisotope.crystalline.api.CStatic;
import com.teamisotope.crystalline.client.tesrs.TESRTuningMachine;
import com.teamisotope.crystalline.common.Crystalline;
import com.teamisotope.crystalline.common.compat.top.TOPInfoProvider;
import com.teamisotope.crystalline.common.item.CItems;
import mcjty.theoneprobe.api.IProbeHitData;
import mcjty.theoneprobe.api.IProbeInfo;
import mcjty.theoneprobe.api.ProbeMode;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDoor;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.text.DecimalFormat;

public class BlockDistillationApparatus extends Block {

    public static final PropertyDirection FACING = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);
    public static final PropertyBool MBSECONDARY = PropertyBool.create("mbsecondary");

    public BlockDistillationApparatus() {
        super(Material.ROCK);
        setUnlocalizedName("crystalline.distillationapparatus").setRegistryName(CStatic.MODID, "distillationapparatus").setHardness(1.0f).setResistance(1.0f).setHarvestLevel("pickaxe", 1);
        setDefaultState(blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH).withProperty(MBSECONDARY, false));
        setCreativeTab(Crystalline.tab);
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, FACING, MBSECONDARY);
    }

    @Override
    public void onBlockHarvested(World worldIn, BlockPos pos, IBlockState state, EntityPlayer player) {
        BlockPos west = pos.west();
        BlockPos east = pos.east();
        BlockPos north = pos.north();
        BlockPos south = pos.south();
        switch (state.getValue(FACING)) {
            case NORTH:
                // Check W+E
                if (worldIn.getBlockState(west).getBlock() == this) {
                    worldIn.setBlockToAir(west);
                }
                if (worldIn.getBlockState(east).getBlock() == this) {
                    worldIn.setBlockToAir(east);
                }
                break;
            case SOUTH:
                // Check E+W
                if (worldIn.getBlockState(east).getBlock() == this) {
                    worldIn.setBlockToAir(east);
                }
                if (worldIn.getBlockState(west).getBlock() == this) {
                    worldIn.setBlockToAir(west);
                }
                break;
            case EAST:
                // Check N+S
                if (worldIn.getBlockState(north).getBlock() == this) {
                    worldIn.setBlockToAir(north);
                }
                if (worldIn.getBlockState(south).getBlock() == this) {
                    worldIn.setBlockToAir(south);
                }
                break;
            case WEST:
                // Check S+N
                if (worldIn.getBlockState(south).getBlock() == this) {
                    worldIn.setBlockToAir(south);
                }
                if (worldIn.getBlockState(north).getBlock() == this) {
                    worldIn.setBlockToAir(north);
                }
                break;
            default: break;
        }
    }

    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
        BlockPos west = pos.west();
        BlockPos east = pos.east();
        BlockPos north = pos.north();
        BlockPos south = pos.south();
        ItemStack newStack = stack.copy();
        newStack.setCount(1);
        switch (placer.getHorizontalFacing()) {
            case NORTH:
                // Check W+E
                if (worldIn.getBlockState(west).getBlock() == Blocks.AIR) {
                    worldIn.setBlockState(pos, state.withProperty(FACING, placer.getHorizontalFacing()).withProperty(MBSECONDARY, true), 2);
                    worldIn.setBlockState(west, state.withProperty(FACING, placer.getHorizontalFacing()).withProperty(MBSECONDARY, false), 2);
                    break;
                }
                if (worldIn.getBlockState(east).getBlock() == Blocks.AIR) {
                    worldIn.setBlockState(pos, state.withProperty(FACING, placer.getHorizontalFacing()).withProperty(MBSECONDARY, false), 2);
                    worldIn.setBlockState(east, state.withProperty(FACING, placer.getHorizontalFacing()).withProperty(MBSECONDARY, true), 2);
                    break;
                }
                worldIn.setBlockToAir(pos);
                if (placer instanceof EntityPlayer) {
                    EntityPlayer p = (EntityPlayer)placer;
                    if (p.getHeldItem(EnumHand.MAIN_HAND).equals(stack)) {
                        p.setHeldItem(EnumHand.MAIN_HAND, stack.copy());
                    } else if (p.getHeldItem(EnumHand.OFF_HAND).equals(stack)) {
                        p.setHeldItem(EnumHand.OFF_HAND, stack.copy());
                    }
                } else {
                    if (!worldIn.isRemote) {
                        worldIn.spawnEntity(new EntityItem(worldIn, pos.getX(), pos.getY() + 1, pos.getZ(), newStack.copy()));
                    }
                }
                break;
            case SOUTH:
                // Check E+W
                if (worldIn.getBlockState(east).getBlock() == Blocks.AIR) {
                    worldIn.setBlockState(pos, state.withProperty(FACING, placer.getHorizontalFacing()).withProperty(MBSECONDARY, true), 2);
                    worldIn.setBlockState(east, state.withProperty(FACING, placer.getHorizontalFacing()).withProperty(MBSECONDARY, false), 2);
                    break;
                }
                if (worldIn.getBlockState(west).getBlock() == Blocks.AIR) {
                    worldIn.setBlockState(pos, state.withProperty(FACING, placer.getHorizontalFacing()).withProperty(MBSECONDARY, false), 2);
                    worldIn.setBlockState(west, state.withProperty(FACING, placer.getHorizontalFacing()).withProperty(MBSECONDARY, true), 2);
                    break;
                }
                worldIn.setBlockToAir(pos);
                if (placer instanceof EntityPlayer) {
                    EntityPlayer p = (EntityPlayer)placer;
                    if (p.getHeldItem(EnumHand.MAIN_HAND).equals(stack)) {
                        p.setHeldItem(EnumHand.MAIN_HAND, stack.copy());
                    } else if (p.getHeldItem(EnumHand.OFF_HAND).equals(stack)) {
                        p.setHeldItem(EnumHand.OFF_HAND, stack.copy());
                    }
                } else {
                    if (!worldIn.isRemote) {
                        worldIn.spawnEntity(new EntityItem(worldIn, pos.getX(), pos.getY() + 1, pos.getZ(), newStack.copy()));
                    }
                }
                break;
            case EAST:
                // Check N+S
                if (worldIn.getBlockState(north).getBlock() == Blocks.AIR) {
                    worldIn.setBlockState(pos, state.withProperty(FACING, placer.getHorizontalFacing()).withProperty(MBSECONDARY, true), 2);
                    worldIn.setBlockState(north, state.withProperty(FACING, placer.getHorizontalFacing()).withProperty(MBSECONDARY, false), 2);
                    break;
                }
                if (worldIn.getBlockState(south).getBlock() == Blocks.AIR) {
                    worldIn.setBlockState(pos, state.withProperty(FACING, placer.getHorizontalFacing()).withProperty(MBSECONDARY, false), 2);
                    worldIn.setBlockState(south, state.withProperty(FACING, placer.getHorizontalFacing()).withProperty(MBSECONDARY, true), 2);
                    break;
                }
                worldIn.setBlockToAir(pos);
                if (placer instanceof EntityPlayer) {
                    EntityPlayer p = (EntityPlayer)placer;
                    if (p.getHeldItem(EnumHand.MAIN_HAND).equals(stack)) {
                        p.setHeldItem(EnumHand.MAIN_HAND, stack.copy());
                    } else if (p.getHeldItem(EnumHand.OFF_HAND).equals(stack)) {
                        p.setHeldItem(EnumHand.OFF_HAND, stack.copy());
                    }
                } else {
                    if (!worldIn.isRemote) {
                        worldIn.spawnEntity(new EntityItem(worldIn, pos.getX(), pos.getY() + 1, pos.getZ(), newStack.copy()));
                    }
                }
                break;
            case WEST:
                // Check S+N
                if (worldIn.getBlockState(south).getBlock() == Blocks.AIR) {
                    worldIn.setBlockState(pos, state.withProperty(FACING, placer.getHorizontalFacing()).withProperty(MBSECONDARY, true), 2);
                    worldIn.setBlockState(south, state.withProperty(FACING, placer.getHorizontalFacing()).withProperty(MBSECONDARY, false), 2);
                    break;
                }
                if (worldIn.getBlockState(north).getBlock() == Blocks.AIR) {
                    worldIn.setBlockState(pos, state.withProperty(FACING, placer.getHorizontalFacing()).withProperty(MBSECONDARY, false), 2);
                    worldIn.setBlockState(north, state.withProperty(FACING, placer.getHorizontalFacing()).withProperty(MBSECONDARY, true), 2);
                    break;
                }
                worldIn.setBlockToAir(pos);
                if (placer instanceof EntityPlayer) {
                    EntityPlayer p = (EntityPlayer)placer;
                    if (p.getHeldItem(EnumHand.MAIN_HAND).equals(stack)) {
                        p.setHeldItem(EnumHand.MAIN_HAND, stack.copy());
                    } else if (p.getHeldItem(EnumHand.OFF_HAND).equals(stack)) {
                        p.setHeldItem(EnumHand.OFF_HAND, stack.copy());
                    }
                } else {
                    if (!worldIn.isRemote) {
                        worldIn.spawnEntity(new EntityItem(worldIn, pos.getX(), pos.getY() + 1, pos.getZ(), newStack.copy()));
                    }
                }
                break;
            default: break;
        }
    }



    // looks like these are necessary ._.
    @Override
    public int getMetaFromState(IBlockState state) {
        return ((state.getValue(FACING).getIndex() - 2) << 1) ^ (state.getValue(MBSECONDARY) ? 1 : 0);
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        return getDefaultState()
                .withProperty(FACING, EnumFacing.getFront((meta >> 1)+2)).withProperty(MBSECONDARY, (1 & meta) == 1);
    }

    @Override
    public BlockRenderLayer getBlockLayer() {
        return BlockRenderLayer.CUTOUT;
    }

    @Override
    public boolean isFullCube(IBlockState state) {
        return false;
    }

    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    @Override
    public boolean canPlaceBlockAt(World worldIn, BlockPos pos) {
        if (pos.getY() >= worldIn.getHeight() - 1) {
            return false;
        } else {
            IBlockState state = worldIn.getBlockState(pos.down());
            return (state.isTopSolid() || state.getBlockFaceShape(worldIn, pos.down(), EnumFacing.UP) == BlockFaceShape.SOLID) && super.canPlaceBlockAt(worldIn, pos) && super.canPlaceBlockAt(worldIn, pos.up());
        }
    }

    @SideOnly(Side.CLIENT)
    public void initModel() {
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), 0, new ModelResourceLocation(getRegistryName(), "inventory"));
    }

}
