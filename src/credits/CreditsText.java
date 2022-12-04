package credits;

import java.awt.*;

/**
 * The CreditsText class is used to create text
 * for the credits state of the game.
 */
public class CreditsText {

    /**The x-coordinate position of the text.*/
    protected final int x;
    /**The y-coordinate position of the text.*/
    protected final int y;
    /**The text to be displayed.*/
    protected final String text;
    /**The size of the text.*/
    protected final int textSize;
    /**The font family of the text.*/
    protected final Font font;

    public CreditsText(int x, int y, String text, int textSize, Font font) {
        this.x = x;
        this.y = y;
        this.text = text;
        this.textSize = textSize;
        this.font = font;

    }

    public void renderText(Graphics graphics) {
        graphics.setFont(font);
        graphics.setColor(Color.WHITE);
        graphics.drawString(text, x, y);
    }

}
