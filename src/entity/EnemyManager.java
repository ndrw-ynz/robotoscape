package entity;

import level.Level;
import tile.TileManager;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * The EnemyManager is a class that manages all the enemies
 * found on each level of the game.
 */
public class EnemyManager {

    /**Contains the enemies found in the level of a game.*/
    private final ArrayList<Enemy> enemyList = new ArrayList<>();

    /**
     * EnemyManager | Initializes the EnemyManager managing the enemies
     * found in the game.
     * @param level The current level of the game.
     */
    public EnemyManager(Level level) {
        setupEnemies(level.getEnemyCoordinates());
    }

    /**
     * setupEnemies | Instantiates the enemies present on the current level based on
     * their x,y-coordinate position and type.
     * @param enemyCoordinates The HashMap containing the enemies and their corresponding coordinates.
     */
    private void setupEnemies(HashMap<String, ArrayList<Point>> enemyCoordinates) {
        for(Map.Entry<String, ArrayList<Point>> entry : enemyCoordinates.entrySet()) {
            for (Point coordinate : entry.getValue()) {
                switch (entry.getKey()) {
                    case "abomination" -> enemyList.add(new Abomination(coordinate.x, coordinate.y-11, 16, 16, 8,2.7f, 1,4));
                    case "cultist" -> enemyList.add(new Cultist(coordinate.x, coordinate.y-8, 16, 16, 7, 2.5f, 2,3));
                    case "drone" -> enemyList.add(new Drone(coordinate.x, coordinate.y, 16, 16, 10, 2.5f, 1, 1));
                    case "robot_air" -> enemyList.add(new RobotAir(coordinate.x, coordinate.y, 16, 16, 8, 2.5f, 2, 4));
                    case "robot_ground" -> enemyList.add(new RobotGround(coordinate.x, coordinate.y-9, 16, 16, 10, 2.5f, 3,6));
                    case "skull_slime" -> enemyList.add(new SkullSlime(coordinate.x, coordinate.y-9, 16, 16, 4, 2.5f, 2, 5));
                    case "turret" -> enemyList.add(new Turret(coordinate.x, coordinate.y, 16, 16, 12, 2.5f, 1, 1));
                }
            }
        }
    }

    /**
     * renderEnemies renders the enemies found in the game.
     * @param graphics The graphics object that draws images on the game screen.
     * @param xOffset The x-value offset of the entity on the game screen.
     * @param yOffset The y-value offset of the entity on the game screen.
     */
    public void renderEnemies(Graphics2D graphics, double xOffset, double yOffset) {
        enemyList.removeIf(Entity::isDead);
        for (Enemy enemy: enemyList) {
            enemy.renderEntity(graphics, xOffset, yOffset);
//            enemy.renderAttentionArea(graphics, xOffset, yOffset);
        }

    }

    /**
     * updateEnemies updates the state and behavior of the enemies on the current level of the game.
     * @param level The current level of the game.
     * @param tileManager The TileManager containing data about the tiles of the game/level.
     * @param player The player of the game.
     */
    public void updateEnemies(Level level, TileManager tileManager, Player player) {
        enemyList.removeIf(Entity::isDead);
        for (Enemy enemy: enemyList) {
            enemy.updateEnemy(level, tileManager, player);
            if(enemy.getHitBox().intersects(player.getHitBox()) && !player.isInvulnerable()) {
                player.initiateDamage(enemy.getDamageValue());
            }
        }
    }

    /**
     * getEnemyList fetches the enemyList of an enemyManager instance.
     * @return Returns an ArrayList<Enemy> containing all the enemies present in the game.
     */
    public ArrayList<Enemy> getEnemyList() {
        return enemyList;
    }
}
