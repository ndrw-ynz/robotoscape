package entity;

import utility.Atlas;

import java.awt.image.BufferedImage;

public class SkullSlime extends Enemy{


    public SkullSlime(int xPosition, int yPosition, int bitWidth, int bitHeight, float entityScale) {
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
        xHitBoxDelta = (int) (2*entityScale);
        yHitBoxDelta = (int) (4*entityScale);

        hitBox.x = xPosition + xHitBoxDelta;
        hitBox.y = yPosition + yHitBoxDelta;
        hitBox.width = 11;
        hitBox.height = 12;
    }

    @Override
    protected void getAnimationImages() {
        animations.put("active", new BufferedImage[7]);
        for (int i=0; i<7; i++) {
            animations.get("active")[i] = Atlas.getSpriteAtlas(Atlas.ENEMY_SKULL_SLIME).getSubimage(i*16, 0, 16, 16);
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
