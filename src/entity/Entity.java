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
    protected int xPosition;
    /** The y-coordinate position of the entity.*/
    protected int yPosition;
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
    protected float entityScale;
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

    /**
     * Entity is the superclass of all moving and interactive entities
     * in the levels of the game.
     * @param xPosition The starting x-coordinate position of the player.
     * @param yPosition The starting y-coordinate position of the player.
     * @param bitWidth The width of the player sprite in pixels.
     * @param bitHeight The height of the player sprite in pixels.
     * @param entityScale The scale value scaling the appearance of the player.
     */
    public Entity(int xPosition, int yPosition, int bitWidth, int bitHeight, float entityScale) {
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.bitWidth = bitWidth;
        this.bitHeight = bitHeight;
        this.entityScale = entityScale;
        this.hitBox = new Rectangle();
    }

    /**
     * renderEntity | Displays the entity on the game screen.
     * @param graphics The graphics object that draws images on the game screen.
     * @param xOffset The x-value offset of the entity on the game screen.
     * @param yOffset The y-value offset of the entity on the game screen.
     */
    public void renderEntity(Graphics2D graphics, int xOffset, int yOffset) {
        BufferedImage playerImage = animations.get(animationState)[(int) Math.floor(animationCounter)];
        int width = (int) (bitWidth*entityScale);
        int height = (int) (bitHeight*entityScale);
        if (!facingRight) {
            graphics.drawImage(playerImage, xPosition +width-xOffset,  yPosition -yOffset, -width, height, null);
        } else {
            graphics.drawImage(playerImage, xPosition -xOffset,  yPosition -yOffset, width, height, null);
        }
        renderHitBox(graphics, xOffset, yOffset);
//        graphics.setColor(Color.GREEN);
//        graphics.drawRect(hitBox.x-xOffset, hitBox.y-yOffset, (int) (hitBox.width*entityScale), (int) (hitBox.height*entityScale));
    }

    /**
     * renderHitBox displays the hit box of the player on the game screen.
     * @param graphics The graphics object that draws images on the game screen.
     */
    public void renderHitBox(Graphics2D graphics, int xOffset, int yOffset) {
        graphics.setColor(Color.GREEN);
        graphics.drawRect(hitBox.x-xOffset, hitBox.y-yOffset, (int) (hitBox.width*entityScale), (int) (hitBox.height*entityScale));
    }

    /**
     * updateHitBox updates the hit box of the entity.
     */
    abstract protected void updateHitBox();

    /**
     * getAnimationImages fetches the animation states and images of the entity.
     */
    abstract protected void getAnimationImages();

    /**
     * updateAnimation updates the current animation state of the entity.
     */
    abstract protected void updateAnimation();

    /**
     * getXPos fetches the x-coordinate position of the entity.
     * @return Returns the x-coordinate position of the entity.
     */
    public int getxPosition() {
        return xPosition;
    }

    /**
     * getYPos fetches the y-coordinate position of the entity.
     * @return Returns the y-coordinate position of the entity.
     */
    public int getyPosition() {
        return yPosition;
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
    public float getEntityScale() {
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