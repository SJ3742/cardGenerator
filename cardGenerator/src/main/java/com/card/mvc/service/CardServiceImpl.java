package com.card.mvc.service;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;

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
            
            // 이미지 불러오기
            String basePath = "C:\\Users\\patch\\Downloads\\cardG\\cardGenerator\\cardGenerator\\src\\main\\resources\\static\\images\\";
            String fileName = card.getType().getId() + ".png";
            File background = new File(basePath + fileName);
            BufferedImage backgroundImage = ImageIO.read(background);
            g2d.drawImage(backgroundImage, 0, 0, null);

            // 카드 이름 출력(여기서부터 미완성)
            g2d.setColor(Color.BLACK);
            g2d.setFont(new Font("Arial", Font.BOLD, 24));
            g2d.drawString(card.getName(), 50, 50);

            g2d.setFont(new Font("Arial", Font.PLAIN, 18));
            g2d.drawString("타입: " + card.getType(), 50, 100);
            g2d.drawString("희귀도: " + card.getRarity(), 50, 150);

            g2d.dispose();

            // 파일 저장 경로 지정(테스트 코드)
            String outputPath = "output/" + card.getName() + "_card.png"; // 저장 폴더와 파일명
            File outputFile = new File(outputPath);

            // 부모 디렉토리가 없으면 생성
            outputFile.getParentFile().mkdirs();

            // 파일로 이미지 저장
            ImageIO.write(bufferedImage, "png", outputFile);
            
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
