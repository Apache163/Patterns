package templatemethod;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AnimationPanel extends JPanel {
    private final List<Shape> shapes = new ArrayList<>();
    private final Random random = new Random();

    public void addShape(Shape shape) {
        shapes.add(shape);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Shape shape : shapes) {
            if (shape instanceof Ball) {
                ((Ball) shape).draw(g);
            } else if (shape instanceof Square) {
                ((Square) shape).draw(g);
            }
        }
    }

    public void animate() {
        new Thread(() -> {
            while (true) {
                for (Shape shape : shapes) {
                    shape.move(getWidth(), getHeight());
                }
                repaint();
                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    return;
                }
            }
        }).start();
    }

    public Color getRandomColor() {
        return new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256));
    }
}