package src.misc;

import src.type.TVoid;
import src.type.AbstractType;

public class Bind<I, V> {

    private final I id;
    private final AbstractType abstractType;
    private final V value;

    public Bind(I id, V value){
        this.id = id;
        this.abstractType = new TVoid();
        this.value = value;
    }

    public Bind(I id, String typename, V value){
        this.id = id;
        this.abstractType = AbstractType.getType(typename);
        this.value = value;
    }

    public Bind(I id, String typename){
        this.id = id;
        this.abstractType = AbstractType.getType(typename);
        this.value = null;
    }

    public I getId() {
        return id;
    }

    public V getValue() {
        return value;
    }

    public AbstractType getType() {
        return abstractType;
    }
}
