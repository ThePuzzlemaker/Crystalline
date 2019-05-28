package ext.tpz.crystalline.entity;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.DamageSource;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;

public class DamageSourceLiquidEssence extends DamageSource {

    public DamageSourceLiquidEssence() {
        super("liquid_essence");
        setDamageBypassesArmor();
        setMagicDamage();
    }

    @Override
    public ITextComponent getDeathMessage(EntityLivingBase entityLivingBaseIn) {
        return new TextComponentString(entityLivingBaseIn.getName() + " took a swig of liquid essence, and was ripped from the inside out. Foolish of them.");
    }

}
