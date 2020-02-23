package com.jtetris.Model;

import java.util.Random;

public class Tetromino {
    private Vector position;
    private boolean[][] shape;
    private boolean[][] nextShape;

    public Tetromino() {
        shape = generateShape();
        nextShape = generateShape();
        position = new Vector(-3, 3);
    }

    private boolean[][] generateShape() {
        switch (new Random().nextInt(7)) {
            case 0:
                return new boolean[][]{{false, true, false, false},
                        {false, true, false, false},
                        {false, true, false, false},
                        {false, true, false, false}};
            case 1:
                return new boolean[][]{{true, false, false},
                        {true, true, true},
                        {false, false, false}};
            case 2:
                return new boolean[][]{{false, false, true},
                        {true, true, true},
                        {false, false, false}};
            case 3:
                return new boolean[][]{{true, true},
                        {true, true}};
            case 4:
                return new boolean[][]{{false, false, false},
                        {false, true, true},
                        {true, true, false}};
            case 5:
                return new boolean[][]{{false, true, false},
                        {true, true, true},
                        {false, false, false}};
            case 6:
                return new boolean[][]{{false, false, false},
                        {true, true, false},
                        {false, true, true}};
        }
        return null;
    }

    public Vector getPosition() {
        return position;
    }

    public boolean[][] getShape() {
        return shape;
    }

    public boolean[][] getNextShape() {
        return nextShape;
    }

    public void moveDown() {
        position = new Vector(position.getY() + 1, position.getX());
    }

    public void moveLeft() {
        position = new Vector(position.getY(), position.getX() - 1);
    }

    public void moveRight() {
        position = new Vector(position.getY(), position.getX() + 1);
    }

    public void rotateRight() {
        boolean[][] rotatedShape = new boolean[shape.length][shape.length];
        for (int y = 0; y < shape.length; y++) {
            for (int x = 0; x < shape.length; x++) {
                rotatedShape[y][x] = shape[shape.length - 1 - x][y];
            }
        }
        shape = rotatedShape;
    }

    public void rotateLeft() {
        boolean[][] rotatedShape = new boolean[shape.length][shape.length];
        for (int y = 0; y < shape.length; y++) {
            for (int x = 0; x < shape.length; x++) {
                rotatedShape[y][x] = shape[x][shape.length - 1 - y];
            }
        }
        shape = rotatedShape;
    }

    public void next() {
        position = new Vector(-2, 3);
        shape = nextShape;
        nextShape = generateShape();
    }
}
