package com.TDS;

import javax.swing.*;
import java.awt.*;
import java.util.*;

public class ConfetePanel extends JComponent {
    private java.util.List<Point> confetes = new ArrayList<>();
    private javax.swing.Timer confeteTimer;
    private Random random = new Random();

    public void dispararConfetes() {
        confetes.clear();
        for (int i = 0; i < 100; i++) {
            int x = random.nextInt(getWidth());
            int y = random.nextInt(getHeight());
            confetes.add(new Point(x, y));
        }

        final int[] contador = {0};
        confeteTimer = new javax.swing.Timer(150, e -> {
            repaint();
            contador[0]++;
            if (contador[0] >= 20) {
                confeteTimer.stop();
                confetes.clear();
                repaint();
            }
        });
        confeteTimer.start();
    }

    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        for (Point p : confetes) {
            g2.setColor(new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256)));
            g2.fillOval(p.x, p.y, 8, 8);
        }
    }
}
