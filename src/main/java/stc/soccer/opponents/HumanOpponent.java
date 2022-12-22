package stc.soccer.opponents;

import stc.soccer.FieldPoint;
import stc.soccer.SoccerGame;

import java.util.Scanner;

public class HumanOpponent implements Opponent {
    private static final OpponentType type = OpponentType.HUMAN;
    private final Scanner in;
    private final String name;

    public HumanOpponent(String name, Scanner in) {
        this.name = name;
        this.in = in;
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
}
