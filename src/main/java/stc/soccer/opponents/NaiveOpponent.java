package stc.soccer.opponents;

import stc.soccer.FieldPoint;
import stc.soccer.SoccerGame;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * This naive artificial opponent is mainly used a test subject.
 * It's moves are based on the Java Random class.
 */
public class NaiveOpponent implements Opponent {

    private static final OpponentType type = OpponentType.NAIVEAI;
    private final String name;
    private final Random rand;

    public NaiveOpponent(String name) {
        this.name = name;
        rand = new Random();
    }

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

    private FieldPoint createRandomPoint(FieldPoint currentPosition) {
        return new FieldPoint(
                currentPosition.column() + rand.nextInt(3) - 1,
                currentPosition.row() + rand.nextInt(3) - 1);
    }

    @Override
    public OpponentType getType() {
        return type;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "NaiveOpponent{" +
                "name='" + name + '\'' +
                ", rand=" + rand +
                '}';
    }
}
