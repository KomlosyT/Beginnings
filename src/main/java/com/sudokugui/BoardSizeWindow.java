package com.sudokugui;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

/**
 *
 * @author KomlosyT
 */
public class BoardSizeWindow extends JFrame {
    public BoardSizeWindow(){
        
        setTitle("Sudoku - Nehézségszint");
        setSize(300, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(4, 1));

        JLabel label = new JLabel("Válassz Sudoku fajtát:", SwingConstants.CENTER);
        add(label);

        JButton treeButton = new JButton("3x3");
        JButton fourButton = new JButton("4x4");
        JButton amorfButton = new JButton("Amorf");
        
        add(treeButton);
        add(fourButton);
        add(amorfButton);
        
        treeButton.addActionListener(e -> startLevelSelectWindow(9));
        fourButton.addActionListener(e -> startLevelSelectWindow(16));

        setVisible(true);
    }
    
    private void startLevelSelectWindow(int size) {
        
        LevelSelectWindow window = new LevelSelectWindow(size);
        
    }

}
