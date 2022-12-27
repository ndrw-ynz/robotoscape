package entity;

import utility.Atlas;

import java.awt.image.BufferedImage;

public class Drone extends AirEnemy{


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
        animations.put("active", new BufferedImage[4]);
        for (int i=0; i<4; i++) {
            animations.get("active")[i] = Atlas.getSpriteAtlas(Atlas.ENEMY_DRONE).getSubimage(i*16, 0, 16, 16);
        }
    }
}
