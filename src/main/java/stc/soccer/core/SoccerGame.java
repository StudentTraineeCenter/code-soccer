package stc.soccer.core;

import java.util.*;

/**
 * Class that defines the template of a game of Code Soccer.
 */
public class SoccerGame {
    private final int columns;
    private final int rows;
    private FieldPoint currentPosition;
    private Map<FieldPoint, List<FieldPoint>> moveHistory;

    /**
     * Constructor for a template of a game of Code Soccer.
     * <p>
     * This constructor initializes the game. Sets the number of rows andcolumns necessary, prepares starting position and
     * prepares moveHistory.
     * @param columns which defines number of columns of a game field.
     * @param rows which defines number of rows of a game field.
     */
    public SoccerGame(int columns, int rows) {
        this.columns = columns;
        this.rows = rows;
        this.currentPosition = new FieldPoint(columns/2, rows/2);
        this.moveHistory = new HashMap<>();
        for (int i = 0; i < columns; ++i) {
            for (int j = 0; j < rows; ++j) {
                moveHistory.put(new FieldPoint(i, j), List.of());
            }
        }
    }

    public Map<FieldPoint, List<FieldPoint>> getMoveHistory() {
        return moveHistory;
    }

    public FieldPoint getCurrentPosition() {
        return currentPosition;
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
                !(isMoveOnSameBounds(currentPosition, dest))) ||
                isPointInsideGoalsAndCloseEnough(dest);
    }

    /**
     * Method that checks whether a point is inside of bounds.
     *
     * @param point whose location should be checked.
     * @return true if point is inside of bounds, otherwise false.
     */
    public boolean isPointInsideBounds(FieldPoint point) {
        return ((point.row() >= 0) && (point.row() < rows)) &&
                ((point.column() >= 0) && (point.column() < columns));
    }

    /**
     * Method for checking whether a point is close enough to the ball.
     *
     * @param point whose location should be checked.
     * @return true if point is close enough, otherwise false.
     */
    public boolean isPointCloseEnough(FieldPoint point) {
        final FieldPoint position = currentPosition;

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
        return (currentPosition.row() != point.row() || currentPosition.column() != point.column());
    }

    /**
     * Method for checking goals.
     *
     * @param point whose location should be checked.
     * @return true if point is located inside a goal, otherwise false.
     */
    public boolean isPointInsideGoalsAndCloseEnough(FieldPoint point) {
        return isPointCloseEnough(point) &&
                (Math.abs(point.column() - columns / 2) < 2) &&
                (point.row() == -1 || point.row() == rows) &&
                (currentPosition.column() >= (columns/2) - 1 &&
                        currentPosition.column() <= (columns/2) - 1);
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
            final List<FieldPoint> pointHist = moveHistory.get(point);

            if (!pointHist.isEmpty()) {
                for (FieldPoint pt: pointHist) {
                    if (pt.equals(currentPosition)) {
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
     * @param point which bounce should be checked.
     * @return true if move does bounce, otherwise false
     */
    public boolean checkBounce(FieldPoint point) {
        if (moveHistory.containsKey(point)) {
            return !moveHistory.get(point).isEmpty() || isPointOnBounds(point);
        } else {
            return true;
        }
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
        return isPointOnColumnBounds(point) || isPointOnRowBounds(point);
    }

    /**
     * Method that checks whether specified move is on a same boundary.
     *
     * @param from point where move starts.
     * @param to point where move ends.
     * @return true if move is on a same boundary, otherwise false.
     */
    public boolean isMoveOnSameBounds(FieldPoint from, FieldPoint to) {
        return (isPointOnRowBounds(from) && isPointOnRowBounds(to)) ||
                (isPointOnColumnBounds(from) && isPointOnColumnBounds(to));
    }

    /**
     * Method that checks whether specified point is on a column boundary.
     *
     * @param point whose location should be checked.
     * @return true if point is on a column boundary, otherwise false.
     */
    public boolean isPointOnColumnBounds(FieldPoint point) {
        return point.column() == 0 || point.column() == columns - 1;
    }

    /**
     * Method that checks whether specified point is on a row boundary.
     *
     * @param point whose location should be checked.
     * @return true if point is on a row boundary, otherwise false.
     */
    public boolean isPointOnRowBounds(FieldPoint point) {
        return (point.row() == 0 || point.row() == rows - 1) &&
                (point.column() != columns / 2);
    }

    /**
     * Method for checking possibility of a move from current position.
     * <p>
     * This method checks whether a move is possible from current ball position.
     * A move is not possible if all other moves from the position were already made
     * or are unable to be done due to the points placement on field bounds.
     *
     * @return true if any move is possible, otherwise false
     */
    public boolean isMovePossible() {
        final boolean rowBoundary = isPointOnRowBounds(currentPosition);
        final boolean columnBoundary = isPointOnColumnBounds(currentPosition);

        if (rowBoundary && columnBoundary) {
            return false;
        } else if (rowBoundary || columnBoundary) {
            if (currentPosition.column() == (columns / 2) - 1 ||
                currentPosition.column() == (columns / 2) + 1) {
                    return moveHistory.get(currentPosition).size() != 5;
            } else {
                return moveHistory.get(currentPosition).size() != 3;
            }
        } else return moveHistory.get(currentPosition).size() != 8;
    }

}
