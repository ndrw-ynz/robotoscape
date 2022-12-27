package entity;

import utility.Atlas;


import java.awt.image.BufferedImage;

public class RobotGround extends GroundEnemy{


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
        animations.put("active", new BufferedImage[6]);
        for (int i=0; i<6; i++) {
            animations.get("active")[i] = Atlas.getSpriteAtlas(Atlas.ENEMY_ROBOT_GROUND_ACTIVE).getSubimage(i*16, 0, 16, 16);
        }

        animations.put("passive", new BufferedImage[6]);
        for (int i=0; i<6; i++) {
            animations.get("passive")[i] = Atlas.getSpriteAtlas(Atlas.ENEMY_ROBOT_GROUND_PASSIVE).getSubimage(i*16, 0, 16, 16);
        }
    }
}
