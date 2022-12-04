package gamestate;

/**
 * GameState is an enum keeping track of the different
 * states of the game and keeping track of the current
 * state of the game.
 */
public enum GameState {
    /**The menu state of the game.*/
    MENU,
    /**The play state of the game.*/
    PLAY,
    /**The credit state of the game.*/
    CREDITS;
    /**Keeps track of the current state of the game.*/
    public static GameState state = MENU;
}
