package gameoverlay;

import gamestate.GameState;
import gamestate.PlayState;
import main.Game;
import text.InteractiveText;
import text.RegularText;
import utility.Atlas;

import java.awt.*;

import static utility.TextUtils.isWithinBoundary;

/**
 * The GameOverOverlay class extends the Overlay class.
 * It contains the contents for the game over overlay of the game.
 */
public class GameOverOverlay extends Overlay{

    /**The game over text of the game over overlay.*/
    private final RegularText gameOverText;
    /**The interactive restart text of the game over overlay.*/
    private final InteractiveText restartText;
    /**The interactive exit text of the game over overlay.*/
    private final InteractiveText exitText;

    /**
     * GameOverOverlay instantiates an overlay that displays the game over overlay of the game.
     * @param game          The main game containing the different states of the game and their configuration.
     * @param windowWidth   The width of the overlay window in pixels.
     * @param windowHeight  The height of the overlay window in pixels.
     */
    public GameOverOverlay(Game game, PlayState playState, int windowWidth, int windowHeight) {
        super(game, playState, windowWidth, windowHeight);
        this.gameOverText = new RegularText(338, "Game Over", 50, 200, Atlas.TARRGET_FONT);
        this.restartText = new InteractiveText(398, "Restart", 40, 200, Atlas.TARRGET_FONT);
        this.exitText = new InteractiveText(458, "Exit", 40, 200, Atlas.TARRGET_FONT);
    }

    @Override
    protected void drawOverlayText(Graphics2D graphics) {
        gameOverText.renderText(graphics, game.getScreenWidth());
        restartText.renderText(graphics, game.getScreenWidth());
        exitText.renderText(graphics, game.getScreenWidth());
    }

    @Override
    public void updateInteractiveText(int x, int y) {
        if (restartText.isBoundaryBoxSet()) restartText.setIsActive(isWithinBoundary(x, y, restartText.getBoundaryBox()));
        if (exitText.isBoundaryBoxSet()) exitText.setIsActive(isWithinBoundary(x, y, exitText.getBoundaryBox()));
    }

    @Override
    public void updateState() {
        if (restartText.isActive()) {
            playState.restartPlayState();
            playState.setIsPaused(false);
        }
        if (exitText.isActive()) {
            GameState.state = GameState.MENU;
            game.resetPlayState();
        }
    }
}
