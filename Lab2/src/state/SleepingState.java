package state;

import java.awt.*;

public class SleepingState implements HumanState {
    @Override
    public void draw(Graphics g, int x, int y) {
        g.setColor(new Color(150, 150, 255));
        g.fillOval(x, y, 100, 100); // Голова
        g.setColor(Color.BLACK);
        g.drawLine(x+30, y+40, x+50, y+40); // Закрытые глаза
        g.drawLine(x+60, y+40, x+80, y+40);
        g.drawArc(x+30, y+50, 40, 20, 0, -180); // Рот (спит)
    }

    @Override
    public String getStateName() {
        return "Спящий";
    }
}