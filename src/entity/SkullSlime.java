package entity;

import utility.Atlas;

/**
 * The SkullSlime class is a ground-based enemy entity class that contains
 * the state and behavior of the SkullSlime enemy entity in the game.
 */
public class SkullSlime extends GroundEnemy{

    /**
     * SkullSlime is a ground-based enemy entity spawned throughout the game.
     * @param xPosition                     The starting x-coordinate position of the SkullSlime entity.
     * @param yPosition                     The starting y-coordinate position of the SkullSlime entity
     * @param bitWidth                      The width of the SkullSlime entity in pixels.
     * @param bitHeight                     The height of the SkullSlime entity in pixels.
     * @param attentionAreaDiameterFactor   The diameter of the attention area of the SkullSlime entity.
     * @param entityScale                   The scale value scaling the appearance of the SkullSlime entity.
     * @param maxNumberOfHearts             The maximum number of hearts of the SkullSlime entity.
     */
    public SkullSlime(int xPosition, int yPosition, int bitWidth, int bitHeight, float attentionAreaDiameterFactor, float entityScale, int maxNumberOfHearts) {
        super(xPosition, yPosition, bitWidth, bitHeight, attentionAreaDiameterFactor, entityScale, maxNumberOfHearts);
        setMovementSpeed(0.4f);
        animationState = "active";
        getAnimationImages();
    }

    @Override public void active() {
        setMovementSpeed(1.0f);
    }

    @Override public void passive() {
        setMovementSpeed(0.4f);
    }

    @Override
    protected void updateHitBox() {
        xHitBoxDelta = (int) (2*entityScale);
        yHitBoxDelta = (int) (4*entityScale);

        hitBox.x = entityCoordinate.x + xHitBoxDelta;
        hitBox.y = entityCoordinate.y + yHitBoxDelta;
        hitBox.width = 12;
        hitBox.height = 12;
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
        animations.put("active", Atlas.extractAnimationImages(Atlas.ENEMY_SKULL_SLIME, 16, 16));
    }
}
