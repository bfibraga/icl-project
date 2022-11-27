package src.type;

public class TCell extends AbstractType implements Type {

    private AbstractType abstractType;

    public TCell(){
        super("Cell");
        this.setType(new TVoid());
    }

    public TCell(AbstractType abstractType){
        super("Cell");
        this.setType(abstractType);
    }

    public AbstractType getType() {
        return abstractType;
    }

    public void setType(AbstractType abstractType) {
        this.abstractType = abstractType;
    }
}
