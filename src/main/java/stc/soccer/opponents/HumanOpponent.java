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

    /**
     * Method used for receiving of a move from human from console.
     * @param game of current game.
     * @return destination point.
     */
    @Override
    public FieldPoint makeMove(SoccerGame game) {
        return new FieldPoint(in.nextInt(), in.nextInt());
    }

    /**
     * Getter for value of OpponentType.
     * @return type of OpponentType.
     */
    public OpponentType getType() {
        return type;
    }

    /**
     * Getter for name of opponent.
     * @return String name of opponent.
     */
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
        return "HumanOpponent{" +
                "goalLocation=" + goalLocation +
                ", name='" + name + '\'' +
                '}';
    }
}
