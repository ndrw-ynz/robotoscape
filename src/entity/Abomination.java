package entity;

import utility.Atlas;

import java.awt.image.BufferedImage;

/**
 * The Abomination class is an entity class that contains
 * the state and behavior of the abomination enemy entity in the game.
 */
public class Abomination extends GroundEnemy{

    public Abomination(int x, int y, int bitWidth, int bitHeight, float attentionAreaDiameterFactor, float entityScale, int maxNumberOfHearts) {
        super(x, y, bitWidth, bitHeight, attentionAreaDiameterFactor, entityScale, maxNumberOfHearts);
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
        animations.put("active", new BufferedImage[5]);
        for (int i=0; i<5; i++) {
            animations.get("active")[i] = Atlas.getSpriteAtlas(Atlas.ENEMY_ABOMINATION_ACTIVE).getSubimage(i*16, 0, 16, 16);
        }

        animations.put("passive", new BufferedImage[5]);
        for (int i=0;i<5; i++) {
            animations.get("passive")[i] = Atlas.getSpriteAtlas(Atlas.ENEMY_ABOMINATION_PASSIVE).getSubimage(i*16, 0, 16, 16);
        }
    }
}
