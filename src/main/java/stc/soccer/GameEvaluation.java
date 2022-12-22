package stc.soccer;

import stc.soccer.opponents.Opponent;

import java.util.Scanner;

public class GameEvaluation {

    private final SoccerGame game;
    private boolean turn;
    private final Opponent ai;
    private final Opponent human;

    public GameEvaluation(SoccerGame game, Opponent ai, Opponent human) {
        this.game = game;
        this.turn = true;
        this.ai = ai;
        this.human = human;
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
            if (turn) {
                destination = human.makeMove(game);
            } else {
                destination = ai.makeMove(game);
                System.out.println(destination);
            }

            while (!game.isMoveValid(destination)) {
                System.out.println("Specified move is not valid. Try again.");
                if (turn) {
                    destination = human.makeMove(game);
                } else {
                    destination = ai.makeMove(game);
                    System.out.println(destination);
                }
            }

            if (game.isPointInsideGoals(destination)) {
                if (game.getCurrentPosition().row() == 0) {
                    return ai;
                } else {
                    return human;
                }
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
            System.out.println("Players " + human.getType() + " turn:");
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
