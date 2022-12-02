package src.misc;

import src.type.*;

import java.util.ArrayList;

public class TypeFunctions {

    public static boolean sameType(Type givenType, Type targetType) {
        return givenType.show().equals(targetType.show());
    }

    public static Type getType(String name){
        if (name.contains("array")){
            return new TArray();
        }
        if (name.equals("bool")){
            return new TBool();
        }
        if (name.equals("cell")){
            return new TCell();
        }
        if (name.contains("func")){
            return new TClosure(new ArrayList<>(), new TVoid());
        }
        if (name.equals("int")){
            return new TInt();
        }
        if (name.equals("struct")){
            return new TStruct();
        }
        if (name.equals("str")){
            return new TStr();
        }
        return new TVoid();
    }
}
