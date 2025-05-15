package state;

import java.awt.*;

public class StressedState implements HumanState {
    @Override
    public void draw(Graphics g, int x, int y) {
        g.setColor(new Color(255, 150, 150));
        g.fillOval(x, y, 100, 100); // Голова
        g.setColor(Color.BLACK);
        g.drawLine(x+30, y+35, x+45, y+35); // Брови (нахмуренные)
        g.drawLine(x+60, y+35, x+75, y+35);
        g.fillOval(x+35, y+45, 10, 10); // Глаза
        g.fillOval(x+65, y+45, 10, 10);
        g.drawArc(x+30, y+60, 40, 20, 0, 180); // Рот (грусть)
    }

    @Override
    public String getStateName() {
        return "Стрессовый";
    }
}