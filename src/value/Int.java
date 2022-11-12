package src.value;

public class Int implements Value {

    private final int value;

    public Int(int value){
        this.value = value;
    }

    @Override
    public String show() {
        return String.valueOf(this.value);
    }

    @Override
    public Object getValue() {
        return this.value;
    }

    @Override
    public boolean isNumber() {
        return true;
    }

    @Override
    public boolean isBoolean() {
        return false;
    }

    @Override
    public Value toInt() {
        return this;
    }

    @Override
    public Value toFloat() {
        float value = ((Integer) this.getValue()).floatValue();
        return new Float(value);
    }

    @Override
    public Value toBool() {
        boolean value = (int) this.getValue() != 0;
        return new Bool(value);
    }

    @Override
    public String toString() {
        return this.show();
    }
}
