package stc.soccer;

import java.util.Scanner;

public class GameSetup {

    private GameSetup() {
        throw new IllegalStateException("Utility class");
    }

    public static void setupGame() {
        Scanner in = new Scanner(System.in);

        System.out.println("------------- Welcome to Code Soccer -------------");
        System.out.println("ALL NUMBERS HAVE TO BE ODD!!");
        System.out.println("What is the number of columns?");

        int columns = in.nextInt();

        /***
         * columns >= 5
         */
        while (columns % 2 == 0 || columns < 5) {
            System.out.println(columns + " is not a valid input. Write different number of columns:");
            columns = in.nextInt();
        }

        System.out.println("What is the number of rows?");

        int rows = in.nextInt();

        /***
         * rows >= 4
         */
        while (rows % 2 == 0 || rows < 4) {
            System.out.println(rows + " is not a valid input. Write different number of columns:");
            rows = in.nextInt();
        }

        System.out.println("Write names of both players:");

        SoccerGame game = new SoccerGame(columns, rows, in.next(), in.next());

        GameEvaluation eval = new GameEvaluation(game);

        eval.play(in);
    }
}
