package entity;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashMap;

/**
 * Entity is an abstract class containing the
 * state and behavior of a given entity on the game.
 */
public abstract class Entity {

    /** The x-coordinate position of the entity.*/
    protected int xPos;
    /** The y-coordinate position of the entity.*/
    protected int yPos;
    /** The width of the entity in pixels.*/
    protected int bitWidth;
    /** The height of the entity in pixels.*/
    protected int bitHeight;
    /** The constant movement speed of the entity in the x-axis.*/
    protected int movementSpeed;
    /** The condition determining if the entity is facing right. */
    boolean facingRight;
    /**
     *  A HashMap containing the entity's animation state as a String key and its
     *  sequence of animation images as an array of BufferedImage.
     */
    protected HashMap<String, BufferedImage[]> animations = new HashMap<>();
    /**
     *  The current animation state of the entity.
     */
    protected String animationState;
    /**
     * A double used as a counter for transitioning between each animation frame
     * on a given animation state.
     */
    protected double animationCounter;
    /**
     * The scaling factor of the entity as it is displayed on the game screen.
     */
    protected int entityScale;
    /**
     * The hit box of an entity.
     */
    protected Rectangle hitBox;
    /**
     * The x-coordinate distance between an entity's x-coordinate position and
     * the actual x-coordinate position of an entity's hit box.
     */
    protected int xHitBoxDelta;
    /**
     * The y-coordinate distance between an entity's y-coordinate position and
     * the actual y-coordinate position of an entity's hit box.
     */
    protected int yHitBoxDelta;

    public Entity(int xPos, int yPos, int bitWidth, int bitHeight, int entityScale) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.bitWidth = bitWidth;
        this.bitHeight = bitHeight;
        this.entityScale = entityScale;
        this.hitBox = new Rectangle();
    }

    /**
     * renderHitBox displays the hit box of the player on the game screen.
     * @param graphics The graphics object that draws images on the game screen.
     */
    abstract protected void renderHitBox(Graphics2D graphics);

    /**
     * updateHitBox updates the hit box of the player.
     */
    abstract protected void updateHitBox();

    /**
     * getXPos fetches the x-coordinate position of the entity.
     * @return Returns the x-coordinate position of the entity.
     */
    public int getxPos() {
        return xPos;
    }

    /**
     * getYPos fetches the y-coordinate position of the entity.
     * @return Returns the y-coordinate position of the entity.
     */
    public int getyPos() {
        return yPos;
    }

    /**
     * getMovementSpeed fetches the movement speed of the entity.
     * @return Returns the movement speed of the entity.
     */
    public int getMovementSpeed() {
        return movementSpeed;
    }

    /**
     * getEntityScale fetches the scale of the entity on the game.
     * @return Returns the scaling factor of the entity.
     */
    public int getEntityScale() {
        return entityScale;
    }

    /**
     * getHitBox fetches the hit box of the entity on the game.
     * @return Returns a Rectangle
     */
    public Rectangle getHitBox() {
        return hitBox;
    }

    /**
     * getxHitBoxDelta fetches the x-coordinate distance of an entity's
     * hit box from its x-coordinate position.
     * @return Returns the x-coordinate distance from the player's x-coordinate
     * position to its hit box's x-coordinate position.
     */
    public int getxHitBoxDelta() {
        return xHitBoxDelta;
    }

    /**
     * getyHitBoxDelta fetches the y-coordinate distance of an entity's
     * hit box from its y-coordinate position.
     * @return Returns the y-coordinate distance from the player's y-coordinate
     * position to its hit box's y-coordinate position.
     */
    public int getyHitBoxDelta() {
        return yHitBoxDelta;
    }

}