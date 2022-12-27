package utility;

import entity.*;
import level.Level;
import tile.Tile;
import tile.TileManager;

import java.awt.*;
import java.awt.geom.Rectangle2D;

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
    public static boolean canEntityMove(float xPos, float yPos, Entity entity, Level level, TileManager tileManager){
        float entityScale = entity.getEntityScale();
        Rectangle.Float entityHitBox = entity.getHitBox();

        float entityLeftX = xPos + entity.getxHitBoxDelta();
        float entityRightX = entityLeftX + entityHitBox.width*entityScale;
        float entityTopY = yPos + entity.getyHitBoxDelta();
        float entityBottomY = entityTopY + entityHitBox.height*entityScale;

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
    private static boolean isRestricted(float x, float y, Level level, Tile[] mapTiles, int[][] levelData) {
        if (x < 0 || x >= level.getLevelWidthTiles() *32 || y < 0 || y >= level.getLevelHeightTiles() * 32) {
            return true;
        }
        int xIndex = (int) x / 32;
        int yIndex = (int) y / 32;
        return mapTiles[levelData[yIndex][xIndex]].getAllowCollision();
    }

    /**
     * getEntityXOffset determines the x-value offset from the left and right
     * border of the game screen.
     * @param xHitBox The x-coordinate of an entity's hit box.
     * @param isMovingLeft The boolean value determining if the entity is moving left.
     * @return Returns the x-value offset of the entity.
     */
    public static float getEntityXOffset(float xHitBox, boolean isMovingLeft) {
        int currentPlayerTile = (int) xHitBox/ 32;
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
    public static float getEntityYOffset(float yHitBox, float airSpeed) {
        int currentPlayerTile = (int) yHitBox/ 32;
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
    public static boolean isEntityOnFloor(float xPos, float yPos, Entity entity, Level level, TileManager tileManager) {
        float entityScale = entity.getEntityScale();
        Rectangle.Float entityHitBox = entity.getHitBox();
        Tile[] mapTiles = tileManager.getMapTilesMonochrome();
        int[][] levelData = level.getLevelData();

        float entityLeftX = xPos + entity.getxHitBoxDelta();
        float entityRightX = entityLeftX + entityHitBox.width*entityScale;
        float entityBottomY = yPos + entity.getyHitBoxDelta() + entityHitBox.height*entityScale;

        boolean isBottomLeftRestricted = isRestricted(entityLeftX, entityBottomY, level, mapTiles, levelData);
        boolean isBottomRightRestricted = isRestricted(entityRightX, entityBottomY, level, mapTiles, levelData);

        return (isBottomLeftRestricted && isBottomRightRestricted);
    }

    /**
     * isEntityOnEdge determines if a given entity is near/on an edge.
     * @param xPos The x-coordinate position of the entity.
     * @param yPos The y-coordinate position of the entity.
     * @param isMovingLeft The boolean value determining if the entity is moving left.
     * @param entity An entity instance.
     * @param level The current level of the game.
     * @param tileManager The tile manager managing all the tiles used in the game.
     * @return Returns a boolean value determining whether the entity is near/on an edge.
     */
    public static boolean isEntityOnEdge(float xPos, float yPos, boolean isMovingLeft, Entity entity, Level level, TileManager tileManager) {
        float entityScale = entity.getEntityScale();
        Rectangle.Float entityHitBox = entity.getHitBox();
        Tile[] mapTiles = tileManager.getMapTilesMonochrome();
        int[][] levelData = level.getLevelData();

        float entityLeftX = xPos + entity.getxHitBoxDelta();
        float entityRightX = entityLeftX + entityHitBox.width*entityScale;
        float entityBottomY = yPos + entity.getyHitBoxDelta() + entityHitBox.height*entityScale;

        if (isMovingLeft) {
            return !isRestricted(entityLeftX + 5, entityBottomY + 5, level, mapTiles, levelData);
        } else {
            return !isRestricted(entityRightX + 5, entityBottomY + 5, level, mapTiles, levelData);
        }
    }

    /**
     * getEntityCenterHitBox determines the center x,y-coordinate of the entity's HitBox.
     * @param entity An entity instance.
     * @return Returns a Point.Float object containing the center x,y-coordinate of the entity's HitBox.
     */
    public static Point.Float getEntityCenterHitBox(Entity entity) {
        Rectangle2D.Float entityHitBox = entity.getHitBox();
        float centerX = (entityHitBox.x+(entityHitBox.width*entity.getEntityScale())/2);
        float centerY = (entityHitBox.y+(entityHitBox.height*entity.getEntityScale())/2);
        return new Point.Float(centerX, centerY);
    }
}