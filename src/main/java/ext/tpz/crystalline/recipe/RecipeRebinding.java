package ext.tpz.crystalline.recipe;

import ext.tpz.crystalline.item.CrystallineItems;
import ext.tpz.crystalline.util.Reference;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class RecipeRebinding implements IRecipe {

    @Nullable
    @Override
    public ResourceLocation getRegistryName() {
        return new ResourceLocation(Reference.MODID + ":rebinding_operation");
    }

    @Override
    public boolean matches(InventoryCrafting inv, World worldIn) {
        boolean rebindingReagentFound = false;
        boolean crystalFound = false;
        boolean otherItemFound = false;
        boolean valid = false;
        for (int i = 1; i < inv.getSizeInventory(); i++) {
            ItemStack s = inv.getStackInSlot(i);
            if (s.getItem() == CrystallineItems.rebinding_reagent) {
                rebindingReagentFound = true;
            } else if (s.getItem() == CrystallineItems.crystal && CrystallineItems.crystal.getType(s).hasBinding()) {
                crystalFound = true;
            } else if (!s.isEmpty()) {
                otherItemFound = true;
            }
        }
        if (!valid && rebindingReagentFound && crystalFound && !otherItemFound) {
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
        boolean rebindingReagentFound = false;
        boolean crystalFound = false;
        boolean otherItemFound = false;
        ItemStack crystal = ItemStack.EMPTY;
        boolean valid = false;
        ItemStack reagent = ItemStack.EMPTY;
        for (int i = 1; i < inv.getSizeInventory(); i++) {
            ItemStack s = inv.getStackInSlot(i);
            if (s.getItem() == CrystallineItems.rebinding_reagent) {
                rebindingReagentFound = true;
                reagent = s.copy();
            } else if (s.getItem() == CrystallineItems.crystal && CrystallineItems.crystal.getType(s).hasBinding()) {
                crystalFound = true;
                crystal = s.copy();
            } else if (!s.isEmpty()) {
                otherItemFound = true;
            }
        }
        if (!valid && rebindingReagentFound && crystalFound && !otherItemFound) {
            valid = true;
        } else if (!valid && otherItemFound) {
            valid = false;
        }
        if (valid) {
            CrystallineItems.crystal.setBound(crystal, CrystallineItems.rebinding_reagent.getBound(reagent));
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
        return new ItemStack(CrystallineItems.rebinding_reagent);
    }

}
