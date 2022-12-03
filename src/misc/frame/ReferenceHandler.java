package src.misc.frame;

import src.misc.Pair;
import src.type.Type;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

public class ReferenceHandler {

    private Set<Pair<Type,Type>> refBlockSet;

    public ReferenceHandler(){
        this.refBlockSet = new LinkedHashSet<>();
    }

    public void addRef(Type type, Type nestedType){
        this.refBlockSet.add(new Pair<>(type, nestedType));
    }

    public void dump() {
        try{
            for (Pair<Type, Type> pair: this.refBlockSet) {
                Type type = pair.getKey();
                Type nestedType = pair.getValue();

                String typename = type.jvmType();
                String nestedTypename = nestedType.jvmType();
                PrintWriter fileWriter = new PrintWriter("./src/jvm/result/" + typename + ".j");

                RefBlock refBlock = new RefBlock(nestedTypename);
                refBlock.def(fileWriter);
            }
        } catch (IOException io){
            io.printStackTrace();
        }

    }
}
