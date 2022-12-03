package src.misc;

public class Coordinates {

    private final String id;
    private final int depth;
    private final String typename;

    public Coordinates(String id, int depth, String typename){
        this.id = id;
        this.depth = depth;
        this.typename = typename;
    }

    public String getId() {
        return id;
    }

    public int getDepth() {
        return depth;
    }

    public String getTypename() {
        return typename;
    }
}
