package ext.tpz.crystalline.insanity;

public enum EnumInsanityStages {

    STAGE_0(0,9),
    STAGE_1(10,29),
    STAGE_2(30,49),
    STAGE_3(50,69),
    STAGE_4(70,89),
    STAGE_5(90,99),
    STAGE_6(100,100),
    STAGE_PERMANENT(101,101);

    int minInsanity;
    int maxInsanity;

    EnumInsanityStages(int minInsanity, int maxInsanity) {
        this.minInsanity = minInsanity;
        this.maxInsanity = maxInsanity;
    }

    public int getMaxInsanity() {
        return maxInsanity;
    }

    public int getMinInsanity() {
        return minInsanity;
    }

}
