package templatemethod;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BouncingShapesApp extends JFrame {
    private final AnimationPanel animationPanel;

    public BouncingShapesApp() {
        setTitle("Bouncing Shapes");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        animationPanel = new AnimationPanel();
        animationPanel.setBackground(Color.WHITE);
        add(animationPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        JButton startBtn = new JButton("Пуск");
        JButton closeBtn = new JButton("Закрыть");

        startBtn.addActionListener(e -> {
            // Добавляем случайную фигуру
            if (Math.random() > 0.5) {
                animationPanel.addShape(new Ball(
                        animationPanel.getWidth() - 50,
                        animationPanel.getHeight() - 50,
                        animationPanel.getRandomColor()
                ));
            } else {
                animationPanel.addShape(new Square(
                        animationPanel.getWidth() - 50,
                        animationPanel.getHeight() - 50,
                        animationPanel.getRandomColor()
                ));
            }
        });

        closeBtn.addActionListener(e -> System.exit(0));

        buttonPanel.add(startBtn);
        buttonPanel.add(closeBtn);
        add(buttonPanel, BorderLayout.SOUTH);

        // Запускаем анимацию
        animationPanel.animate();
    }
}