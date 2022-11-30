package src.misc;

import src.misc.frame.DefFrame;

import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Queue;

public class CodeBlock {

    private Queue<String> code;

    //TODO Create Factory class to handle multiple frame types and gensym diferently.
    private DefFrame currDefFrame;

    public CodeBlock(){
        this.code = new LinkedList<>();
        this.currDefFrame = new DefFrame();
    }

    public DefFrame getCurrFrame() {
        return currDefFrame;
    }

    public void setCurrFrame(DefFrame currDefFrame) {
        this.currDefFrame = currDefFrame;
    }

    public void emit(String operation){
        this.code.add(operation);
    }

    //TODO Change Implementation
    public String gensym() {
        return this.currDefFrame.addField();
    }

    public void dump(PrintWriter out){
        if (!this.code.isEmpty()){
            String op = code.remove();
            out.println("\t\t" + op);
            this.dump(out);
        }
    }
}
