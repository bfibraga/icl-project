package src.astnodes;

import src.type.TVoid;
import src.type.Type;

public abstract class TypeHolder {

    private Type type;

    public TypeHolder() {
        this.type = new TVoid();
    }

    public TypeHolder(Type type){
        this.type = type;
    }

    public Type getType() {
        return type;
    }

    public Type setType(Type type) {
        this.type = type;
        //System.out.println(type);
        return this.getType();
    }
}
