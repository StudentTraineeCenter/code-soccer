# Documentation

## `public class SoccerGame`

Class that defines the template of a game of Code Soccer.

## `public SoccerGame(int columns, int rows)`

Constructor for a template of a game of Code Soccer.

This constructor initializes the game. Sets the number of rows andcolumns necessary, prepares starting position and prepares moveHistory.

 * **Parameters:**
   * `columns` — which defines number of columns of a game field.
   * `rows` — which defines number of rows of a game field.

## `public void addMove(FieldPoint dest)`

Method for adding a new move in both directions.

 * **Parameters:** `dest` — new location to generate and add new move with.

## `private void addMoveToMap(FieldPoint point1, FieldPoint point2)`

Method for adding a move to move history in one direction.

 * **Parameters:**
   * `point1` — that should be inserted to history.
   * `point2` — that should be inserted to history.

## `public boolean isMoveValid(FieldPoint dest)`

Method for validation of a new move.

This method utilizes other methods to decide, whether a desired move is valid.

 * **Parameters:** `dest` — whose generated move should be validated.
 * **Returns:** true if desired move is valid, otherwise false.

## `public boolean isPointInsideBounds(FieldPoint point)`

Method that checks whether a point is inside of bounds.

 * **Parameters:** `point` — whose location should be checked.
 * **Returns:** true if point is inside of bounds, otherwise false.

## `public boolean isPointCloseEnough(FieldPoint point)`

Method for checking whether a point is close enough to the ball.

 * **Parameters:** `point` — whose location should be checked.
 * **Returns:** true if point is close enough, otherwise false.

## `public boolean isMoveMade(FieldPoint point)`

Method for checking whether a move was made.

 * **Parameters:** `point` — whose relative position to the ball should be checked.
 * **Returns:** true if a move was made, otherwise false.

## `public boolean isPointInsideGoalsAndCloseEnough(FieldPoint point)`

Method for checking goals.

 * **Parameters:** `point` — whose location should be checked.
 * **Returns:** true if point is located inside a goal, otherwise false.

## `public boolean isMoveInHistory(FieldPoint point)`

Method for checking move history.

This method handles the history of all moves during the this. Passed FieldPoint point in conjunction with current ball position makes a move, that gets checked in move history.

 * **Parameters:** `point` — of a move that should be checked in move history.
 * **Returns:** true if specified move was already played, otherwise false.

## `public boolean checkBounce(FieldPoint point)`

Method for checking bounce mechanic.

This method handles games bounce mechanic. If a bounce of a ball at the desired destination is detected, the same player is going to play. Otherwise, it's other players turn.

 * **Parameters:** `point` — which bounce should be checked.
 * **Returns:** true if move does bounce, otherwise false

## `public boolean isPointOnBounds(FieldPoint point)`

Method for checking points location.

This method checks whether passed FieldPoint point is placed on bounds of the soccer field excluding goals.

 * **Parameters:** `point` — whose position should be checked.
 * **Returns:** true if passed point is placed on bounds of a field, otherwise false.

## `public boolean isMoveOnSameBounds(FieldPoint from, FieldPoint to)`

Method that checks whether specified move is on a same boundary.

 * **Parameters:**
   * `from` — point where move starts.
   * `to` — point where move ends.
 * **Returns:** true if move is on a same boundary, otherwise false.

## `public boolean isPointOnColumnBounds(FieldPoint point)`

Method that checks whether specified point is on a column boundary.

 * **Parameters:** `point` — whose location should be checked.
 * **Returns:** true if point is on a column boundary, otherwise false.

## `public boolean isPointOnRowBounds(FieldPoint point)`

Method that checks whether specified point is on a row boundary.

 * **Parameters:** `point` — whose location should be checked.
 * **Returns:** true if point is on a row boundary, otherwise false.

## `public boolean isMovePossible()`

Method for checking possibility of a move from current position.

This method checks whether a move is possible from current ball position. A move is not possible if all other moves from the position were already made or are unable to be done due to the points placement on field bounds.

 * **Returns:** true if any move is possible, otherwise false
