package entity;

import utility.Atlas;

/**
 * The Drone class is an air-based enemy entity class that contains
 * the state and behavior of the Drone enemy entity in the game.
 */
public class Drone extends AirEnemy{

    /**
     * Drone is an air-based enemy entity spawned throughout the game.
     * @param xPosition                     The starting x-coordinate position of the Drone entity.
     * @param yPosition                     The starting y-coordinate position of the Drone entity
     * @param bitWidth                      The width of the Drone entity in pixels.
     * @param bitHeight                     The height of the Drone entity in pixels.
     * @param attentionAreaDiameterFactor   The diameter of the attention area of the Drone entity.
     * @param entityScale                   The scale value scaling the appearance of the Drone entity.
     * @param maxNumberOfHearts             The maximum number of hearts of the Drone entity.
     */
    public Drone(int xPosition, int yPosition, int bitWidth, int bitHeight, float attentionAreaDiameterFactor, float entityScale, int maxNumberOfHearts) {
        super(xPosition, yPosition, bitWidth, bitHeight, attentionAreaDiameterFactor, entityScale, maxNumberOfHearts);
        setMovementSpeed(0.004f);
        animationState = "active";
        getAnimationImages();
    }

    @Override
    protected void updateHitBox() {
        xHitBoxDelta = (int) (4*entityScale);
        yHitBoxDelta = (int) (4*entityScale);

        hitBox.x = entityCoordinate.x + xHitBoxDelta;
        hitBox.y = entityCoordinate.y + yHitBoxDelta;
        hitBox.width = 7;
        hitBox.height = 4;
    }

    @Override
    protected void updateAnimation() {
        animationCounter += 0.04;
        if (animationCounter > animations.get(animationState).length) {
            animationCounter = 0.0;
        }
    }

    @Override
    protected void getAnimationImages() {
        animations.put("active", Atlas.extractAnimationImages(Atlas.ENEMY_DRONE, 16, 16));
    }
}
