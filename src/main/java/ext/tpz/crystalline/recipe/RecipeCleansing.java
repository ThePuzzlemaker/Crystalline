package ext.tpz.crystalline.recipe;

import ext.tpz.crystalline.item.CrystallineItems;
import ext.tpz.crystalline.util.Reference;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class RecipeCleansing implements IRecipe {

    @Nullable
    @Override
    public ResourceLocation getRegistryName() {
        return new ResourceLocation(Reference.MODID + ":cleansing_operation");
    }

    @Override
    public boolean matches(InventoryCrafting inv, World worldIn) {
        boolean crystalFound = false;
        boolean cleansingReagentFound = false;
        boolean otherItemFound = false;
        boolean valid = false;
        for (int i = 1; i < inv.getSizeInventory(); i++) {
            ItemStack s = inv.getStackInSlot(i);
            if (s.getItem() == CrystallineItems.crystal) {
                crystalFound = true;
            } else if (s.getItem() == CrystallineItems.cleansing_reagent) {
                cleansingReagentFound = true;
            } else if (!s.isEmpty()) {
                otherItemFound = true;
            }
        }
        if (!valid && cleansingReagentFound && crystalFound && !otherItemFound) {
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
        boolean crystalFound = false;
        boolean cleansingReagentFound = false;
        boolean otherItemFound = false;
        boolean valid = false;
        ItemStack crystal = ItemStack.EMPTY;
        for (int i = 1; i < inv.getSizeInventory(); i++) {
            ItemStack s = inv.getStackInSlot(i);
            if (s.getItem() == CrystallineItems.crystal) {
                crystalFound = true;
                crystal = s.copy();
            } else if (s.getItem() == CrystallineItems.cleansing_reagent) {
                cleansingReagentFound = true;
            } else if (!s.isEmpty()) {
                otherItemFound = true;
            }
        }
        if (!valid && cleansingReagentFound && crystalFound && !otherItemFound) {
            valid = true;
        } else if (!valid && otherItemFound) {
            valid = false;
        }
        if (valid) {
            CrystallineItems.crystal.setDrained(crystal, true);
            CrystallineItems.crystal.setPotential(crystal, 0);
            return crystal;
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
