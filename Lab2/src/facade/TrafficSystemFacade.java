package facade;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TrafficSystemFacade {
    private final JFrame frame;
    private final Car car;
    private final TrafficLight trafficLight;
    private final Timer timer;

    public TrafficSystemFacade() {
        frame = new JFrame("Traffic Simulation");
        frame.setSize(600, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        car = new Car();
        trafficLight = new TrafficLight();

        frame.add(new DrawingPanel());

        timer = new Timer(50, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.repaint();
            }
        });
    }

    public void startSimulation() {
        frame.setVisible(true);
        timer.start();

        new Thread(() -> {
            while (true) {
                trafficLight.changeColor();
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private class DrawingPanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            setBackground(new Color(240, 240, 240));

            // Рисуем светофор
            trafficLight.draw(g, 300, 150);

            // Рисуем машину
            car.move(trafficLight.getCurrentColor());
            car.draw(g);
        }
    }

    private class Car {
        private int angle = 0;
        private final int radius = 100;
        private final int centerX = 300;
        private final int centerY = 300;
        private boolean stopped = false;

        public void move(Color lightColor) {
            if (lightColor == Color.RED) {
                stopped = true;
            } else if (lightColor == Color.GREEN) {
                stopped = false;
            }

            if (!stopped) {
                angle = (angle + 1) % 360;
            }
        }

        public void draw(Graphics g) {
            int x = centerX + (int)(radius * Math.cos(Math.toRadians(angle))) - 25;
            int y = centerY + (int)(radius * Math.sin(Math.toRadians(angle))) - 15;

            // Кузов (больше размер)
            g.setColor(new Color(248, 191, 211)); //Розовая машинка :3
            g.fillRect(x, y, 50, 30);

            // Окна
            g.setColor(new Color(196, 202, 255)); // Голубые окошки :3
            g.fillRect(x + 5, y + 5, 40, 10);

            // Колеса
            g.setColor(Color.BLACK);
            g.fillOval(x - 5, y + 20, 15, 15);
            g.fillOval(x + 40, y + 20, 15, 15);
        }
    }

    private class TrafficLight {
        private final Color[] colors = {Color.RED, Color.YELLOW, Color.GREEN};
        private int current = 0;

        public Color getCurrentColor() {
            return colors[current];
        }

        public void changeColor() {
            current = (current + 1) % colors.length;
        }

        public void draw(Graphics g, int x, int y) {
            // Корпус светофора (уменьшенный)
            g.setColor(Color.BLACK);
            g.fillRect(x - 15, y - 50, 30, 100);

            // Сигналы (круглые, пропорциональные)
            drawLight(g, x, y - 30, colors[0], current == 0);    // Красный
            drawLight(g, x, y,     colors[1], current == 1);     // Желтый
            drawLight(g, x, y + 30, colors[2], current == 2);    // Зеленый
        }

        private void drawLight(Graphics g, int x, int y, Color color, boolean active) {
            if (active) {
                g.setColor(color);
                g.fillOval(x - 12, y - 12, 24, 24);

// Эффект свечения
                Graphics2D g2d = (Graphics2D) g;
                g2d.setColor(new Color(color.getRed(), color.getGreen(), color.getBlue(), 80));
                g2d.fillOval(x - 16, y - 16, 32, 32);
            } else {
                // Неактивный свет (тусклый)
                Color dimmed = new Color(
                        color.getRed()/3,
                        color.getGreen()/3,
                        color.getBlue()/3
                );
                g.setColor(dimmed);
                g.fillOval(x - 10, y - 10, 20, 20);
            }
        }
    }
}
