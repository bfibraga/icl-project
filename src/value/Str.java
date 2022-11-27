package src.value;

import src.exceptions.InvalidTypeConvertion;

public class Str implements Value {

    private final String value;

    public Str(){
        this.value = "";
    }

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
    public boolean isString() {
        return true;
    }

    @Override
    public boolean isFunc() {
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

    @Override
    public boolean equals(Object obj) {
        if (this == obj){
            return true;
        }

        if (obj instanceof Str){
            return ((Str)obj).show().equals(this.show());
        }

        return false;
    }
}
