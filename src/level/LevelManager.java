package level;

import main.Game;
import tile.Tile;
import tile.TileManager;

import java.awt.*;

/**
 * The LevelManager class manages the levels used in the play
 * state of the game and renders the individual levels of the
 * game.
 */
public class LevelManager {
    /**The current level of the game.*/
    private Level currentLevel;
    /**The TileManager managing the tiles used in the game.*/
    private final TileManager tileManager;
    /**Comprises the entire component of the game and manages its states.*/
    private final Game game;

    /**
     * LevelManager | Initializes the LevelManager managing the levels displayed
     *                in the game.
     * @param game The game that manages the components of the game and its states.
     */
    public LevelManager(Game game) {
        this.game = game;
        this.currentLevel = new Level(game, 1);
        this.tileManager = new TileManager();
    }

    /**
     * renderLevel | Displays the level on the game screen.
     * @param graphics The graphics object that draws images on the game screen.
     * @param xOffset The x-value offset of the tiles on the game screen.
     * @param yOffset The y-value offset of the tiles on the game screen.
     */
    public void renderLevel(Graphics graphics, int xOffset, int yOffset) {
        int levelWidth = currentLevel.getLevelWidthTiles();
        int levelHeight = currentLevel.getLevelHeightTiles();
        Tile[] mapTiles = tileManager.getMapTilesMonochrome(); //TODO: Later change this, generalize to diff levels.
        int [][] levelData = currentLevel.getLevelData();

        for (int row = 0; row < levelHeight; row++) {
            for (int col = 0; col < levelWidth; col++) {
                graphics.drawImage(mapTiles[levelData[row][col]].getImage(), game.getTileSize()*col-xOffset, game.getTileSize()*row-yOffset, game.getTileSize(), game.getTileSize(), null);
            }
        }
    }

    public void updateLevel() {

    }

    public Level getCurrentLevel() {
        return currentLevel;
    }
}