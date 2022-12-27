package entity;

import level.Level;
import tile.TileManager;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * The EnemyManager class manages all the enemies
 * found on each level of the game.
 */
public class EnemyManager {

    /**Contains the enemies found in the level of a game.*/
    private final ArrayList<Enemy> enemyArrayList = new ArrayList<>();

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
                    case "abomination" -> enemyArrayList.add(new Abomination(coordinate.x, coordinate.y-11, 16, 16, 6,2.7f, 4));
                    case "cultist" -> enemyArrayList.add(new Cultist(coordinate.x, coordinate.y-8, 16, 16, 5, 2.5f, 3));
                    case "drone" -> enemyArrayList.add(new Drone(coordinate.x, coordinate.y, 16, 16, 8, 2.5f, 2));
                    case "robot_air" -> enemyArrayList.add(new RobotAir(coordinate.x, coordinate.y, 16, 16, 5, 2.5f, 4));
                    case "robot_ground" -> enemyArrayList.add(new RobotGround(coordinate.x, coordinate.y-9, 16, 16, 10, 2.5f, 6));
                    case "skull_slime" -> enemyArrayList.add(new SkullSlime(coordinate.x, coordinate.y-9, 16, 16, 4, 2.5f, 5));
                    case "turret" -> enemyArrayList.add(new Turret(coordinate.x, coordinate.y, 16, 16, 10, 2.5f, 1));
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
    public void renderEnemies(Graphics2D graphics, int xOffset, int yOffset) {
        for (Enemy enemy: enemyArrayList) {
            enemy.renderEntity(graphics, xOffset, yOffset);
            enemy.renderAttentionArea(graphics, xOffset, yOffset);
        }

    }

    /**
     * updateEnemies updates the state and behavior of the enemies on the current level of the game.
     * @param level The current level of the game.
     * @param tileManager The TileManager containing data about the tiles of the game/level.
     * @param player The player of the game.
     */
    public void updateEnemies(Level level, TileManager tileManager, Player player) {
        for (Enemy enemy: enemyArrayList) {
            enemy.updateEnemy(level, tileManager, player);
        }
    }
}
