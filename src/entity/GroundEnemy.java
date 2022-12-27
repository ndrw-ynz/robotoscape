package entity;

import level.Level;
import tile.TileManager;

import static utility.PlayUtils.*;

/**
 * GroundEnemy is an abstract class extending the class Enemy.
 * GroundEnemy contains the state and behavior of the ground-based enemies found in the game.
 */
public abstract class GroundEnemy extends Enemy {

    /**
     * GroundEnemy is the superclass of all moving and interactive ground-based enemy entities
     * found in the game.
     * @param xPosition                     The starting x-coordinate position of the enemy.
     * @param yPosition                     The starting y-coordinate position of the enemy.
     * @param bitWidth                      The width of the enemy sprite in pixels.
     * @param bitHeight                     The height of the enemy sprite in pixels.
     * @param attentionAreaDiameterFactor   The scaling factor for the diameter of the attention area of the enemy.
     * @param entityScale                   The scale value scaling the appearance of the enemy.
     * @param maxNumberOfHearts             The maximum number of hearts of the entity.
     */
    public GroundEnemy(int xPosition, int yPosition, int bitWidth, int bitHeight, float attentionAreaDiameterFactor, float entityScale, int maxNumberOfHearts) {
        super(xPosition, yPosition, bitWidth, bitHeight, attentionAreaDiameterFactor, entityScale, maxNumberOfHearts);
    }

    @Override
    protected void updateMovement(Level level, TileManager tileManager) {
        float xSpeed = facingRight ? movementSpeed : -movementSpeed;
        /*
         * The entity cannot move left/right based on its movement speed.
         * Checks if there is still some space between the entities left and right space.
         * Changes face direction of the entity if it does.
         */
        if (!canEntityMove(entityCoordinate.x + xSpeed, entityCoordinate.y, this, level, tileManager)) {
            xSpeed = getEntityXOffset(entityCoordinate.x + xHitBoxDelta, !facingRight);
            facingRight = !facingRight;
        }

        if (isEntityOnEdge(entityCoordinate.x, entityCoordinate.y, !facingRight, this, level, tileManager)){
            facingRight = !facingRight;
            return;
        }

        // If the entity can move at that direction, add the xSpeed to its x-coordinate position
        if(canEntityMove(entityCoordinate.x + xSpeed, entityCoordinate.y, this, level, tileManager)) {
            entityCoordinate.x += xSpeed;
        }
    }
}