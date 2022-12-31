package stc.soccer.core;

import stc.soccer.opponents.GoalLocationType;
import stc.soccer.opponents.NaiveOpponent;
import stc.soccer.opponents.Opponent;

/**
 * This is a utility class called in Main class to set up and start a game of Code Soccer.
 */
public class GameSetup {
    private static final int COLUMNS = 9;
    private static final int ROWS = 11;

    private GameSetup() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * Utility method to set up and start a new Code Soccer game.
     */
    public static Opponent setupGame() {
        System.out.println("------------- Welcome to Code Soccer -------------");

        GameEvaluation eval = new GameEvaluation(new SoccerGame(COLUMNS, ROWS), new NaiveOpponent("Naive1T", GoalLocationType.TOP), new NaiveOpponent("Naive2B", GoalLocationType.BOTTOM));

        return eval.play();
    }
}
