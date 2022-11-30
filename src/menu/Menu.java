package menu;

import gamestate.GameState;
import main.Game;
import utility.Atlas;

import java.awt.*;

import static utility.MenuUtils.isWithinBoundary;

/**
 * The Menu class contains the contents of the menu of the game.
 */
public class Menu {
    /**Comprises the entire component of the game and manages its states.*/
    private final Game game;
    /**The title text of the menu. */
    private final MenuText titleText;
    /**The interactive start text of the menu. */
    private final MenuInteractiveText startText;
    /**The interactive credits text of the menu. */
    private final MenuInteractiveText creditsText;
    /**The interactive exit text of the menu. */
    private final MenuInteractiveText exitText;

    /**
     * Menu | Initializes the menu component of the game.
     * @param game The main game containing the different states of the
     *             game and their configuration.
     */
    public Menu(Game game) {
        this.game = game;

        int titleY = 120; // y-coordinate position of the title text.
        int titleSize = 120; // font size of the title text.

        int selectionsTotalHeight = 240; // The total height occupied by the interactive text.
        int selectionsY = (game.getScreenHeight() - titleY+titleSize - selectionsTotalHeight) / 2 + titleY; // The start of the y-coordinate position of the interactive text.

        int numSelections = 3;  // The number of interactive text in selection.
        int selectionHeight = selectionsTotalHeight / numSelections; // The height for each interactive text in selection.

        titleText = new MenuText(titleY, "Robotoscape", titleSize, Atlas.TARRGET_FONT); // Initializes titleText.
        startText = new MenuInteractiveText(selectionsY, "Start", 60, Atlas.TARRGET_FONT); // Initializes startText
        creditsText = new MenuInteractiveText(selectionsY + selectionHeight, "Credits", 60, Atlas.TARRGET_FONT); // Initializes creditsText.
        exitText = new MenuInteractiveText(selectionsY + selectionHeight*2, "Exit", 60, Atlas.TARRGET_FONT); // Initializes exitText.
    }

    /**
     * renderMenu | Renders the components of the menu on the game screen.
     * @param graphics The graphics object that draws images on the game screen.
     */
    public void renderMenu(Graphics graphics) {
        titleText.renderText(graphics, game.getScreenWidth());
        startText.renderText(graphics, game.getScreenWidth());
        creditsText.renderText(graphics, game.getScreenWidth());
        exitText.renderText(graphics, game.getScreenWidth());
    }

    /**
     * updateInteractiveText | Sets the activity status of the interactive
     * text in the menu based on the position of the mouse on the screen.
     * @param x The x-coordinate position of the mouse.
     * @param y The y-coordinate position of the mouse.
     */
    public void updateInteractiveText(int x, int y) {
        startText.setIsActive(isWithinBoundary(x, y, startText.getBoundaryBox()));
        creditsText.setIsActive(isWithinBoundary(x, y, creditsText.getBoundaryBox()));
        exitText.setIsActive(isWithinBoundary(x, y, exitText.getBoundaryBox()));
    }

    /**
     * updateState | Changes the game state based on the option clicked
     * by the user on an active interactive text.
     */
    public void updateState() {
        if(startText.getIsActive()) {
            GameState.state = GameState.PLAY;
        } else if (creditsText.getIsActive()) {
            // Change game state to credits
        } else if (exitText.getIsActive()) {
            game.exitGame();
        }
    }
}
