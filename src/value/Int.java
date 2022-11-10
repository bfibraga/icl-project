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
    public boolean isNumber() {
        return true;
    }

    @Override
    public boolean isBoolean() {
        return false;
    }

    @Override
    public String toString() {
        return this.show();
    }
}
