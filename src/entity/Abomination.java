package entity;

import utility.Atlas;

import java.awt.image.BufferedImage;

/**
 * The Abomination class is an entity class that contains
 * the state and behavior of the abomination enemy entity in the game.
 */
public class Abomination extends Enemy{

    public Abomination(int x, int y, int bitWidth, int bitHeight, float entityScale) {
        super(x, y, bitWidth, bitHeight, entityScale);
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
        xHitBoxDelta = (int) (5*entityScale);
        yHitBoxDelta = (int) (entityScale);

        hitBox.x = xPosition + xHitBoxDelta;
        hitBox.y = yPosition + yHitBoxDelta;
        hitBox.width = 6;
        hitBox.height = 14;
    }

    @Override
    protected void updateAnimation(){
        animationCounter += 0.04;
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
