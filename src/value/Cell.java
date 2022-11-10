package src.value;

public class Cell {

    private final Value value;

    public Cell(Value value) {
        this.value = value;
    }

    public Value getValue() {
        return value;
    }

    @Override
    public String toString() {
        return this.getValue().toString();
    }
}
