package stc.soccer;

import java.util.*;

public class SoccerGame {
    private final int columns;
    private final int rows;
    private FieldPoint currentPosition;
    private Map<FieldPoint, List<FieldPoint>> moveHistory;
    private final String player1;

    public SoccerGame(int columns, int rows, String name1) {
        this.columns = columns;
        this.rows = rows;
        this.currentPosition = new FieldPoint(columns/2, rows/2);
        this.moveHistory = new HashMap<>();
        this.player1 = name1;
    }

    public int getColumns() {
        return columns;
    }

    public int getRows() {
        return rows;
    }

    public Map<FieldPoint, List<FieldPoint>> getMoveHistory() {
        return moveHistory;
    }

    public FieldPoint getCurrentPosition() {
        return currentPosition;
    }

    public String getPlayer1() {
        return player1;
    }

    /**
     * Method for adding a new move in both directions.
     *
     * @param dest new location to generate and add new move with.
     */
    public void addMove(FieldPoint dest) {
        addMoveToMap(dest, currentPosition);
        addMoveToMap(currentPosition, dest);

        currentPosition = dest;
    }

    /**
     * Method for adding a move to move history in one direction.
     *
     * @param point1 that should be inserted to history.
     * @param point2 that should be inserted to history.
     */
    private void addMoveToMap(FieldPoint point1, FieldPoint point2) {
        if (moveHistory.containsKey(point1)) {
            List<FieldPoint> hist = new ArrayList<>(moveHistory.remove(point1));
            hist.add(point2);
            moveHistory.put(point1, hist);
        } else {
            moveHistory.put(point1, Collections.singletonList(point2));
        }
    }

    /**
     * Method for validation of a new move.
     * <p>
     * This method utilizes other methods to decide, whether a desired move is valid.
     *
     * @param dest whose generated move should be validated.
     * @return true if desired move is valid, otherwise false.
     */
    public boolean isMoveValid(FieldPoint dest) {
        return (isPointInsideBounds(dest) && isMoveMade(dest) &&
                isPointCloseEnough(dest) && !isMoveInHistory(dest) &&
                !(isPointOnBounds(this.getCurrentPosition()) && isPointOnBounds(dest))) ||
                isPointInsideGoals(dest);
    }

    /**
     * Method that checks whether a point is inside of bounds.
     *
     * @param point whose location should be checked.
     * @return true if point is inside of bounds, otherwise false.
     */
    public boolean isPointInsideBounds(FieldPoint point) {
        return ((point.row() >= 0) && (point.row() < this.getRows())) &&
                ((point.column() >= 0) && (point.column() < this.getColumns()));
    }

    /**
     * Method for checking whether a point is close enough to the ball.
     *
     * @param point whose location should be checked.
     * @return true if point is close enough, otherwise false.
     */
    public boolean isPointCloseEnough(FieldPoint point) {
        final FieldPoint position = this.getCurrentPosition();

        return ((position.row() - point.row()) > -2 && (position.row() - point.row()) < 2) &&
                ((position.column() - point.column()) > -2 && (position.column() - point.column()) < 2);
    }

    /**
     * Method for checking whether a move was made.
     *
     * @param point whose relative position to the ball should be checked.
     * @return true if a move was made, otherwise false.
     */
    public boolean isMoveMade(FieldPoint point) {
        return (this.getCurrentPosition().row() != point.row() || this.getCurrentPosition().column() != point.column());
    }

    /**
     * Method for checking goals.
     *
     * @param point whose location should be checked.
     * @return true if point is located inside a goal, otherwise false.
     */
    public boolean isPointInsideGoals(FieldPoint point) {
        return isPointCloseEnough(point) &&
                (Math.abs(point.column() - this.getColumns() / 2) < 2) &&
                (point.row() == -1 || point.row() == this.getRows());
    }

    /**
     * Method for checking move history.
     * <p>
     * This method handles the history of all moves during the this. Passed
     * FieldPoint point in conjunction with current ball position makes a move,
     * that gets checked in move history.
     *
     * @param point of a move that should be checked in move history.
     * @return true if specified move was already played, otherwise false.
     */
    public boolean isMoveInHistory(FieldPoint point) {
        if (this.getMoveHistory().containsKey(point)) {
            final List<FieldPoint> pointHist = this.getMoveHistory().get(point);

            if (!pointHist.isEmpty()) {
                for (FieldPoint pt: pointHist) {
                    if (pt.equals(this.getCurrentPosition())) {
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
     * @return true if move does bounce, otherwise false
     */
    public boolean checkBounce(FieldPoint point) {
        return this.getMoveHistory().containsKey(point) || isPointOnBounds(point);
    }

    /**
     * Method for checking points location.
     * <p>
     * This method checks whether passed FieldPoint point is placed on bounds of the soccer field
     * excluding goals.
     *
     * @param point whose position should be checked.
     * @return true if passed point is placed on bounds of a field, otherwise false.
     */
    public boolean isPointOnBounds(FieldPoint point) {
        return ((point.row() == 0 || point.row() == this.getRows() - 1) ||
                (point.column() == 0 || point.column() == this.getColumns() - 1)) &&
                (point.column() != this.getColumns() / 2);
    }

    /** TODO
     * Method for checking possibility of a move from current position.
     * <p>
     * This method checks whether a move is possible from current ball position.
     * A move is not possible if all other moves from the position were already made
     * or are unable to be done due to the points placement on field bounds.
     *
     * @return true if any move is possible, otherwise false
     */
    public boolean checkMovePossibility() {
        if (isPointOnBounds(this.getCurrentPosition())) {
            return true;
        } else {
            return this.getMoveHistory().get(this.getCurrentPosition()).size() == 8;
        }
    }

}
