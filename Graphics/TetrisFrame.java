package com.jtetris.Graphics;

import com.jtetris.Model.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Timer;
import java.util.TimerTask;

public class TetrisFrame extends JFrame {
    private final int HEIGHT = 30;
    private final int WIDTH = 10;
    private final int TICK_DELAY = 250;
    private final int MOVE_DELAY = 75;
    private TetrisPanel tetrisPanel;
    private NextPanel nextPanel;
    private Game game;

    public TetrisFrame() {
        setTitle("JTetris");
        setLocationByPlatform(true);
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                game.move(e.getKeyCode());
                tetrisPanel.setTetromino(game.getTetromino());
            }
        });
        tetrisPanel = new TetrisPanel(HEIGHT, WIDTH);
        nextPanel = new NextPanel(HEIGHT);
        game = new Game(HEIGHT, WIDTH);
        add(tetrisPanel, BorderLayout.WEST);
        add(nextPanel, BorderLayout.EAST);
        pack();

        Timer tickTimer = new Timer();
        TimerTask tick = new TimerTask() {
            public void run() {
                game.tick();
                tetrisPanel.setGrid(game.getGrid());
                tetrisPanel.setTetromino(game.getTetromino());
                if (game.getTetromino() != null)
                    nextPanel.setShape(game.getTetromino().getNextShape());
            }
        };
        tickTimer.scheduleAtFixedRate(tick, TICK_DELAY, TICK_DELAY);
        Timer moveTimer = new Timer();
        TimerTask move = new TimerTask() {
            public void run() {
                game.releaseKey();
            }
        };
        moveTimer.scheduleAtFixedRate(move, MOVE_DELAY, MOVE_DELAY);
    }
}
