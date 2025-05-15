package templatemethod;

import java.awt.*;

public class Square extends Shape {
    private static final int SIZE = 30;

    public Square(int x, int y, Color color) {
        super(x, y, color);
    }

    @Override
    protected void changeDirection(int width, int height) {
        bounceIfNeeded(width - SIZE, height - SIZE);
    }

    @Override
    protected void updatePosition() {
        x += dx;
        y += dy;
    }

    @Override
    protected void draw() {
        // Реализация рисования в панели
    }

    public void draw(Graphics g) {
        g.setColor(color);
        g.fillRect(x, y, SIZE, SIZE);
    }
}