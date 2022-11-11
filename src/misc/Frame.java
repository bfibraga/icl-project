package src.misc;

import src.jvm.JVM;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class Frame {

    Frame previous;
    String id;
    private List<String> fields;

    public Frame(){
        this("java/lang/Object", null);
    }

    public Frame(String id){
        this(id, new Frame());
    }

    public Frame(String id, Frame previous){
        this.id = id;
        this.previous = previous;
        this.fields = new ArrayList<>();
    }

    public Frame getPrevious() {
        return previous;
    }

    public String getId() {
        return id;
    }

    public void getNFrames(List<Frame> result, int level){
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

    public Frame pushFrame(String id){
        Frame result = new Frame(id, this);

        return result;
    }

    public Frame popFrame(){
        return this.previous;
    }

    public void def(PrintWriter out){
        out.println(String.format(".class public %s",  this.id));
        out.println(".super java/lang/Object");
        out.println(String.format(".field public sl L%s", this.previous.getId()));

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
        block.emit(String.format("%s_%d\n", JVM.ASTORE, 3));
    }

    @Override
    public String toString() {
        return this.id;
    }
}
