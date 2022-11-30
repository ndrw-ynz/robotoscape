package menu;

import utility.Atlas;

import java.awt.*;

import static utility.MenuUtils.getTextCenterXPosition;

/**
 * The class MenuText is used to create text
 * displayed on the menu of the game.
 */
public class MenuText {

    /**The y-coordinate position of the text.*/
    protected final int y;
    /**A String containing the text to be displayed.*/
    protected final String text;
    /**The size of the text.*/
    protected final int textSize;
    /**The font family of the text.*/
    protected final Font font;

    /**
     * MenuText | Initializes the text used in the menu.
     * @param y The y-coordinate position of the text.
     * @param text A String containing the text to be displayed.
     * @param textSize The size of the text.
     * @param fontFamily The font family of the text.
     */
    public MenuText(int y, String text, int textSize, String fontFamily) {
        this.y = y;
        this.text = text;
        this.textSize = textSize;
        this.font = Atlas.getFont(fontFamily).deriveFont(Font.PLAIN, textSize);
    }

    /**
     * renderText | Displays the text on the game screen.
     * @param graphics The graphics object that draws images on the game screen.
     * @param screenWidth The width of the game screen in pixels.
     */
    public void renderText(Graphics graphics, int screenWidth) {
        int x = getTextCenterXPosition(text, font, graphics, screenWidth);
        graphics.setFont(font);
        graphics.setColor(Color.WHITE);
        graphics.drawString(text, x, y);
    }
}
