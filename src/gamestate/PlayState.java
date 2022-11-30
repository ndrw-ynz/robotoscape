package gamestate;

import entity.Player;
import level.LevelManager;
import loading.Loading;
import main.Game;
import main.GamePanel;
import tile.TileManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

/**
 * The PlayState class extends the State class and implements
 * StateMethods.
 * It maintains the play state of the game as the game starts
 * and implements different levels and its components on the
 * game screen.
 */
public class PlayState extends State implements StateMethods {

    /**The panel of the game where the components of different states are displayed.*/
    private GamePanel gamePanel;
    /**The player that is controlled by the user in the game.*/
    private final Player player;
    /**Manages the levels of the game and its state.*/
    private final LevelManager levelManager;
    /**Manages the tiles used in the game.*/
    private final TileManager tileManager;
    /**Manages the loading screen of the game as introduced before the level starts.*/
    private Loading loading;
    /**The offset of the player as it reaches the border on the x-axis of the game screen.*/
    private int xOffset;
    /**The maximum value of offset in the x-axis of the game screen.*/
    private final int maxXOffset;
    /**The x-coordinate for the left border on the game screen.*/
    private final int leftBorder;
    /**The x-coordinate for the right border on the game screen.*/
    private final int rightBorder;
    /** The offset of the player as it reaches the border on the y-axis of the game screen.*/
    private int yOffset;
    /**The maximum value of offset in the y-axis of the game screen.*/
    private final int maxYOffset;
    /**The y-coordinate for the upper border on the game screen.*/
    private final int upBorder;
    /**The y-coordinate for the lower border on the game screen.*/
    private final int downBorder;

    /**
     * PlayState | Initializes the play state of the game.
     * @param game The main game containing the different states of the
     *             game and their configuration.
     * @param gamePanel The panel of the game where the components of
     *                  different states are displayed.
     */
    public PlayState(Game game, GamePanel gamePanel) {
        super(game);
        this.gamePanel = gamePanel;
        player = new Player(100, 100, 36, 23, game.getEntityScale());
        levelManager = new LevelManager(game);
        tileManager = new TileManager();
        loading = new Loading(levelManager.getCurrentLevel().getLevelNumber());

        int levelWidthTiles = levelManager.getCurrentLevel().getLevelWidthTiles();
        int levelHeightTiles =  levelManager.getCurrentLevel().getLevelHeightTiles();

        int screenWidth = game.getScreenWidth();
        int screenHeight = game.getScreenHeight();
        int tileSize = game.getTileSize();

        leftBorder = (int) (screenWidth * 0.4);
        rightBorder = (int) (screenWidth * 0.6);
        upBorder = (int) (screenHeight * 0.5);
        downBorder = (int) (screenHeight * 0.5);

        maxXOffset = (levelWidthTiles - screenWidth/tileSize)*tileSize;
        maxYOffset = (levelHeightTiles - screenHeight/tileSize)*tileSize;
    }

    @Override
    public void render(Graphics graphics) {
        updateOffsets();
        player.renderPlayer((Graphics2D) graphics, xOffset, yOffset);
        levelManager.renderLevel(graphics, xOffset, yOffset);
    }

    @Override
    public void update() {
        player.updatePlayer(levelManager.getCurrentLevel(), tileManager);
        levelManager.updateLevel();
    }

    /**
     * updateOffsets | Updates the xOffset and yOffset of the player on
     * the game as it moves towards the x-axis and y-axis border of the
     * game screen and applies the offset on the components of the game,
     * as it is displayed on the game screen.
     */
    private void updateOffsets() {
        /*

        */
        int playerXPos = player.getxPos()+ player.getxHitBoxDelta();
        int xDiff = playerXPos - xOffset;

        /*
        If the player exceeds the leftBorder and rightBorder even with xOffset,
        add the excess value to xOffset.
        */
        if (xDiff < leftBorder) {
            xOffset += xDiff - leftBorder;
        } else if (xDiff > rightBorder) {
            xOffset += xDiff - rightBorder;
        }
        int playerYPos = player.getyPos()+ player.getyHitBoxDelta();
        int yDiff = playerYPos - yOffset;

        /*
        If the player exceeds the upBorder and downBorder even with yOffset,
        add the excess value to yOffset.
         */
        if (yDiff < upBorder) {
            yOffset += yDiff - upBorder;
        } else if (yDiff > downBorder) {
            yOffset += yDiff - downBorder;
        }

        /*
        If xOffset exceeds maxXOffset, retain maximum value possible for xOffset.
        If xOffset is negative, reset to 0.
        */
        if (xOffset > maxXOffset) {
            xOffset = maxXOffset;
        } else if (xOffset < 0) {
            xOffset = 0;
        }

        /*
        if yOffset exceeds maxYOffset, retain maximum value possible for yOffset.
        if yOffset is negative, reset to 0. */
        if (yOffset > maxYOffset) {
            yOffset = maxYOffset;
        } else if (yOffset < 0) {
            yOffset = 0;
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (SwingUtilities.isLeftMouseButton(e)) {
            player.setIsCharging(true);
        }

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (SwingUtilities.isLeftMouseButton(e)) {
            player.setIsCharging(false);
            player.setIsShooting(true);
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch(e.getKeyCode()) {
            case KeyEvent.VK_SPACE:
                player.setIsJumping(true);
                break;
            case KeyEvent.VK_A:
                player.setIsMovingLeft(true);
                break;
            case KeyEvent.VK_D:
                player.setIsMovingRight(true);
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch(e.getKeyCode()) {
            case KeyEvent.VK_SPACE:
                player.setIsJumping(false);
                break;
            case KeyEvent.VK_A:
                player.setIsMovingLeft(false);
                break;
            case KeyEvent.VK_D:
                player.setIsMovingRight(false);
                break;
        }
    }
}
