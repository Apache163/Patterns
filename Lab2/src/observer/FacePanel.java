package observer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class FacePanel extends JFrame {
    private boolean leftEyeClicked = false;
    private boolean rightEyeClicked = false;
    private Color noseColor = new Color(255, 200, 150);
    private boolean isSmiling = false;
    private final int NORMAL_PUPIL_SIZE = 10;
    private final int BIG_PUPIL_SIZE = 50;

    public FacePanel() {
        setTitle("Рожица с большими зрачками");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                handleClick(e.getX(), e.getY());
                repaint();
            }
        });
    }

    private void handleClick(int x, int y) {
        if (isInLeftEye(x, y)) {
            leftEyeClicked = !leftEyeClicked;
            System.out.println("Клик на левый глаз. Состояние: " +
                    (leftEyeClicked ? "увеличенный зрачок" : "нормальный зрачок"));
        } else if (isInRightEye(x, y)) {
            rightEyeClicked = !rightEyeClicked;
            System.out.println("Клик на правый глаз. Состояние: " +
                    (rightEyeClicked ? "увеличенный зрачок" : "нормальный зрачок"));
        } else if (isInNose(x, y)) {
            noseColor = new Color(
                    (int)(Math.random() * 255),
                    (int)(Math.random() * 255),
                    (int)(Math.random() * 255)
            );
            System.out.println("Клик на нос. Новый цвет!");
        } else if (isInMouth(x, y)) {
            isSmiling = !isSmiling;
            System.out.println("Клик на рот. Состояние: " +
                    (isSmiling ? "улыбка" : "грусть"));
        } else {
            System.out.println("Клик вне зон лица: X=" + x + " Y=" + y);
        }
    }

    private boolean isInLeftEye(int x, int y) {
        return x >= 100 && x <= 150 && y >= 100 && y <= 150;
    }

    private boolean isInRightEye(int x, int y) {
        return x >= 250 && x <= 300 && y >= 100 && y <= 150;
    }

    private boolean isInNose(int x, int y) {
        return x >= 175 && x <= 225 && y >= 175 && y <= 225;
    }

    private boolean isInMouth(int x, int y) {
        return x >= 125 && x <= 275 && y >= 275 && y <= 325;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        drawFace(g);
    }

    private void drawFace(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, getWidth(), getHeight());

        // Голова
        g.setColor(Color.YELLOW);
        g.fillOval(50, 50, 300, 300);

        // Левый глаз
        g.setColor(Color.WHITE);
        g.fillOval(100, 100, 50, 50);
        g.setColor(Color.BLACK);
        int leftPupilSize = leftEyeClicked ? BIG_PUPIL_SIZE : NORMAL_PUPIL_SIZE;
        g.fillOval(125 - leftPupilSize/2, 125 - leftPupilSize/2, leftPupilSize, leftPupilSize);

        // Правый глаз
        g.setColor(Color.WHITE);
        g.fillOval(250, 100, 50, 50);
        g.setColor(Color.BLACK);
        int rightPupilSize = rightEyeClicked ? BIG_PUPIL_SIZE : NORMAL_PUPIL_SIZE;
        g.fillOval(275 - rightPupilSize/2, 125 - rightPupilSize/2, rightPupilSize, rightPupilSize);

        // Нос
        g.setColor(noseColor);
        g.fillOval(175, 175, 50, 50);

        // Рот
        g.setColor(Color.RED);
        if (isSmiling) {
            g.drawArc(125, 250, 150, 100, 180, 180);
        } else {
            g.drawArc(125, 300, 150, 100, 0, 180);
        }

        // Подпись
        g.setColor(Color.BLACK);
        g.drawString("Кликайте на глаза, нос и рот!", 100, 380);
    }
}