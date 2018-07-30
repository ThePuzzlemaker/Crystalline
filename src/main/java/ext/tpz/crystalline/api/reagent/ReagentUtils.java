package ext.tpz.crystalline.api.reagent;

import ext.tpz.crystalline.insanity.InsanityWorldSavedData;
import ext.tpz.crystalline.item.CrystallineItems;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextComponentString;

import java.util.Iterator;
import java.util.List;
import java.util.UUID;

public class ReagentUtils {

    public static IReagent from(String id) {
        return ReagentRegistry.getRegistry().getValue(new ResourceLocation(id));
    }

    public static String to(IReagent reagent) {
        return reagent.getRegistryName().toString();
    }

    public static boolean consume(IReagent reagent, EntityPlayer player, ItemStack crystalStack) {
        List<Slot> slots = player.inventoryContainer.inventorySlots;
        Iterator<Slot> i = slots.iterator();
        int lowestReagentFound = 0;
        int minValue = reagent.getValue();
        ItemStack bestReagent = ItemStack.EMPTY;
        UUID uuid = player.getUniqueID();
        Slot slot = null;
        while (i.hasNext()) {
            Slot sl = i.next();
            ItemStack s = ItemStack.EMPTY;
            if (sl.getHasStack()) {
                s = sl.getStack();
            } else {
                continue;
            }
            if (!s.isEmpty()) {
                if (s.getItem() == CrystallineItems.reagent) {
                    IReagent value = CrystallineItems.reagent.getType(s);
                    if (value.getValue() >= minValue) {
                        if (value.getValue() < lowestReagentFound || lowestReagentFound == 0) {
                            lowestReagentFound = value.getValue();
                            bestReagent = s;
                            slot = sl;
                        } else {
                            continue;
                        }
                    } else {
                        continue;
                    }
                } else {
                    continue;
                }
                break;
            }
        } if (bestReagent != ItemStack.EMPTY) {
            if (slot.getStack().getCount() == 1) {
                slot.putStack(ItemStack.EMPTY);
            } else {
                slot.decrStackSize(1);
            }
            if (reagent.isUnstable()) {
                InsanityWorldSavedData data = InsanityWorldSavedData.get(player.getEntityWorld());
                data.setPlayer(player.getUniqueID(), 101);
                InsanityWorldSavedData.set(data, player.getEntityWorld());
            }
            return true;
        } else {
            player.sendStatusMessage(new TextComponentString("No valid reagents were found in your inventory!"), true);
            return false;
        }
    }

}
