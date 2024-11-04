package com.card.mvc.model.dto;

public enum Type {
    GRASS(1, "#78C850"),
    WATER(2, "#6890F0"),
    FIRE(3, "#F08030"),
    LIGHTNING(4, "#F8D030"),
    DARK(5, "#705848"),
    STEEL(6, "#B8B8D0"),
    DRAGON(7, "#7038F8"),
    NORMAL(8, "#A8A878");

    private final int id;
    private final String colorCode;

    Type(int id, String colorCode) {
        this.id = id;
        this.colorCode = colorCode;
    }

    public int getId() {
        return id;
    }

    public String getColorCode() {
        return colorCode;
    }
}

