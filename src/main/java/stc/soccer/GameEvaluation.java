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

    private void turnOutput(boolean turn) {
        if (turn) {
            System.out.println("Players " + game.getPlayer1() + " turn:");
        } else {
            System.out.println("Players " + game.getPlayer2() + " turn:");
        }

        System.out.println("Current location of ball is " + game.getBallPosition().toString() + ".");
    }

    private boolean isMoveValid(FieldPoint dest) {
        return (isPointInsideBounds(dest) && isMoveMade(dest) &&
                isPointCloseEnough(dest) && !isMoveInHistory(dest) &&
                !(isPointOnBounds(game.getBallPosition()) && isPointOnBounds(dest))) ||
                isPointInsideGoals(dest);
    }

    private boolean isPointInsideBounds(FieldPoint point) {
        return ((point.row() >= 0) && (point.row() < game.getRows())) &&
                ((point.column() >= 0) && (point.column() < game.getColumns()));
    }

    private boolean isPointCloseEnough(FieldPoint point) {
        final FieldPoint position = game.getBallPosition();

        return ((position.row() - point.row()) > -2 && (position.row() - point.row()) < 2) &&
                ((position.column() - point.column()) > -2 && (position.column() - point.column()) < 2);
    }

    private boolean isMoveMade(FieldPoint point) {
        return (game.getBallPosition().row() != point.row() || game.getBallPosition().column() != point.column());
    }

    private boolean isPointInsideGoals(FieldPoint point) {
        return isPointCloseEnough(point) &&
                (Math.abs(point.column() - game.getColumns() / 2) < 2) &&
                (point.row() == -1 || point.row() == game.getRows());
    }

    private boolean isMoveInHistory(FieldPoint dest) {

        if (game.getMoveHistory().containsKey(dest)) {
            final List<FieldPoint> pointHist = game.getMoveHistory().get(dest);

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

    private void checkBounce(FieldPoint dest) {
        if (game.getMoveHistory().containsKey(dest) || isPointOnBounds(dest)) {
            System.out.println("It's a bounce!!!");
        } else {
            turn = !turn;
        }
    }

    private boolean isPointOnBounds(FieldPoint point) {
        return ((point.row() == 0 || point.row() == game.getRows() - 1) ||
                (point.column() == 0 || point.column() == game.getColumns() - 1)) &&
                (point.column() != game.getColumns() / 2);
    }
}
