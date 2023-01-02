package gameoverlay;

import main.Game;
import text.InteractiveText;
import text.RegularText;

import java.awt.*;

/**
 * The GameOverOverlay class extends the Overlay class.
 * It contains the contents for the game over overlay of the game.
 */
public class GameOverOverlay extends Overlay{

//    private final RegularText gameOverText;
//    private final InteractiveText exitText;

    /**
     * GameOverOverlay instantiates an overlay that displays the game over overlay of the game.
     * @param game          The main game containing the different states of the game and their configuration.
     * @param windowWidth   The width of the overlay window in pixels.
     * @param windowHeight  The height of the overlay window in pixels.
     */
    public GameOverOverlay(Game game, int windowWidth, int windowHeight) {
        super(game, windowWidth, windowHeight);
//        this.gameOverText = new RegularText();
//        this.exitText = new InteractiveText();
    }

    @Override
    protected void drawOverlayText(Graphics2D graphics) {

    }

    @Override
    public void updateInteractiveText(int x, int y) {

    }

    @Override
    public void updateState() {

    }
}
