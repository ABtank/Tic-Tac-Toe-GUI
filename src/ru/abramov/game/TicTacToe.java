package ru.abramov.game;

import java.util.Random;
import java.util.Scanner;

public class TicTacToe {

    private static final char DOT_HUMAN = 'X';
    private static final char DOT_AI = 'O';
    private static final char DOT_EMPTY = '.';
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final Random RANDOM = new Random();
    private static final int SIZE_WIN = 4;
    private static final int SIZE_FIELD = 9;

    private static char[][] field;
    private static int fieldSizeX;
    private static int fieldSizeY;

    private static void initField() {
        fieldSizeX = SIZE_FIELD;
        fieldSizeY = SIZE_FIELD;
        field = new char[fieldSizeY][fieldSizeX];
        for (int y = 0; y < fieldSizeY; y++) {
            for (int x = 0; x < fieldSizeX; x++) {
                field[y][x] = DOT_EMPTY;
            }
        }
    }

    private static void printField() {
        System.out.println("----------");
        System.out.print("+ ");
        for (int i = 0; i < fieldSizeX * 2 + 1; i++) {
            System.out.print((i % 2 == 0) ? " " : i / 2 + 1);
        }
        System.out.println();
        for (int y = 0; y < fieldSizeY; y++) {
            System.out.print(y + 1 + " |");
            for (int x = 0; x < fieldSizeX; x++) {
                System.out.print(field[y][x] + "|");
            }
            System.out.println();
        }
        System.out.println("----------");
    }

    private static void humanTurn() {
        int x;
        int y;
        do {
            System.out.println("Введи координаты Х и Y через пробел");
            x = SCANNER.nextInt() - 1;
            y = SCANNER.nextInt() - 1;
        } while (!isValidCell(x, y) || !isEmptyCell(x, y));
        field[y][x] = DOT_HUMAN;
    }

    private static boolean isValidCell(int x, int y) {
        return x >= 0 && x < fieldSizeX && y >= 0 && y < fieldSizeY;
    }

    private static boolean isEmptyCell(int x, int y) {
        return field[y][x] == DOT_EMPTY;
    }

    private static void aiTurn() {
        int x;
        int y;
        do {
            x = RANDOM.nextInt(fieldSizeX);
            y = RANDOM.nextInt(fieldSizeY);
        } while (!isEmptyCell(x, y));
        field[y][x] = DOT_AI;
    }

    private static boolean checkWin(char c) {
        for (int y = 0; y < fieldSizeY; y++) {
            for (int x = 0; x < fieldSizeX; x++) {
                if (field[y][x] == c) {
                    if (fieldSizeY - y >= SIZE_WIN) {
                        if (checkWinVertical(c, y, x)) return true;

                        if (fieldSizeX - x >= SIZE_WIN) {
                            if (checkWinLeftDown(c, y, x)) return true;
                        }
                        if (x >= SIZE_WIN) {
                            if (checkWinRightUp(c, y, x)) return true;
                        }
                    }
                    if (fieldSizeX - x >= SIZE_WIN) {
                        if (checkWinHorizon(c, y, x)) return true;
                    }
                }
            }
        }
        return false;
    }

    private static boolean checkWinHorizon(char c, int y, int x) {
        int win = 0;
        for (int i = 0; i < SIZE_WIN; i++) {
            if (field[y][x+i] == c) {
                win++;
            }
        }
        return win >= SIZE_WIN;
    }

    private static boolean checkWinVertical(char c, int y, int x) {
        int win = 0;
        for (int i = 0; i < SIZE_WIN; i++) {
            if (field[y+i][x] == c) {
                win++;
            }
        }
        return win >= SIZE_WIN;
    }

    private static boolean checkWinLeftDown(char c, int y, int x) {
        int win = 0;
        for (int i = 0; i < SIZE_WIN; i++) {
            if (field[y + i][x + i] == c) win++;
        }
        return win >= SIZE_WIN;
    }

    private static boolean checkWinRightUp(char c, int y, int x) {
        int win = 0;
        for (int i = 0; i < SIZE_WIN; i++) {
            if (field[y + i][x - i] == c) win++;
        }
        return win >= SIZE_WIN;
    }


    private static boolean isDraw() {
        for (int y = 0; y < fieldSizeY; y++) {
            for (int x = 0; x < fieldSizeX; x++) {
                if (field[y][x] == DOT_EMPTY) return false;
            }
        }
        return true;
    }


    public static void main(String[] args) {
        initField();
        printField();
        while (true) {
            humanTurn();
            printField();
            if (checkWin(DOT_HUMAN)) {
                System.out.println("Human win");
                break;
            }
            if (isDraw()) {
                System.out.println("Draw");
                break;
            }
            aiTurn();
            printField();
            if (checkWin(DOT_AI)) {
                System.out.println("AI win");
                break;
            }
            if (isDraw()) {
                System.out.println("Draw");
                break;
            }
        }
    }
}
