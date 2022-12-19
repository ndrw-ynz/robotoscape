package entity;

import level.Level;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * The EnemyManager class manages all the enemies
 * found on each level of the game.
 */
public class EnemyManager {

    private final ArrayList<Enemy> enemyArrayList = new ArrayList<>();

    /**
     * EnemyManager | Initializes the EnemyManager managing the enemies
     * found in the game.
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
                    case "abomination" -> {
                        enemyArrayList.add(new Abomination(coordinate.x, coordinate.y, 16, 16, 3.0f));
                    }
                    case "cultist" -> {
                        enemyArrayList.add(new Cultist(coordinate.x, coordinate.y, 16, 16, 2.5f));
                    }
                    case "drone" -> {
                        enemyArrayList.add(new Drone(coordinate.x, coordinate.y, 16, 16, 2.5f));
                    }
                    case "robot_air" -> {
                        enemyArrayList.add(new RobotAir(coordinate.x, coordinate.y, 16, 16, 2.5f));
                    }
                    case "robot_ground" -> {
                        enemyArrayList.add(new RobotGround(coordinate.x, coordinate.y, 16, 16, 2.5f));

                    }
                    case "skull_slime" -> {
                        enemyArrayList.add(new SkullSlime(coordinate.x, coordinate.y, 16, 16, 2.5f));
                    }
                    case "turret" -> {
                        enemyArrayList.add(new Turret(coordinate.x, coordinate.y, 16, 16, 2.5f));
                    }
                }
            }
        }
    }

    public void renderEnemies(Graphics2D graphics, int xOffset, int yOffset) {
        for (Enemy enemy: enemyArrayList) {
            enemy.renderEntity(graphics, xOffset, yOffset);
        }

    }

    public void updateEnemies() {
        for (Enemy enemy: enemyArrayList) {
            enemy.updateEnemy();
        }
    }
}
