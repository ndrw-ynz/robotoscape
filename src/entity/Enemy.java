package entity;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Enemy extends Entity{

    private int attentionRadius;
    private boolean isActive;

    /**
     * Enemy is the superclass of all moving and interactive enemy entities
     * in the levels of the game.
     *
     * @param xPosition   The starting x-coordinate position of the enemy.
     * @param yPosition   The starting y-coordinate position of the enemy.
     * @param bitWidth    The width of the enemy sprite in pixels.
     * @param bitHeight   The height of the enemy sprite in pixels.
     * @param entityScale The scale value scaling the appearance of the enemy.
     */
    public Enemy(int xPosition, int yPosition, int bitWidth, int bitHeight, float entityScale) {
        super(xPosition, yPosition, bitWidth, bitHeight, entityScale);
    }

    public abstract void updateEnemy();
}
