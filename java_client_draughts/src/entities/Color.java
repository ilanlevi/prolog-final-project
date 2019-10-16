package entities;

public enum Color {
    BLACK("Black"),
    WHITE("White");

    private String name;

    public String getName() {
        return name;
    }

    Color(String name) {
        this.name = name;
    }
}
