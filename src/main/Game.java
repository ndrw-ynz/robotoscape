package main;

import gamestate.CreditsState;
import gamestate.GameState;
import gamestate.MenuState;
import gamestate.PlayState;
import loading.LoadingPhase;

import java.awt.*;

/**
 * The Game class is the class containing the different states of the
 * game and their configurations.
 */
public class Game implements Runnable{
    /**The panel of the game where the components of different game states are displayed.*/
    private final GamePanel gamePanel;
    /**The pre-determined size of sprites in the game.*/
    protected final int BIT_SIZE = 16;
    /**The pre-determined maximum number of columns containing tiles/entities in the screen.*/
    protected final int MAX_SCREEN_COL = 30;
    /**The pre-determined maximum number of rows containing tiles/entities in the screen.*/
    protected final int MAX_SCREEN_ROW = 16;
    /**The pre-determined screen width of the game window.*/
    protected final int SCREEN_WIDTH = BIT_SIZE * MAX_SCREEN_COL * 3; // 1440 pixels
    /**The pre-determined screen height of the game window.*/
    protected final int SCREEN_HEIGHT = BIT_SIZE * MAX_SCREEN_ROW * 3; // 768 pixels
    /**The pre-determined scaling factor for displaying tiles in the game.*/
    protected final int TILE_SCALE = 2;
    /**The pre-determined scaling factor for displaying entities in the game.*/
    protected final float ENTITY_SCALE = 2.0f;
    /**The pre-determined size of tiles displayed in the game.*/
    protected final int TILE_SIZE = BIT_SIZE * TILE_SCALE; // 32 x 32 tile size
    /**The state containing the state and behavior for the play state of the game.*/
    private PlayState playState;
    /**The state containing the state and behavior for the menu state of the game.*/
    private MenuState menuState;
    /**The state containing the state and behavior for the credits state of the game.*/
    private CreditsState creditsState;
    /**Stores the current time of the game in nanoseconds.*/
    private long gameTime;
    private int currentUpdates;

    /**
     * Game | Comprises the entire component of the game and manages its states.
     */
    public Game () {
        initStates();
        gamePanel = new GamePanel(this);
        new GameWindow(gamePanel);
        startGameLoop();
    }

    /**
     * resetPlayState creates a new PlayState of the game.
     */
    public void resetPlayState() {
        playState = new PlayState(this);
    }

    /**
     * initStates | Initializes the component states of the game.
     */
    private void initStates() {
        playState = new PlayState(this);
        menuState = new MenuState(this);
        creditsState = new CreditsState(this);
    }

    /**
     * startGameLoop | Starts the thread running the game, initiating the
     * start of the game.
     */
    private void startGameLoop() {
        Thread gameThread = new Thread(this);
        gameThread.start();
    }

    /**
     * renderGame | Renders the components of the current state of the game.
     * @param graphics The graphics object that draws images on the game screen.
     */
    public void renderGame(Graphics graphics) {
        switch (GameState.state) {
            case MENU -> menuState.render(graphics);
            case PLAY -> {
                if (LoadingPhase.phase == LoadingPhase.INIT) {
                    playState.initLoading();
                }
                playState.render(graphics);
            }
            case CREDITS -> creditsState.render(graphics);
            default -> {
            }
        }
    }

    /**
     * updateGame | Updates the states of the game.
     */
    public void updateGame(){
        switch (GameState.state) {
            case MENU -> menuState.update();
            case PLAY -> {
                if (LoadingPhase.phase == LoadingPhase.INIT) {
                    playState.initLoading();
                }
                if (!playState.isPaused()) playState.update();
            }
        }
    }

    /**
     * exitGame | Quits the game and terminates the program's processes.
     */
    public void exitGame() {
        System.exit(0);
    }

    /**
     * run | Initiates the game loop of the game and tracks the frames
     * and updates made per second.
     */
    @Override
    public void run() {
        int FPS = 120;
        double timePerFrame = 1000000000.0 / FPS;
        int UPS = 200;
        double timePerUpdate = 1000000000.0 / UPS;

        long previousTime = System.nanoTime();

        int frames = 0;
        int updates = 0;
        long lastCheck = System.currentTimeMillis();

        double deltaU = 0;
        double deltaF = 0;

        //noinspection InfiniteLoopStatement
        while (true) {
            long currentTime = System.nanoTime();
            gameTime = System.nanoTime();

            deltaU += (currentTime - previousTime) / timePerUpdate;
            deltaF += (currentTime - previousTime) / timePerFrame;
            previousTime = currentTime;

            if (deltaU >= 1) {
                updateGame();
                updates++;
                deltaU--;
            }

            if (deltaF >= 1) {
                gamePanel.repaint();
                frames++;
                deltaF--;
            }

            if (System.currentTimeMillis() - lastCheck >= 1000) {
                lastCheck = System.currentTimeMillis();
                System.out.println("FPS: " + frames + " | UPS: " + updates);
                currentUpdates = updates;
                frames = 0;
                updates = 0;

            }
        }
    }

    /**
     * getCurrentUpdates fetches the current updates-per-second of the game from the game loop.
     * @return Returns the current updates-per-second of the game from the game loop.
     */
    public int getCurrentUpdates() {return currentUpdates;}


    /**
     * getPlayingState | Fetches the playing state of the game.
     * @return Returns the PlayState of the game.
     */
    public PlayState getPlayingState() {
        return playState;
    }

    /**
     * getMenuState | Fetches the menu state of the game.
     * @return Returns the MenuState of the game.
     */
    public MenuState getMenuState() {
        return menuState;
    }

    /**
     * getCreditsState fetches the credit state of the game.
     * @return Returns the CreditState of the game.
     */
    public CreditsState getCreditsState() {return creditsState;}

    /**
     * getScreenWidth | Fetches the width of the game screen.
     * @return Returns the width of the game screen.
     */
    public int getScreenWidth() {return SCREEN_WIDTH;}

    /**
     * getScreenHeight | Fetches the height of the game screen.
     * @return Returns the height of the game screen.
     */
    public int getScreenHeight() {return SCREEN_HEIGHT;}
    /**
     * getEntityScale | Fetches the scale of entities.
     * @return Returns the scale value of entities.
     */
    public float getEntityScale() {return ENTITY_SCALE;}

    /**
     * getTileSize | Fetches the size of tiles in the game.
     * @return Returns the size of tiles in the game.
     */
    public int getTileSize() {return TILE_SIZE;}

    /**
     * getGameTime | Fetches the current game time in nanoseconds.
     * @return Returns the current game time in nanoseconds.
     */
    public long getGameTime() {return gameTime;}
}