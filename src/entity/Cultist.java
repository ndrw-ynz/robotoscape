package entity;

import utility.Atlas;

import java.awt.image.BufferedImage;

public class Cultist extends Enemy {

    public Cultist(int xPosition, int yPosition, int bitWidth, int bitHeight, float entityScale) {
        super(xPosition, yPosition, bitWidth, bitHeight, entityScale);
        animationState = "active";
        getAnimationImages();
    }
    @Override
    public void updateEnemy() {
        updateHitBox();
        updateAnimation();
    }

    @Override
    protected void updateHitBox() {
        xHitBoxDelta = (int) (3*entityScale);
        yHitBoxDelta = (int) (entityScale);

        hitBox.x = xPosition + xHitBoxDelta;
        hitBox.y = yPosition + yHitBoxDelta;
        hitBox.width = 10;
        hitBox.height = 14;
    }

    @Override
    protected void getAnimationImages() {
        animations.put("active", new BufferedImage[4]);
        for (int i=0;i<4;i++) {
            animations.get("active")[i] = Atlas.getSpriteAtlas(Atlas.ENEMY_CULTIST).getSubimage(i*16, 0, 16, 16);
        }
    }

    @Override
    protected void updateAnimation() {
        animationCounter += 0.04;
        if (animationCounter > animations.get(animationState).length) {
            animationCounter = 0.0;
        }
    }
}
