package templatemethod;

import java.awt.*;

public class Ball extends Shape {
    private static final int SIZE = 30;

    public Ball(int x, int y, Color color) {
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
        g.fillOval(x, y, SIZE, SIZE);
    }
}