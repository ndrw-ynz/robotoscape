package entity;

import utility.Atlas;

import java.awt.image.BufferedImage;

public class Turret extends Enemy{

    public Turret(int xPosition, int yPosition, int bitWidth, int bitHeight, float entityScale) {
        super(xPosition, yPosition, bitWidth, bitHeight, entityScale);
        animationState = "passive";
        getAnimationImages();
    }


    @Override
    public void updateEnemy() {
        updateHitBox();
        updateAnimation();
    }

    @Override
    protected void updateHitBox() {
        xHitBoxDelta = (int) (4*entityScale);
        yHitBoxDelta = (int) (5*entityScale);

        hitBox.x = xPosition + xHitBoxDelta;
        hitBox.y = yPosition + yHitBoxDelta;
        hitBox.width = 8;
        hitBox.height = 10;
    }

    @Override
    protected void getAnimationImages() {
        animations.put("charging", new BufferedImage[8]);
        for (int i=0; i<8; i++) {
            animations.get("charging")[i] = Atlas.getSpriteAtlas(Atlas.ENEMY_TURRET_CHARGING).getSubimage(i*16, 0, 16, 16);
        }

        animations.put("passive", new BufferedImage[3]);
        for (int i=0; i<3; i++) {
            animations.get("passive")[i] = Atlas.getSpriteAtlas(Atlas.ENEMY_TURRET_PASSIVE).getSubimage(i*16, 0, 16, 16);
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
