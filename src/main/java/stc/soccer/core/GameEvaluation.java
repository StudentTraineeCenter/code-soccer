package stc.soccer.core;

import stc.soccer.core.utils.FieldPoint;
import stc.soccer.opponents.types.GoalLocationType;
import stc.soccer.opponents.Opponent;


/**
 * This method is an evaluation system that controls the whole game of Code Soccer.
 */
public class GameEvaluation {

    private final SoccerGame game;
    private boolean turn;
    private final Opponent opponentTop;
    private final Opponent opponentBottom;


    /**
     * This constructor is used for initialization of an evaluation system for code soccer.
     * <p>
     * It has only one restriction and that is that it needs two opponents of different GaolLocationType. If this
     * restriction isn't fulfilled, an IllegalStateException is thrown.
     * @param game template that evaluation system has to follow.
     * @param opponentTop player whose goal is located on top of the field.
     * @param opponentBottom player whose goal is located on bottom of the field.
     */
    public GameEvaluation(SoccerGame game, Opponent opponentTop, Opponent opponentBottom) {
        if (opponentTop.getGoalLocation() == GoalLocationType.TOP &&
                opponentBottom.getGoalLocation() == GoalLocationType.BOTTOM) {
            this.game = game;
            this.turn = true;
            this.opponentTop = opponentTop;
            this.opponentBottom = opponentBottom;
        } else {
            throw new IllegalStateException("Opponents passed to GameEvaluation do not have correct goal locations:\n" +
                    "TOP> " + opponentTop + "\nBOTTOM> " + opponentBottom);
        }
    }

    /**
     * Method used to start the game.
     *
     * @return winner of finished game
     */
    public Opponent play() {
        while (true) {
            FieldPoint destination;
            turnOutput(turn);

            if (turn) {
                destination = opponentTop.makeMove(game);
            } else {
                destination = opponentBottom.makeMove(game);
            }

            while (!game.isMoveValid(destination)) {
                System.out.println("Specified move is not valid. Try again.");
                if (turn) {
                    destination = opponentTop.makeMove(game);
                } else {
                    destination = opponentBottom.makeMove(game);
                }
            }

            System.out.println(destination);
            turn = changePlayers(turn, destination);
            game.addMove(destination);

            final int gameFinished = isGameFinished(turn, game.getCurrentPosition());

            if (gameFinished == 1) {
                return opponentTop;
            } else if (gameFinished == -1) {
                return opponentBottom;
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
            System.out.println("Players " + opponentTop.getName() + " turn:");
        } else {
            System.out.println("Players " + opponentBottom.getName() + " turn:");
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
        if (!game.checkBounce(point)) {
            turn = !turn;
        }

        return turn;
    }

    /**
     * Method that checks whether a game has fulfilled requirements for an end.
     *
     * @param turn that represents whose turn it is.
     * @param destination of a ball.
     * @return 1 if top player won, -1 if bottom player won, otherwise 0.
     */
    private int isGameFinished(boolean turn, FieldPoint destination) {
        if (game.isPointInsideGoalsAndCloseEnough(destination)) {
            if (game.getCurrentPosition().row() == -1) {
                return 1;
            } else {
                return -1;
            }
        }

        if (!game.isMovePossible()) {
            if (turn) {
                return -1;
            } else {
                return 1;
            }
        }

        return 0;
    }
}
