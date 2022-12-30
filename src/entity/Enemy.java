package entity;

import level.Level;
import tile.TileManager;

import java.awt.*;
import java.awt.geom.Ellipse2D;

import static utility.PlayUtils.getEntityCenterHitBox;

/**
 * Enemy is an abstract class that extends the class Entity.
 * Enemy contains the state and behavior of enemy entities of the game.
 */
public abstract class Enemy extends Entity{

    /**The activity state of the enemy instance.*/
    protected boolean isActive;
    /**The scaling factor determining the diameter of attentionArea.*/
    protected float attentionAreaDiameterFactor;
    /**The domain area of the enemy that determines the activity status of the enemy as the player's hitBox intersects with the attentionArea.*/
    protected Ellipse2D.Float attentionArea;

    /**
     * Enemy is the superclass of all moving and interactive enemy entities
     * in the levels of the game.
     * @param xPosition                     The starting x-coordinate position of the enemy.
     * @param yPosition                     The starting y-coordinate position of the enemy.
     * @param bitWidth                      The width of the enemy sprite in pixels.
     * @param bitHeight                     The height of the enemy sprite in pixels.
     * @param attentionAreaDiameterFactor   The scaling factor for the diameter of the attention area of the enemy.
     * @param entityScale                   The scale value scaling the appearance of the enemy.
     * @param damageValue                   The value of damage dealt by the enemy.
     * @param maxNumberOfHearts             The maximum number of hearts of the enemy.
     */
    public Enemy(int xPosition, int yPosition, int bitWidth, int bitHeight, float attentionAreaDiameterFactor, float entityScale, int damageValue, int maxNumberOfHearts) {
        super(xPosition, yPosition, bitWidth, bitHeight, entityScale, damageValue, maxNumberOfHearts);
        this.attentionAreaDiameterFactor = attentionAreaDiameterFactor;
        attentionArea = new Ellipse2D.Float(0, 0, attentionAreaDiameterFactor*bitWidth*entityScale+15, attentionAreaDiameterFactor*bitHeight*entityScale+15);
    }

    /**
     * Sets the active state of the enemy.
     */
    public abstract void active();

    /**
     * Sets the passive state of the enemy.
     */
    public abstract void passive();

    /**
     * updateActivity updates the activity status of an enemy.
     * @param level The current level of the game.
     */
    protected void updateActivity(Level level, Player player) {
        isActive = attentionArea.intersects(player.getHitBox());
        if (isActive) {
            facingRight = getEntityCenterHitBox(player).x > getEntityCenterHitBox(this).x;
            active();
        } else {
            passive();
        }
    }

    /**
     * updateAttentionArea updates the x,y-coordinate of the attentionArea of the enemy.
     */
    protected void updateAttentionArea() {
        Point.Float centerHitBox = getEntityCenterHitBox(this);
        attentionArea.x = (int) (centerHitBox.x-(attentionArea.width)/2);
        attentionArea.y = (int) (centerHitBox.y-(attentionArea.height)/2);
    }

    /**
     * updateEnemy updates the enemy-specific state and behavior of the entity.
     * @param level The current level of the game.
     * @param tileManager The TileManager containing data about the tiles of the game/level.
     * @param player The player of the game.
     */
    public void updateEnemy(Level level, TileManager tileManager, Player player) {
        updateEntity(level, tileManager);
        updateActivity(level, player);
    }

    @Override
    public void updateEntity(Level level, TileManager tileManager) {
        super.updateEntity(level, tileManager);
        updateAttentionArea();
    }

    /**
     * renderAttentionArea renders the attentionArea of the enemy on the game screen.
     * @param graphics The graphics object that draws images on the game screen.
     * @param xOffset The x-value offset of the entity on the game screen.
     * @param yOffset The y-value offset of the entity on the game screen.
     */
    public void renderAttentionArea(Graphics2D graphics, double xOffset, double yOffset) {
        Ellipse2D.Float renderArea = attentionArea;
        renderArea.x -= xOffset;
        renderArea.y -= yOffset;
        graphics.setColor(Color.MAGENTA);
        graphics.draw(renderArea);
    }
}