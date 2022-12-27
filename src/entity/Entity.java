package entity;

import hearts.Hearts;
import tile.TileManager;
import level.Level;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.HashMap;

/**
 * Entity is an abstract class containing the
 * state and behavior of a given entity on the game.
 */
public abstract class Entity {

    /** The current number of lives of the entity.*/
    protected Hearts hearts;
    /** The x,y-coordinate position of the entity.*/
    protected Point2D.Float entityCoordinate;
    /** The width of the entity in pixels.*/
    protected int bitWidth;
    /** The height of the entity in pixels.*/
    protected int bitHeight;
    /** The constant movement speed of the entity.*/
    protected float movementSpeed;
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
    protected Rectangle2D.Float hitBox;
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
     * @param xPosition         The starting x-coordinate position of the entity.
     * @param yPosition         The starting y-coordinate position of the entity.
     * @param bitWidth          The width of the entity sprite in pixels.
     * @param bitHeight         The height of the entity sprite in pixels.
     * @param entityScale       The scale value scaling the appearance of the entity.
     * @param maxNumberOfHearts The maximum number of hearts of the entity.
     */
    public Entity(int xPosition, int yPosition, int bitWidth, int bitHeight, float entityScale, int maxNumberOfHearts) {
        this.entityCoordinate = new Point2D.Float(xPosition, yPosition);
        this.bitWidth = bitWidth;
        this.bitHeight = bitHeight;
        this.entityScale = entityScale;
        this.hearts = new Hearts(maxNumberOfHearts);
        this.hitBox = new Rectangle2D.Float();
    }

    /**
     * renderEntity | Displays the entity on the game screen.
     * @param graphics  The graphics object that draws images on the game screen.
     * @param xOffset   The x-value offset of the entity on the game screen.
     * @param yOffset   The y-value offset of the entity on the game screen.
     */
    public void renderEntity(Graphics2D graphics, int xOffset, int yOffset) {
        BufferedImage playerImage = animations.get(animationState)[(int) Math.floor(animationCounter)];
        int width = (int) (bitWidth*entityScale);
        int height = (int) (bitHeight*entityScale);
        if (!facingRight) {
            graphics.drawImage(playerImage, (int) (entityCoordinate.x +width-xOffset), (int) (entityCoordinate.y -yOffset), -width, height, null);
        } else {
            graphics.drawImage(playerImage, (int) (entityCoordinate.x -xOffset), (int) (entityCoordinate.y -yOffset), width, height, null);
        }
        renderHitBox(graphics, xOffset, yOffset);
        hearts.displayHearts(graphics, this, xOffset, yOffset);
    }

    /**
     * renderHitBox displays the hit box of the player on the game screen.
     * @param graphics The graphics object that draws images on the game screen.
     */
    protected void renderHitBox(Graphics2D graphics, int xOffset, int yOffset) {
        graphics.setColor(Color.GREEN);
        graphics.drawRect((int) (hitBox.x-xOffset), (int) (hitBox.y-yOffset), (int) (hitBox.width*entityScale), (int) (hitBox.height*entityScale));
    }

    /**
     * updateEntity | Updates the current state of the entity.
     * @param level         The current level of the game.
     * @param tileManager   The TileManager containing data about the tiles of the game/level.
     */
    public void updateEntity(Level level, TileManager tileManager) {
        updateHitBox();
        updateMovement(level, tileManager);
        updateAnimation();
    }

    /**
     * updateHitBox updates the hit box of the entity.
     */
    abstract protected void updateHitBox();

    /**
     * updateMovement | Updates the movement of the entity.
     * @param level         The current level of the game.
     * @param tileManager   The TileManager containing data about the tiles of the game/level.
     */
    abstract protected void updateMovement(Level level, TileManager tileManager);

    /**
     * getAnimationImages fetches the animation states and images of the entity.
     */
    abstract protected void getAnimationImages();

    /**
     * updateAnimation updates the current animation state of the entity.
     */
    abstract protected void updateAnimation();

    /**
     * getEntityCoordinate fetches the x,y-coordinate position of the entity.
     * @return Returns the x,y-coordinate position of the entity.
     */
    public Point2D.Float getEntityCoordinate() {return entityCoordinate;}

    /**
     * getEntityScale fetches the scale of the entity on the game.
     * @return Returns the scaling factor of the entity.
     */
    public float getEntityScale() {
        return entityScale;
    }

    /**
     * getBitWidth fetches the bit width of the entity.
     * @return Returns the bit width of the entity.
     */
    public int getBitWidth() {return bitWidth;}

    /**
     * getHitBox fetches the hit box of the entity on the game.
     * @return Returns a Rectangle
     */
    public Rectangle2D.Float getHitBox() {
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

    /**
     * setMovementSpeed fetches the movement speed of the entity.
     */
    public void setMovementSpeed(float movementSpeed) {
        this.movementSpeed = movementSpeed;
    }
}