package entity;

import utility.Atlas;

/**
 * The RobotAir class is an air-based enemy entity class that contains
 * the state and behavior of the RobotAir enemy entity in the game.
 */
public class RobotAir extends AirEnemy{

    /**
     * RobotAir is an air-based enemy entity spawned throughout the game.
     * @param xPosition                     The starting x-coordinate position of the RobotAir entity.
     * @param yPosition                     The starting y-coordinate position of the RobotAir entity
     * @param bitWidth                      The width of the RobotAir entity in pixels.
     * @param bitHeight                     The height of the RobotAir entity in pixels.
     * @param attentionAreaDiameterFactor   The diameter of the attention area of the RobotAir entity.
     * @param entityScale                   The scale value scaling the appearance of the RobotAir entity.
     * @param maxNumberOfHearts             The maximum number of hearts of the RobotAir entity.
     */
    public RobotAir(int xPosition, int yPosition, int bitWidth, int bitHeight, float attentionAreaDiameterFactor, float entityScale, int maxNumberOfHearts) {
        super(xPosition, yPosition, bitWidth, bitHeight, attentionAreaDiameterFactor, entityScale, maxNumberOfHearts);
        setMovementSpeed(0.006f);
        animationState = "active";
        getAnimationImages();
    }

    @Override
    protected void updateHitBox() {
        xHitBoxDelta = (int) (3*entityScale);
        yHitBoxDelta = (int) (2*entityScale);

        hitBox.x = entityCoordinate.x + xHitBoxDelta;
        hitBox.y = entityCoordinate.y + yHitBoxDelta;
        hitBox.width = 9;
        hitBox.height = 8;
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
        animations.put("active", Atlas.extractAnimationImages(Atlas.ENEMY_ROBOT_AIR, 16, 16));
    }
}
