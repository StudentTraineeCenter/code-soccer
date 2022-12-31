package stc.soccer.opponents;

import stc.soccer.core.FieldPoint;
import stc.soccer.core.SoccerGame;

//TODO comments

/**
 * This Interface should be used as a template for every opponent
 * that wants to challenge any other opponent in a game of paper soccer. It contains declaration
 * of every method that GameEvaluation.java has to use in order to play the game.
 */
public interface Opponent {

    FieldPoint makeMove(SoccerGame game);

    OpponentType getType();

    String getName();

    GoalLocationType getGoalLocation();

}
