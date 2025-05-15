package state;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HumanPanel extends JFrame {
    private HumanState currentState;
    private JLabel stateLabel;

    public HumanPanel() {
        setTitle("Человечек с состояниями");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Панель для кнопок
        JPanel buttonPanel = new JPanel();
        add(buttonPanel, BorderLayout.SOUTH);

        // Кнопки
        JButton semesterBtn = new JButton("Семестр");
        JButton vacationBtn = new JButton("Каникулы");
        JButton sessionBtn = new JButton("Сессия");

        // Метка для отображения состояния
        stateLabel = new JLabel("Состояние: ", SwingConstants.CENTER);
        add(stateLabel, BorderLayout.NORTH);

        // Добавление кнопок
        buttonPanel.add(semesterBtn);
        buttonPanel.add(vacationBtn);
        buttonPanel.add(sessionBtn);

        // Установка начального состояния
        setState(new HappyState());

        // Обработчики кнопок
        semesterBtn.addActionListener(e -> setState(new SleepingState()));
        vacationBtn.addActionListener(e -> setState(new HappyState()));
        sessionBtn.addActionListener(e -> setState(new StressedState()));

        // Панель для рисования человечка
        JPanel drawingPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                currentState.draw(g, 150, 100);
            }
        };
        add(drawingPanel, BorderLayout.CENTER);
    }

    private void setState(HumanState state) {
        this.currentState = state;
        stateLabel.setText("Состояние: " + state.getStateName());
        repaint();
        System.out.println("Состояние изменено на: " + state.getStateName());
    }
}