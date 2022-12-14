package stc.soccer;

import stc.soccer.opponents.NaiveOpponent;

import java.util.Scanner;

public class GameSetup {

    private GameSetup() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * Utility method to set up and start a new Code Soccer game.
     */
    public static void setupGame() {
        Scanner in = new Scanner(System.in);
        int columns;
        int rows;

        System.out.println("------------- Welcome to Code Soccer -------------");

        System.out.println("What is the number of columns?");
        columns = getGameValues(in);

        System.out.println("What is the number of rows?");
        rows = getGameValues(in);

        System.out.println("Write a name of humanoid player:");
        GameEvaluation eval = new GameEvaluation(new SoccerGame(columns, rows, in.next()), new NaiveOpponent());

        eval.play(in);
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
