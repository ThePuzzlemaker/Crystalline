package com.teamisotope.crystalline.setup;

import com.teamisotope.crystalline.groups.CrystallineMainGroup;
import com.teamisotope.crystalline.world.OreGeneration;

public class CrystallineSetup {

    public CrystallineMainGroup crystallineMainGroup = new CrystallineMainGroup();

    public void init() {
        OreGeneration.setupOreGeneration();
    }
}
