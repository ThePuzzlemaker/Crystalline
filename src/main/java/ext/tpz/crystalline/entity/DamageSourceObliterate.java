package ext.tpz.crystalline.entity;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;

public class DamageSourceObliterate extends DamageSource {

    EntityPlayer source;

    public DamageSourceObliterate(EntityPlayer source) {
        super("obliterate");
        this.source = source;
        setDamageBypassesArmor();
        setMagicDamage();
    }

    @Override
    public ITextComponent getDeathMessage(EntityLivingBase entityLivingBaseIn) {
        return new TextComponentString("Cthulu has been fed by " + entityLivingBaseIn.getName() + ", thanks to " + source.getName());
    }
}
