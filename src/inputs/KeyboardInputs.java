package inputs;

import gamestate.GameState;
import main.GamePanel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyboardInputs implements KeyListener {
    // ** TEMP
    // DON'T ALTER COMPONENTS FROM THIS CLASS. ONLY SET VARIABLES, AND HAVE
    // GETTERS FOR EACH VARIABLE TO BE USED IN update() from Game.java.
    // **
    private final GamePanel gamePanel;

    public KeyboardInputs(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch(GameState.state) {
            case MENU:
                gamePanel.getGame().getMenuState().keyPressed(e);
                break;
            case PLAY:
                gamePanel.getGame().getPlayingState().keyPressed(e);
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch(GameState.state) {
            case MENU:
                gamePanel.getGame().getMenuState().keyReleased(e);
                break;
            case PLAY:
                gamePanel.getGame().getPlayingState().keyReleased(e);
                break;
        }
    }
}
