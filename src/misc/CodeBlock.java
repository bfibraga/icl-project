package src.misc;

import src.misc.frame.DefBlock;
import src.misc.frame.LabeledBlock;
import src.misc.frame.SubBlock;
import src.misc.frame.BlockType;

import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Queue;

public class CodeBlock {

    private Queue<String> code;

    private DefBlock currDefBlock;
    private SubBlock labelsBlock;

    public CodeBlock(){
        this.code = new LinkedList<>();
        this.currDefBlock = new DefBlock();
        this.labelsBlock = new LabeledBlock();
    }

    public void emit(String operation){
        this.code.add(operation);
    }

    //TODO Testing
    public String gensym(BlockType type) {
        if (type.equals(BlockType.CODE))
            return this.currDefBlock.gensym();

        if (type.equals(BlockType.LABEL))
            return this.labelsBlock.gensym();

        throw new RuntimeException("Invalid type of CodeType");
    }

    public void dump(PrintWriter out){
        if (!this.code.isEmpty()){
            String op = code.remove();
            out.println("\t\t" + op);
            this.dump(out);
        }
    }


    public DefBlock getCurrFrame() {
        return currDefBlock;
    }

    public void setCurrFrame(DefBlock currDefBlock) {
        this.currDefBlock = currDefBlock;
    }
}
