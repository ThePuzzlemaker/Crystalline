package ext.tpz.crystalline.modes.universe;

import com.google.common.collect.Lists;
import ext.tpz.crystalline.api.crystal.ICrystal;
import ext.tpz.crystalline.api.mode.ICrystalMode;
import ext.tpz.crystalline.entity.EntityObliterateEntity;
import ext.tpz.crystalline.item.CrystallineItems;
import ext.tpz.crystalline.util.Reference;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;
import java.util.List;

public class ModeObliterateEntity implements ICrystalMode {

    @Override
    public String getUnlocalizedName() {
        return "crystalline.mode.obliterate_entity";
    }

    @Override
    public ActionResult<ItemStack> use(ItemStack crystal, EntityPlayer player) {
        if (CrystallineItems.crystal.getReagent(crystal).consume(player, crystal)) {
            EntityObliterateEntity eOE = new EntityObliterateEntity(player.getEntityWorld(), player);
            eOE.shoot(player, player.rotationPitch, player.rotationYaw, 0.0F, 1.5F, 1.0F);
            player.getEntityWorld().spawnEntity(eOE);
            return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, crystal);
        }
        return new ActionResult<ItemStack>(EnumActionResult.FAIL, crystal);
    }

    @Override
    public List<ICrystal> getValidCrystals() {
        return Lists.newArrayList(/*BaseModCrystals.rift_crystal, BaseModCrystals.universe_crystal*/);
    }

    @Override
    public boolean isValidCrystal(ItemStack crystal) {
        return getValidCrystals().contains(CrystallineItems.crystal.getType(crystal));
    }

    @Override
    public ICrystalMode setRegistryName(ResourceLocation name) {
        return null;
    }

    @Nullable
    @Override
    public ResourceLocation getRegistryName() {
        return new ResourceLocation(Reference.MODID, "mode.obliterate_entity");
    }

    @Override
    public Class<ICrystalMode> getRegistryType() {
        return ICrystalMode.class;
    }

}
