package stc.soccer.opponents;

import stc.soccer.core.FieldPoint;
import stc.soccer.core.SoccerGame;

import java.util.Scanner;


/**
 * This opponent is used as a form of human input. It can be used to try the game on your own.
 */
public class HumanOpponent implements Opponent {

    private static final OpponentType type = OpponentType.HUMAN;
    private final GoalLocationType goalLocation;
    private final Scanner in;
    private final String name;

    public HumanOpponent(String name, GoalLocationType goalLocation) {
        this.name = name;
        this.in = new Scanner(System.in);
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
