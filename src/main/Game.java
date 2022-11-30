package main;

import gamestate.GameState;
import gamestate.MenuState;
import gamestate.PlayState;

import java.awt.*;

/**
 * The Game class is the class containing the different states of the
 * game and their configurations.
 */
public class Game implements Runnable{
    private final GamePanel gamePanel;
    protected final int BIT_SIZE = 16;
    protected final int MAX_SCREEN_COL = 30;
    protected final int MAX_SCREEN_ROW = 16;
    protected final int SCREEN_WIDTH = BIT_SIZE * MAX_SCREEN_COL * 3; // 1440 pixels
    protected final int SCREEN_HEIGHT = BIT_SIZE * MAX_SCREEN_ROW * 3; // 768 pixels
    protected final int TILE_SCALE = 2;
    protected final int ENTITY_SCALE = 2;
    protected final int TILE_SIZE = BIT_SIZE * TILE_SCALE; // 32 x 32 tile size
    protected final int ENTITY_SIZE = BIT_SIZE * ENTITY_SCALE; // 32 x 32 tile size
    private PlayState playState;
    private MenuState menuState;

    /**
     * Game | Comprises the entire component of the game and manages its states.
     */
    public Game () {
        initStates();
        gamePanel = new GamePanel(this);
        GameWindow gameWindow = new GameWindow(gamePanel);
        startGameLoop();
    }

    /**
     * initStates | Initializes the component states of the game.
     */
    private void initStates() {
        playState = new PlayState(this, gamePanel);
        menuState = new MenuState(this);
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
            case MENU:
                menuState.render(graphics);
                break;
            case PLAY:
                playState.render(graphics);
                break;
            default:
                break;
        }
    }

    /**
     * updateGame | Updates the states of the game.
     */
    public void updateGame(){
        switch (GameState.state) {
            case MENU:
                menuState.update();
                break;
            case PLAY:
                playState.update();
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

        while (true) {
            long currentTime = System.nanoTime();

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
                frames = 0;
                updates = 0;

            }
        }
    }

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
    public int getEntityScale() {return ENTITY_SCALE;}

    /**
     * getTileSize | Fetches the size of tiles in the game.
     * @return Returns the size of tiles in the game.
     */
    public int getTileSize() {return TILE_SIZE;}}