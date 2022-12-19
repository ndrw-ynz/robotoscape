package utility;

import entity.Entity;
import level.Level;
import tile.Tile;
import tile.TileManager;

import java.awt.*;

/**
 * The PlayUtils abstract class contains static methods
 * solely utilized in maintaining the game logic throughout
 * the game.
 */
public abstract class PlayUtils {
    /**
     * canEntityMove determines if an entity can move on the
     * given cartesian coordinate in the game.
     * @param xPos The x-coordinate position in the current game screen.
     * @param yPos The y-coordinate position in the current game screen.
     * @param entity The entity of concern in the game.
     * @param level The current level of the game.
     * @param tileManager The TileManager containing data about the tiles of the game/level.
     * @return Returns a boolean value.
     */
    public static boolean canEntityMove(int xPos, int yPos, Entity entity, Level level, TileManager tileManager){
        float entityScale = entity.getEntityScale();
        Rectangle entityHitBox = entity.getHitBox();

        int entityLeftX = xPos + entity.getxHitBoxDelta();
        int entityRightX = (int) (entityLeftX + entityHitBox.width*entityScale);
        int entityTopY = yPos + entity.getyHitBoxDelta();
        int entityBottomY = (int) (entityTopY + entityHitBox.height*entityScale);

        int[][] levelData = level.getLevelData();
        Tile[] mapTiles = tileManager.getMapTilesMonochrome();

        boolean isTopLeftRestricted = isRestricted(entityLeftX, entityTopY, level, mapTiles, levelData);
        boolean isTopRightRestricted = isRestricted(entityRightX, entityTopY, level, mapTiles, levelData);
        boolean isBottomLeftRestricted = isRestricted(entityLeftX, entityBottomY, level, mapTiles, levelData);
        boolean isBottomRightRestricted = isRestricted(entityRightX, entityBottomY, level, mapTiles, levelData);

        return !isTopLeftRestricted && !isTopRightRestricted && !isBottomLeftRestricted && !isBottomRightRestricted;
    }

    /**
     * isRestricted determines if the tile on a given cartesian
     * coordinate restricts an entity's movement.
     * @param x The x-coordinate position in the current game screen.
     * @param y The y-coordinate position in the current game screen.
     * @param level The current level of the game.
     * @param mapTiles An array of Tile containing the map tiles.
     * @param levelData A 2D array containing the data of the level's tiles value.
     * @return Returns a boolean value.
     */
    private static boolean isRestricted(int x, int y, Level level, Tile[] mapTiles, int[][] levelData) {
        if (x < 0 || x >= level.getLevelWidthTiles() *32 || y < 0 || y >= level.getLevelHeightTiles() * 32) {
            return true;
        }
        int xIndex = x / 32;
        int yIndex = y / 32;
        return mapTiles[levelData[yIndex][xIndex]].getAllowCollision();
    }

    /**
     * getEntityXOffset determines the x-value offset from the left and right
     * border of the game screen.
     * @param xHitBox The x-coordinate of an entity's hit box.
     * @param isMovingLeft The boolean value determining if the entity is moving left.
     * @return Returns the x-value offset of the entity.
     */
    public static int getEntityXOffset(int xHitBox, boolean isMovingLeft) {
        int currentPlayerTile = xHitBox/ 32;
        if (isMovingLeft) {
            // check offset from left, make as xSpeed
            int tileXPos = currentPlayerTile * 32;
            return tileXPos - xHitBox + 2;
        }
        else {
            // check offset from right, make as xSpeed
            int tileXPos = (currentPlayerTile+1) * 32;
            return tileXPos - xHitBox - 2;
        }
    }

    /**
     * getEntityYOffset determines the y-value offset from the up and down
     * border of the game screen.
     * @param yHitBox The y-coordinate of an entity's hit box.
     * @param airSpeed The speed of an entity on air.
     * @return Returns the y-value offset of the entity.
     */
    public static int getEntityYOffset(int yHitBox, float airSpeed) {
        int currentPlayerTile = yHitBox/ 32;
        if (airSpeed > 0) {
            int tileYPos = currentPlayerTile * 32;
            return yHitBox - tileYPos + 2;
        } else {
            int tileYPos = (currentPlayerTile+1) * 32;
            return tileYPos - yHitBox - 2;
        }
    }

    /**
     * isEntityOnFloor determines if a given entity is situated above
     * a restricted tile in the game.
     * @param xPos The x-coordinate position of the entity in the current game screen.
     * @param yPos The y-coordinate position of the entity in the current game screen.
     * @param entity The entity of concern in the game.
     * @param level The current level of the game.
     * @param tileManager The TileManager containing data about the tiles of the game/level.
     * @return Returns a boolean value.
     */
    public static boolean isEntityOnFloor(int xPos, int yPos, Entity entity, Level level, TileManager tileManager) {
        float entityScale = entity.getEntityScale();
        Rectangle entityHitBox = entity.getHitBox();
        Tile[] mapTiles = tileManager.getMapTilesMonochrome();
        int[][] levelData = level.getLevelData();

        int entityLeftX = xPos + entity.getxHitBoxDelta();
        int entityRightX = (int) (entityLeftX + entityHitBox.width*entityScale);
        int entityBottomY = (int) (yPos + entity.getyHitBoxDelta() + entityHitBox.height*entityScale);

        boolean isBottomLeftRestricted = isRestricted(entityLeftX, entityBottomY, level, mapTiles, levelData);
        boolean isBottomRightRestricted = isRestricted(entityRightX, entityBottomY, level, mapTiles, levelData);

        return (isBottomLeftRestricted && isBottomRightRestricted);
    }
}
