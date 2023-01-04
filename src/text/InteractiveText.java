package text;

import java.awt.*;

import static utility.TextUtils.*;

/**
 * MenuInteractiveText is a class extending class MenuText,
 * containing additional functionalities for interaction with
 * the text.
 */
public class InteractiveText extends RegularText {

    /**The interactive box surrounding the boundaries of the text.*/
    private Rectangle boundaryBox;
    /**Determines if the boundary box of the interactive text is set.*/
    private boolean isBoundaryBoxSet;
    /**The color of the text.*/
    private Color color;
    /**The condition of the text as it is active when the mouse is overing the text.*/
    private boolean isActive;
    /**Counter used to create blinking effect on menu text.*/
    protected float blinkingCounter;

    /**
     * MenuInteractiveText initializes the text with interactive properties displayed
     * in the menu.
     * @param y The y-coordinate position of the text.
     * @param text A String containing the text to be displayed.
     * @param textSize The size of the text.
     * @param alphaValue The alpha value of the text.
     * @param fontFamily The font family of the text.
     */
    public InteractiveText(int y, String text, int textSize, int alphaValue, String fontFamily) {
        super(y, text, textSize, alphaValue, fontFamily);
        color = new Color(255, 255, 255, alphaValue);
    }

    @Override
    public void renderText(Graphics graphics, int screenWidth) {
        blinkingCounter += 0.2;
        if (blinkingCounter > 5) blinkingCounter = 0;
        color = (isActive && blinkingCounter < 2.5) ? Color.YELLOW : Color.MAGENTA;
        if (!isActive) color = new Color(255, 255, 255, alphaValue);
        int x = getTextCenterXPosition(text, font, graphics, screenWidth);
        int widthText = getTextWidth(text, font, graphics);
        int heightText = getTextHeight(text, font, graphics);
        boundaryBox = new Rectangle(x, y-textSize, widthText, heightText);
        isBoundaryBoxSet = true;
        graphics.setFont(font);
        graphics.setColor(color);
        graphics.drawString(text, x, y);
    }

    /**
     * getBoundaryBox fetches the boundary box surrounding the interactive text.
     * @return Returns the boundary box of the interactive text.
     */
    public Rectangle getBoundaryBox() {return boundaryBox;}

    /**
     * setIsActive sets the value of the activity of the interactive text.
     * @param isActive The active status of the interactive text.
     */
    public void setIsActive(boolean isActive) {this.isActive = isActive;}

    /**
     * isActive fetches the activity status of the interactive text.
     * @return Returns the activity status of the interactive text.
     */
    public boolean isActive() {return this.isActive;}

    /**
     * isBoundaryBoxSet fetches the value of isBoundarySet of the interactive text.
     * @return Returns the value of isBoundarySet of the interactive text.
     */
    public boolean isBoundaryBoxSet() {return this.isBoundaryBoxSet;}
}
