package src.misc.frame;

import src.jvm.JVM;
import src.misc.CodeBlock;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class DefFrame extends Frame {

    DefFrame previous;
    String id;
    private List<String> fields;

    public DefFrame(){
        this("java/lang/Object", null);
    }

    public DefFrame(String id){
        this(id, new DefFrame());
    }

    public DefFrame(String id, DefFrame previous){
        this.id = id;
        this.previous = previous;
        this.fields = new ArrayList<>();
    }

    public DefFrame getPrevious() {
        return previous;
    }

    public String getId() {
        return id;
    }

    public void getNFrames(List<DefFrame> result, int level){
        result.add(this);
        if (level > 0){
            getNFrames(result, level-1);
        }
    }

    public String addField(){
        int size = this.fields.size();
        String result = "v" + size;
        this.fields.add(result);
        return result;
    }

    public DefFrame pushFrame(String id){
        DefFrame result = new DefFrame(id, this);

        return result;
    }

    public DefFrame popFrame(){
        return this.previous;
    }

    public void def(PrintWriter out){
        out.println(String.format(".class public %s",  this.id));
        out.println(".super java/lang/Object");
        out.println(String.format(".field public sl L%s;", this.previous.getId()));

        for (String var: this.fields) {
            out.println(String.format(".field public %s I", var));
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

    @Override
    public String toString() {
        return this.id;
    }

    @Override
    public String gensym() {
        return this.addField();
    }
}
