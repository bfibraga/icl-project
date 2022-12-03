package src.misc.frame;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

public class ReferenceHandler {

    private Map<String, RefBlock> refBlockMap;
    private Set<RefBlock> refBlockSet;

    public ReferenceHandler(){
        this.refBlockSet = new LinkedHashSet<>();
        this.refBlockMap = new HashMap<>();
    }

    public void dump() {
        try{
            for (RefBlock refBlock: this.refBlockSet) {
                String id = refBlock.gensym();
                PrintWriter fileWriter = new PrintWriter("./src/jvm/result/" + id + ".j");

                refBlock.def(fileWriter);
            }
        } catch (IOException io){
            io.printStackTrace();
        }

    }
}
