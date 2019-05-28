package ext.tpz.crystalline.recipe;

import ext.tpz.crystalline.crystals.BaseModCrystals;
import ext.tpz.crystalline.item.CrystallineItems;
import ext.tpz.crystalline.util.Reference;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class RecipeBinding implements IRecipe {

    @Nullable
    @Override
    public ResourceLocation getRegistryName() {
        return new ResourceLocation(Reference.MODID + ":binding_operation");
    }

    @Override
    public boolean matches(InventoryCrafting inv, World worldIn) {
        boolean bindingCrystalFound = false;
        boolean lifeCrystalFound = false;
        boolean otherItemFound = false;
        boolean valid = false;
        for (int i = 1; i < inv.getSizeInventory(); i++) {
            ItemStack s = inv.getStackInSlot(i);
            if (s.getItem() == CrystallineItems.crystal && CrystallineItems.crystal.getType(s) == BaseModCrystals.life_crystal) {
                lifeCrystalFound = true;
            } else if (s.getItem() == CrystallineItems.crystal && CrystallineItems.crystal.getType(s).hasBinding()) {
                bindingCrystalFound = true;
            } else if (!s.isEmpty()) {
                otherItemFound = true;
            }
        }
        if (!valid && lifeCrystalFound && bindingCrystalFound && !otherItemFound) {
            valid = true;
        } else if (!valid && otherItemFound) {
            valid = false;
        }
        return valid;
    }

    @Override
    public IRecipe setRegistryName(ResourceLocation name) {
        return this;
    }

    @Override
    public boolean canFit(int width, int height) {
        if (width >= 2 && height >= 2) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public ItemStack getCraftingResult(InventoryCrafting inv) {
        boolean bindingCrystalFound = false;
        boolean lifeCrystalFound = false;
        boolean otherItemFound = false;
        boolean valid = false;
        ItemStack life = ItemStack.EMPTY;
        ItemStack binding = ItemStack.EMPTY;
        for (int i = 1; i < inv.getSizeInventory(); i++) {
            ItemStack s = inv.getStackInSlot(i);
            if (s.getItem() == CrystallineItems.crystal && CrystallineItems.crystal.getType(s) == BaseModCrystals.life_crystal) {
                lifeCrystalFound = true;
                life = s.copy();
            } else if (s.getItem() == CrystallineItems.crystal && CrystallineItems.crystal.getType(s).hasBinding()) {
                bindingCrystalFound = true;
                binding = s.copy();
            } else if (!s.isEmpty()) {
                otherItemFound = true;
            }
        }
        if (!valid && lifeCrystalFound && bindingCrystalFound && !otherItemFound) {
            valid = true;
        } else if (!valid && otherItemFound) {
            valid = false;
        }
        if (valid) {
            CrystallineItems.crystal.setBound(binding, CrystallineItems.crystal.getBound(life));
            return binding;
        } else {
            return ItemStack.EMPTY;
        }
    }

    @Override
    public Class<IRecipe> getRegistryType() {
        return IRecipe.class;
    }

    @Override
    public ItemStack getRecipeOutput() {
        return new ItemStack(CrystallineItems.crystal);
    }

}
