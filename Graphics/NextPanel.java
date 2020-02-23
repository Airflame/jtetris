package com.jtetris.Graphics;

import javax.swing.*;
import java.awt.*;

public class NextPanel extends JPanel {
    private final int SIZE = 32;
    private int height;
    private boolean[][] shape;

    NextPanel(int height) {
        this.height = height;
    }

    public void setShape(boolean[][] shape) {
        this.shape = shape;
        repaint();
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(5*SIZE, height*SIZE);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        setBackground(new Color(44, 62, 80));
        if (shape != null) {
            g.setColor(new Color(52, 73, 94));
            double offset = (5.0 - shape.length) / 2.0;
            for (int shapeY = 0; shapeY < shape.length; shapeY++) {
                for (int shapeX = 0; shapeX < shape.length; shapeX++) {
                    double y = shapeY + offset;
                    double x = shapeX + offset;
                    if (shape[shapeY][shapeX])
                        g.fillRect((int)Math.round(x * SIZE), (int)Math.round(y * SIZE), SIZE, SIZE);
                }
            }
        }
    }
}
