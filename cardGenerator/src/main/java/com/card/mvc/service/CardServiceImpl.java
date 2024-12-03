package com.card.mvc.service;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import javax.imageio.ImageIO;

import org.springframework.stereotype.Service;

import com.card.mvc.model.dto.Card;

@Service
public class CardServiceImpl implements CardService {
    /**
     * 카드 데이터를 기반으로 이미지 생성
     */
    @Override
    public byte[] createCard(Card card) {
        try {
            // 이미지 크기와 설정
            int width = 367;
            int height = 700;
            BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2d = bufferedImage.createGraphics();

            // 배경 색상 설정
            g2d.setColor(Color.WHITE);
            g2d.fillRect(0, 0, width, height);

            // 카드 이름 출력
            g2d.setColor(Color.BLACK);
            g2d.setFont(new Font("Arial", Font.BOLD, 24));
            g2d.drawString(card.getName(), 50, 50);

            // 기타 카드 정보 출력
            g2d.setFont(new Font("Arial", Font.PLAIN, 18));
            g2d.drawString("타입: " + card.getType(), 50, 100);
            g2d.drawString("희귀도: " + card.getRarity(), 50, 150);

            // 이미지 작성 완료
            g2d.dispose();

            // 이미지를 바이트 배열로 변환
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(bufferedImage, "png", baos);
            return baos.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
