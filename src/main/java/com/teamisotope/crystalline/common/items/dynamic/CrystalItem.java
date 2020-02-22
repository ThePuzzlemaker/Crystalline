package com.teamisotope.crystalline.common.items.dynamic;

import com.teamisotope.crystalline.common.Crystalline;
import net.minecraft.item.Item;

public class CrystalItem extends Item {

    public CrystalItem() {
        super(new Item.Properties().group(Crystalline.setup.crystallineMainGroup));
        setRegistryName("crystalline:crystalitem");
    }

}
