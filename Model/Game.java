package com.jtetris.Model;

import java.awt.event.KeyEvent;

public class Game {
    private int height;
    private int width;
    private boolean[][] grid;
    private Tetromino tetromino;
    private boolean isInstant;
    private boolean isRunning = true;
    private boolean isKeyPressed;

    public Game(int height, int width) {
        this.height = height;
        this.width = width;
        reset();
    }

    private void reset() {
        grid = new boolean[height][width];
        tetromino = new Tetromino();
        isInstant = false;
        isRunning = true;
        isKeyPressed = false;
    }

    public boolean[][] getGrid() {
        return grid;
    }

    public Tetromino getTetromino() {
        return tetromino;
    }

    public void tick() {
        if (isRunning) {
            if (!isInstant) {
                if (!onGround())
                    tetromino.moveDown();
                else {
                    isRunning = addToGrid();
                    tetromino.next();
                }
            } else {
                while (!onGround())
                    tetromino.moveDown();
                isRunning = addToGrid();
                tetromino.next();
                isInstant = false;
            }
            for (int y = height - 1; y >= 0; y--) {
                int n = 0;
                for (int x = 0; x < width; x++) {
                    if (grid[y][x])
                        n++;
                }
                if (n == width) {
                    removeRow(y);
                    y++;
                }
            }
        } else
            tetromino = null;
    }

    public void move(int keyCode) {
        if (isRunning) {
            if (!isKeyPressed) {
                if (keyCode == KeyEvent.VK_LEFT) {
                    tetromino.moveLeft();
                    if (isCollision())
                        tetromino.moveRight();
                }
                if (keyCode == KeyEvent.VK_RIGHT) {
                    tetromino.moveRight();
                    if (isCollision())
                        tetromino.moveLeft();
                }
                if (keyCode == KeyEvent.VK_DOWN) {
                    isInstant = true;
                    tick();
                }
                if (keyCode == KeyEvent.VK_UP) {
                    tetromino.rotateRight();
                    if (isCollision())
                        tetromino.rotateLeft();
                }
                isKeyPressed = true;
            }
        } else {
            if (keyCode == KeyEvent.VK_R)
                reset();
        }
    }

    private boolean isCollision() {
        Vector position = tetromino.getPosition();
        boolean[][] shape = tetromino.getShape();
        for (int shapeY = 0; shapeY < shape.length; shapeY++) {
            for (int shapeX = 0; shapeX < shape.length; shapeX++) {
                int y = shapeY + position.getY();
                int x = shapeX + position.getX();
                if (shape[shapeY][shapeX] && (x < 0 || x > width - 1 || grid[Math.max(y, 0)][x]))
                    return true;
            }
        }
        return false;
    }

    private boolean onGround() {
        Vector position = tetromino.getPosition();
        boolean[][] shape = tetromino.getShape();
        for (int shapeY = 0; shapeY < shape.length; shapeY++) {
            for (int shapeX = 0; shapeX < shape.length; shapeX++) {
                int y = shapeY + position.getY();
                int x = shapeX + position.getX();
                if (x < 0 || x > width - 1 || y < 0 || y > height - 1)
                    continue;
                if (shape[shapeY][shapeX]) {
                    if (y == height - 1 || grid[Math.min(y + 1, height - 1)][x])
                        return true;
                }
            }
        }
        return false;
    }

    private boolean addToGrid() {
        Vector position = tetromino.getPosition();
        boolean[][] shape = tetromino.getShape();
        for (int shapeY = 0; shapeY < shape.length; shapeY++) {
            for (int shapeX = 0; shapeX < shape.length; shapeX++) {
                int y = shapeY + position.getY();
                int x = shapeX + position.getX();
                if (shape[shapeY][shapeX]) {
                    if (y < 0)
                        return false;
                    grid[y][x] = true;
                }
            }
        }
        return true;
    }

    private void removeRow(int n) {
        for (int y = n; y >= 0; y--) {
            for (int x = 0; x < width; x++) {
                if (y > 0)
                    grid[y][x] = grid[y - 1][x];
                else
                    grid[y][x] = false;
            }
        }
    }

    public void releaseKey() {
        isKeyPressed = false;
    }
}
