package com.card.mvc.model.dto;

public enum Rarity {
	C(1), U(2), R(3), RR(4), AR(5), SR(6), SAR(7), IR(8), UR(9);

	private final int level;

	Rarity(int level) {
		this.level = level;
	}

	public int getLevel() {
		return level;
	}
}
