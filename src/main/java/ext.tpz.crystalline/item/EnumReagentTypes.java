package ext.tpz.crystalline.item;

public enum EnumReagentTypes {

    BASIC("basic"),
    ADVANCED("advanced"),
    EXTREME("extreme"),
    RIFT("rift"),
    UNIVERSE("universe"),
    NONE("none"),

    VOID("void");


    private String name;

    EnumReagentTypes(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

}
