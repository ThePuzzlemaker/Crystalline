package com.teamisotope.crystalline.common.block.am;

import com.teamisotope.crystalline.api.CStatic;
import com.teamisotope.crystalline.common.Crystalline;
import com.teamisotope.crystalline.common.item.CItems;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Random;

public class BlockArcanumMinerale extends Block {

    public BlockArcanumMinerale() {
        super(Material.ROCK);
        setUnlocalizedName("crystalline.arcanumminerale").setRegistryName(CStatic.MODID, "arcanumminerale").setHardness(3.0f).setResistance(15.0f).setHarvestLevel("pickaxe", 3);
        setCreativeTab(Crystalline.tab);
    }

    @Override
    public void dropBlockAsItemWithChance(World worldIn, BlockPos pos, IBlockState state, float chance, int fortune) {
        super.dropBlockAsItemWithChance(worldIn, pos, state, chance, fortune);
    }

    @Override
    public int quantityDroppedWithBonus(int fortune, Random random) {
        if (fortune > 0 && Item.getItemFromBlock(this) != this.getItemDropped((IBlockState)this.getBlockState().getValidStates().iterator().next(), random, fortune))
        {
            int i = random.nextInt(fortune + 2) - 1;

            if (i < 0)
            {
                i = 0;
            }

            return this.quantityDropped(random) * (i + 1);
        }
        else
        {
            return this.quantityDropped(random);
        }
    }

    @Override
    public int quantityDropped(Random random) {
        return 2 + random.nextInt(2);
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return CItems.arcanumMineraleCrystal;
    }

    @SideOnly(Side.CLIENT)
    public void initModel() {
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), 0, new ModelResourceLocation(getRegistryName(), "inventory"));
    }

}