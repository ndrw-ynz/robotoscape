package main;

import inputs.KeyboardInputs;
import inputs.MouseInputs;

import javax.swing.*;
import java.awt.*;

/**
 * The GamePanel class extends the JPanel class, and is the
 * component of the game where the different states of the game
 * and their components are displayed.
 */
public class GamePanel extends JPanel{

    protected final KeyboardInputs keyboardInputs;
    protected final MouseInputs mouseInputs;
    private final Game game;

    /**
     * GamePanel initializes the JPanel component of the game.
     * @param game The main game containing the different states of the
     *             game and their configuration.
     */
    public GamePanel(Game game) {
        this.game = game;
        // Determine size of panel.
        this.mouseInputs = new MouseInputs(this);
        this.keyboardInputs = new KeyboardInputs(this);
        addKeyListener(keyboardInputs);
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);
        // Configures state of GamePanel.
        this.setPreferredSize(new Dimension(game.SCREEN_WIDTH, game.SCREEN_HEIGHT));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.setFocusable(true);
        this.requestFocus(); // input focus to gamePanel, allowing interactions
    }

    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        game.renderGame(graphics);
        graphics.dispose();
    }

    public void update() {

    }

    public KeyboardInputs getKeyboardInputs() {
        return keyboardInputs;
    }

    public MouseInputs getMouseInputs() {
        return mouseInputs;
    }

    public Game getGame() {
        return game;
    }

}
