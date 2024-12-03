package com.card.mvc.service;

import org.springframework.stereotype.Service;

import com.card.mvc.model.dto.Card;

@Service
public interface CardService {
	public byte[] createCard(Card card);
	
}
