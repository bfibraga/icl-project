package src.value;

import src.exceptions.InvalidTypeConvertion;

public class Str implements Value {

    private final String value;

    public Str(String value){
        this.value = value;
    }

    @Override
    public String show() {
        return this.value;
    }

    @Override
    public boolean isNumber() {
        return false;
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
    public Value toInt() {
        throw new InvalidTypeConvertion(this.getClass().getName(), Int.class.getName());
    }

    @Override
    public Value toBool() {
        throw new InvalidTypeConvertion(this.getClass().getName(), Bool.class.getName());
    }

    @Override
    public String toString() {
        return this.show();
    }
}
