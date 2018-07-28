package ext.tpz.crystalline.item.dynamic;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import ext.tpz.crystalline.api.crystal.CrystalRegistry;
import ext.tpz.crystalline.api.crystal.CrystalUtils;
import ext.tpz.crystalline.api.crystal.ICrystal;
import ext.tpz.crystalline.api.mode.ICrystalMode;
import ext.tpz.crystalline.api.mode.ModeUtils;
import ext.tpz.crystalline.api.reagent.IReagent;
import ext.tpz.crystalline.api.reagent.ReagentUtils;
import ext.tpz.crystalline.crystals.BaseModCrystals;
import ext.tpz.crystalline.entity.EntityObliterateBlock;
import ext.tpz.crystalline.entity.EntityObliterateEntity;
import ext.tpz.crystalline.insanity.InsanityWorldSavedData;
import ext.tpz.crystalline.item.CrystallineItems;
import ext.tpz.crystalline.item.EnumCrystalModes;
import ext.tpz.crystalline.item.EnumCrystalTypes;
import ext.tpz.crystalline.item.EnumReagentTypes;
import ext.tpz.crystalline.modes.BaseModModes;
import ext.tpz.crystalline.reagents.BaseModReagents;
import ext.tpz.crystalline.util.Reference;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.tileentity.TileEntityItemStackRenderer;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityItemFrame;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.*;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.client.model.Attributes;
import net.minecraftforge.client.model.IModel;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.client.model.obj.OBJLoader;
import net.minecraftforge.client.model.obj.OBJModel;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.*;
import java.util.function.Function;

public class ItemCrystal extends Item {

    public ItemCrystal() {
        this.setRegistryName("crystal").setUnlocalizedName(Reference.MODID + ".crystal").setMaxStackSize(1);
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        super.addInformation(stack, worldIn, tooltip, flagIn);
        ICrystal type = getType(stack);
        String textType = I18n.format("crystalline.lore.crystal.type", I18n.format(type.getUnlocalizedName()));
        String insanity = I18n.format("crystalline.lore.crystal.insanity", I18n.format(type.causesInsanity() ? "crystalline.i18n.can" : "crystalline.i18n.doesnot"));
        String bound = I18n.format("crystalline.lore.crystal.bound", type.hasBinding() ? getBound(stack) : "");

        tooltip.add(TextFormatting.RESET + textType);
        tooltip.add(TextFormatting.RESET + I18n.format("crystal.drained", isDrained(stack) ? I18n.format("i18n.yes") : I18n.format("i18n.no")));
        tooltip.add(TextFormatting.RESET + insanity);
        tooltip.add(TextFormatting.RESET + I18n.format("crystal.properties"));
        tooltip.add(TextFormatting.RESET + "  " + I18n.format("crystal.potential", Integer.toString(getPotential(stack)) + "%"));
        if (type.hasBinding()) { tooltip.add(TextFormatting.RESET + "  " + bound); }
        IReagent reagent = getReagent(stack);
        String textReagent = "  " + I18n.format("crystal.reagent", I18n.format(reagent.getUnlocalizedName()));
        tooltip.add(TextFormatting.RESET + textReagent);
    }

    public ICrystal getType(ItemStack stack) {
        if (stack.hasTagCompound()) {
            if (stack.getTagCompound().hasKey("type")) {
                return CrystalUtils.from(stack.getTagCompound().getString("type"));
            } else {
                setType(stack, BaseModCrystals.null_crystal);
                return BaseModCrystals.null_crystal;
            }
        } else {
            setType(stack, BaseModCrystals.null_crystal);
            return BaseModCrystals.null_crystal;
        }
    }

    public void setType(ItemStack stack, ICrystal type) {
        if (stack.hasTagCompound()) {
            stack.getTagCompound().setString("type", CrystalUtils.to(type));
        } else {
            NBTTagCompound tmp = new NBTTagCompound();
            tmp.setString("type", CrystalUtils.to(type));
            stack.setTagCompound(tmp);
        }
    }

