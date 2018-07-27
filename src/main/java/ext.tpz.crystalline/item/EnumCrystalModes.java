package ext.tpz.crystalline.item;

public enum EnumCrystalModes {

    // Rift & Universe Crystal
    OBLITERATE_ENTITY("obliterate_entity", "Obliterate Entity"),
    OBLITERATE_BLOCK("obliterate_block", "Obliterate Block"),

    // Universe Only
    ULTRA_CLEANSE("ultra_cleanse", "Ultra Cleanse"),

    // None
    NONE("none", "missingno");

    private String name;
    private String locName;

    EnumCrystalModes(String name, String locName) {
        this.name = name;
        this.locName = locName;
    }

    public String getName() {
        return this.name;
    }

    public String getLocName() {
        return this.locName;
    }

}
