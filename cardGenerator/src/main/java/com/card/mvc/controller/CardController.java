package com.card.mvc.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.card.mvc.model.dto.Card;
import com.card.mvc.service.CardService;

@RestController  // JSON 반환을 위해 @RestController 사용
@RequestMapping("/")
public class CardController {

    private final CardService service;

    public CardController(CardService service) {
        this.service = service;
    }

    // 받아온 데이터로 카드 생성
    @PostMapping("/generate-card")
    public ResponseEntity<Card> generateCard(@RequestBody Card card) {
    	System.out.println("생성 요청");
        try {
            // 카드 생성 로직
            Card newCard = service.createCard(card); // this.service로 변경
            if (newCard != null) {
                return ResponseEntity.status(HttpStatus.CREATED).body(newCard);
            }
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        } catch (Exception e) {
            // 실패 시 INTERNAL_SERVER_ERROR 반환
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
