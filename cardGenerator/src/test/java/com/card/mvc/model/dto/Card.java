package com.card.mvc.model.dto;

import java.io.File;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Card {
	private String sId; //스트리머 고유 아이디(플랫폼 정보 2자리+아이디로 구성)
	private String imgURL; //이미지
	private Rarity rarity; //희귀도(enum)
	private Type type; //타입
	private String ability;
	private Skill skill1;
	private Skill skill2;
	
	Card(){};
	
	public String getsId() {
		return sId;
	}
	public void setsId(String sId) {
		this.sId = sId;
	}
	public String getImgURL() {
		return imgURL;
	}
	public void setImgURL(String imgURL) {
		this.imgURL = imgURL;
	}
	public Rarity getRarity() {
		return rarity;
	}
	public void setRarity(Rarity rarity) {
		this.rarity = rarity;
	}
	public Type getType() {
		return type;
	}
	public void setType(Type type) {
		this.type = type;
	}
	public String getAbility() {
		return ability;
	}
	public void setAbility(String ability) {
		this.ability = ability;
	}
	public Skill getSkill1() {
		return skill1;
	}
	public void setSkill1(Skill skill1) {
		this.skill1 = skill1;
	}
	public Skill getSkill2() {
		return skill2;
	}
	public void setSkill2(Skill skill2) {
		this.skill2 = skill2;
	}
	
	//JSON 변환
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
    public String toString(){
    	return this.toJson();
    }
}
