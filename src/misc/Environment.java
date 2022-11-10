package src.misc;

import src.exceptions.IDMultipleDeclarations;
import src.exceptions.UndeclaredID;

import java.util.HashMap;
import java.util.Map;

public class Environment<V> {

    private Environment<V> sl;
    private Map<String, V> def;

    public Environment(){
        this(null);
    }

    public Environment(Environment<V> sl){
        this.sl = sl;
        this.def = new HashMap<>();
    }

    public Environment<V> beginScope(){
        return new Environment<>(this);
    }

    public Environment<V> endScope(){
        return this.sl;
    }

    public V find(String id){
        V result;
        if (this.def.containsKey(id)){
            result = this.def.get(id);
        } else if (this.sl != null) {
            result = this.sl.find(id);
        } else {
            throw new UndeclaredID(id);
        }

        return result;
    }

    public void assoc(String id, V value){
        if (this.def.containsKey(id)){
            throw new IDMultipleDeclarations(id);
        } else {
            this.def.put(id, value);
        }
    }
}
