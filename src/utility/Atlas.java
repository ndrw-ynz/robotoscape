package utility;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

/**
 * The Atlas class contains the path to specific game resources used
 * in displaying the animation of the player, the tiles used in each game
 * level, and the fonts used.
 */
public class Atlas {

    // PLAYER-SPECIFIC ANIMATION SPRITE ATLAS (8 Atlas)
    /**The file path for the idle status animation states of the player.*/
    public static final String PLAYER_STATIC_IDLE = "player/static_idle.png";
    /**The file path for the wake status animation states of the player.*/
    public static final String PLAYER_WAKE = "player/wake.png";
    /**The file path for the move status animation states of the player.*/
    public static final String PLAYER_MOVE = "player/move_with_FX.png";
    /**The file path for the jump status animation states of the player.*/
    public static final String PLAYER_JUMP = "player/move_without_FX.png";
    /**The file path for the charge status animation states of the player.*/
    public static final String PLAYER_CHARGE = "player/charge.png";
    /**The file path for the shoot status animation states of the player.*/
    public static final String PLAYER_SHOOT = "player/shoot_without_FX.png";
    /**The file path for the dash status animation states of the player.*/
    public static final String PLAYER_GAS_DASH = "player/GAS_dash_with_FX.png";
    /**The file path for the damaged status animation states of the player.*/
    public static final String PLAYER_DAMAGED = "player/damaged.png";
    /**The file path for the death status animation states of the player.*/
    public static final String PLAYER_DEATH = "player/death.png";
    /**The file path for the monochrome tile sets used in building the levels of the game.*/
    // TILE SETS USED IN LEVEL-BUILDING.
    public static final String LEVEL_MONOCHROME_TILESET = "tiles/monochrome_tilemap_transparent_packed.png";
    /**The file path for Silver font used in the game.*/
    // FONTS USED IN THE GAME.
    public static final String SILVER_FONT = "fonts/Silver.ttf";
    /**The file path for Robus font used in the game.*/
    public static final String ROBUS_FONT = "fonts/Robus.ttf";
    /**The file path for Tarrget font used in the game.*/
    public static final String TARRGET_FONT = "fonts/TarrgetPlatinumItalic.ttf";

    /**
     * getSpriteAtlas extracts game images from the local directory to the game.
     * @param source A String containing the local file directory for the game image resource.
     * @return Returns a BufferedImage containing the extracted game image.
     */
    public static BufferedImage getSpriteAtlas(String source) {
        try {
            return ImageIO.read(Objects.requireNonNull(Atlas.class.getResourceAsStream("/" + source)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * getFont fetches the fonts from the local directory to the game.
     * @param source A String containing the local file directory for the true-type font file.
     * @return Returns a Font extracted from the local font directory.
     */
    public static Font getFont(String source) {
        try {
            InputStream fontFile = Objects.requireNonNull(Atlas.class.getResourceAsStream("/" + source));
            return Font.createFont(Font.TRUETYPE_FONT, fontFile);
        } catch (FontFormatException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
