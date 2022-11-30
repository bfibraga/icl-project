package src.misc;

import src.type.TVoid;
import src.type.Type;

public class Bind<I, V> {

    private final I id;
    private final Type type;
    private final V value;

    public Bind(I id, V value){
        this.id = id;
        this.type = new TVoid();
        this.value = value;
    }

    public Bind(I id, String typename, V value){
        this.id = id;
        this.type = TypeFunctions.getType(typename);
        this.value = value;
    }

    public Bind(I id, String typename){
        this.id = id;
        System.out.println(typename);

        this.type = TypeFunctions.getType(typename);
        this.value = null;
    }

    public I getId() {
        return id;
    }

    public V getValue() {
        return value;
    }

    public Type getType() {
        return type;
    }

    @Override
    public String toString() {
        return "Bind{" +
                "id=" + id +
                ", type=" + type.show() +
                ", value=" + value.getClass().getSimpleName() +
                "}";
    }
}
