package utility;

import java.awt.*;

/**
 * LoadingUtils is a class containing utility methods
 * for the display of the loading sequence of the game.
 */
public abstract class LoadingUtils {

    /**
     * getLoadingTextCenterXPosition | Determines the x-coordinate position of the loading
     * text on the game screen.
     * @param text The loading text.
     * @param font The font of the loading text.
     * @param graphics The graphics object that draws state components on the game screen.
     * @param screenWidth The width of the game screen.
     * @return Returns the x-coordinate position of the text.
     */
    public static int getLoadingTextCenterXPosition(String text, Font font, Graphics graphics, int screenWidth){
        int textWidth = (int) graphics.getFontMetrics(font).getStringBounds(text, graphics).getWidth();
        return (screenWidth - textWidth)/2;
    }

    /**
     * getLoadingTextCenterYPosition | Determines the y-coordinate position of the loading
     * text on the game screen.
     * @param text The loading text.
     * @param font The font of the loading text.
     * @param graphics The graphics object that draws state components on the game screen.
     * @param screenHeight The height of the game screen.
     * @return Returns the y-coordinate position of the text.
     */
    public static int getLoadingTextCenterYPosition(String text, Font font, Graphics graphics, int screenHeight) {
        int textHeight = (int) graphics.getFontMetrics(font).getStringBounds(text, graphics).getHeight();
        return (screenHeight - textHeight)/3;
    }
}
