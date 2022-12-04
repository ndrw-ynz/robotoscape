package menu;

import java.awt.*;

import static utility.MenuUtils.*;

/**
 * MenuInteractiveText is a class extending class MenuText,
 * containing additional functionalities for interaction with
 * the text.
 */
public class MenuInteractiveText extends MenuText{

    /**The interactive box surrounding the boundaries of the text.*/
    private Rectangle boundaryBox;
    /**The color of the text.*/
    private Color color;
    /**The condition of the text as it is active when the mouse is overing the text.*/
    private boolean isActive;


    /**
     * MenuInteractiveText | Initializes the text with interactive properties displayed
     * in the menu.
     * @param y The y-coordinate position of the text.
     * @param text A String containing the text to be displayed.
     * @param textSize The size of the text.
     * @param fontFamily The font family of the text.
     */
    public MenuInteractiveText(int y, String text, int textSize, String fontFamily) {
        super(y, text, textSize, fontFamily);
        color = Color.WHITE;
    }

    @Override
    public void renderText(Graphics graphics, int screenWidth) {
        blinkingCounter += 0.2;
        if (blinkingCounter > 5) blinkingCounter = 0;
        color = (isActive && blinkingCounter < 2.5) ? Color.YELLOW : Color.MAGENTA;
        if (!isActive) color = Color.WHITE;
        int x = getTextCenterXPosition(text, font, graphics, screenWidth);
        int widthText = getTextWidth(text, font, graphics);
        int heightText = getTextHeight(text, font, graphics);
        boundaryBox = new Rectangle(x, y-textSize, widthText, heightText);
        graphics.setFont(font);
        graphics.setColor(color);
        graphics.drawString(text, x, y);
    }

    /**
     * getBoundaryBox | Fetches the boundary box surrounding the interactive text.
     * @return Returns the boundary box of the interactive text.
     */
    public Rectangle getBoundaryBox() {return boundaryBox;}

    /**
     * setIsActive | Sets the value of the activity of the interactive text.
     * @param isActive The active status of the interactive text.
     */
    public void setIsActive(boolean isActive) {this.isActive = isActive;}

    /**
     * getIsActive | Fetches the activity status of the interactive text.
     * @return Returns the activityy status of the interactive text.
     */
    public boolean getIsActive() {return this.isActive;}
}
