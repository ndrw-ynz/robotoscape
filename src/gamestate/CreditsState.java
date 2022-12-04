package gamestate;

import credits.Credits;
import main.Game;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

/**
 * The CreditsState class extends the State class and implements
 * StateMethods.
 * It maintains the credits state of the game as the game displays
 * the credits of the game on the game screen.
 */
public class CreditsState extends State implements StateMethods {

    /**The credits object */
    private Credits credits;

    public CreditsState(Game game) {
        super(game);
        this.credits = new Credits(game);
    }

    /**
     * render | Displays the different components of a given state
     * on the game screen.
     *
     * @param graphics The graphics object that draws state components on the game screen.
     */
    @Override
    public void render(Graphics graphics) {

    }

    /**
     * update | Updates the states of the game.
     */
    @Override
    public void update() {

    }

    /**
     * mouseClicked | Executes events following the click of the user's mouse.
     *
     * @param e A MouseEvent containing information about user mouse input.
     */
    @Override
    public void mouseClicked(MouseEvent e) {

    }

    /**
     * mousePressed | Executes events following the pressed buttons of the user's mouse.
     *
     * @param e A MouseEvent containing information about user mouse input.
     */
    @Override
    public void mousePressed(MouseEvent e) {

    }

    /**
     * mouseReleased | Executes events following the release of the buttons of the user's mouse.
     *
     * @param e A MouseEvent containing information about user mouse input.
     */
    @Override
    public void mouseReleased(MouseEvent e) {

    }

    /**
     * mouseMoved | Executes events following the movement of the user's mouse.
     *
     * @param e A MouseEvent containing information about user mouse input.
     */
    @Override
    public void mouseMoved(MouseEvent e) {

    }

    /**
     * keyPressed | Executes events following the press of the keys of the user's keyboard.
     *
     * @param e A KeyEvent containing information about user keyboard input.
     */
    @Override
    public void keyPressed(KeyEvent e) {

    }

    /**
     * keyReleased | Executes events following the release of the keys of the user's keyboard.
     *
     * @param e A KeyEvent containing information about user keyboard input.
     */
    @Override
    public void keyReleased(KeyEvent e) {

    }
}
