package credits;

import gamestate.GameState;
import main.Game;
import text.InteractiveText;
import text.RegularText;
import utility.Atlas;

import java.awt.*;

import static utility.TextUtils.isWithinBoundary;

/**
 * Credits is a class that stores the components of the
 * credits state of the game. The credit class displays
 * the asset creators of the game and its developer.
 */
public class Credits {
    /**Comprises the entire component of the game and manages its states.*/
    private final Game game;
    /**The header text for the fonts text.*/
    private final RegularText fontsHeaderText;
    /**The credits text for the Tarrget Font.*/
    private final RegularText fontsTarrgetFontCreditText;
    /**The credits text for the Robus Font.*/
    private final RegularText fontsRobusFontCreditText;
    /**The credits text for the Minimal Pixel Font.*/
    private final RegularText fontsMinimalPixelFontCreditText;
    /**The header text for the player credit text.*/
    private final RegularText playerHeaderText;
    /**The credits text for the player asset creator.*/
    private final RegularText playerCreditText;
    /**The header text for the enemy credit text.*/
    private final RegularText enemyHeaderText;
    /**The credits text for the enemy asset creator.*/
    private final RegularText enemyCreditText;
    /**The header text for the level credit text.*/
    private final RegularText levelTitleText;
    /**The credits text for the level asset creator.*/
    private final RegularText levelCreditText;
    /**The header text for the developer credit text.*/
    private final RegularText developerHeaderText;
    /**The credits text for the game developer.*/
    private final RegularText developerCreditText;

    /**The interactive back text of the credits.*/
    private final InteractiveText backButton;

    /**
     * Game | Initializes the credits component of the game.
     * @param game The main game containing the different states of the
     *             game and their configuration.
     */
    public Credits(Game game) {
        this.game = game;
        int headerTextHeight = 80;
        int creditTextHeight = 60;

        // Text for Fonts Credit
        fontsHeaderText = new RegularText(60, "Font Assets", headerTextHeight, 200, Atlas.MINIMAL_PIXEL_FONT);
        fontsTarrgetFontCreditText = new RegularText(110 , "Tarrget Font by Iconian Fonts", creditTextHeight, 200, Atlas.MINIMAL_PIXEL_FONT);
        fontsRobusFontCreditText = new RegularText(170, "Robus Font by Toko Laris Djaja", creditTextHeight, 200, Atlas.MINIMAL_PIXEL_FONT);
        fontsMinimalPixelFontCreditText = new RegularText(230, "Minimal Pixel Font by Mounir Tohami", creditTextHeight, 200, Atlas.MINIMAL_PIXEL_FONT);

        // Text for Player Assets Credit
        playerHeaderText = new RegularText(290, "Player Character Assets", headerTextHeight, 200, Atlas.MINIMAL_PIXEL_FONT);
        playerCreditText = new RegularText(340, "Robot by PenUsbMic", creditTextHeight, 200, Atlas.MINIMAL_PIXEL_FONT);

        // Text for Enemy Assets Credit
        enemyHeaderText = new RegularText(400, "Enemy Character Assets", headerTextHeight, 200, Atlas.MINIMAL_PIXEL_FONT);
        enemyCreditText = new RegularText(450, "Enemy by 0x72", creditTextHeight, 200, Atlas.MINIMAL_PIXEL_FONT);

        // Text for Level Tile Assets Credit
        levelTitleText = new RegularText(510, "Level Tiles Assets", headerTextHeight, 200, Atlas.MINIMAL_PIXEL_FONT);
        levelCreditText = new RegularText(560, "1-Bit Platformer Tiles by Kenney (Assets)", creditTextHeight, 200, Atlas.MINIMAL_PIXEL_FONT);

        // Text for Game Developer Credit
        developerHeaderText = new RegularText(620, "Game Developer", headerTextHeight, 200, Atlas.MINIMAL_PIXEL_FONT);
        developerCreditText = new RegularText(670, "ndrw-ynz (Andrew Albert A. Yanza)", creditTextHeight, 200, Atlas.MINIMAL_PIXEL_FONT);

        // Interactive Text serving as Back Button in Credits
        backButton = new InteractiveText(740, "Back", 100, 200, Atlas.MINIMAL_PIXEL_FONT);
    }

    /**
     * renderCredits renders the components of the credits on the game screen.
     * @param graphics The graphics object that draws images on the game screen.
     */
    public void renderCredits(Graphics graphics) {
        fontsHeaderText.renderText(graphics, game.getScreenWidth());
        fontsTarrgetFontCreditText.renderText(graphics, game.getScreenWidth());
        fontsRobusFontCreditText.renderText(graphics, game.getScreenWidth());
        fontsMinimalPixelFontCreditText.renderText(graphics, game.getScreenWidth());

        playerHeaderText.renderText(graphics, game.getScreenWidth());
        playerCreditText.renderText(graphics, game.getScreenWidth());

        enemyHeaderText.renderText(graphics, game.getScreenWidth());
        enemyCreditText.renderText(graphics, game.getScreenWidth());

        levelTitleText.renderText(graphics, game.getScreenWidth());
        levelCreditText.renderText(graphics, game.getScreenWidth());

        developerHeaderText.renderText(graphics, game.getScreenWidth());
        developerCreditText.renderText(graphics, game.getScreenWidth());

        backButton.renderText(graphics, game.getScreenWidth());
    }

    /**
     * updateInteractiveText sets the activity status of the interactive
     * text in the credits based on the x,y-coordinate position of the mouse on the game screen.
     * @param x The x-coordinate position of the mouse.
     * @param y The y-coordinate position of the mouse.
     */
    public void updateInteractiveText(int x, int y) {
        if (backButton.isBoundaryBoxSet()) backButton.setIsActive(isWithinBoundary(x, y, backButton.getBoundaryBox()));
    }

    /**
     * updateState changes the game state based on the option clicked
     * by the user on an active interactive text.
     */
    public void updateState() {
        if (backButton.isActive()) {
            GameState.state = GameState.MENU;
        }
    }
}