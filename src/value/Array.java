package src.value;

import src.exceptions.InvalidTypeConvertion;

import java.util.ArrayList;
import java.util.List;

public class Array implements Value {

    //TODO Implement this value.
    private final List<Value> values;

    public Array(){
        this.values = new ArrayList<>();
    }

    public Array(List<Value> values) {
        this.values = values;
    }

    public int length(){
        return this.values.size();
    }

    public Value get(int index){
        return this.values.get(index);
    }

    public Value set(int index, Value value){
        return this.values.set(index, value);
    }

    public void push(Value value){
        this.values.add(value);
    }

    public Value pop(){
        int lastIndex = this.length() - 1;
        return this.values.remove(lastIndex);
    }

    public void queue(Value value){
        this.values.add(0, value);
    }

    public Value dequeue(){
        return this.pop();
    }

    @Override
    public String show() {
        return null;
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
        StringBuilder result = new StringBuilder("[ ");
        for (Value value: this.values) {
            result.append(value).append(", ");
        }
        result.delete(result.length()-2, result.length()-1);
        result.append("]");
        return result.toString();
    }
}
