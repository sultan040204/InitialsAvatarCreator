package com.utils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.stream.Collectors;

public class Main {

    public String createAvatarImage(String firstName, String lastName){
        String initials = String.valueOf(lastName.charAt(0))+firstName.charAt(0);
        int width = 200;
        int height = 200;

        BufferedImage avatar = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = avatar.createGraphics();

        // Задаем цвет фона
        g2d.setColor(Color.WHITE);
        g2d.fillRect(0, 0, width, height);

        // Задаем цвет текста и шрифт
        g2d.setColor(Color.GRAY);
        Font font = new Font("Arial", Font.PLAIN, 48);
        g2d.setFont(font);

        // Рассчитываем положение текста по центру
        int stringWidth = g2d.getFontMetrics().stringWidth(initials);
        int stringHeight = g2d.getFontMetrics().getHeight();
        int x = (width - stringWidth) / 2;
        int y = ((height - stringHeight) / 2) + g2d.getFontMetrics().getAscent();

        // Рисуем инициалы на аватарке
        g2d.drawString(initials, x, y);
        g2d.dispose();

        // Сохраняем изображение в файл
        try {
            String symbols = "abcdefghijklmnopqrstuvwxyz1234567890";
            String random = new Random().ints(8, 0, symbols.length())
                    .mapToObj(symbols::charAt)
                    .map(Object::toString)
                    .collect(Collectors.joining());
            ImageIO.write(avatar, "PNG", new File(random+".png"));
            return random;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
