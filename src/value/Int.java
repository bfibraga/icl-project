package src.value;

public class Int implements Value {

    private int value;

    public Int(int value){
        this.value = value;
    }

    @Override
    public String show() {
        return String.valueOf(this.value);
    }

    public int getValue() {
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
    public boolean isCell() {
        return false;
    }

    @Override
    public boolean isString() {
        return false;
    }

    @Override
    public Value toInt() {
        return this;
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

    @Override
    public boolean equals(Object obj) {
        if (this == obj){
            return true;
        }

        if (obj instanceof Int){
            return ((Int)obj).getValue() == this.getValue();
        }

        return false;
    }
}
