package com.sudokugui;

import javax.swing.*;
import java.awt.*;

/**
 *
 * @author KomlosyT
 */

public class GUI extends JFrame {
    private static final int SIZE = 9;
    private JTextField[][] cells = new JTextField[SIZE][SIZE];
    private int[][] solutionBoard;
    private int remainingHints = 3; 
    private Timer timer;
    private int elapsedSeconds = 0;
    private JLabel timerLabel = new JLabel("Idő: 0:00");
    
    public GUI(int[][] gameBoard, int[][] solutionBoard, int size, int boxSize) {
        this.solutionBoard = solutionBoard;
        this.cells = new JTextField[size][size];

        JPanel gridPanel = new JPanel(new GridLayout(size, size));
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));

        JButton checkButton = new JButton("Ellenőrzés");
        JButton helpButton = new JButton("Tipp(" + remainingHints + ")");
        JButton backButton = new JButton("Vissza");

        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 5));
        infoPanel.add(timerLabel);
        
        setTitle("Sudoku Játék");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 600);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);

        

        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                JTextField cell = new JTextField();
                cell.setHorizontalAlignment(JTextField.CENTER);
                cell.setFont(new Font("SansSerif", Font.BOLD, 30));

                int value = gameBoard[row][col];
                if (value != 0) {
                    cell.setText(String.valueOf(value));
                    cell.setEditable(false);
                    cell.setBackground(Color.LIGHT_GRAY);
                } else {
                    final int r = row;
                    final int c = col;
                    cell.addActionListener(e -> {
                        try {
                            int input = Integer.parseInt(cell.getText());
                            if (input == solutionBoard[r][c]) {
                                cell.setBackground(Color.GREEN);
                                cell.setEditable(false);
                            } else {
                                cell.setBackground(Color.PINK);
                            }
                        } catch (NumberFormatException ex) {
                            cell.setBackground(Color.RED);
                        }
                    });
                }
                int top = (row % boxSize == 0) ? 3 : 1;
                int left = (col % boxSize == 0) ? 3 : 1;
                int bottom = (row == size - 1) ? 3 : 1;
                int right = (col == size - 1) ? 3 : 1;

                cell.setBorder(BorderFactory.createMatteBorder(top, left, bottom, right, Color.BLACK));
                

                cells[row][col] = cell;
                gridPanel.add(cell);
            }
        }
        
        timer = new Timer(1000, e -> {
            elapsedSeconds++;
            int minutes = elapsedSeconds / 60;
            int seconds = elapsedSeconds % 60;
            timerLabel.setText(String.format("Idő: %d:%02d", minutes, seconds));
        });
        timer.start();
        
        backButton.addActionListener(e -> {
            dispose(); 
            new BoardSizeWindow(); // Megnyitja a főmenüt újra
        });        
        
        
        helpButton.addActionListener(e -> {
           if (remainingHints <= 0) {
               JOptionPane.showMessageDialog(this, "Elfogytak a tippek.");
               return;
           }

           java.util.List<int[]> emptyCells = new java.util.ArrayList<>();

           for (int row = 0; row < SIZE; row++) {
               for (int col = 0; col < SIZE; col++) {
                   if (cells[row][col].getText().isEmpty()) {
                       emptyCells.add(new int[]{row, col});
                   }
               }
           }

           if (emptyCells.isEmpty()) {
               JOptionPane.showMessageDialog(this, "Nincs kitöltetlen mező, nincs hova tippet adni.");
               return;
           }

           java.util.Random rand = new java.util.Random();
           int[] targetCell = emptyCells.get(rand.nextInt(emptyCells.size()));

           int row = targetCell[0];
           int col = targetCell[1];
           cells[row][col].setText(String.valueOf(solutionBoard[row][col]));
           cells[row][col].setEditable(false);
           cells[row][col].setBackground(new Color(173, 216, 230)); // világoskék szín

           remainingHints--;
           helpButton.setText("Tipp (" + remainingHints + ")");

           if (remainingHints == 0) {
               helpButton.setEnabled(false);
           }
       });


        
        checkButton.addActionListener(e -> {
            boolean allCorrect = true;
            boolean allFilled = true;

            for (int row = 0; row < SIZE; row++) {
                for (int col = 0; col < SIZE; col++) {
                    String text = cells[row][col].getText();
                    if (text.isEmpty()) {
                        allFilled = false;
                        allCorrect = false; // üres = nem kész még
                        continue;
                    }
                    try {
                        int value = Integer.parseInt(text);
                        if (value != solutionBoard[row][col]) {
                            cells[row][col].setBackground(Color.PINK);
                            allCorrect = false;
                        }
                    } catch (NumberFormatException ex) {
                        cells[row][col].setBackground(Color.RED);
                        allCorrect = false;
                    }
                }
            }

            if (allCorrect && allFilled) {
                JOptionPane.showMessageDialog(this, "Gratulálunk, helyes a megoldás!");
            } else if (allCorrect) {
                JOptionPane.showMessageDialog(this, "Eddig jó, de még van kitöltetlen mező!");
            } else {
                JOptionPane.showMessageDialog(this, "Van néhány hiba, nézd át újra!");
            }

        if (allCorrect && allFilled) {
            timer.stop(); // időmérés leállítása
            JOptionPane.showMessageDialog(this, "Gratulálunk, helyes a megoldás!\nIdő: " + timerLabel.getText());
        }

        });

        buttonPanel.add(backButton);
        buttonPanel.add(checkButton);
        buttonPanel.add(helpButton);
        add(infoPanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.SOUTH);
        add(gridPanel, BorderLayout.CENTER);
        setVisible(true);
    }
}
