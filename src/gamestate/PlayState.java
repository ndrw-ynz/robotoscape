package gamestate;

import entity.EnemyManager;
import entity.Player;
import gameoverlay.GameOverOverlay;
import gameoverlay.PauseOverlay;
import level.Level;
import level.LevelManager;
import loading.Loading;
import loading.LoadingPhase;
import main.Game;
import main.GamePanel;
import projectiles.ProjectileManager;
import tile.TileManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;

/**
 * The PlayState class extends the State class and implements
 * StateMethods.
 * It maintains the play state of the game as the game starts
 * and implements different levels and its components on the
 * game screen.
 */
public class PlayState extends State implements StateMethods {

    /**The panel of the game where the components of different game states are displayed.*/
    private final GamePanel gamePanel;
    /**The player that is controlled by the user in the game.*/
    private final Player player;
    /**Manages the levels of the game and its state.*/
    private final LevelManager levelManager;
    /**Manages the tiles used in the game.*/
    private final TileManager tileManager;
    /**Manages the enemies in the current level of the game.*/
    private final EnemyManager enemyManager;
    /**Manages the projectiles in the current level of the game.*/
    private final ProjectileManager projectileManager;
    /**Manages the loading screen of the game as introduced before the level starts.*/
    private Loading loading;
    /**Manages the pause overlay screen of the game.*/
    private final PauseOverlay pauseOverlay;
    /**Manages the game over overlay screen of the game.*/
    private final GameOverOverlay gameOverOverlay;
    /**The state of the play state, whether it's displaying loading or not.*/
    private boolean isLoading;
    /**The state of the play state, whether the game is paused or not.*/
    private boolean isPaused;
    /**The offset of the player as it reaches the border on the x-axis of the game screen.*/
    private double xOffset;
    /**The maximum value of offset of the player in the x-axis of the game screen.*/
    private final int maxXOffset;
    /**The x-coordinate for the left border of the player on the game screen.*/
    private final int leftBorder;
    /**The x-coordinate for the right border of the player on the game screen.*/
    private final int rightBorder;
    /** The offset of the player as it reaches the border on the y-axis of the game screen.*/
    private double yOffset;
    /**The maximum value of offset of the player in the y-axis of the game screen.*/
    private final int maxYOffset;
    /**The y-coordinate for the upper border of the player on the game screen.*/
    private final int upBorder;
    /**The y-coordinate for the lower border of the player on the game screen.*/
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
        levelManager = new LevelManager(game);
        tileManager = new TileManager();
        enemyManager = new EnemyManager(levelManager.getCurrentLevel());
        projectileManager = new ProjectileManager(levelManager.getCurrentLevel(), tileManager, enemyManager);

        player = new Player(levelManager.getCurrentLevel().getPlayerXPosition(), levelManager.getCurrentLevel().getPlayerYPosition(), 36, 23, game.getEntityScale(), 1,4);

