package ext.tpz.crystalline.item;

public enum EnumCrystalTypes {

    VOID("void"),
    CLEANSING("cleansing"),
    ADMINISTRATION("administration"),
    LIFE("life"),
    KNOWLEDGE("knowledge"),
    RIFT("rift"),
    UNIVERSE("universe"),
    ARTIFICIAL("artificial"),
    UNKNOWN("unknown");

    private String name;

    EnumCrystalTypes(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

}
