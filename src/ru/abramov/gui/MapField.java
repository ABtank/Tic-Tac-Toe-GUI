package ru.abramov.gui;

import javax.swing.*;
import java.awt.*;

public class MapField extends JPanel {

    public static final int MODE_HVH = 0;
    public static final int MODE_HVA = 1;

    MapField(){
        setBackground(Color.PINK);
    }

    void startNewGame(int gameMode, int fieldSizeX, int fieldSizeY, int winLength ){
        System.out.printf("mode=%d\nfsx = %d\nwin = %d",gameMode,fieldSizeX,fieldSizeY,winLength);
    }
}
