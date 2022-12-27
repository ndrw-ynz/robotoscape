package entity;

import level.Level;
import utility.Atlas;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Turret extends StationaryEnemy{

    public Turret(int xPosition, int yPosition, int bitWidth, int bitHeight, float attentionAreaDiameterFactor, float entityScale, int maxNumberOfHearts) {
        super(xPosition, yPosition, bitWidth, bitHeight, attentionAreaDiameterFactor, entityScale, maxNumberOfHearts);
        animationState = "passive";
        getAnimationImages();
    }

    @Override public void active() {

    }

    @Override public void passive() {

    }

    @Override
    protected void updateActivity(Level level, Player player) {

    }

    @Override
    protected void updateHitBox() {
        xHitBoxDelta = (int) (4*entityScale);
        yHitBoxDelta = (int) (5*entityScale);

        hitBox.x = entityCoordinate.x + xHitBoxDelta;
        hitBox.y = entityCoordinate.y + yHitBoxDelta;
        hitBox.width = 8;
        hitBox.height = 10;
    }

    @Override
    protected void updateAnimation() {
        animationCounter += 0.04;
        if (animationCounter > animations.get(animationState).length) {
            animationCounter = 0.0;
        }
    }

    @Override
    public void renderAttentionArea(Graphics2D graphics, int xOffset, int yOffset) {

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
}
