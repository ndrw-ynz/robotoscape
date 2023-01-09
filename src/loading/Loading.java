package loading;

import level.Level;
import main.Game;
import utility.Atlas;

import java.awt.*;

import static utility.LoadingUtils.*;

/**
 * Loading is a class that stores the components of the
 * loading state of the game.
 */
public class Loading {
    /**Comprises the entire component of the game and manages its states.*/
    private final Game game;
    /**The loading text to be displayed on the game screen.*/
    private final String loadingText;
    /**Counter used to create blinking effect on loading text.*/
    private float blinkingCounter;
    /**The alpha value of the loading text, altered during start and end phase of loading phase.*/
    private double alphaValue;
    /**The x-coordinate position of the loading text at the designated x-axis position of the loading text*/
    private int xLoadingPosition;
    /**The y-coordinate position of the loading text at the designated y-axis position  of the loading text.*/
    private int yLoadingPosition;
    /**Determines if the values of xLoadingPosition and yLoadingPosition are set.*/
    private boolean isPositionSet;
    /**The starting time of when the class Loading is initialized.*/
    private final long loadingInitTime;
    /**The x-axis offset speed in transitioning from the initial loading screen placement to its destination on the level. */
    private double xOffsetSpeed;
    /**The y-axis offset speed in transitioning from the initial loading screen placement to its destination on the level. */
    private double yOffsetSpeed;

    /**
     * Loading | Initializes the loading component of the game.
     * @param level The current level of the game and its state.
     */
    public Loading(Game game, Level level) {
        this.game = game;
        String numToText = "";
        switch(level.getLevelNumber()) {
            case 1 -> numToText = "One";
            case 2 -> numToText = "Two";
            case 3 -> numToText = "Three";
            case 4 -> numToText = "Four";
            case 5 -> numToText = "Five";
            case 6 -> numToText = "Six";
            case 7 -> numToText = "Seven";
            case 8 -> numToText = "Eight";
            case 9 -> numToText = "Nine";
            case 10 -> numToText = "Ten";
        }
        loadingText = "Level " + numToText;
        loadingInitTime = game.getGameTime();
        LoadingPhase.phase = LoadingPhase.START;
    }

    /**
     * renderLoading | Displays the loading text on the game screen.
     * @param graphics The graphics object that draws state components on the game screen.
     * @param levelDimension The dimension of the current level of the game.
     */
    public void renderLoading(Graphics graphics, Dimension levelDimension) {
        blinkingCounter += 0.5;
        if (blinkingCounter > 5) blinkingCounter = 0;
        Font font = Atlas.getFont(Atlas.ROBUS_FONT).deriveFont(Font.PLAIN, 100);
        Color color = blinkingCounter < 2.5 ? new Color(255, 255, 0, (int) alphaValue) : new Color(255, 0, 255, (int) alphaValue);
        int x = getLoadingTextCenterXPosition(loadingText, font, graphics, game.getScreenWidth());
        int y = getLoadingTextCenterYPosition(loadingText, font, graphics, game.getScreenHeight());
        graphics.setFont(font);
        graphics.setColor(color);
        graphics.drawString(loadingText, x, y);
        /*
        * Sets up xLoadingPosition and yLoadingPosition if the position hasn't been set yet.
        * This is on renderLoading because graphics is required for setting up, which is not
        * available at the constructor.
        * */
        if(!isPositionSet) {
            xLoadingPosition = levelDimension.width/2 - game.getScreenWidth()/2;
            yLoadingPosition = levelDimension.height/2 - game.getScreenHeight()/2;
            isPositionSet = true;
        }
    }

    /**
     * updateLoadingPhase | Updates the current LoadingPhase.
     * @param currentTime The current time since the initialization of Loading in nanoseconds.
     */
    public void updateLoadingPhase(double currentTime) {
        double timeDifference = (currentTime - loadingInitTime) / 1000000000.0;
        if (timeDifference < 2) {
            LoadingPhase.phase = LoadingPhase.START;
        } else if (timeDifference <= 8) {
            LoadingPhase.phase = LoadingPhase.TRANSITION;
        } else if (timeDifference <= 11){
            LoadingPhase.phase = LoadingPhase.END;
        } else {
            LoadingPhase.phase = LoadingPhase.NONE;
        }
    }

    /**
     * updateAlphaValue | Updates the alphaValue of the loading text.
     * @param transitionToOpacity The condition for if the text is transitioning to opacity.
     */
    public void updateAlphaValue(boolean transitionToOpacity) {
        double time = transitionToOpacity ? 3 * game.getCurrentUpdates() : 2 * game.getCurrentUpdates(); // UPS of game x duration in second
        double speed = (255) / time;
        alphaValue += transitionToOpacity ? -speed : speed;
        if (alphaValue < 0) alphaValue = 0;
        if (alphaValue > 255) alphaValue = 255;
    }

    /**
     * setOffsetSpeeds | Determines and sets the values of the xOffsetSpeed and yOffsetSpeed
     * between two points in a 6-second time interval.
     * @param startX The x-coordinate position of the starting point.
     * @param startY The y-coordinate position of the starting point.
     * @param endX The x-coordinate position of the destination point.
     * @param endY The y-coordinate position of the destination point.
     */
    public void setOffsetSpeeds(int startX, int startY, int endX, int endY) {
        double deltaX = endX - startX;
        double deltaY = endY - startY;
        double direction = Math.atan2(deltaY, deltaX);

        xOffsetSpeed = (Math.abs(deltaX) / game.getCurrentUpdates()*.3) * Math.cos(direction);
        yOffsetSpeed = (Math.abs(deltaY) / game.getCurrentUpdates()*.3) * Math.sin(direction);

        System.out.println("xOffsetSpeed : " + xOffsetSpeed + " yOffsetSpeed : " + yOffsetSpeed);
    }

    /**
     * getxLoadingPosition | Fetches the x-coordinate position of the loading text.
     * @return Returns the x-coordinate position of the loading text.
     */
    public int getxLoadingPosition() {return xLoadingPosition;}

    /**
     * getyLoadingPosition | Fetches the y-coordinate position of the loading text.
     * @return Returns the y-coordinate position of the loading text.
     */
    public int getyLoadingPosition() {return yLoadingPosition;}

    /**
     * getxOffsetSpeed | Fetches the x-axis offset speed of the initial loading screen to the destination.
     * @return Returns the x-axis offset speed from the initial loading screen placement to the destination.
     */
    public double getxOffsetSpeed() {return xOffsetSpeed;}

    /**
     * getyOffsetSpeed | Fetches the y-axis offset speed of the initial loading screen to the destination.
     * @return Returns the y-axis offset speed from the initial loading screen placement to the destination.
     */
    public double getyOffsetSpeed() {return yOffsetSpeed;}

}
