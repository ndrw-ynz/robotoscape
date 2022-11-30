package inputs;

import gamestate.GameState;
import main.GamePanel;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MouseInputs implements MouseListener, MouseMotionListener {

    private final GamePanel gamePanel;
    public MouseInputs(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }
    @Override
    public void mouseClicked(MouseEvent e) {
        switch(GameState.state) {
            case MENU:
                gamePanel.getGame().getMenuState().mouseClicked(e);

        }

    }

    @Override
    public void mousePressed(MouseEvent e) {
        switch(GameState.state) {
            case PLAY:
                gamePanel.getGame().getPlayingState().mousePressed(e);

        }

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        switch(GameState.state) {
            case PLAY:
                gamePanel.getGame().getPlayingState().mouseReleased(e);
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        switch(GameState.state) {
            case MENU:
                gamePanel.getGame().getMenuState().mouseMoved(e);
        }
    }
}
