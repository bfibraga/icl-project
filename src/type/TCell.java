package src.type;

public class TCell implements Type {

    private static final String TYPE_NAME = "Cell";

    private Type abstractType;

    public TCell(){
        this.setType(new TVoid());
    }

    public TCell(Type type){
        this.setType(type);
    }

    public Type getType() {
        return abstractType;
    }

    public void setType(Type abstractType) {
        this.abstractType = abstractType;
    }

    @Override
    public String show() {
        return TYPE_NAME;
    }
}
