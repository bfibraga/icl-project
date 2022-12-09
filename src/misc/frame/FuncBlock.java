package src.misc.frame;

import src.type.TClosure;

import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class FuncBlock {

    private static final String TOKEN = "closure_%s_to_%s";
    private String interfaceId;

    private TClosure closure;

    private Queue<String> applyOps;

    private DefBlock defBlock;

    public FuncBlock(TClosure closure, DefBlock defBlock){
        this.closure = closure;
        this.interfaceId = String.format(TOKEN, closure.getParams(), closure.getReturnType().jvmType());
        this.applyOps = new LinkedList<>();
        this.defBlock = new DefBlock("frame_closure" + defBlock.getId(), defBlock);
    }

    public String getInterfaceId() {
        return interfaceId;
    }

    public void add(String op){
        this.applyOps.add(op);
    }

    public void defInterface(PrintWriter out){
        out.println(".interface public " + this.getInterfaceId());
        out.println(".super java/lang/Object");
        out.println(".method public abstract apply(" + this.closure.getParams() + ")" + this.closure.getReturnType().jvmType());
        out.println(".end method");
    }

    public void defFuncFrameBlock(PrintWriter out){
        this.defBlock.def(out);
    }

    public void defClosure(PrintWriter out){
        out.println(".class public closure_NANI");
        out.println(".super java/lang/Object");
        out.println(".implements " + this.getInterfaceId());
        out.println(".field sl " + this.defBlock.getId());
        out.println();

        out.println(".method public apply(" + this.closure.getParams() + ")" + this.closure.getReturnType().jvmType());
        out.println("\t.limit locals 4");
        out.println("\t.limit stack 256");
        this.dump(out);
        out.println(".end method");

        out.println("""
                
                .method	public <init>()V
                \taload_0
                \tinvokenonvirtual java/lang/Object/<init>()V
                \treturn
                .end method""");

        out.close();
    }

    public void dump(PrintWriter out){
        if (!this.applyOps.isEmpty()){
            String currOp = this.applyOps.poll();
            out.println("\t" + currOp);
            this.dump(out);
        }
    }
}