    public IReagent getReagent(ItemStack stack) {
        if (stack.hasTagCompound()) {
            if (stack.getTagCompound().hasKey("reagent")) {
                return ReagentUtils.from(stack.getTagCompound().getString("reagent"));
            } else {
                setReagent(stack, BaseModReagents.reagent_null);
                return BaseModReagents.reagent_null;
            }
        } else {
            setReagent(stack, BaseModReagents.reagent_null);
            return BaseModReagents.reagent_null;
        }
    }

    public void setReagent(ItemStack stack, IReagent reagent) {
        if (stack.hasTagCompound()) {
            stack.getTagCompound().setString("reagent", ReagentUtils.to(reagent));
        } else {
            NBTTagCompound tmp = new NBTTagCompound();
            tmp.setString("reagent", ReagentUtils.to(reagent));
            stack.setTagCompound(tmp);
        }
    }


    public boolean isDrained(ItemStack stack) {
        if (stack.hasTagCompound()) {
            if (stack.getTagCompound().hasKey("drained")) {
                return stack.getTagCompound().getBoolean("drained");
            } else {
                setDrained(stack, false);
                return false;
            }
        } else {
            setDrained(stack, false);
            return false;
        }
    }

    public void setDrained(ItemStack stack, boolean drained) {
        if (stack.hasTagCompound()) {
            stack.getTagCompound().setBoolean("drained", drained);
        } else {
            NBTTagCompound tmp = new NBTTagCompound();
            tmp.setBoolean("type", drained);
            stack.setTagCompound(tmp);
        }
    }

    public int getPotential(ItemStack stack) {
        if (stack.hasTagCompound()) {
            if (stack.getTagCompound().hasKey("potential")) {
                return stack.getTagCompound().getInteger("potential");
            } else {
                setPotential(stack, 100);
                return 100;
            }
        } else {
            setPotential(stack, 100);
            return 100;
        }
    }

    public void setPotential(ItemStack stack, int potential) {
        if (stack.hasTagCompound()) {
            stack.getTagCompound().setInteger("potential", potential);
        } else {
            NBTTagCompound tmp = new NBTTagCompound();
            tmp.setInteger("potential", potential);
            stack.setTagCompound(tmp);
        }
    }

    public ICrystalMode getMode(ItemStack stack) {
        if (stack.hasTagCompound()) {
            if (stack.getTagCompound().hasKey("mode")) {
                return ModeUtils.from(stack.getTagCompound().getString("mode"));
            } else {
                setMode(stack, BaseModModes.mode_null);
                return BaseModModes.mode_null;
            }
        } else {
            setMode(stack, BaseModModes.mode_null);
            return BaseModModes.mode_null;
        }
    }

    public void setMode(ItemStack stack, ICrystalMode mode) {
        if (stack.hasTagCompound()) {
            stack.getTagCompound().setString("mode", ModeUtils.to(mode));
        } else {
            NBTTagCompound tmp = new NBTTagCompound();
            tmp.setString("mode", ModeUtils.to(mode));
            stack.setTagCompound(tmp);
        }
    }

    public ICrystalMode cycleMode(ItemStack stack) {
        ICrystal type = getType(stack);
        ICrystalMode currentMode = getMode(stack);
        List<ICrystalMode> modes = type.getModes();
        int currentModeId = modes.indexOf(currentMode);
        int nextModeId = currentModeId+1 <= modes.size() ? currentModeId+1 : 0;
        ICrystalMode nextMode = modes.get(nextModeId);
        setMode(stack, nextMode);
        return nextMode;
    }

