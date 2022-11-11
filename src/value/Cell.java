package src.value;

public class Cell {

    private Value value;

    public Cell(Value value) {
        this.value = value;
    }

    public Value getValue() {
        return value;
    }

    public void setValue(Value value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return this.getValue().toString();
    }
}
