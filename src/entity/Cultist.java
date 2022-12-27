package entity;

import utility.Atlas;


/**
 * The Cultist class is a ground-based enemy entity class that contains
 * the state and behavior of the Cultist enemy entity in the game.
 */
public class Cultist extends GroundEnemy {

    /**
     * Cultist is a ground-based enemy entity spawned throughout the game.
     * @param xPosition                     The starting x-coordinate position of the Cultist entity.
     * @param yPosition                     The starting y-coordinate position of the Cultist entity
     * @param bitWidth                      The width of the Cultist entity in pixels.
     * @param bitHeight                     The height of the Cultist entity in pixels.
     * @param attentionAreaDiameterFactor   The diameter of the attention area of the Cultist entity.
     * @param entityScale                   The scale value scaling the appearance of the Cultist entity.
     * @param maxNumberOfHearts             The maximum number of hearts of the Cultist entity.
     */
    public Cultist(int xPosition, int yPosition, int bitWidth, int bitHeight, float attentionAreaDiameterFactor, float entityScale, int maxNumberOfHearts) {
        super(xPosition, yPosition, bitWidth, bitHeight, attentionAreaDiameterFactor, entityScale, maxNumberOfHearts);
        setMovementSpeed(0.4f);
        animationState = "active";
        getAnimationImages();
    }

    @Override public void active() {
        setMovementSpeed(1.2f);
    }

    @Override public void passive() {
        setMovementSpeed(0.4f);
    }

    @Override
    protected void updateHitBox() {
        xHitBoxDelta = (int) (2*entityScale);
        yHitBoxDelta = (int) (entityScale);

        hitBox.x = entityCoordinate.x + xHitBoxDelta;
        hitBox.y = entityCoordinate.y + yHitBoxDelta;
        hitBox.width = 11;
        hitBox.height = 15;
    }

    @Override
    protected void updateAnimation() {
        animationCounter += isActive ? 0.06 : 0.03;
        if (animationCounter > animations.get(animationState).length) {
            animationCounter = 0.0;
        }
    }

    @Override
    protected void getAnimationImages() {
        animations.put("active",  Atlas.extractAnimationImages(Atlas.ENEMY_CULTIST, 16, 16));
    }
}
