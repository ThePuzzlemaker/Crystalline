package ext.tpz.crystalline.entity;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.DamageSource;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;

public class DamageSourceCleansingPotion extends DamageSource {

    public DamageSourceCleansingPotion() {
        super("cleansing_potion");
        setDamageBypassesArmor();
        setMagicDamage();
    }

    @Override
    public ITextComponent getDeathMessage(EntityLivingBase entityLivingBaseIn) {
        return new TextComponentString(entityLivingBaseIn.getName() + " drank a potion to cure insanity and instantly died. " + TextFormatting.ITALIC + "It's not very effective...");
    }

}
