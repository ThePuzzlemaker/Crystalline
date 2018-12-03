package ext.tpz.crystalline.potion;

import ext.tpz.crystalline.util.Reference;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.attributes.AbstractAttributeMap;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.potion.PotionType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class PotionDirtshield extends Potion {

    private ResourceLocation texture = new ResourceLocation(Reference.MODID, "textures/misc/potions.png");

    public PotionDirtshield() {
        super(false, 0x79553a);
        this.setPotionName("Dirtshield");
        this.setIconIndex(0, 0);
        this.setRegistryName(new ResourceLocation("crystalline", "dirtshield"));
    }

    @Override
    public boolean shouldRenderInvText(PotionEffect effect) {
        return true;
    }

    @Override
    public boolean shouldRender(PotionEffect effect) {
        return true;
    }

    public PotionEffect apply(EntityLivingBase entity, int duration) {
        return apply(entity, duration, 0);
    }

    public PotionEffect apply(EntityLivingBase entity, int duration, int level) {
        PotionEffect effect = new PotionEffect(this, duration, level, false, false);
        entity.addPotionEffect(effect);
        return effect;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getStatusIconIndex() {
        Minecraft.getMinecraft().renderEngine.bindTexture(texture);
        return super.getStatusIconIndex();
    }

}
