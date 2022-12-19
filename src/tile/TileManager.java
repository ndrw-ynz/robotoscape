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
    public static final ArrayList<Integer> tileMonochromeNonCollisions = new ArrayList<>(Arrays.asList(0,4,5,24,25,26,140,141,142,166));

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
                mapTilesMonochrome[index] = new Tile(monochromeTilesetImage.getSubimage(col * 16, row * 16, 16, 16), allowCollision);
            }
        }
        // add future tiles here if there is
    }

    /**
     * getMapTilesMonochrome | Fetches the data for the map tiles of monochrome design.
     * @return Returns the data containing monochrome tiles.
     */
    public Tile[] getMapTilesMonochrome() {
        return mapTilesMonochrome;
    }
}