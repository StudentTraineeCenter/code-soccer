package stc.soccer.opponents;

import stc.soccer.FieldPoint;
import stc.soccer.SoccerGame;

public interface ArtificialOpponent {

    FieldPoint makeMove(SoccerGame game);

}
