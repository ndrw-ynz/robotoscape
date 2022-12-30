package entity;

import level.Level;
import utility.Atlas;

import java.awt.*;

/**
 * The Turret class is a stationary enemy entity class that contains
 * the state and behavior of the Turret enemy entity in the game.
 */
public class Turret extends StationaryEnemy{

    /**
     * Turret is a stationary enemy entity spawned throughout the game.
     * @param xPosition                     The starting x-coordinate position of the Turret entity.
     * @param yPosition                     The starting y-coordinate position of the Turret entity
     * @param bitWidth                      The width of the Turret entity in pixels.
     * @param bitHeight                     The height of the Turret entity in pixels.
     * @param attentionAreaDiameterFactor   The diameter of the attention area of the Turret entity.
     * @param entityScale                   The scale value scaling the appearance of the Turret entity.
     * @param damageValue                   The value of damage dealt by the enemy.
     * @param maxNumberOfHearts             The maximum number of hearts of the Turret entity.
     */
    public Turret(int xPosition, int yPosition, int bitWidth, int bitHeight, float attentionAreaDiameterFactor, float entityScale, int damageValue, int maxNumberOfHearts) {
        super(xPosition, yPosition, bitWidth, bitHeight, attentionAreaDiameterFactor, entityScale, damageValue, maxNumberOfHearts);
        animationState = "passive";
        getAnimationImages();
    }

    @Override public void active() {

    }

    @Override public void passive() {

    }

    @Override
    protected void updateActivity(Level level, Player player) {

    }

    @Override
    protected void updateHitBox() {
        xHitBoxDelta = (int) (4*entityScale);
        yHitBoxDelta = (int) (5*entityScale);

        hitBox.x = entityCoordinate.x + xHitBoxDelta;
        hitBox.y = entityCoordinate.y + yHitBoxDelta;
        hitBox.width = 8;
        hitBox.height = 10;
    }

    @Override
    protected void updateAnimation() {
        animationCounter += 0.04;
        if (animationCounter > animations.get(animationState).length) {
            animationCounter = 0.0;
        }
    }

    @Override
    public void renderAttentionArea(Graphics2D graphics, double xOffset, double yOffset) {

    }

    @Override
    protected void getAnimationImages() {
        animations.put("charging", Atlas.extractAnimationImages(Atlas.ENEMY_TURRET_CHARGING, 16, 16));
        animations.put("passive", Atlas.extractAnimationImages(Atlas.ENEMY_TURRET_PASSIVE, 16, 16));
    }
}
