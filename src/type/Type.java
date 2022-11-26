package src.type;

import src.astnodes.ASTNode;
import src.exceptions.InvalidTypeConvertion;
import src.misc.Environment;

public abstract class Type {

    private final String typeName;

    public Type(String typeName){
        this.typeName = typeName;
    }

    public String show(){
        return this.typeName;
    }

    @Override
    public String toString() {
        return this.show();
    }

    public static Type validType(Type nodeType, Environment<Type> e, Type requestType){
        if (!nodeType.equals(requestType))
            throw new InvalidTypeConvertion(nodeType.show(), requestType.show());

        return nodeType;
    }

    public boolean sameType(Type type2){
        return this.show().equals(type2.show());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;

        if (obj instanceof Type){
            System.out.println("Aqui " + this.show() + ", " + ((Type) obj).show());
            boolean equal = ((Type) obj).show().equals(this.show());
            System.out.println(equal);
            return equal;
        }
        return false;
    }
}
