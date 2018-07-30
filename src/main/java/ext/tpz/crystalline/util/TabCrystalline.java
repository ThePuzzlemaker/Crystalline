package ext.tpz.crystalline.util;

import ext.tpz.crystalline.api.crystal.CrystalRegistry;
import ext.tpz.crystalline.api.crystal.ICrystal;
import ext.tpz.crystalline.api.essence.liquid.EssenceLiquidRegistry;
import ext.tpz.crystalline.api.essence.liquid.IEssenceLiquid;
import ext.tpz.crystalline.api.essence.powder.EssencePowderRegistry;
import ext.tpz.crystalline.api.essence.powder.IEssencePowder;
import ext.tpz.crystalline.api.reagent.IReagent;
import ext.tpz.crystalline.api.reagent.ReagentRegistry;
import ext.tpz.crystalline.crystals.BaseModCrystals;
import ext.tpz.crystalline.item.CrystallineItems;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

import java.util.Iterator;

import static ext.tpz.crystalline.item.CrystallineItems.*;

public class TabCrystalline extends CreativeTabs {

    public TabCrystalline() {
        super("crystalline");
    }

    public ItemStack getTabIconItem() {
        ItemStack knowledge = new ItemStack(crystal);
        crystal.setType(knowledge, BaseModCrystals.knowledge_crystal);
        return knowledge;
    }

    // Dynamically add all items (crystals, bottles, powders, reagents, etc)
    @Override
    public void displayAllRelevantItems(NonNullList<ItemStack> stacks) {
        Iterator<ICrystal> crystals = CrystalRegistry.getRegistry().iterator();
        while (crystals.hasNext()) {
            ICrystal type = crystals.next();
            ItemStack crystalStack = new ItemStack(crystal);
            crystal.setType(crystalStack, type);
            crystal.setReagent(crystalStack, type.getReagentType());
            crystal.setPotential(crystalStack, 100);
            crystal.setDrained(crystalStack, false);
            stacks.add(crystalStack);
        }
        Iterator<IEssenceLiquid> liquids = EssenceLiquidRegistry.getRegistry().iterator();
        while (liquids.hasNext()) {
            IEssenceLiquid liquid = liquids.next();
            ItemStack liquidStack = new ItemStack(essence_bottle);
            essence_bottle.setType(liquidStack, liquid);
            stacks.add(liquidStack);
        }
        Iterator<IEssencePowder> powders = EssencePowderRegistry.getRegistry().iterator();
        while (powders.hasNext()) {
            IEssencePowder powder = powders.next();
            ItemStack powderStack = new ItemStack(essence_powder);
            essence_powder.setType(powderStack, powder);
            stacks.add(powderStack);
        }
        Iterator<IReagent> reagents = ReagentRegistry.getRegistry().iterator();
        while (reagents.hasNext()) {
            IReagent type = reagents.next();
            ItemStack reagentStack = new ItemStack(reagent);
            reagent.setType(reagentStack, type);
            stacks.add(reagentStack);
        }
        stacks.add(new ItemStack(rebinding_reagent));
        stacks.add(new ItemStack(cleansing_reagent));
        stacks.add(new ItemStack(cleansing_potion));
    }
}
