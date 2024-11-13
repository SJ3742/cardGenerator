package com.card.mvc.model.dto;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Streamer {
	private String sId;
	private int follower;
	private String name;
	private String imgURL;

	Streamer() {
	}

	public Streamer(String sId, int follower, String name, String imgURL) {
		this.sId = sId;
		this.follower = follower;
		this.name = name;
		this.imgURL = imgURL;
	}

	public String getsId() {
		return sId;
	}

	public void setsId(String sId) {
		this.sId = sId;
	}

	public int getFollower() {
		return follower;
	}

	public void setFollower(int follower) {
		this.follower = follower;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImgURL() {
		return imgURL;
	}

	public void setImgURL(String imgURL) {
		this.imgURL = imgURL;
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
