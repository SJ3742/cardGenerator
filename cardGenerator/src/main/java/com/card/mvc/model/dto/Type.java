package com.card.mvc.model.dto;

public enum Type {
    GRASS(1, "#66a945"),
    FIRE(2, "#e56c3e"),
    WATER(3, "#5185c5"),
    LIGHTNING(4, "#fbb917"),
    PSYCHIC(5, "#dd6b7b"),
    FIGHTING(6, "#e09c40"),
    DARK(7, "#4c4948"),
    STEEL(8, "#69a9c7"),
    DRAGON(9, "#535ca8"),
    NORMAL(10, "#949495"),
	TRAINER(11, "#FFFFFF");

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

