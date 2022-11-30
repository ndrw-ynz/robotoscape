package gamestate;

import main.Game;

public abstract class State {

    protected Game game;

    public State(Game game) {
        this.game = game;
    }

    public Game getGame() {
        return game;
    }
}
