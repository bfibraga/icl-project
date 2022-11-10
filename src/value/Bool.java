package src.value;

public class Bool implements Value {

    private final boolean value;

    public Bool(boolean value){
        this.value = value;
    }

    @Override
    public String show() {
        return String.valueOf(this.value);
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
    public String toString() {
        return this.show();
    }
}
