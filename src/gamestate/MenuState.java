package gamestate;

import main.Game;
import menu.Menu;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

/**
 * The MenuState class extends the class State and implements
 * StateMethods. MenuState keeps the state of the menu of the
 * game.
 */
public class MenuState extends State implements StateMethods {

    /**Menu object containing the components of the menu state.*/
    private final Menu menu;

    /**
     * MenuState | Initializes the menu state of the game.
     * @param game The main game containing the different states of the
     *             game and their configuration.
     */
    public MenuState(Game game) {
        super(game);
        this.menu = new Menu(game);
    }

    @Override
    public void render(Graphics graphics) {
        menu.renderMenu(graphics);
    }

    @Override
    public void update() {

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        menu.updateState();
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        menu.updateInteractiveText(e.getX(), e.getY());
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
