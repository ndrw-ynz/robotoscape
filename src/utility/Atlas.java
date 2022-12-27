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
public abstract class Atlas {

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

    // ENEMY-SPECIFIC ANIMATION SPRITE ATLAS (10 Atlas)
    /**The file path for the active animation state of the abomination enemy entity.*/
    public static final String ENEMY_ABOMINATION_ACTIVE = "enemy/abomination_active.png";
    /**The file path for the passive animation state of the abomination enemy entity.*/
    public static final String ENEMY_ABOMINATION_PASSIVE = "enemy/abomination_passive.png";
    /**The file path for the animation state of the cultist enemy entity.*/
    public static final String ENEMY_CULTIST = "enemy/cultist.png";
    /**The file path for the animation state of the drone enemy entity.*/
    public static final String ENEMY_DRONE = "enemy/drone.png";
    /**The file path for the animation state of the robot air enemy entity.*/
    public static final String ENEMY_ROBOT_AIR = "enemy/robot_air.png";
    /**The file path for the active animation state of the robot ground enemy entity.*/
    public static final String ENEMY_ROBOT_GROUND_ACTIVE = "enemy/robot_ground_active.png";
    /**The file path for the passive animation state of the robot ground enemy entity.*/
    public static final String ENEMY_ROBOT_GROUND_PASSIVE = "enemy/robot_ground_passive.png";
    /**The file path for the animation state of the skull slime enemy entity.*/
    public static final String ENEMY_SKULL_SLIME = "enemy/skull_slime.png";
    /**The file path for the active animation state of the turret enemy entity.*/
    public static final String ENEMY_TURRET_CHARGING = "enemy/turret_charging.png";
    /**The file path for the passive animation state of the turret enemy entity.*/
    public static final String ENEMY_TURRET_PASSIVE = "enemy/turret_passive.png";

    // TILE SETS USED IN LEVEL-BUILDING.
    /**The file path for the monochrome tile sets used in building the levels of the game.*/
    public static final String LEVEL_MONOCHROME_TILESET = "tiles/monochrome_tilemap_transparent_packed.png";

    // HEARTS ASSETS USED IN THE GAME.
    /**The file path for the heart animations used in the game.*/
    public static final String HEART_ANIMATIONS = "hearts/hearts.png";

    // FONTS USED IN THE GAME.
    /**The file path for Silver font used in the game.*/
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

    /**
     * extractAnimationImages extracts the individual animation frames for a given animation image.
     * @param source            A String containing the local file directory for the game image resource.
     * @param subImageWidth     The width of the sub-image to be extracted from the animation image.
     * @param subImageHeight    The height of the sub-image to be extracted from the animation image.
     * @return Returns a BufferedImage array containing the individual animation frames for a given animation image.
     */
    public static BufferedImage[] extractAnimationImages(String source, int subImageWidth, int subImageHeight) {
        BufferedImage image = getSpriteAtlas(source);
        int numOfImages = image.getWidth() / subImageWidth;
        BufferedImage[] animationImages = new BufferedImage[numOfImages];
        for (int i = 0; i < numOfImages; i++) {
            animationImages[i] = image.getSubimage(i*subImageWidth, 0, subImageWidth, subImageHeight);
        }
        return animationImages;
    }
}