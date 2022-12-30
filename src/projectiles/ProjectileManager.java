package projectiles;

import entity.Enemy;
import entity.EnemyManager;
import level.Level;
import tile.TileManager;
import utility.Atlas;
import utility.PlayUtils;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;

/**
 * ProjectileManager is a class that manages all the projectiles
 * created in the game.
 */
public class ProjectileManager {

    /** The current level of the game.*/
    private final Level level;
    /** The tile manager that manages the tiles used in the game.*/
    private final TileManager tileManager;
    /** Manages all the enemies used in the game.*/
    private final EnemyManager enemyManager;
    /** Contains all the projectiles of the player in the game.*/
    private final ArrayList<Projectile> playerProjectiles = new ArrayList<>();

    /**
     * ProjectileManager is a class that manages all the projectiles created in the game.
     * @param level         The current level of the game.
     * @param tileManager   The tileManager instance that manages all the tiles used in current level of the game.
     * @param enemyManager  The enemyManager instance that manages all the tiles used in the current level of the game.
     */
    public ProjectileManager(Level level, TileManager tileManager, EnemyManager enemyManager) {
        this.level = level;
        this.tileManager = tileManager;
        this.enemyManager = enemyManager;
    }

    /**
     * createPlayerProjectile creates a projectile from the player.
     * @param start     The starting x,y-coordinate of the projectile.
     * @param end       The ending x,y-coordinate of the projectile.
     * @param xOffset   The x-value offset of the entity on the game screen.
     * @param yOffset   The y-value offset of the entity on the game screen.
     */
    public void createPlayerProjectile(Point2D.Float start, Point2D.Float end, double xOffset, double yOffset, int damageValue) {
        playerProjectiles.add(new Projectile(start, end, xOffset, yOffset, 3.2f, damageValue, Atlas.FIRE_PROJECTILE, Atlas.FIRE_EXPLOSION));
    }

    /**
     * updatePlayerProjectiles updates the projectiles created by the player.
     */
    public void updatePlayerProjectiles() {
        // If the projectile is not active, remove the projectile from the list, deleting it.
        playerProjectiles.removeIf(projectile -> !projectile.isActive());
        for (Projectile projectile : playerProjectiles) {
            // Updates the state of the projectile.
            projectile.updateProjectile();
            // Check if projectile intersects with the enemy hit box.
            for (Enemy enemy: enemyManager.getEnemyList()) {
                if (projectile.getHitBox().intersects(enemy.getHitBox()) && !projectile.getHasDealtDamage()) {
                    projectile.setIsExploding(true);
                    projectile.setHasDealtDamage(true);
                    enemy.initiateDamage(1);
                }
            }
            // Check if projectile is on a collision tile or goes out of boundaries.
            if (PlayUtils.isProjectileOnCollision(projectile, level, tileManager)) {
                projectile.setIsExploding(true);
            }
        }
    }

    /**
     * renderPlayerProjectiles displays the projectiles created by the player on the game screen.
     * @param graphics  The graphics object that draws images on the game screen.
     * @param xOffset   The x-value offset of the entity on the game screen.
     * @param yOffset   The y-value offset of the entity on the game screen.
     */
    public void renderPlayerProjectiles(Graphics2D graphics, double xOffset, double yOffset) {
        if (playerProjectiles.isEmpty()) return;
        for (Projectile projectile : playerProjectiles) {
            projectile.renderProjectile(graphics, xOffset, yOffset);
        }
    }
}
