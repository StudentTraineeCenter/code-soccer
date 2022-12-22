package stc.soccer.opponents;

import stc.soccer.FieldPoint;
import stc.soccer.SoccerGame;

//TODO comments

/**
 * This Interface should be used as a template for every artificial opponent
 * that wants to challenge any other opponent. It contains declaration
 * of every method that GameEvaluation.java has to use in order to play the game.
 */
public interface ArtificialOpponent {

    FieldPoint makeMove(SoccerGame game);

}