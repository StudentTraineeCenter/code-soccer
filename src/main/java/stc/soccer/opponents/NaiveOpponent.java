package stc.soccer.opponents;

import stc.soccer.core.FieldPoint;
import stc.soccer.core.SoccerGame;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * This naive artificial opponent is mainly used a test subject.
 * It's moves are based on the Java Random class.
 */
public class NaiveOpponent implements Opponent {

    private static final OpponentType type = OpponentType.NAIVEAI;
    private final GoalLocationType goalLocation;
    private final String name;
    private final Random rand;

    public NaiveOpponent(String name, GoalLocationType goalLocation) {
        this.name = name;
        this.goalLocation = goalLocation;
        rand = new Random();
    }

    /**
     * Method used for receiving of a pseudo-random move.
     * @param game of current game.
     * @return destination point.
     */
    @Override
    public FieldPoint makeMove(SoccerGame game) {
        Set<FieldPoint> triedPoints = new HashSet<>();
        FieldPoint point = createRandomPoint(game.getCurrentPosition());

        while (!game.isMoveValid(point)) {
            triedPoints.add(point);
            point = createRandomPoint(game.getCurrentPosition());
            while (triedPoints.contains(point)) {
                point = createRandomPoint(game.getCurrentPosition());
            }
        }

        return point;
    }

    /**
     * Method for creation of a point in correct format to check its validity.
     * @param currentPosition is a current location of ball that's used to make a relative move to it.
     * @return FieldPoint in correct format.
     */
    private FieldPoint createRandomPoint(FieldPoint currentPosition) {
        return new FieldPoint(
                currentPosition.column() + rand.nextInt(3) - 1,
                currentPosition.row() + rand.nextInt(3) - 1);
    }

    /**
     * Getter for value of OpponentType.
     * @return type of OpponentType.
     */
    @Override
    public OpponentType getType() {
        return type;
    }

    /**
     * Getter for name of opponent.
     * @return String name of opponent.
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * Getter for value of GoalLocation.
     * @return type of GoalLocationType.
     */
    @Override
    public GoalLocationType getGoalLocation() {
        return goalLocation;
    }

    @Override
    public String toString() {
        return "NaiveOpponent{" +
                "goalLocation=" + goalLocation +
                ", name='" + name + '\'' +
                '}';
    }
}
