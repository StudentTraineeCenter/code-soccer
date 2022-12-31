package stc.soccer.opponents;

import stc.soccer.core.FieldPoint;
import stc.soccer.core.SoccerGame;

/**
 * This Interface should be used as a template for every opponent
 * that wants to challenge any other opponent in a game of paper soccer. It contains declaration
 * of every method that GameEvaluation.java has to use in order to play the game.
 */
public interface Opponent {

    /**
     * Method used for receiving of a move from an opponent.
     * @param game of current game.
     * @return destination point.
     */
    FieldPoint makeMove(SoccerGame game);

    /**
     * Getter for value of OpponentType.
     * @return type of OpponentType.
     */
    OpponentType getType();

    /**
     * Getter for name of opponent.
     * @return String name of opponent.
     */
    String getName();

    /**
     * Getter for value of GoalLocation.
     * @return type of GoalLocationType.
     */
    GoalLocationType getGoalLocation();

}
