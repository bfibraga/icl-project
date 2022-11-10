package src.misc;

public class Coordinates {

    private final String id;
    private final int depth;

    public Coordinates(String id, int depth){
        this.id = id;
        this.depth = depth;
    }

    public String getId() {
        return id;
    }

    public int getDepth() {
        return depth;
    }
}
