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
        return (checkVerticalWin(c) || checkHorizonWin(c) || checkLeftDownWin(c));
    }

    private static boolean checkHorizonWin(char c) {
        int win = 0;
        for (int y = 0; y < fieldSizeY; y++) {
            win = 0;
            for (int x = 0; x < fieldSizeX; x++) {
                if (fieldSizeX - x >= SIZE_WIN || field[y][x] == c) {
                    if (field[y][x] == c) win++;
                    if (win >= SIZE_WIN) return true;
                } else {
                    win = 0;
                }
            }
        }
        return false;
    }

    private static boolean checkVerticalWin(char c) {
        int win = 0;
        for (int y = 0; y < fieldSizeY; y++) {
            win = 0;
            for (int x = 0; x < fieldSizeX; x++) {
                if (fieldSizeX - x >= SIZE_WIN || field[x][y] == c) {
                    if (field[x][y] == c) win++;
                    if (win >= SIZE_WIN) return true;
                } else {
                    win = 0;
                }
            }
        }
        return false;
    }

    private static boolean checkLeftDownWin(char c) {
        int win = 0;
        for (int y = fieldSizeY; y >= 0; y--) {
            win = 0;
            for (int x = 0; x < fieldSizeX; x++) {
                if((x+y)==field.length-1) {
                    if (fieldSizeX - x >= SIZE_WIN || field[y][x] == c) {
                        if (field[y][x] == c) win++;
                        if (win >= SIZE_WIN) return true;
                    } else {
                        win = 0;
                    }
                }
            }
        }
        return false;
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
