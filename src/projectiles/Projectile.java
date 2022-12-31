package projectiles;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import static utility.Atlas.extractAnimationImages;

/**
 * The Projectile class contains the state and behavior of the
 * projectiles in the game.
 */
public class Projectile {

    /** The x,y-coordinate of the projectile.*/
    private final Point2D.Float coordinates;
    /** The interactive hit box of the projectile.*/
    private final Rectangle2D.Float hitBox;
    /** The damage value of the projectile.*/
    private final int damageValue;
    /** The counter used for transitioning between each animation frame of the projectile as it is shot.*/
    private float shootCounter;
    /** The counter used for transitioning between each animation frame of the projectile as it explodes.*/
    private float explodeCounter;
    /** The direction of the projectile.*/
    private final double direction;
    /** The speed of the projectile.*/
    private final float speed;
    /** The active state of the projectile.*/
    private boolean isActive;
    /** The exploding state of the projectile.*/
    private boolean isExploding;
    /** Determines if the projectile has already dealt damage.*/
    private boolean hasDealtDamage;
    /** The set of animation frames used for the projectile as it is shot.*/
    private final BufferedImage[] shootAnimations;
    /** The set of animation frames used for the projectile as it explodes.*/
    private final BufferedImage[] explosionAnimations;

    public Projectile(Point2D.Float start, Point2D.Float end, double xOffset, double yOffset, float speed, int damageValue, String shootAnimationPath, String explosionAnimationPath) {
        this.shootAnimations = extractAnimationImages(shootAnimationPath, 16, 16);
        this.explosionAnimations = extractAnimationImages(explosionAnimationPath, 16, 16);
        this.coordinates = new Point2D.Float(start.x, start.y);
        this.hitBox = new Rectangle2D.Float(start.x, start.y, 20, 20);
        float directionX = (int) (end.x - start.x + xOffset);
        float directionY = (int) (end.y - start.y + yOffset);
        this.direction = Math.atan2(directionY, directionX);
        this.speed = speed;
        this.damageValue = damageValue;
        this.isActive = true;
    }

    /**
     * renderProjectile displays the projectile on the game screen.
     * @param graphics  The graphics object that draws images on the game screen.
     * @param xOffset   The x-value offset of the entity on the game screen.
     * @param yOffset   The y-value offset of the entity on the game screen.
     */
    public void renderProjectile(Graphics2D graphics, double xOffset, double yOffset) {
        if (!isExploding) {
            graphics.drawImage(shootAnimations[(int) Math.floor(shootCounter)], (int) (coordinates.x - xOffset), (int) (coordinates.y - yOffset), 20, 20, null);
//            renderHitBox(graphics, xOffset, yOffset);
        } else {
            graphics.drawImage(explosionAnimations[(int) Math.floor(explodeCounter)], (int) (coordinates.x - xOffset), (int) (coordinates.y - yOffset), 20, 20, null);
        }
    }

    /**
     * renderHitBox displays the hit box of the projectile on the game screen.
     * @param graphics  The graphics object that draws images on the game screen.
     * @param xOffset   The x-value offset of the entity on the game screen.
     * @param yOffset   The y-value offset of the entity on the game screen.
     */
    private void renderHitBox(Graphics2D graphics, double xOffset, double yOffset) {
        graphics.setColor(Color.ORANGE);
        graphics.drawRect((int) (hitBox.x - xOffset), (int) (hitBox.y - yOffset), (int) hitBox.width, (int) hitBox.height);
    }

    /**
     * updateProjectile updates the state of the projectile.
     */
    public void updateProjectile() {
        if (!isExploding) {
            coordinates.x += speed*Math.cos(direction);
            coordinates.y += speed*Math.sin(direction);
            hitBox.x = coordinates.x;
            hitBox.y = coordinates.y;

            shootCounter += 0.08f;
            if (shootCounter > shootAnimations.length) {
                shootCounter = 0.0f;
            }
        } else {
            explodeCounter += 0.04f;
            if (explodeCounter > explosionAnimations.length) {
                explodeCounter = 0.0f;
                isActive = false;
            }
        }
    }

    /**
     * getHitBox fetches the Rectangle2D.Float hit box of the projectile.
     * @return Returns a Rectangle2D.Float hit box of the projectile.
     */
    public Rectangle2D.Float getHitBox() {return hitBox;}

    public int getDamageValue() {return damageValue;}

    /**
     * isActive fetches the active state of the projectile.
     * @return Returns a boolean value determining the isActive state of the projectile.
     */
    public boolean isActive() {return isActive;}

    public boolean getHasDealtDamage() {return hasDealtDamage;}

    /**
     * setIsExploding sets the projectile's state isExploding by a boolean value.
     * @param isExploding The boolean value determining the isExploding state of the projectile.
     */
    public void setIsExploding(boolean isExploding) {
        this.isExploding = isExploding;
    }

    public void setHasDealtDamage(boolean hasDealtDamage) {this.hasDealtDamage = hasDealtDamage;}
}
