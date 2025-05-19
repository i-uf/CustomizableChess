package com.i_uf.chess.utils.client;

import java.awt.*;
import java.util.LinkedList;
import java.util.function.Consumer;

public class Canvas {
    public final int x, y, width, height;
    public Canvas(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
    private final LinkedList<Consumer<Graphics>> context = new LinkedList<>();

    public Canvas graphics(Graphics graphics) {
        while(!context.isEmpty()) context.pop().accept(graphics);
        return this;
    }

    public Canvas image(int x, int y, int w, int h, int color, Image image) {
        context.add(graphics->graphics.drawImage(image, x, y, w, h, new Color(color, true), null));
        return this;
    }
    public Canvas image(int x, int y, int w, int h, Image image) {
        context.add(graphics->graphics.drawImage(image, x, y, w, h, null));
        return this;
    }
    public Canvas fill(int x, int y, int w, int h, int color) {
        context.add(graphics-> {
            graphics.setColor(new Color(color, true));
            graphics.fillRect(x, y, w, h);
        });
        return this;
    }
    public Canvas text(String text, int x, int y, int w, int h, int color, int alignment, Font font) {
        context.add(graphics-> {
            String text1 = width(text, w, font, graphics);
            graphics.setColor(new Color(color, true));
            graphics.setFont(font);
            int modifiedY = y + switch (alignment / 3) {
                case 1 -> (h - ascent(font, graphics) - descent(font, graphics)) / 2 + ascent(font, graphics);
                case 2 -> h - descent(font, graphics);
                default -> ascent(font, graphics);
            };
            for (String text2 : text1.split("\n")) {
                graphics.drawString(
                        text2, x + switch (alignment % 3) {
                            case 1 -> (w - width(text2, font, graphics)) / 2;
                            case 2 -> w - width(text2, font, graphics);
                            default -> 0;
                        }, modifiedY);
                modifiedY += h;
            }
        });
        return this;
    }

    private String width(String text, int width, Font font, Graphics g) {
        if (width(text, font, g) <= width || width == 0) return text;
        var index = 0;
        var total = width("..", font, g);
        while (index < text.length() && (total += width(text.charAt(index), font, g)) <= width) index++;
        return "%s..".formatted(text.substring(0, index));
    }
    public int width(char c, Font font, Graphics g) {
        return g.getFontMetrics(font).charWidth(c);
    }
    public int width(String s, Font font, Graphics g) {
        return s.chars().map(it -> width((char) it, font, g) ).sum();
    }
    private int ascent(Font font, Graphics g) {
        return g.getFontMetrics(font).getAscent();
    }

    private int descent(Font font, Graphics g) {
        return g.getFontMetrics(font).getDescent();
    }
}
