public enum Cell {
    VOID,
    X,
    O;

    @Override
    public String toString() {
        if (this == X) {
            return "X";
        } else if (this == O) {
            return "O";
        } else {
            return "_";
        }
    }
}
