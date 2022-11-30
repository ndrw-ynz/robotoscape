package main;

import javax.swing.*;

public class GameWindow extends JFrame {

    public GameWindow(GamePanel panel) {
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Robotoscape");

        window.add(panel);

        window.pack(); // Fit inside window

        window.setLocationRelativeTo(null); // Place window at center

        window.setVisible(true);// Make window visible
    }
}
