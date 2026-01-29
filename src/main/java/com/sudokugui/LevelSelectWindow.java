package com.sudokugui;

import com.sudokugui.Board.SudokuGenerator;

import javax.swing.*;
import java.awt.*;

/**
 *
 * @author KomlosyT
 */


public class LevelSelectWindow extends JFrame {
    public LevelSelectWindow(int size) {
        setTitle("Sudoku - Nehézségszint");
        setSize(300, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(4, 1));

        JLabel label = new JLabel("Válassz nehézségi szintet:", SwingConstants.CENTER);
        add(label);

        JButton easyBtn = new JButton("Könnyű");
        JButton mediumBtn = new JButton("Közepes");
        JButton hardBtn = new JButton("Nehéz");

        add(easyBtn);
        add(mediumBtn);
        add(hardBtn);

        easyBtn.addActionListener(e -> startGame(calculateHoles("easy", size), size));
        mediumBtn.addActionListener(e -> startGame(calculateHoles("medium", size), size));
        hardBtn.addActionListener(e -> startGame(calculateHoles("hard", size), size));

        setVisible(true);
    }

    private int calculateHoles(String difficulty, int size) {
    int totalCells = size * size;
    double ratio;

    switch (difficulty.toLowerCase()) {
        case "easy": ratio = 0.40; break;    
        case "medium": ratio = 0.60; break; 
        case "hard": ratio = 0.70; break; 
        default: ratio = 0.60; 
    }

    return (int) Math.round(totalCells * ratio);
}
    
    private void startGame(int holes, int size) {
    int boxSize = (int) Math.sqrt(size);
    Board.SudokuGenerator generator = new Board.SudokuGenerator(size, boxSize);
    int[][] solution = generator.generateBoard();

    int[][] puzzle = new int[size][size];
    for (int i = 0; i < size; i++) {
        System.arraycopy(solution[i], 0, puzzle[i], 0, size);
    }

    Board.removeCells(puzzle, holes);
    new GUI(puzzle, solution, size, boxSize);
    dispose();
    }
}
