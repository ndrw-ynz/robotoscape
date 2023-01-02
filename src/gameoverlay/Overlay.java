package gameoverlay;

import main.Game;

import java.awt.*;

/**
 * The Overlay class is an abstract class containing the state and behavior of
 * an overlay window in the game.
 */
public abstract class Overlay {
    /**Comprises the entire component of the game and manages its state.*/
    protected Game game;
    /**The width of the overlay window in pixels.*/
    protected int windowWidth;
    /**The height of the overlay window in pixels.*/
    protected int windowHeight;
    /**The x,y-coordinate position of the overlay window.*/
    protected Point windowCoordinate;

    /**
     * Overlay is the superclass of all types of overlays displayed in the game.
     * @param game          The main game containing the different states of the game and their configuration.
     * @param windowWidth   The width of the overlay window in pixels.
     * @param windowHeight  The height of the overlay window in pixels.
     */
    public Overlay(Game game, int windowWidth, int windowHeight) {
        this.game = game;
        this.windowWidth = windowWidth;
        this.windowHeight = windowHeight;
        this.windowCoordinate = new Point((game.getScreenWidth() - windowWidth)/2,  (game.getScreenHeight() - windowHeight)/2);

    }

    /**
     * drawOverlayWindow displays the overlay window to the game screen.
     * @param graphics  The graphics object that draws images on the game screen.
     */
    protected void drawOverlayWindow(Graphics2D graphics) {
        graphics.setColor(new Color(0, 0, 0, 230));
        graphics.fillRoundRect(windowCoordinate.x, windowCoordinate.y, windowWidth, windowHeight, 15, 15);

        graphics.setColor(new Color(255, 255, 255, 200));
        graphics.setStroke(new BasicStroke(3));
        graphics.drawRoundRect(windowCoordinate.x + 5, windowCoordinate.y + 5, windowWidth - 10, windowHeight - 10, 5, 5);
    }

    /**
     * drawOverlayText displays the text of the overlay window to the game screen.
     * @param graphics  The graphics object that draws images on the game screen.
     */
    protected abstract void drawOverlayText(Graphics2D graphics);

    /**
     * updateInteractiveText sets the activity status of the interactive text in the overlay
     * window based on the x,y-coordinate position of the mouse on the game screen.
     * @param x The x-coordinate position of the mouse.
     * @param y The y-coordinate position of the mouse.
     */
    public abstract void updateInteractiveText(int x, int y);

    /**
     * updateState updates the game state based on the interactive text present
     * on the overlay window.
     */
    public abstract void updateState();
}
