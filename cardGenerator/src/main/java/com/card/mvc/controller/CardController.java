package com.card.mvc.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.card.mvc.model.dto.Card;
import com.card.mvc.service.CardService;

@RestController
@RequestMapping("/card")
public class CardController {

    private final CardService service;

    public CardController(CardService service) {
        this.service = service;
    }

    /**
     * @param card
     * @return
     */
    @PostMapping
    public ResponseEntity<byte[]> generateCard(@RequestBody Card card) {
        try {
            byte[] newCard = service.createCard(card);
            if (newCard != null) {
                HttpHeaders headers = new HttpHeaders();
                headers.add("Content-Type", "image/png");
                // 카드 이름에 맞춰 파일명 지정
                headers.add("Content-Disposition", "inline; filename=" + card.getName() + "_card.png");
                return new ResponseEntity<>(newCard, headers, HttpStatus.CREATED);
            }
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}

