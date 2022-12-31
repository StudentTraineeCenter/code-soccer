package stc.soccer.opponents;

import stc.soccer.core.FieldPoint;
import stc.soccer.core.SoccerGame;

import java.util.Scanner;

public class HumanOpponent implements Opponent {

    private static final OpponentType type = OpponentType.HUMAN;
    private final GoalLocationType goalLocation;
    private final Scanner in;
    private final String name;

    public HumanOpponent(String name, Scanner in, GoalLocationType goalLocation) {
        this.name = name;
        this.in = in;
        this.goalLocation = goalLocation;
    }

    @Override
    public FieldPoint makeMove(SoccerGame game) {
        return new FieldPoint(in.nextInt(), in.nextInt());
    }

    public OpponentType getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    @Override
    public GoalLocationType getGoalLocation() {
        return goalLocation;
    }

    @Override
    public String toString() {
        return "HumanOpponent{" +
                "goalLocation=" + goalLocation +
                ", name='" + name + '\'' +
                '}';
    }
}
