package com.jtetris.Graphics;

import com.jtetris.Model.Tetromino;
import com.jtetris.Model.Vector;

import javax.swing.*;
import java.awt.*;

public class TetrisPanel extends JPanel {
    private final int SIZE = 32;
    private int height;
    private int width;
    private boolean[][] grid;
    private Tetromino tetromino;

    TetrisPanel(int height, int width) {
        this.height = height;
        this.width = width;
        grid = new boolean[height][width];
    }

    public void setGrid(boolean[][] grid) {
        this.grid = grid;
        repaint();
    }

    public void setTetromino(Tetromino tetromino) {
        this.tetromino = tetromino;
        repaint();
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(width*SIZE,height*SIZE);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        setBackground(new Color(52, 73, 94));
        g.setColor(new Color(149, 165, 166));
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (grid[y][x])
                    g.fillRect(x*SIZE, y*SIZE, SIZE, SIZE);
            }
        }
        g.setColor(new Color(236, 240, 241));
        if (tetromino != null) {
            Vector position = tetromino.getPosition();
            boolean[][] shape = tetromino.getShape();
            for (int shapeY = 0; shapeY < shape.length; shapeY++) {
                for (int shapeX = 0; shapeX < shape.length; shapeX++) {
                    int y = shapeY + position.getY();
                    int x = shapeX + position.getX();
                    if (shape[shapeY][shapeX])
                        g.fillRect(x*SIZE,y*SIZE, SIZE, SIZE);
                }
            }
        }
    }
}
