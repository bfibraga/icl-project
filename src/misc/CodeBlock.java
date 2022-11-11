package src.misc;

import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Queue;

public class CodeBlock {

    private Queue<String> code;
    private Frame currFrame;

    public CodeBlock(){
        this.code = new LinkedList<>();
        this.currFrame = new Frame();
    }

    public Frame getCurrFrame() {
        return currFrame;
    }

    public void setCurrFrame(Frame currFrame) {
        this.currFrame = currFrame;
    }

    public void emit(String operation){
        this.code.add(operation);
    }

    public String gensym() {
        return this.currFrame.addField();
    }

    public void dump(PrintWriter out){
        if (!code.isEmpty()){
            String op = code.remove();
            out.println("\t\t" + op);
            this.dump(out);
        }
    }
}
