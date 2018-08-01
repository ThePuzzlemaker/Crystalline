package ext.tpz.crystalline.entity;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.DamageSource;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;

public class DamageSourceBitesTheDust extends DamageSource {

    public DamageSourceBitesTheDust() {
        super("biting_the_dust");
        setDamageBypassesArmor();
        setMagicDamage();
    }

    @Override
    public ITextComponent getDeathMessage(EntityLivingBase entityLivingBaseIn) {
        return new TextComponentString(entityLivingBaseIn.getName() + " bit the dust.");
    }

}
