package src.misc.frame;

import src.misc.Pair;
import src.misc.TypeFunctions;
import src.type.TClosure;
import src.type.Type;

import java.io.IOException;
import java.io.PrintWriter;

public class FuncBlock {

    private static final String TOKEN = "closure_interface_";
    private String interfaceId;
    private String applySignature;

    private TClosure closure;

    private DefBlock defBlock;
    private int nLocals;

    public FuncBlock(TClosure closure, DefBlock defBlock){
        this.closure = closure;
        this.defBlock = defBlock;

        StringBuilder interfaceParamsList = new StringBuilder();
        StringBuilder applyParamsList = new StringBuilder();

        for (Pair<String, Type> pair: closure.getParams()) {
            Type type = pair.getValue();

            interfaceParamsList.append(TypeFunctions.convertTypename(type.jvmType())).append("_");
            applyParamsList.append(type.jvmType());
        }
        if (!interfaceParamsList.isEmpty())
            interfaceParamsList.deleteCharAt(interfaceParamsList.length()-1);


        this.interfaceId = String.format("%s_%s", interfaceParamsList, TypeFunctions.convertTypename(closure.getReturnType().jvmType()));
        this.applySignature = String.format("(%s)%s", applyParamsList, closure.getReturnType().jvmType());
        this.nLocals = Math.max(4, this.closure.getParams().size()+3) ;
    }

    public String getInterfaceId() {
        return interfaceId;
    }

    public String getApplySignature() {
        return applySignature;
    }

    public DefBlock getDefBlock() {
        return defBlock;
    }

    public void defInterface(PrintWriter out){
        out.println(".interface public " + TOKEN + this.getInterfaceId());
        out.println(".super java/lang/Object");

        out.println(".method public abstract apply" + this.getApplySignature());
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
            out.println(".implements closure_interface_" + this.getInterfaceId());
            out.println(".field sl L" + this.defBlock.getPrevious().getId() + ";");

            out.println("""
                
                .method	public <init>()V
                \taload_0
                \tinvokenonvirtual java/lang/Object/<init>()V
                \treturn
                .end method
                """);

            out.println(".method public apply" + this.getApplySignature());
            out.println("\t.limit locals " + nLocals);
            out.println("\t.limit stack 256");

            //out.println("\taconst_null");
            //out.println("\tastore_3");

            return out;

        } catch (IOException io){
            io.printStackTrace();
        }
        return null;
    }
}
