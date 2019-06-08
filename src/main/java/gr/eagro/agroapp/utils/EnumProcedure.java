package gr.eagro.agroapp.utils;

public enum EnumProcedure {

    PLANTING("Σπορά"),
    FERTILIZING("Λίπανση"),
    CUTTING("Κλάδεμα"),
    COLLECTION("Συγγομιδή"),
    UPROOTING("Ξερίζωμα");

    private String displayName;

    EnumProcedure(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }


    @Override
    public String toString() {
        return getDisplayName();
    }

    public static EnumProcedure getValueOf(String value) {
        for (EnumProcedure procedure : EnumProcedure.values())
            if(procedure.getDisplayName().equals(value))
                return procedure;
        return null;
    }

}
