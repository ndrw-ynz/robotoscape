package entity;

import utility.Atlas;

import java.awt.image.BufferedImage;

public class SkullSlime extends GroundEnemy{


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
        animations.put("active", new BufferedImage[7]);
        for (int i=0; i<7; i++) {
            animations.get("active")[i] = Atlas.getSpriteAtlas(Atlas.ENEMY_SKULL_SLIME).getSubimage(i*16, 0, 16, 16);
        }
    }
}
