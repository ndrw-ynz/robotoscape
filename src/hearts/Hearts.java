package hearts;

import entity.Entity;
import utility.Atlas;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

/**
 * The Hearts class contains the number of hearts of a
 * given entity in the game.
 */
public class Hearts {

    /** maxNumberOfHearts determines the maximum number of hearts of an entity.*/
    int maxNumberOfHearts;
    /** currentNumberOfHearts determines the current number of hearts of an entity.*/
    int currentNumberOfHearts;
    /** heartAnimations is an image containing the heart animations of the game.*/
    private static final BufferedImage[] heartAnimations = Atlas.extractAnimationImages(Atlas.HEART_ANIMATIONS, 16, 16);
    /**
     * A double used as a counter for transitioning between each animation frame
     * on a given animation state.
     */
    private float animationCounter;

    /**
     * Hearts instantiates a Hearts object that contains the number of hearts of a given
     * entity in the game.
     * @param maxNumberOfHearts The maximum number of hearts of an entity.
     */
    public Hearts(int maxNumberOfHearts) {
        this.maxNumberOfHearts = maxNumberOfHearts;
        this.currentNumberOfHearts = maxNumberOfHearts;
    }

    /**
     * displayHearts displays the hearts of the entity.
     * @param graphics  The graphics object that draws images on the game screen.
     * @param entity    An entity instance.
     * @param xOffset   The x-value offset of the entity on the game screen.
     * @param yOffset   The y-value offset of the entity on the game screen.
     */
    public void displayHearts(Graphics2D graphics, Entity entity, int xOffset, int yOffset) {
        animationCounter += 0.15f;
        if (animationCounter > 3.0) {
            animationCounter = 0.0f;
        }
        BufferedImage currentHeart = heartAnimations[(int) Math.floor(animationCounter)];
        Rectangle2D.Float entityHitBox = entity.getHitBox();

        Point.Float entityCenterTop = new Point.Float((entityHitBox.x+(entityHitBox.width*entity.getEntityScale())/2), entity.getEntityCoordinate().y);

        int xCoordinate = (int) (entityCenterTop.x - (maxNumberOfHearts*16 - 2*(maxNumberOfHearts-1))/2);
        int yCoordinate = (int) (entityCenterTop.y - 15);
        for (int i = 0; i < maxNumberOfHearts; i++) {
            graphics.drawImage(currentHeart,xCoordinate+i*18 - xOffset, yCoordinate - yOffset, 16, 16, null);
        }
    }
}