package state;

import java.awt.*;

public interface HumanState {
    void draw(Graphics g, int x, int y);
    String getStateName();
}