package utility;

import java.awt.*;

/**
 * LoadingUtils is a class containing utility methods
 * for the display of the loading sequence of the game.
 */
public abstract class LoadingUtils {

    /**
     * getLoadingTextCenterXPosition determines the x-coordinate position of the loading
     * text on the game screen.
     * @param   text The loading text.
     * @param   font The font of the loading text.
     * @param   graphics The graphics object that draws state components on the game screen.
     * @param   screenWidth The width of the game screen.
     * @return  Returns the x-coordinate position of the text.
     */
    public static int getLoadingTextCenterXPosition(String text, Font font, Graphics graphics, int screenWidth){
        int textWidth = (int) graphics.getFontMetrics(font).getStringBounds(text, graphics).getWidth();
        return (screenWidth - textWidth)/2;
    }

    /**
     * getLoadingTextCenterYPosition determines the y-coordinate position of the loading
     * text on the game screen.
     * @param text          The loading text.
     * @param font          The font of the loading text.
     * @param graphics      The graphics object that draws state components on the game screen.
     * @param screenHeight  The height of the game screen.
     * @return              Returns the y-coordinate position of the text.
     */
    public static int getLoadingTextCenterYPosition(String text, Font font, Graphics graphics, int screenHeight) {
        int textHeight = (int) graphics.getFontMetrics(font).getStringBounds(text, graphics).getHeight();
        return (screenHeight - textHeight)/3;
    }

    /**
     * getScreenDestination determines the x,y-coordinate destination of the screen based on the x,y-coordinate position of
     * the player, the dimension of the level, and the dimension of the game screen.
     * @param playerCoordinate  The x,y-coordinate position of the player in the level.
     * @param levelDimension    The dimension of the level.
     * @param screenDimension   The dimension of the game screen.
     * @return                  Returns a Point containing the x,y-coordinate destination.
     */
    public static Point getScreenDestination(Point playerCoordinate, Dimension levelDimension, Dimension screenDimension) {
        int xDestination = playerCoordinate.x - screenDimension.width/2;
        int yDestination = playerCoordinate.y - screenDimension.height/2;
        if (xDestination < 0) xDestination = 0;
        if (xDestination > levelDimension.width) xDestination = levelDimension.width - screenDimension.width;
        if (yDestination < 0) yDestination = 0;
        if (yDestination > levelDimension.height) yDestination = levelDimension.height - screenDimension.height;
        return new Point(xDestination, yDestination);
    }
}
