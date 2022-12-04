package loading;

public enum LoadingPhase {
    /**Determines the initialization process of the loading state.*/
    INIT,
    /**Determines if the loading screen is on the start phase.*/
    START,
    /**Determines if the loading screen is on the transition phase.*/
    TRANSITION,
    /**Determines if the loading screen is on the end phase.*/
    END,
    NONE;

    public static LoadingPhase phase = NONE;
}
