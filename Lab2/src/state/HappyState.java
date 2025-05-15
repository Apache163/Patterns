package state;

import java.awt.*;

public class HappyState implements HumanState {
    @Override
    public void draw(Graphics g, int x, int y) {
        g.setColor(new Color(255, 255, 150));
        g.fillOval(x, y, 100, 100); // Голова
        g.setColor(Color.BLACK);
        g.fillOval(x+30, y+30, 15, 15); // Глаза
        g.fillOval(x+60, y+30, 15, 15);
        g.drawArc(x+25, y+40, 50, 30, 0, -180); // Улыбка
    }

    @Override
    public String getStateName() {
        return "Счастливый";
    }
}