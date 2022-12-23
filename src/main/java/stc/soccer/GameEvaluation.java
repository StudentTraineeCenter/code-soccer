package stc.soccer;

import stc.soccer.opponents.Opponent;

import java.util.Scanner;

public class GameEvaluation {

    private final SoccerGame game;
    private boolean turn;
    private final Opponent opponent1;
    private final Opponent opponent2;

    public GameEvaluation(SoccerGame game, Opponent opponent1, Opponent opponent2) {
        this.game = game;
        this.turn = true;
        this.opponent1 = opponent1;
        this.opponent2 = opponent2;
    }

    /**
     * Method used to start the game.
     *
     * @param in for using already pre-existing Scanner.
     * @return winner of finished game
     */
    public Opponent play(Scanner in) {
        while (true) {
            FieldPoint destination;
            turnOutput(turn);

            //TODO turn decision in method???
            //TODO goal decision
            if (turn) {
                destination = opponent1.makeMove(game);
            } else {
                destination = opponent2.makeMove(game);
            }

            while (!game.isMoveValid(destination)) {
                System.out.println("Specified move is not valid. Try again.");
                if (turn) {
                    destination = opponent1.makeMove(game);
                } else {
                    destination = opponent2.makeMove(game);
                }
            }

            System.out.println(destination);
            turn = changePlayers(turn, destination); //TODO fuse end game with change players and add move
            game.addMove(destination);

            if (game.isPointInsideGoals(destination)) {
                if (game.getCurrentPosition().row() == 0) {
                    return opponent2;
                } else {
                    return opponent1;
                }
            }

            if (!game.isMovePossible()) {
                if (turn) {
                    return opponent2;
                } else {
                    return opponent1;
                }
            }

        }
    }

    /**
     * Method for informing players about their turns.
     *
     * @param turn whose purpose is to identify which players turn it is.
     */
    private void turnOutput(boolean turn) {
        if (turn) {
            System.out.println("Players " + opponent1.getType() + " turn:");
        } else {
            System.out.println("AIs turn:");
        }

        System.out.println("Current location of ball is " + game.getCurrentPosition().toString() + ".");
    }

    /**
     * Method for changing turn of both players based on the bounce mechanic.
     *
     * @param turn which represents which players turn it is
     * @param point which bounce is checked in order to correctly change players
     * @return boolean representation of next players turn
     */
    private boolean changePlayers(boolean turn, FieldPoint point) {
        if (game.checkBounce(point)) {
            System.out.println("It's a bounce!!!");
        } else {
            turn = !turn;
        }

        return turn;
    }
}
