package stc.soccer.opponents;

import stc.soccer.core.FieldPoint;
import stc.soccer.core.SoccerGame;

public class ShortestPathOpponent implements Opponent {

    private static final int DEPTH = 5;
    private static final OpponentType type = OpponentType.SHORTESTAI;
    private final GoalLocationType goalLocation;
    private final String name;

    public ShortestPathOpponent(GoalLocationType goalLocation, String name) {
        this.goalLocation = goalLocation;
        this.name = name;
    }

    @Override
    public FieldPoint makeMove(SoccerGame game) {
        return null;
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
        return "ShortestPathOpponent{" +
                "goalLocation=" + goalLocation +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public GoalLocationType getGoalLocation() {
        return goalLocation;
    }
}
