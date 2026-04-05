package dnaanalyzer;

import javax.swing.SwingUtilities;

import dnaanalyzer.ui.MainFrame;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainFrame frame = new MainFrame();
            frame.setVisible(true);
        });
    }
}
