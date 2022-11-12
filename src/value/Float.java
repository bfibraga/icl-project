package src.value;

public class Float implements Value {

    private final float value;

    public Float(float value){
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
        int value = ((java.lang.Float) this.getValue()).intValue();
        return new Int(value);
    }

    @Override
    public Value toFloat() {
        return this;
    }

    @Override
    public Value toBool() {
        boolean value = (float) this.getValue() != 0;
        return new Bool(value);
    }

    @Override
    public String toString() {
        return this.show();
    }
}
