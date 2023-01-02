package text;

import utility.Atlas;

import java.awt.*;

import static utility.TextUtils.getTextCenterXPosition;

/**
 * The class RegularText is used to create text
 * displayed on the menu of the game.
 */
public class RegularText {

    /**The y-coordinate position of the text.*/
    protected final int y;
    /**A String containing the text to be displayed.*/
    protected final String text;
    /**The size of the text.*/
    protected final int textSize;
    /**The alpha value of the text.*/
    protected final int alphaValue;
    /**The font family of the text.*/
    protected final Font font;

    /**
     * RegularText initializes the text used in the game.
     * @param y The y-coordinate position of the text.
     * @param text A String containing the text to be displayed.
     * @param textSize The size of the text.
     * @param alphaValue The alpha value of the text.
     * @param fontFamily The font family of the text.
     */
    public RegularText(int y, String text, int textSize, int alphaValue, String fontFamily) {
        this.y = y;
        this.text = text;
        this.textSize = textSize;
        this.alphaValue = alphaValue;
        this.font = Atlas.getFont(fontFamily).deriveFont(Font.PLAIN, textSize);
    }

    /**
     * renderText | Displays the text on the game screen.
     * @param graphics The graphics object that draws images on the game screen.
     * @param screenWidth The width of the game screen in pixels.
     */
    public void renderText(Graphics graphics, int screenWidth) {
        Color color = new Color(255, 255, 255, alphaValue);
        int x = getTextCenterXPosition(text, font, graphics, screenWidth);
        graphics.setFont(font);
        graphics.setColor(color);
        graphics.drawString(text, x, y);
    }
}
