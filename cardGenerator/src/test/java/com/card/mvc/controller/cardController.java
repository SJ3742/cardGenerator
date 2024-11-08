package com.card.mvc.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.card.mvc.model.dto.Card;
import com.card.mvc.service.cardService;

@Controller
@RequestMapping("/")
public class cardController {
	private cardService service;
	public cardController(cardService service) {
		this.service = service;
	}
	
	//받아오고 카드 생성
	@PostMapping
	public ResponseEntity<Card> generateCard(@RequestBody Card card){
        try {
            // 카드 생성 로직
            Card newCard = cardService.createCard(card);

            return ResponseEntity.status(HttpStatus.CREATED).body(newCard);
        } catch (Exception e) {
            // 실패 시 INTERNAL_SERVER_ERROR 반환
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
	}
	
	
	
}
