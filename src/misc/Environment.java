package src.misc;

import src.exceptions.IDMultipleDeclarationsException;
import src.exceptions.UndeclaredIDException;

import java.util.HashMap;
import java.util.Map;

public class Environment<V> {

    private Environment<V> sl;
    private Map<String, V> def;
    private int depth;

    public Environment(){
        this(null);
    }

    public Environment(Environment<V> sl){
        this.sl = sl;
        this.def = new HashMap<>();
        this.depth = this.sl != null ? this.sl.getDepth() + 1 : 0;
    }

    public int getDepth() {
        return depth;
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
            throw new UndeclaredIDException(id);
        }

        return result;
    }

    public void assoc(String id, V value){
        if (this.def.containsKey(id)){
            throw new IDMultipleDeclarationsException(id);
        } else {
            this.def.put(id, value);
        }
    }

    public void alter(String id, V value){
        this.def.put(id, value);
    }

    public void clear(String id) {
        this.def.remove(id);
    }

    @Override
    public String toString() {
        return "Environment{" +
                "depth=" + depth +
                ", def=" + def +
                ", sl=\n" + sl +
                '}' + "\n";
    }
}