    public boolean consumeReagent(EnumReagentTypes type, EntityPlayer player, ItemStack stack) {
        List<Slot> slots = player.inventoryContainer.inventorySlots;
        Iterator<Slot> i = slots.iterator();
        // 0: none
        // 1: basic
        // 2: advanced
        // 3: extreme
        // 4: rift
        // 5: universe
        int lowestReagentFound = 0;
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
                switch (type) {
                    case BASIC:
                        if (s.getItem() == CrystallineItems.reagent_basic) {
                            if (1<lowestReagentFound||lowestReagentFound==0) {
                                lowestReagentFound = 1;
                                bestReagent = s;
                                slot = sl;
                            }
                        } else if (s.getItem() == CrystallineItems.reagent_advanced) {
                            if (2<lowestReagentFound||lowestReagentFound==0) {
                                lowestReagentFound = 2;
                                bestReagent = s;
                                slot = sl;
                            }
                        } else if (s.getItem() == CrystallineItems.reagent_extreme) {
                            if (3<lowestReagentFound||lowestReagentFound==0) {
                                lowestReagentFound = 3;
                                bestReagent = s;
                                slot = sl;
                            }
                        } else if (s.getItem() == CrystallineItems.reagent_rift) {
                            if (4<lowestReagentFound||lowestReagentFound==0) {
                                lowestReagentFound = 4;
                                bestReagent = s;
                                slot = sl;
                            }
                        } else if (s.getItem() == CrystallineItems.reagent_universe) {
                            if (lowestReagentFound==0) {
                                lowestReagentFound = 5;
                                bestReagent = s;
                                slot = sl;
                            }
                        } else {
                            continue;
                        }
                        break;
                    case ADVANCED:
                        if (s.getItem() == CrystallineItems.reagent_advanced) {
                            if (2<lowestReagentFound||lowestReagentFound==0) {
                                lowestReagentFound = 2;
                                bestReagent = s;
                                slot = sl;
                            }
                        } else if (s.getItem() == CrystallineItems.reagent_extreme) {
                            if (3<lowestReagentFound||lowestReagentFound==0) {
                                lowestReagentFound = 3;
                                bestReagent = s;
                                slot = sl;
                            }
                        } else if (s.getItem() == CrystallineItems.reagent_rift) {
                            if (4<lowestReagentFound||lowestReagentFound==0) {
                                lowestReagentFound = 4;
                                bestReagent = s;
                                slot = sl;
                            }
                        } else if (s.getItem() == CrystallineItems.reagent_universe) {
                            if (lowestReagentFound==0) {
                                lowestReagentFound = 5;
                                bestReagent = s;
                                slot = sl;
                            }
                        } else {
                            continue;
                        }
                        break;
                    case EXTREME:
                        if (s.getItem() == CrystallineItems.reagent_extreme) {
                            if (3<lowestReagentFound||lowestReagentFound==0) {
                                lowestReagentFound = 3;
                                bestReagent = s;
                                slot = sl;
                            }
                        } else if (s.getItem() == CrystallineItems.reagent_rift) {
                            if (4<lowestReagentFound||lowestReagentFound==0) {
                                lowestReagentFound = 4;
                                bestReagent = s;
                                slot = sl;
                            }
                        } else if (s.getItem() == CrystallineItems.reagent_universe) {
                            if (lowestReagentFound==0) {
                                lowestReagentFound = 5;
                                bestReagent = s;
                                slot = sl;
                            }
                        } else {
                            continue;
                        }
                        break;
                    case RIFT:
                        if (s.getItem() == CrystallineItems.reagent_rift) {
                            if (4<lowestReagentFound||lowestReagentFound==0) {
                                lowestReagentFound = 4;
                                bestReagent = s;
                                slot = sl;
                            }
                        } else if (s.getItem() == CrystallineItems.reagent_universe) {
                            if (lowestReagentFound==0) {
                                lowestReagentFound = 5;
                                bestReagent = s;
                                slot = sl;
                                InsanityWorldSavedData data = InsanityWorldSavedData.get(player.getEntityWorld());
                                if (data.getPlayer(uuid) + 1 < 100) {
                                    data.setPlayer(uuid, data.getPlayer(uuid) + 1);
                                } else {
                                    data.setPlayer(uuid, 100);
                                }
                            }
                        } else {
                            continue;
                        }
                        break;
                    case UNIVERSE:
                        if (s.getItem() == CrystallineItems.reagent_universe) {
                            if (lowestReagentFound==0) {
                                lowestReagentFound = 5;
                                bestReagent = s;
                                slot = sl;
                                InsanityWorldSavedData data = InsanityWorldSavedData.get(player.getEntityWorld());
                                if (data.getPlayer(uuid) + 1 < 100) {
                                    data.setPlayer(uuid, data.getPlayer(uuid) + 1);
                                } else {
                                    data.setPlayer(uuid, 100);
                                }
                            }
                        } else {
                            continue;
                        }
                        break;
                    case NONE:
                        break;
                    case VOID:
                        break;
                }
            }
        }
        if (bestReagent != ItemStack.EMPTY) {
            if (slot.getStack().getCount() == 1) {
                slot.putStack(ItemStack.EMPTY);
            } else {
                slot.decrStackSize(1);
            }
            if (lowestReagentFound == 4) {
                InsanityWorldSavedData data = InsanityWorldSavedData.get(player.getEntityWorld());
                data.setPlayer(player.getUniqueID(), 101);
                InsanityWorldSavedData.set(data, player.getEntityWorld());
            }
        } else {
            player.sendStatusMessage(new TextComponentString("No valid reagents were found in your inventory!"), true);
            return false;
        }
        return true;
    }


    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
        ItemStack stack = player.getHeldItem(hand);
        if (!isDrained(stack) && getPotential(stack) > 0) switch (getType(stack)) {
            case "cleansing":
                if (consumeReagent(EnumReagentTypes.BASIC, player, stack)) {
                    BlockPos pos = player.getPosition();
                    ItemStack s = new ItemStack(CrystallineItems.cleansing_reagent, 1);
                    if ((getPotential(stack) - 10) > 0) {
                        setPotential(stack, getPotential(stack) - 10);
                    } else {
                        player.sendStatusMessage(new TextComponentString(TextFormatting.RED + "Not enough potential!"), true);
                        return new ActionResult<ItemStack>(EnumActionResult.FAIL, stack);
                    }
                    if (!player.inventory.addItemStackToInventory(s)) {
                        EntityItem item = new EntityItem(world, pos.getX(), pos.getY(), pos.getZ(), s);
                        world.spawnEntity(item);
                    } else {
                        player.openContainer.detectAndSendChanges();
                    }

                    UUID uuid = player.getUniqueID();
                    InsanityWorldSavedData data = InsanityWorldSavedData.get(player.getEntityWorld());
                    if (data.getPlayer(uuid) + 1 < 100) {
                        data.setPlayer(uuid, data.getPlayer(uuid) + 1);
                    } else {
                        data.setPlayer(uuid, 100);
                    }
                }
                break;
            case "administration":
                EntityPlayer target = world.getPlayerEntityByName(getBound(stack));
                if (target != null) {
                    if (player.isSneaking()) {
                        if (consumeReagent(EnumReagentTypes.EXTREME, player, stack)) {
                            if ((getPotential(stack) - 10) >= 0) {
                                setPotential(stack, getPotential(stack) - 10);
                            } else {
                                player.sendStatusMessage(new TextComponentString(TextFormatting.RED + "Not enough potential!"), true);
                                return new ActionResult<ItemStack>(EnumActionResult.FAIL, stack);
                            }
                            target.setHealth(target.getHealth() - 2.5f);
                            UUID uuid = player.getUniqueID();
                            InsanityWorldSavedData data = InsanityWorldSavedData.get(player.getEntityWorld());
                            if (data.getPlayer(uuid)+25 < 100) {
                                data.setPlayer(uuid, data.getPlayer(uuid) + 25);
                            } else {
                                data.setPlayer(uuid, 100);
                            }
                            InsanityWorldSavedData.set(data, world);
                        }
                    } else {
                        if (consumeReagent(EnumReagentTypes.EXTREME, player, stack)) {
                            double strength = 0.5D;
                            if ((getPotential(stack) - 5) >= 0) {
                                setPotential(stack, getPotential(stack) - 5);
                            } else {
                                player.sendStatusMessage(new TextComponentString(TextFormatting.RED + "Not enough potential!"), true);
                                return new ActionResult<ItemStack>(EnumActionResult.FAIL, stack);
                            }
                            switch(player.getHorizontalFacing()) {
                                case UP:
                                    target.setVelocity(0.0D, strength, 0.0D);
                                    break;
                                case DOWN:
                                    target.setVelocity(0.0D, -strength, 0.0D);
                                    break;
                                case EAST:
                                    target.setVelocity(strength, (-player.rotationPitch)/100, 0.0D);
                                    break;
                                case WEST:
                                    target.setVelocity(-strength, (-player.rotationPitch)/100, 0.0D);
                                    break;
                                case NORTH:
                                    target.setVelocity(0.0D, (-player.rotationPitch)/100, -strength);
                                    break;
                                case SOUTH:
                                    target.setVelocity(0.0D, (-player.rotationPitch)/100, strength);
                                    break;
                            }
                            UUID uuid = player.getUniqueID();
                            InsanityWorldSavedData data = InsanityWorldSavedData.get(player.getEntityWorld());
                            if (data.getPlayer(uuid)+25 < 100) {
                                data.setPlayer(uuid, data.getPlayer(uuid) + 5);
                            } else {
                                data.setPlayer(uuid, 100);
                            }
                            InsanityWorldSavedData.set(data, world);
                        }
                    }
                }
                break;

        }
        else {
            player.sendStatusMessage(new TextComponentString(TextFormatting.RED + "This crystal is drained of potential!"), true);
            return ActionResult.newResult(EnumActionResult.FAIL, stack);
        }
        if (getPotential(stack) == 0) {
            setDrained(stack, true);
        }
        return ActionResult.newResult(EnumActionResult.SUCCESS, stack);
    }

    @SideOnly(Side.CLIENT)
    public void initModel() {
        ModelResourceLocation knowledge = new ModelResourceLocation(getRegistryName() + "_knowledge", "inventory");
        ModelResourceLocation administration = new ModelResourceLocation(getRegistryName() + "_administration", "inventory");
        ModelResourceLocation cleansing = new ModelResourceLocation(getRegistryName() + "_cleansing", "inventory");
        ModelResourceLocation life = new ModelResourceLocation(getRegistryName() + "_life", "inventory");
        ModelResourceLocation universe = new ModelResourceLocation(getRegistryName() + "_universe", "inventory");
        ModelResourceLocation unknown = new ModelResourceLocation(getRegistryName() + "_unknown", "inventory");
        ModelResourceLocation drained = new ModelResourceLocation(Reference.CRYSTAL_MODEL_BASE + ".drained", "inventory");
        ModelResourceLocation rift = new ModelResourceLocation(getRegistryName() + "_rift", "inventory");

        Iterator<ICrystal> iterator  = CrystalRegistry.getRegistry().iterator();

        while (iterator.hasNext()) {
            ICrystal crystal = iterator.next();
            ModelBakery.registerItemVariants(this, crystal.getModel());
        }

        ModelBakery.registerItemVariants(this, knowledge, administration, cleansing, life, universe, unknown, drained, rift);

        ModelLoader.setCustomMeshDefinition(this, new ItemMeshDefinition() {
            @Override
            public ModelResourceLocation getModelLocation(ItemStack stack) {
                if (isDrained(stack))
                    return drained;
                return getType(stack).getModel();
            }
        });
    }

    String theIntellijDebuggerIsAnnoyingSoIAddedThisMethodToEditSoTheClassesAreReloadedWithoutAnyChanges() {
        // If this is in an actual release, blame IntelliJ.
        String a = "This is so stupid.";
        String b = "Here you see my ramblings as I try to get this stupid thing to work.";
        String c = "Am I done yet?";
        String d = "When will this work?";
        String e = "Getting closer...";
        String f = "Almost there...";
        String g = "Nearing the end...";
        String h = "Some more testing...";
        String i = "Come on";
        return a + " " + b + " " + c + " " + d + " " + e + " " + f + " " + g + " " + h + " " + i;
    }

    public void setBound(ItemStack stack, String playerName) {
        if (stack.hasTagCompound()) {
            stack.getTagCompound().setString("bound", playerName);
        } else {
            NBTTagCompound tmp = new NBTTagCompound();
            tmp.setString("bound", playerName);
            stack.setTagCompound(tmp);
        }
    }

    public String getBound(ItemStack stack) {
        if (stack.hasTagCompound()) {
            if (stack.getTagCompound().hasKey("bound")) {
                return stack.getTagCompound().getString("bound");
            } else {
                setBound(stack, "SickPlayerNameThatDoesNotExist");
                return "SickPlayerNameThatDoesNotExist";
            }
        } else {
            setBound(stack, "SickPlayerNameThatDoesNotExist");
            return "SickPlayerNameThatDoesNotExist";
        }
    }


}
