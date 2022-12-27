package entity;

import utility.Atlas;

/**
 * The Abomination class is a ground-based enemy entity class that contains
 * the state and behavior of the Abomination enemy entity in the game.
 */
public class Abomination extends GroundEnemy{

    /**
     * Abomination is a ground-based enemy entity spawned throughout the game.
     * @param xPosition                     The starting x-coordinate position of the Abomination entity.
     * @param yPosition                     The starting y-coordinate position of the Abomination entity
     * @param bitWidth                      The width of the Abomination entity in pixels.
     * @param bitHeight                     The height of the Abomination entity in pixels.
     * @param attentionAreaDiameterFactor   The diameter of the attention area of the Abomination entity.
     * @param entityScale                   The scale value scaling the appearance of the Abomination entity.
     * @param maxNumberOfHearts             The maximum number of hearts of the Abomination entity.
     */
    public Abomination(int xPosition, int yPosition, int bitWidth, int bitHeight, float attentionAreaDiameterFactor, float entityScale, int maxNumberOfHearts) {
        super(xPosition, yPosition, bitWidth, bitHeight, attentionAreaDiameterFactor, entityScale, maxNumberOfHearts);
        setMovementSpeed(0.8f);
        animationState = "passive";
        getAnimationImages();
    }

    @Override
    public void active() {
        setMovementSpeed(1.3f);
        animationState = "active";
    }

    @Override
    public void passive() {
        setMovementSpeed(0.8f);
        animationState = "passive";
    }


    @Override
    protected void updateHitBox() {
        xHitBoxDelta = (int) (4*entityScale);
        yHitBoxDelta = (int) (entityScale);

        hitBox.x = entityCoordinate.x + xHitBoxDelta;
        hitBox.y = entityCoordinate.y + yHitBoxDelta;
        hitBox.width = 8;
        hitBox.height = 15;
    }

    @Override
    protected void updateAnimation(){
        animationCounter += isActive ? 0.08 : 0.04;
        if (animationCounter > animations.get(animationState).length) {
            animationCounter = 0.0;
        }
    }

    @Override
    protected void getAnimationImages() {
        animations.put("active", Atlas.extractAnimationImages(Atlas.ENEMY_ABOMINATION_ACTIVE, 16, 16));
        animations.put("passive", Atlas.extractAnimationImages(Atlas.ENEMY_ABOMINATION_PASSIVE, 16, 16));
    }
}