        pauseOverlay = new PauseOverlay(game, this, 570, 240);
        gameOverOverlay = new GameOverOverlay(game, this, 570, 160);

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
        if (isLoading) {
            loading.renderLoading(graphics, levelManager.getCurrentLevel().getLevelDimension());
        } else {
            updateOffsetsFromPlayer();
            player.renderEntity((Graphics2D) graphics, xOffset, yOffset);
        }
        levelManager.renderLevel(graphics, xOffset, yOffset);
        enemyManager.renderEnemies((Graphics2D) graphics, xOffset, yOffset);
        projectileManager.renderPlayerProjectiles((Graphics2D) graphics, xOffset, yOffset);
        if (isPaused && !player.isDead()) pauseOverlay.renderOverlay((Graphics2D) graphics);
        if (player.isDead()) gameOverOverlay.renderOverlay((Graphics2D) graphics);
    }

    @Override
    public void update() {
        if (player.isDead()) return;
        if (isLoading) {
            Level currentLevel = levelManager.getCurrentLevel();
            loading.updateLoadingPhase(game.getGameTime());
            switch(LoadingPhase.phase){
                case START -> {
                    loading.updateAlphaValue(false);
                    xOffset = loading.getxLoadingPosition();
                    yOffset = loading.getyLoadingPosition();
                }
                case TRANSITION -> {
                    // Determine xOffsetSpeed and yOffsetSpeed if not yet set.
                    if (loading.getxOffsetSpeed() == 0 && loading.getyOffsetSpeed() == 0) {
                        loading.setOffsetSpeeds(loading.getxLoadingPosition(), loading.getyLoadingPosition(), currentLevel.getPlayerXPosition(), currentLevel.getPlayerYPosition());
                    }
                    updateOffsetFromLoading(loading.getxOffsetSpeed(), loading.getyOffsetSpeed());
                }
                case END -> {
                    loading.updateAlphaValue(true);
                    // wait for x seconds
                }
                case NONE -> {
                     isLoading = false;
                }
            }
        }
        if (!isLoading) {
            player.updateEntity(levelManager.getCurrentLevel(), tileManager);
        }
//        levelManager.updateLevel();
        enemyManager.updateEnemies(levelManager.getCurrentLevel(), tileManager, player);
        projectileManager.updatePlayerProjectiles();
    }

    /**
     * initLoading | Initializes loading instance of the game and sets
     * isLoading to true.
     */
    public void initLoading() {
        loading = new Loading(game, levelManager.getCurrentLevel());
        isLoading = true;
    }

    /**
     * updateOffsetsFromLoading | Updates the xOffset and yOffset of
     * the game based on the position of the loading text from the center
     * of the level and the position of the player from the level.
     */
    private void updateOffsetFromLoading(double xOffsetSpeed, double yOffsetSpeed) {
        xOffset += xOffsetSpeed;
        yOffset += yOffsetSpeed;
    }

    /**
     * updateOffsetsFromPlayer | Updates the xOffset and yOffset of the player on
     * the game as it moves towards the x-axis and y-axis border of the
     * game screen and applies the offset on the components of the game,
     * as it is displayed on the game screen.
     */
    private void updateOffsetsFromPlayer() {
        /*

        */
        float playerXPos = player.getEntityCoordinate().x + player.getxHitBoxDelta();
        double xDiff = playerXPos - xOffset;

        /*
        If the player exceeds the leftBorder and rightBorder even with xOffset,
        add the excess value to xOffset.
        */
        if (xDiff < leftBorder) {
            xOffset += xDiff - leftBorder;
        } else if (xDiff > rightBorder) {
            xOffset += xDiff - rightBorder;
        }
        float playerYPos = player.getEntityCoordinate().y+ player.getyHitBoxDelta();
        double yDiff = playerYPos - yOffset;

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
        if (isPaused && !player.isDead()) pauseOverlay.updateState();
        if (player.isDead()) gameOverOverlay.updateState();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (isPaused) return;
        if (SwingUtilities.isLeftMouseButton(e) && !player.getIsGunOnCoolDown() && !player.getIsOnAir()) {
            player.setIsCharging(true);
        }

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (isPaused) return;
        if (SwingUtilities.isLeftMouseButton(e) && !player.getIsGunOnCoolDown() && player.getIsCharging()) {
            Point2D.Float startCoordinate = player.getGunPointCoordinate();
            Point2D.Float endCoordinate = new Point2D.Float(e.getX(), e.getY());
            // Creates a projectile.
            projectileManager.createPlayerProjectile(startCoordinate, endCoordinate, xOffset, yOffset, player.getDamageValue());
            // Sets state of the player.
            player.setIsCharging(false);
            player.setIsShooting(true);
            player.setIsGunOnCoolDown(true);
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        if (isPaused && !player.isDead()) pauseOverlay.updateInteractiveText(e.getX(), e.getY());
        if (player.isDead()) gameOverOverlay.updateInteractiveText(e.getX(), e.getY());
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_ESCAPE -> isPaused = !isPaused;
            case KeyEvent.VK_ENTER -> isLoading = false;
            case KeyEvent.VK_SPACE -> player.setIsJumping(true);
            case KeyEvent.VK_A -> player.setIsMovingLeft(true);
            case KeyEvent.VK_D -> player.setIsMovingRight(true);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_SPACE -> player.setIsJumping(false);
            case KeyEvent.VK_A -> player.setIsMovingLeft(false);
            case KeyEvent.VK_D -> player.setIsMovingRight(false);
        }
    }

    /**
     * isPaused fetches the current isPaused state of PlayState.
     * @return Returns a boolean value determining whether the current game is paused or not.
     */
    public boolean isPaused() {return isPaused;}

    /**
     * setIsPaused sets the isPaused state of PlayState.
     * @param isPaused The boolean value determining the isPaused state of PlayState.
     */
    public void setIsPaused(boolean isPaused) {
        this.isPaused = isPaused;
    }
}
