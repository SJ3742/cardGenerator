package com.card.mvc.model.dto;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Skill {
	private List<Type> cost; //비용(마나)
	private String name; //스킬 이름
	private int dmg; // 데미지
	private String desc; //설명

	public List<Type> getCost() {
		return cost;
	}

	public void setCost(List<Type> cost) {
		this.cost = cost;
	}

	public int getDmg() {
		return dmg;
	}

	public void setDmg(int dmg) {
		this.dmg = dmg;
	}

	// JSON 변환
	public String toJson() {
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			return objectMapper.writeValueAsString(this);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			return "{}";
		}
	}

	@Override
	public String toString() {
		return this.toJson();
	}
}
