package level;

import main.Game;
import utility.Atlas;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * The Level class contains the images and data
 * about the structure of an instance of a  game level.
 */
public class Level {
    /**Comprises the entire component of the game and manages its states.*/
    private final Game game;

    /**The level number of a level.*/
    private final int levelNumber;
    /**The image mapping of a level.*/
    private final BufferedImage levelImage;
    /**The width of the level in tiles.*/
    private final int levelWidthTiles;
    /**The height of the level in tiles.*/
    private final int levelHeightTiles;
    /**Contains the respective tile data for each row,col-coordinate tile in the game.*/
    private int[][] levelData;
    /**The x-coordinate position of the player based on the level image.*/
    private int playerXPosition;
    /**The y-coordinate position of the player based on the level image.*/
    private int playerYPosition;

    /**
     * Level | Initializes a level and its attributes and
     * data based on the level number.
     * @param levelNumber The level number of a level.
     */
    public Level(Game game, int levelNumber) {
        this.game = game;
        this.levelNumber = levelNumber;
        String levelFile = "levels/level" + levelNumber + ".png";
        this.levelImage = Atlas.getSpriteAtlas(levelFile);
        this.levelWidthTiles = levelImage.getWidth();
        this.levelHeightTiles = levelImage.getHeight();
        setupLevelData();
    }

    /**
     * setupLevelData | Sets up the level data of the level based on
     * the level number, and parses its level image with its RGB values
     * which contain the level's associated tile value from the tile manager.
     * The data is stored in a 2D array, with its rows representing the
     * y-axis and columns representing the x-axis.
     */
    public void setupLevelData() {
        // returned RED value DICTATES tile type.
        levelData = new int[levelHeightTiles][levelWidthTiles];
        for (int row = 0; row < levelHeightTiles; row++) {
            for (int col = 0; col < levelWidthTiles; col++) {
                Color color = new Color(levelImage.getRGB(col, row));
                int red = color.getRed();
                int green = color.getGreen();
                int blue = color.getBlue();
                if (red + green + blue == 765) { // if white, blank. (code not final.)
                    levelData[row][col] = 0;
                } else {
                    levelData[row][col] = color.getRed() + color.getGreen();
                }
                if (blue == 255 && red + green + blue == 255) {
                    playerXPosition = game.getTileSize()*col;
                    playerYPosition = game.getTileSize()*row;
                }
            }
        }
    }

    /**
     * getLevelData | Fetches the data of the level stored in a 2D array.
     * @return Returns a 2D int array containing the individual tile
     *         values on a given row,col-coordinate.
     */
    public int[][] getLevelData() {
        return levelData;
    }

    /**
     * getLevelWidthTiles | Fetches the width of the level based on the
     *                      number of its tiles on the y-axis..
     * @return Returns the width of the level in tiles.
     */
    public int getLevelWidthTiles() {
        return levelWidthTiles;
    }

    /**
     * getLevelHeightTiles | Fetches the height of the level based on the
     *                       number of its tiles in the x-axis.
     * @return Returns the height of the level in tiles.
     */
    public int getLevelHeightTiles() {
        return levelHeightTiles;
    }

    /**
     * getLevelDimension | Fetches the width,height dimension of the level.
     * @return Returns the dimension of the level containing its width and height.
     */
    public Dimension getLevelDimension() {return new Dimension(levelWidthTiles * game.getTileSize(), levelHeightTiles * game.getTileSize());}

    /**
     * getLevelNumber | Fetches the level number of the level.
     * @return Returns the level number of the level.
     */
    public int getLevelNumber() {return levelNumber;}

    /**
     * getPlayerXPosition | Fetches the x-coordinate position of the player based
     * on the level.
     * @return Returns the x-coordinate position of the player.
     */
    public int getPlayerXPosition() {return playerXPosition;}

    /**
     * getPlayerYPosition | Fetches the y-coordinate position of the player
     * based on the level.
     * @return Returns the y-coordinate position of the player.
     */
    public int getPlayerYPosition() {return playerYPosition;}
}