package src.misc.frame;

import src.jvm.JVM;
import src.misc.CodeBlock;
import src.type.TVoid;
import src.type.Type;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DefBlock implements SubBlock {

    private static final String TOKEN = "v";

    DefBlock previous;
    String id;
    private Map<String, Type> fields;

    public DefBlock(){
        this("java/lang/Object", null);
    }

    public DefBlock(String id){
        this(id, new DefBlock());
    }

    public DefBlock(String id, DefBlock previous){
        this.id = id;
        this.previous = previous;
        this.fields = new HashMap<>();
    }

    public DefBlock getPrevious() {
        return previous;
    }

    public String getId() {
        return id;
    }


    public DefBlock pushFrame(String id){
        DefBlock result = new DefBlock(id, this);

        return result;
    }

    public DefBlock popFrame(){
        return this.previous;
    }

    public void def(PrintWriter out){
        out.println(String.format(".class public %s",  this.id));
        out.println(".super java/lang/Object");
        out.println(String.format(".field public sl L%s;", this.previous.getId()));

        for (Map.Entry<String, Type> entry: this.fields.entrySet()) {
            String varname = entry.getKey();
            Type type = entry.getValue();
            String typename = type.jvmType();
            out.println(String.format(".field public %s %s", varname, typename.contains("Ref_of_") ?
                    "L" + typename + ";" :
                    typename ));
        }

        out.println("""
                
                .method	public <init>()V
                \taload_0
                \tinvokenonvirtual java/lang/Object/<init>()V
                \treturn
                .end method""");

        out.close();
    }

    public void pop(CodeBlock block) {
        block.emit(String.format("%s_%d", JVM.ALOAD, 3));
        block.emit(String.format("%s %s/sl L%s;", JVM.GETFIELD, this, this.previous));
        block.emit(String.format("%s_%d", JVM.ASTORE, 3));
    }

    public void setType(String id, Type newType){
        this.fields.put(id, newType);
    }

    @Override
    public String toString() {
        return this.id;
    }

    @Override
    public String gensym() {
        int size = this.fields.size();
        String result = TOKEN + size;
        this.fields.put(result, new TVoid());
        return result;
    }
}
