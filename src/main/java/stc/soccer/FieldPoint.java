package stc.soccer;

public record FieldPoint(int column, int row) {

    @Override
    public String toString() {
        return "[" + column + ";" + row + "]";
    }

    public boolean equals(FieldPoint point) {
        return (point.row == this.row) && (point.column == this.column);
    }


}