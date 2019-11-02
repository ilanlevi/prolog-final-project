package entities;

public enum Color {
    BLACK("Black"),
    WHITE("White");

    private String name;

    public String getName() {
        return name;
    }

    public static Color other(Color color) {
        if (color == BLACK)
            return WHITE;
        return BLACK;
    }

    Color(String name) {
        this.name = name;
    }
}
