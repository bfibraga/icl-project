package src.misc;

public class Bind<I, V> {

    private final I id;
    private final V value;

    public Bind(I id, V value){
        this.id = id;
        this.value = value;
    }

    public I getId() {
        return id;
    }

    public V getValue() {
        return value;
    }
}
