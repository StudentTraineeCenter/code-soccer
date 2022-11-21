package stc.soccer;

import java.util.List;
import java.util.Scanner;

public class GameEvaluation {

    private final SoccerGame game;
    private boolean turn;

    public GameEvaluation(SoccerGame game) {
        this.game = game;
        this.turn = true;
    }

    /**
     * Method used to start the game.
     *
     * @param in for using already pre-existing Scanner.
     */
    public void play(Scanner in) {
        while (true) {
            turnOutput(turn);

            FieldPoint destination = new FieldPoint(in.nextInt(), in.nextInt());

            while (!isMoveValid(destination)) {
                System.out.println("Specified move is not valid. Try again.");
                destination = new FieldPoint(in.nextInt(), in.nextInt());
            }

            if (isPointInsideGoals(destination)) {
                break;
            }

            checkBounce(destination);
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

        System.out.println("Current location of ball is " + game.getBallPosition().toString() + ".");
    }

    /**
     * Method for validation of a new move.
     * <p>
     * This method utilizes other methods to decide, whether a desired move is valid.
     *
     * @param dest whose generated move should be validated.
     * @return true if desired move is valid, otherwise false.
     */
    private boolean isMoveValid(FieldPoint dest) {
        return (isPointInsideBounds(dest) && isMoveMade(dest) &&
                isPointCloseEnough(dest) && !isMoveInHistory(dest) &&
                !(isPointOnBounds(game.getBallPosition()) && isPointOnBounds(dest))) ||
                isPointInsideGoals(dest);
    }

    /**
     * Method that checks whether a point is inside of bounds.
     *
     * @param point whose location should be checked.
     * @return true if point is inside of bounds, otherwise false.
     */
    private boolean isPointInsideBounds(FieldPoint point) {
        return ((point.row() >= 0) && (point.row() < game.getRows())) &&
                ((point.column() >= 0) && (point.column() < game.getColumns()));
    }

    /**
     * Method for checking whether a point is close enough to the ball.
     *
     * @param point whose location should be checked.
     * @return true if point is close enough, otherwise false.
     */
    private boolean isPointCloseEnough(FieldPoint point) {
        final FieldPoint position = game.getBallPosition();

        return ((position.row() - point.row()) > -2 && (position.row() - point.row()) < 2) &&
                ((position.column() - point.column()) > -2 && (position.column() - point.column()) < 2);
    }

    /**
     * Method for checking whether a move was made.
     *
     * @param point whose relative position to the ball should be checked.
     * @return true if a move was made, otherwise false.
     */
    private boolean isMoveMade(FieldPoint point) {
        return (game.getBallPosition().row() != point.row() || game.getBallPosition().column() != point.column());
    }

    /**
     * Method for checking goals.
     *
     * @param point whose location should be checked.
     * @return true if point is located inside a goal, otherwise false.
     */
    private boolean isPointInsideGoals(FieldPoint point) {
        return isPointCloseEnough(point) &&
                (Math.abs(point.column() - game.getColumns() / 2) < 2) &&
                (point.row() == -1 || point.row() == game.getRows());
    }

    /**
     * Method for checking move history.
     * <p>
     * This method handles the history of all moves during the game. Passed
     * FieldPoint point in conjunction with current ball position makes a move,
     * that gets checked in move history.
     *
     * @param point of a move that should be checked in move history.
     * @return true if specified move was already played, otherwise false.
     */
    private boolean isMoveInHistory(FieldPoint point) {
        if (game.getMoveHistory().containsKey(point)) {
            final List<FieldPoint> pointHist = game.getMoveHistory().get(point);

            if (!pointHist.isEmpty()) {
                for (FieldPoint pt: pointHist) {
                    if (pt.equals(game.getBallPosition())) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    /**
     * Method for checking bounce mechanic.
     * <p>
     * This method handles games bounce mechanic. If a bounce of a ball at the desired
     * destination is detected, the same player is going to play. Otherwise, it's other
     * players turn.
     *
     * @param point whose bounce should be checked.
     */
    private void checkBounce(FieldPoint point) {
        if (game.getMoveHistory().containsKey(point) || isPointOnBounds(point)) {
            System.out.println("It's a bounce!!!");
        } else {
            turn = !turn;
        }
    }

    /**
     * Method for checking points location.
     * <p>
     * This method checks whether passed FieldPoint point is placed on bounds of the soccer field.
     *
     * @param point whose position should be checked.
     * @return true if passed point is placed on bounds of a field, otherwise false.
     */
    private boolean isPointOnBounds(FieldPoint point) {
        return ((point.row() == 0 || point.row() == game.getRows() - 1) ||
                (point.column() == 0 || point.column() == game.getColumns() - 1)) &&
                (point.column() != game.getColumns() / 2);
    }
}
