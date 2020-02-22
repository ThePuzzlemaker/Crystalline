package com.teamisotope.crystalline.common.setup;

import com.teamisotope.crystalline.common.groups.CrystallineMainGroup;
import com.teamisotope.crystalline.common.world.OreGeneration;

public class CrystallineSetup {

    public CrystallineMainGroup crystallineMainGroup = new CrystallineMainGroup();

    public void init() {
        OreGeneration.setupOreGeneration();
    }
}
