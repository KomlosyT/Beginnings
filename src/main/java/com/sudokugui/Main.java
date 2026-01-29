package com.sudokugui;

import javax.swing.SwingUtilities;

/**
 *
 * @author KomlosyT
 */

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(BoardSizeWindow::new);
    }
}
