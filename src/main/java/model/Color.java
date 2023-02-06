package model;

public enum Color {
    WHITE("белый"), BLACK("черный"), BROWN("коричневый"), RED("красный"), ORANGE("оранжевый"),
    YELLOW("желтый"), PINK("розовый"), GREEN("зеленый"), VIOLET("фиолетовый");
    private final String text;

    Color(String text) {
        this.text = text;
    }
}
