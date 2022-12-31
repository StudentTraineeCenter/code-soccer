package stc.soccer.core;

import stc.soccer.opponents.GoalLocationType;
import stc.soccer.opponents.NaiveOpponent;
import stc.soccer.opponents.Opponent;

import java.util.Scanner;

public class GameSetup {
    private static final int COLUMNS = 9;
    private static final int ROWS = 11;

    private GameSetup() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * Utility method to set up and start a new Code Soccer game.
     */
    public static Opponent setupGame() {
        Scanner in = new Scanner(System.in);
        System.out.println("------------- Welcome to Code Soccer -------------");

        //TODO give programmers chance to just send an empty opponent
        GameEvaluation eval = new GameEvaluation(new SoccerGame(COLUMNS, ROWS), new NaiveOpponent("Naive1T", GoalLocationType.TOP), new NaiveOpponent("Naive2B", GoalLocationType.BOTTOM));
//        GameEvaluation eval = new GameEvaluation(new SoccerGame(COLUMNS, ROWS), new NaiveOpponent("Naive1T", GoalLocationType.TOP), new HumanOpponent("Woodz", in, GoalLocationType.BOTTOM));

        return eval.play(in);//TODO probably setup playing field so we can search in it
    }

    /**
     * Method for receiving data to set up a new game.
     * <p>
     * It is used for receiving number of columns and rows of the field. Both of
     * these values have to be odd and bigger than five.
     *
     * @param in for using a pre-existing scanner.
     * @return valid value received from the user.
     */
    private static int getGameValues(Scanner in) {
        int value = in.nextInt();

        while (value % 2 == 0 || value < 5) {
            System.out.println(value + " is not a valid input.\nThis number has to be odd and >= 5.\nWrite a different number:");
            value = in.nextInt();
        }

        return value;
    }
}
