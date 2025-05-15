package templatemethod;

import java.awt.*;

public abstract class Shape {
    protected int x, y;
    protected int dx = 2, dy = 3;
    protected Color color;

    public Shape(int x, int y, Color color) {
        this.x = x;
        this.y = y;
        this.color = color;
    }

    // Изменяем модификатор на public
    public final void move(int width, int height) {
        changeDirection(width, height);
        updatePosition();
        draw();
    }

    protected abstract void changeDirection(int width, int height);
    protected abstract void updatePosition();
    protected abstract void draw();

    protected void bounceIfNeeded(int width, int height) {
        if (x < 0 || x > width) dx = -dx;
        if (y < 0 || y > height) dy = -dy;
    }
}