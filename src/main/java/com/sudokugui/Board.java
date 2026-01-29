package com.sudokugui;

/**
 *
 * @author KomlosyT
 */

public class Board {

    public static boolean solveSudoku(int[][] board, int boxSize) {
        int size = board.length;

        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                if (board[row][col] == 0) {
                    int[] numbers = new int[size];
                    for (int i = 0; i < size; i++) numbers[i] = i + 1;
                    shuffleArray(numbers);

                    for (int num : numbers) {
                        if (isValid(board, row, col, num, boxSize)) {
                            board[row][col] = num;

                            if (solveSudoku(board, boxSize)) return true;

                            board[row][col] = 0;
                        }
                    }

                    return false;
                }
            }
        }
        return true;
    }

    public static boolean isValid(int[][] board, int row, int col, int num, int boxSize) {
        int size = board.length;

        for (int i = 0; i < size; i++) {
            if (board[row][i] == num || board[i][col] == num) return false;
        }

        int startRow = (row / boxSize) * boxSize;
        int startCol = (col / boxSize) * boxSize;
        for (int i = 0; i < boxSize; i++) {
            for (int j = 0; j < boxSize; j++) {
                if (board[startRow + i][startCol + j] == num) return false;
            }
        }

        return true;
    }

    public static void shuffleArray(int[] array) {
        for (int i = array.length - 1; i > 0; i--) {
            int j = (int) (Math.random() * (i + 1));
            int temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }
    }

    public static void removeCells(int[][] board, int holes) {
        int size = board.length;
        int removed = 0;
        while (removed < holes) {
            int row = (int) (Math.random() * size);
            int col = (int) (Math.random() * size);

            if (board[row][col] != 0) {
                board[row][col] = 0;
                removed++;
            }
        }
    }

    public static class SudokuGenerator {
        private final int size;
        private final int boxSize;

        public SudokuGenerator(int size, int boxSize) {
            this.size = size;
            this.boxSize = boxSize;
        }

        public int[][] generateBoard() {
            int[][] board = new int[size][size];
            solveSudoku(board, boxSize);
            return board;
        }
    }
}
