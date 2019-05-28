package ext.tpz.crystalline.api.insanity;

import ext.tpz.crystalline.common.Crystalline;
import ext.tpz.crystalline.common.capabilities.CCapabilities;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber
public class InsanityHandler {


    private IInsanity getInsanity(EntityPlayer e) {
        if (e.hasCapability(InsanityCapability.CAPABILITY_INSANITY, EnumFacing.UP)) {
            return e.getCapability(InsanityCapability.CAPABILITY_INSANITY, EnumFacing.UP);
        }
        return null;
    }

    @SubscribeEvent
    public void attachCapabilities(AttachCapabilitiesEvent<Entity> e) {
        if (e.getObject() instanceof EntityPlayer) {
            EntityPlayer ent = (EntityPlayer)e.getObject();
            if (ent.hasCapability(InsanityCapability.CAPABILITY_INSANITY, EnumFacing.UP))
                return;
            e.addCapability(InsanityCapability.CAPABILITY_INSANITY_RL, new InsanityCapability());
        }
    }

    @SubscribeEvent
    public void clonePlayer(PlayerEvent.Clone e) {
        EntityPlayer oldPlayer = e.getOriginal();
        EntityPlayer newPlayer = e.getEntityPlayer();

        IInsanity oldInsanity = getInsanity(oldPlayer);
        IInsanity newInsanity = getInsanity(newPlayer);

        if (oldInsanity != null && newInsanity != null) {
            newInsanity.setInsanity(oldInsanity.getInsanity());
        }
    }

}
