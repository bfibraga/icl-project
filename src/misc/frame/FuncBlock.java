package src.misc.frame;

import src.misc.Pair;
import src.type.TClosure;
import src.type.Type;

import java.io.IOException;
import java.io.PrintWriter;

public class FuncBlock {

    private static final String TOKEN = "closure_interface_";
    private String interfaceId;

    private TClosure closure;

    private DefBlock defBlock;

    public FuncBlock(TClosure closure, DefBlock defBlock){
        this.closure = closure;
        StringBuilder paramsList = new StringBuilder();
        this.defBlock = new DefBlock("closure_" + defBlock.getId(), defBlock);

        for (Pair<String, Type> pair: closure.getParams()) {
            String id = pair.getKey();
            Type type = pair.getValue();

            String symID = this.defBlock.gensym();
            this.defBlock.setType(symID, type);
            paramsList.append(type.jvmType()).append(",");
        }
        if (!paramsList.isEmpty())
            paramsList.deleteCharAt(paramsList.length()-1);

        this.interfaceId = String.format("(%s)%s", paramsList, closure.getReturnType().jvmType());
    }

    public String getInterfaceId() {
        return interfaceId;
    }

    public DefBlock getDefBlock() {
        return defBlock;
    }

    public void defInterface(PrintWriter out){
        out.println(".interface public " + TOKEN + this.getInterfaceId());
        out.println(".super java/lang/Object");
        out.println(".method public abstract apply" + this.getInterfaceId());
        out.println(".end method");

        out.close();
    }

    public void defFuncFrameBlock(String path){
        try{
            PrintWriter out = new PrintWriter(path + "/" + defBlock.getId() + ".j");

            this.defBlock.def(out);
            out.close();
        } catch (IOException io){
            io.printStackTrace();
        }
    }

    public PrintWriter defClosure(String path, String id){

        try {
            PrintWriter out = new PrintWriter(path + "/" + id  + ".j");

            out.println(".class public " + id);
            out.println(".super java/lang/Object");
            out.println(".implements closure_" + this.getInterfaceId());
            System.out.println(this.defBlock.getId());
            out.println(".field sl " + this.defBlock.getId());
            out.println();

            out.println("""
                
                .method	public <init>()V
                \taload_0
                \tinvokenonvirtual java/lang/Object/<init>()V
                \treturn
                .end method""");

            out.println(".method public apply" + this.getInterfaceId());
            out.println("\t.limit locals " + (this.closure.getParams().size() + 2));
            out.println("\t.limit stack 256");

            return out;

        } catch (IOException io){
            io.printStackTrace();
        }
        return null;
    }
}
