package entity;

import utility.Atlas;

import java.awt.image.BufferedImage;


public class Cultist extends GroundEnemy {

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
        animations.put("active", new BufferedImage[4]);
        for (int i=0;i<4;i++) {
            animations.get("active")[i] = Atlas.getSpriteAtlas(Atlas.ENEMY_CULTIST).getSubimage(i*16, 0, 16, 16);
        }
    }
}
