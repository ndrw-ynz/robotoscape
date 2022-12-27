package entity;

import level.Level;
import tile.TileManager;

import java.awt.*;

/**
 * StationaryEnemy is an abstract class extending the class Enemy.
 * StationaryEnemy contains the state and behavior of stationary enemies found in the game.
 */
public class StationaryEnemy extends Enemy {
    /**
     * StationaryEnemy is the superclass of all stationary enemy entities
     * in the levels of the game.
     *
     * @param xPosition                     The starting x-coordinate position of the enemy.
     * @param yPosition                     The starting y-coordinate position of the enemy.
     * @param bitWidth                      The width of the enemy sprite in pixels.
     * @param bitHeight                     The height of the enemy sprite in pixels.
     * @param attentionAreaDiameterFactor   The scaling factor for the radius of the attention area of the enemy.
     * @param entityScale                   The scale value scaling the appearance of the enemy.
     * @param maxNumberOfHearts             The maximum number of hearts of the entity.
     */
    public StationaryEnemy(int xPosition, int yPosition, int bitWidth, int bitHeight, float attentionAreaDiameterFactor, float entityScale, int maxNumberOfHearts) {
        super(xPosition, yPosition, bitWidth, bitHeight, attentionAreaDiameterFactor, entityScale, maxNumberOfHearts);
    }

    @Override
    public void active() {

    }

    @Override
    public void passive() {

    }

    @Override
    protected void updateActivity(Level level, Player player) {

    }

    @Override
    protected void updateAttentionArea() {

    }

    @Override
    public void renderAttentionArea(Graphics2D graphics, int xOffset, int yOffset) {

    }

    @Override
    protected void updateHitBox() {

    }

    @Override
    protected void updateMovement(Level level, TileManager tileManager) {

    }

    @Override
    protected void getAnimationImages() {

    }

    @Override
    protected void updateAnimation() {

    }
}
