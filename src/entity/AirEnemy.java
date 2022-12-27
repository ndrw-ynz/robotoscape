package entity;

import level.Level;
import tile.TileManager;

import java.awt.geom.Point2D;

import static utility.PlayUtils.getEntityCenterHitBox;

/**
 * AirEnemy is an abstract class extending the class Enemy.
 * AirEnemy contains the state and behavior of the air-based enemies found in the game.
 */
public abstract class AirEnemy extends Enemy{

    protected Point2D.Float targetPoint;

    /**
     * AirEnemy is the superclass of all moving and interactive air-based enemy entities
     * found in the game.
     * @param xPosition                     The starting x-coordinate position of the enemy.
     * @param yPosition                     The starting y-coordinate position of the enemy.
     * @param bitWidth                      The width of the enemy sprite in pixels.
     * @param bitHeight                     The height of the enemy sprite in pixels.
     * @param attentionAreaDiameterFactor   The scaling factor for the diameter of the attention area of the enemy.
     * @param entityScale                   The scale value scaling the appearance of the enemy.
     * @param maxNumberOfHearts             The maximum number of hearts of the entity.
     */
    public AirEnemy(int xPosition, int yPosition, int bitWidth, int bitHeight, float attentionAreaDiameterFactor, float entityScale, int maxNumberOfHearts) {
        super(xPosition, yPosition, bitWidth, bitHeight, attentionAreaDiameterFactor, entityScale, maxNumberOfHearts);
        targetPoint = new Point2D.Float(xPosition, yPosition);
    }

    @Override public void active() {
        // change movementspeed?
    }

    @Override public void passive() {
        // change movementspeed?
        targetPoint = getEntityCenterHitBox(this);
    }

    @Override
    public void updateActivity(Level level, Player player) {
        // todo: get target point
        isActive = attentionArea.intersects(player.getHitBox());
        if (isActive) {
            targetPoint = getEntityCenterHitBox(player);
            facingRight = targetPoint.x > getEntityCenterHitBox(this).x;
            active();
        } else {
            passive();
        }
    }

    @Override
    protected void updateMovement(Level level, TileManager tileManager) {
        // TODO: add movement, from target point (if active)
        if(!isActive) return;
        double deltaX = targetPoint.x - getEntityCoordinate().x;
        double deltaY = targetPoint.y - getEntityCoordinate().y;
        entityCoordinate.x += deltaX*movementSpeed;
        entityCoordinate.y += deltaY*movementSpeed;
    }
}
