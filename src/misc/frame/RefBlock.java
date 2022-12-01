package src.misc.frame;

import java.io.PrintWriter;
import java.sql.Ref;
import java.util.ArrayList;
import java.util.List;

public class RefBlock implements SubBlock {

    private static final String TOKEN = "ref_of_";
    private String type;

    public RefBlock(String type){
        this.type = type;
    }

    public RefBlock(RefBlock contentType){
        this(TOKEN + contentType.getType());
    }

    public void def(PrintWriter out){
        String contentType = this.gensym();

        out.println(String.format(".class public %s", contentType));
        out.println(".super java/lang/Object");

        out.println(String.format(".field public v %s;", this.type));

        out.println("""
                
                .method	public <init>()V
                \taload_0
                \tinvokenonvirtual java/lang/Object/<init>()V
                \treturn
                .end method""");

        out.close();
    }

    public String getType() {
        return type;
    }

    @Override
    public String gensym() {
        return TOKEN + type;
    }
}
