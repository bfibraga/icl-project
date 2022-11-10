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
