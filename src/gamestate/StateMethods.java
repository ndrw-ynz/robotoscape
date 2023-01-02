package gamestate;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

/**
 * StateMethods is an interface implementing the methods
 * for the different states of the game.
 */
public interface StateMethods {
    /**
     * render | Displays the different components of a given state
     * on the game screen.
     * @param graphics The graphics object that draws state components on the game screen.
     */
    void render(Graphics graphics);

    /**
     * update | Updates the states of the game.
     */
    void update();

    /**
     * mouseClicked | Executes events following the click of the user's mouse.
     * @param e A MouseEvent containing information about user mouse input.
     */
    void mouseClicked(MouseEvent e);

    /**
     * mousePressed | Executes events following the pressed buttons of the user's mouse.
     * @param e A MouseEvent containing information about user mouse input.
     */
    void mousePressed(MouseEvent e);

    /**
     * mouseReleased | Executes events following the release of the buttons of the user's mouse.
     * @param e A MouseEvent containing information about user mouse input.
     */
    void mouseReleased(MouseEvent e);

    /**
     * mouseMoved | Executes events following the movement of the user's mouse.
     * @param e A MouseEvent containing information about user mouse input.
     */
    void mouseMoved(MouseEvent e);

    /**
     * keyPressed | Executes events following the press of the keys of the user's keyboard.
     * @param e A KeyEvent containing information about user keyboard input.
     */
    void keyPressed(KeyEvent e);

    /**
     * keyReleased | Executes events following the release of the keys of the user's keyboard.
     * @param e A KeyEvent containing information about user keyboard input.
     */
    void keyReleased(KeyEvent e);
}
