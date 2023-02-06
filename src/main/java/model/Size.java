package model;

public enum Size {
    SMALL("35-36"), MEDIUM("37-38"), LARGE("39-40");

    private final String text;

    Size(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
