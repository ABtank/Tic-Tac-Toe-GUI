package ru.abramov.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameWindow extends JFrame{
    private static final int WIN_WIDTH = 507;
    private static final int WIN_HEIGHT = 555;
    private static final int WIN_POSX = 650;
    private static final int WIN_POSY = 250;
    public final MapField mapField;

    GameWindow() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(WIN_WIDTH, WIN_HEIGHT);
        setLocation(WIN_POSX, WIN_POSY);
        setResizable(false);
        setTitle("Tic Tac Toe");
        JButton btnStart = new JButton("Start");
        JButton btnExit = new JButton("Exit");
        JPanel panelBottom = new JPanel(new GridLayout(1, 2));
        panelBottom.add(btnStart);
        panelBottom.add(btnExit);
        mapField = new MapField();
        Settings settings = new Settings(this);
        add(mapField, BorderLayout.CENTER);
        add(panelBottom, BorderLayout.SOUTH);
        btnExit.addActionListener(e -> System.exit(0));
        btnStart.addActionListener(e -> settings.setVisible(true));
        setVisible(true);
    }

    void startNewGame(int gameMode, int fieldSizeX, int fieldSizeY, int winLen) {
        mapField.startNewGame(gameMode, fieldSizeX, fieldSizeY, winLen);
    }
}

