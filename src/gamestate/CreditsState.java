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

    private final Credits credits;

    public CreditsState(Game game) {
        super(game);
        this.credits = new Credits(game);
    }

    @Override
    public void render(Graphics graphics) {
        credits.renderCredits(graphics);
    }

    @Override
    public void update() {

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        credits.updateState();
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        credits.updateInteractiveText(e.getX(), e.getY());
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}