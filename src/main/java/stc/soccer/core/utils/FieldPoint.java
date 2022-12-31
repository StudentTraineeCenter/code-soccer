package stc.soccer.core.utils;

/**
 * This record represents a point in a field as an object.
 * @param column in a field.
 * @param row in a field.
 */
public record FieldPoint(int column, int row) {

    @Override
    public String toString() {
        return "[" + column + ";" + row + "]";
    }

    public boolean equals(FieldPoint point) {
        return (point.row == this.row) && (point.column == this.column);
    }


}