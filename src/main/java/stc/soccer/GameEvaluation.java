package stc.soccer;

import stc.soccer.opponents.ArtificialOpponent;

import java.util.Scanner;

public class GameEvaluation {

    private final SoccerGame game;
    private boolean turn;
    private ArtificialOpponent ai;

    public GameEvaluation(SoccerGame game, ArtificialOpponent ai) {
        this.game = game;
        this.turn = true;
        this.ai = ai;
    }

    /**
     * Method used to start the game.
     *
     * @param in for using already pre-existing Scanner.
     */
    public void play(Scanner in) {
        while (true) {
            FieldPoint destination;
            turnOutput(turn);

            FieldPoint destination = new FieldPoint(in.nextInt(), in.nextInt());

            while (!isMoveValid(destination)) {
                System.out.println("Specified move is not valid. Try again.");
                destination = new FieldPoint(in.nextInt(), in.nextInt());
            }

            if (game.isPointInsideGoals(destination)) {
                break;
            }

            turn = changePlayers(turn, destination);
            game.addMove(destination);
        }
    }

    /**
     * Method for informing players about their turns.
     *
     * @param turn whose purpose is to identify which players turn it is.
     */
    private void turnOutput(boolean turn) {
        if (turn) {
            System.out.println("Players " + game.getPlayer1() + " turn:");
        } else {
            System.out.println("Players " + game.getPlayer2() + " turn:");
        }

        System.out.println("Current location of ball is " + game.getCurrentPosition().toString() + ".");
    }

    //TODO comment
    private boolean changePlayers(boolean turn, FieldPoint point) {
        if (game.checkBounce(point)) {
            System.out.println("It's a bounce!!!");
        } else {
            turn = !turn;
        }

        return turn;
    }
}
