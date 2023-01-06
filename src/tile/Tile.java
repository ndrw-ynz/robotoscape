package tile;

import java.awt.image.BufferedImage;

/**
 * The Tile class determines the state of a given tile of the game.
 */
public class Tile {
    /**The image of the tile.*/
    private final BufferedImage image;
    /**The condition determining if the tile allows collision.*/
    private final boolean allowCollision;
    /**The condition determining if the tile deals damage.*/
    private final boolean dealsDamage;

    /**
     * Tile initializes a tile object used in the game.
     * @param image             The image of the tile.
     * @param allowCollision    The condition determining if the tile allows collision.
     * @param dealsDamage       The condition determining if the tile deals damage.
     */
    public Tile(BufferedImage image, boolean allowCollision, boolean dealsDamage) {
        this.image = image;
        this.allowCollision = allowCollision;
        this.dealsDamage = dealsDamage;
    }

    /**
     * getImage fetches the image of the tile.
     * @return Returns a BufferedImage
     */
    public BufferedImage getImage() {
        return image;
    }

    /**
     * getAllowCollision fetches the allowCollision state of the tile.
     * @return Returns the allowCollision state of the tile.
     */
    public boolean getAllowCollision() {
        return allowCollision;
    }

    /**
     * dealsDamage fetches the dealsDamage state of the tile.
     * @return Returns the dealsDamage state of the tile.
     */
    public boolean dealsDamage() {
        return dealsDamage;
    }

}
