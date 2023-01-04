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
 * The PauseOverlay class extends the Overlay class.
 * It contains the contents for the pause overlay of the game.
 */
public class PauseOverlay extends Overlay{

    /**The pause text of the pause overlay.*/
    private final RegularText pauseText;
    /**The interactive continue text of the pause overlay.*/
    private final InteractiveText continueText;
    /**The interactive exit text of the pause overlay.*/
    private final InteractiveText exitText;

    /**
     * PauseOverlay instantiates an overlay that displays the pause overlay of the game.
     * @param game          The main game containing the different states of the game and their configuration.
     * @param windowWidth   The width of the overlay window in pixels.
     * @param windowHeight  The height of the overlay window in pixels.
     */
    public PauseOverlay(Game game, PlayState playState, int windowWidth, int windowHeight) {
        super(game, playState, windowWidth, windowHeight);
        int textSpacePadding = 35;
        int textSpaceHeight = windowHeight - textSpacePadding*2;
        int bigTextHeight = 50;
        int allocatedMainSpacing = 63;
        int selectionsTextHeight = 35;

        int textYStart = ((game.getScreenHeight() - textSpaceHeight)/2 + textSpacePadding);

        this.pauseText = new RegularText(textYStart, "Game Paused", bigTextHeight, 200, Atlas.TARRGET_FONT);
        this.continueText = new InteractiveText(textYStart+allocatedMainSpacing, "continue", selectionsTextHeight, 200, Atlas.TARRGET_FONT);
        this.exitText = new InteractiveText(textYStart+allocatedMainSpacing+selectionsTextHeight+20, "Exit", selectionsTextHeight, 200, Atlas.TARRGET_FONT);
    }

    @Override
    protected void drawOverlayText(Graphics2D graphics) {
        pauseText.renderText(graphics, game.getScreenWidth());
        continueText.renderText(graphics, game.getScreenWidth());
        exitText.renderText(graphics, game.getScreenWidth());
    }

    @Override
    public void updateInteractiveText(int x, int y) {
        if (continueText.isBoundaryBoxSet()) continueText.setIsActive(isWithinBoundary(x, y, continueText.getBoundaryBox()));
        if (exitText.isBoundaryBoxSet()) exitText.setIsActive(isWithinBoundary(x, y, exitText.getBoundaryBox()));
    }

    @Override
    public void updateState() {
        if (continueText.isActive()) {
            playState.setIsPaused(false);
        } else if (exitText.isActive()) {
            GameState.state = GameState.MENU;
            game.resetPlayState();
        }
    }
}
