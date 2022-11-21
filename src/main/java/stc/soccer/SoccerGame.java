package stc.soccer;

import java.util.*;

public class SoccerGame {
    private final int columns;
    private final int rows;
    private FieldPoint currentPosition;
    private Map<FieldPoint, List<FieldPoint>> moveHistory;
    private final String player1;
    private final String player2;

    public SoccerGame(int columns, int rows, String name1, String name2) {
        this.columns = columns;
        this.rows = rows;
        this.currentPosition = new FieldPoint(columns/2, rows/2);
        this.moveHistory = new HashMap<>();
        this.player1 = name1;
        this.player2 = name2;
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

    public FieldPoint getBallPosition() {
        return currentPosition;
    }

    public String getPlayer1() {
        return player1;
    }

    public String getPlayer2() {
        return player2;
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

}
