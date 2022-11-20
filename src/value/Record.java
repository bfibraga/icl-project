package src.value;

import src.astnodes.ASTNode;
import src.exceptions.InvalidTypeConvertion;

import java.util.HashMap;
import java.util.Map;

public class Record implements Value {

    private Map<String, Value> fields;

    public Record(){
        this.fields = new HashMap<>();
    }

    public Value get(String field){
        return this.fields.get(field);
    }

    public Map<String, Value> getFields() {
        return fields;
    }

    public void set(String field, Value value){
        this.fields.put(field, value);
    }

    @Override
    public String show() {
        StringBuilder result = new StringBuilder("[ ");
        for (Map.Entry<String, Value> entry: this.fields.entrySet()) {
            String id = entry.getKey();
            Value value = entry.getValue();

            result.append(id).append(": ").append(value.show()).append(", ");
        }
        result.delete(result.length()-2, result.length()-1);
        result.append("]");
        return result.toString();
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
        return this.show();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj){
            return true;
        }

        if (obj instanceof Record){
            for (Map.Entry<String, Value> entry: this.getFields().entrySet()) {
                String id = entry.getKey();
                Value value = entry.getValue();
                Value valueObj = ((Record) obj).get(id);

                if (!valueObj.equals(value)){
                    return false;
                }
            }
            return true;
        }

        return false;
    }
}
