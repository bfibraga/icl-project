package src.value;

public class Bool implements Value {

    private final boolean value;

    public Bool() {
        this.value = true;
    }

    public Bool(boolean value){
        this.value = value;
    }

    @Override
    public String show() {
        return String.valueOf(this.value);
    }

    public boolean getValue() {
        return this.value;
    }

    @Override
    public boolean isNumber() {
        return false;
    }

    @Override
    public boolean isBoolean() {
        return true;
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
    public boolean isFunc() {
        return false;
    }

    @Override
    public Value toInt() {
        int value = this.getValue() ? 1 : 0;
        return new Int(value);
    }

    @Override
    public Value toBool() {
        return this;
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

        if (obj instanceof Bool){
            return ((Bool)obj).getValue() == this.getValue();
        }

        return false;
    }
}
