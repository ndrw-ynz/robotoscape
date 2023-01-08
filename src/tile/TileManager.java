package tile;

import utility.Atlas;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * The TileManager class manages the tiles used in the play state
 * of the game.
 */
public class TileManager {

    /**Stores tile data used in building levels in a Tile array.*/
    private Tile[] mapTilesMonochrome;
    /**Stores a list of tiles that don't have collision properties.*/
    public static final ArrayList<Integer> tileMonochromeNonCollisions = new ArrayList<>(Arrays.asList(0,3,4,5,16,17,18,19,20,23,24,25,26,37,38,39,40,43,53,54,59,122,140,141,142,166,188,207,208,209,288));
    public static final ArrayList<Integer> tileMonochromeDamage = new ArrayList<>(Arrays.asList(122, 166));
    /**
     * TileManager | Initializes the tile manager of the game.
     */
    public TileManager() {
        setupMapTiles();
    }

    /**
     * setupMapTiles | Gathers the tile data from their respective images and extracts
     * tile images and assigns individual tile attributes.
     */
    private void setupMapTiles() {
        // For mapTilesMonochrome
        BufferedImage monochromeTilesetImage = Atlas.getSpriteAtlas(Atlas.LEVEL_MONOCHROME_TILESET);
        mapTilesMonochrome = new Tile[400];
        for (int row = 0; row < 20; row++) {
            for (int col = 0; col < 20; col++) {
                int index = row * 20 + col;
                boolean allowCollision = !tileMonochromeNonCollisions.contains(index);
                boolean dealsDamage = tileMonochromeDamage.contains(index);
                boolean isDoor = index == 59; // 59 is the index determining whether the tile is a door.
                mapTilesMonochrome[index] = new Tile(monochromeTilesetImage.getSubimage(col * 16, row * 16, 16, 16), allowCollision, dealsDamage, isDoor);
            }
        }
    }

    /**
     * getMapTilesMonochrome | Fetches the data for the map tiles of monochrome design.
     * @return Returns the data containing monochrome tiles.
     */
    public Tile[] getMapTilesMonochrome() {
        return mapTilesMonochrome;
    }
}