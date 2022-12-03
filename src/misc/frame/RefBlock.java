package src.misc.frame;

import src.misc.TypeFunctions;

import java.io.PrintWriter;
import java.sql.Ref;
import java.util.ArrayList;
import java.util.List;

public class RefBlock implements SubBlock {

    private static final String TOKEN = "Ref_of_";
    private String type;

    public RefBlock(String type){
        this.type = type;
    }

    public RefBlock(RefBlock contentType){
        this(TOKEN + contentType.getType());
    }

    public void def(PrintWriter out){
        String contentType = TOKEN + type;

        out.println(String.format(".class public %s", contentType));
        out.println(".super java/lang/Object");

        out.println(String.format(".field public v %s",
                this.type.contains("Ref_of_") ?
                "L" + this.type + ";" :
                this.type ));

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
        if (this.type.contains("Ref_of_"))
            return type;
        return TOKEN + type;
    }
}
