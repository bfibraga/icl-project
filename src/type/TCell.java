package src.type;

public class TCell extends Type{

    private Type type;

    public TCell(){
        super("Cell");
    }

    public TCell(Type type){
        super("Cell");
        this.setType(type);
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }
}
