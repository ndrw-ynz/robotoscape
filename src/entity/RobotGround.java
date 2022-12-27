package entity;

import utility.Atlas;

/**
 * The RobotGround class is a ground-based enemy entity class that contains
 * the state and behavior of the Cultist enemy entity in the game.
 */
public class RobotGround extends GroundEnemy{

    /**
     * RobotGround is a ground-based enemy entity spawned throughout the game.
     * @param xPosition                     The starting x-coordinate position of the RobotGround entity.
     * @param yPosition                     The starting y-coordinate position of the RobotGround entity
     * @param bitWidth                      The width of the RobotGround entity in pixels.
     * @param bitHeight                     The height of the RobotGround entity in pixels.
     * @param attentionAreaDiameterFactor   The diameter of the attention area of the RobotGround entity.
     * @param entityScale                   The scale value scaling the appearance of the RobotGround entity.
     * @param maxNumberOfHearts             The maximum number of hearts of the RobotGround entity.
     */
    public RobotGround(int xPosition, int yPosition, int bitWidth, int bitHeight, float attentionAreaDiameterFactor, float entityScale, int maxNumberOfHearts) {
        super(xPosition, yPosition, bitWidth, bitHeight, attentionAreaDiameterFactor, entityScale, maxNumberOfHearts);
        animationState = "active";
        getAnimationImages();
    }

    @Override public void active() {
        setMovementSpeed(0.8f);
        animationState = "active";
    }

    @Override public void passive() {
        setMovementSpeed(0.0f);
        animationState = "passive";
    }

    @Override
    protected void updateHitBox() {
        xHitBoxDelta = (int) (2*entityScale);
        yHitBoxDelta = (int) (4*entityScale);

        hitBox.x = entityCoordinate.x + xHitBoxDelta;
        hitBox.y = entityCoordinate.y + yHitBoxDelta;
        hitBox.width = 11;
        hitBox.height = 12;
    }

    @Override
    protected void updateAnimation() {
        animationCounter += isActive ? 0.08f : 0.04f;
        if (animationCounter > animations.get(animationState).length) {
            animationCounter = 0.0;
        }
    }

    @Override
    protected void getAnimationImages() {
        animations.put("active", Atlas.extractAnimationImages(Atlas.ENEMY_ROBOT_GROUND_ACTIVE, 16, 16));
        animations.put("passive", Atlas.extractAnimationImages(Atlas.ENEMY_ROBOT_GROUND_PASSIVE, 16, 16));
    }
}
