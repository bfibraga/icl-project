package src.type;

import src.exceptions.InvalidTypeConvertion;

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

    public static Type validType(Type nodeType, Type requestType){
        if (!nodeType.equals(new TVoid()) ||  !nodeType.equals(requestType))
            throw new InvalidTypeConvertion(nodeType.show(), requestType.show());

        return nodeType;
    }

    public boolean sameType(Type type){
        return this.show().equals(type.show());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;

        if (obj instanceof Type){
            return ((Type) obj).show().equals(this.show());
        }
        return false;
    }

    public static Type getType(String name){
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
