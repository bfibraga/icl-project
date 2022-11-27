package src.type;

import src.exceptions.InvalidTypeConvertion;

public abstract class AbstractType implements Type {

    private final String typeName;

    public AbstractType(String typeName){
        this.typeName = typeName;
    }

    @Override
    public String show(){
        return this.typeName;
    }

    @Override
    public String toString() {
        return this.show();
    }

    public boolean sameType(AbstractType abstractType) {
        return this.show().equals(abstractType.show());
    }

    public static AbstractType getType(String name){
        if (name.equals("array")){
            return new TArray();
        }
        if (name.equals("bool")){
            return new TBool();
        }
        if (name.equals("cell")){
            return new TCell();
        }
        if (name.equals("func")){
            return new TClosure();
        }
        if (name.equals("int")){
            return new TInt();
        }
        if (name.equals("rec")){
            return new TRecord();
        }
        if (name.equals("str")){
            return new TStr();
        }
        return new TVoid();
    }
}
