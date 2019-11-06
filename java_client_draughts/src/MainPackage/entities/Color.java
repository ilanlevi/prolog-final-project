package MainPackage.entities;

public enum Color {
    BLACK("black"),
    WHITE("white");

    private String name;

    public String getName() {
        return name;
    }

    public static Color other(Color color) {
        if (color == BLACK)
            return WHITE;
        return BLACK;
    }

    public static Color fromString(String colorName){
        if (colorName == null || colorName.isEmpty() || colorName.equals(WHITE.getName()))
            return WHITE;
        return BLACK;
    }

    Color(String name) {
        this.name = name;
    }
}
