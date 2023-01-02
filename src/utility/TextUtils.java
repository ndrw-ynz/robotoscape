package utility;

import java.awt.*;

/**
 * TextUtils is a class containing utility methods for
 * use in the menu state of the game.
 */
public abstract class TextUtils {

    /**
     * isWithinBoundary checks if the given cartesian coordinate position of the mouse
     * is within the boundaries of the boundary box.
     * @param mouseX The x-coordinate of the mouse on the game screen.
     * @param mouseY The y-coordinate of the mouse on the game screen.
     * @param textBoundaryBox The boundary box of concern on the menu.
     * @return Returns a boolean condition determining if the mouse is within
     * the boundary of the boundary box on the menu or not.
     */
    public static boolean isWithinBoundary(int mouseX, int mouseY, Rectangle textBoundaryBox) {
        int topX = textBoundaryBox.x;
        int topY = textBoundaryBox.y;
        int bottomX = topX + textBoundaryBox.width;
        int bottomY = topY + textBoundaryBox.height;
        return (mouseX > topX && mouseY > topY) && (mouseX < bottomX && mouseY < bottomY);
    }

    /**
     * getTextCenterXPosition determines the x-coordinate position of a text
     * as it is placed in the center of the game screen.
     * @param text The text to be displayed.
     * @param font The font of the text.
     * @param graphics The graphics object that draws images on the game screen.
     * @param screenWidth The total width of the game screen.
     * @return Returns the x-coordinate position for the text to be displayed at the center of the game screen.
     */
    public static int getTextCenterXPosition(String text, Font font, Graphics graphics, int screenWidth){
        int textWidth = (int) graphics.getFontMetrics(font).getStringBounds(text, graphics).getWidth();
        return (screenWidth - textWidth)/2;
    }

    /**
     * getTextWidth determines the width of the text according to its font family.
     * @param text A string containing the text.
     * @param font The font of the text.
     * @param graphics The graphics object that draws images on the game screen.
     * @return Returns the width of the text.
     */
    public static int getTextWidth(String text, Font font, Graphics graphics) {
        return (int) graphics.getFontMetrics(font).getStringBounds(text, graphics).getWidth();
    }

    /**
     * getTextHeight determines the height of the text according to its font family.
     * @param text A string containing the text.
     * @param font The font of the text.
     * @param graphics The graphics object that draws images on the game screen.
     * @return Returns the height of the text.
     */
    public static int getTextHeight(String text, Font font, Graphics graphics) {
        return (int) graphics.getFontMetrics(font).getStringBounds(text, graphics).getHeight();
    }
}