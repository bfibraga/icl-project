package src.misc.frame;

import java.io.PrintWriter;
import java.sql.Ref;
import java.util.ArrayList;
import java.util.List;

public class RefBlock implements SubBlock {

    private static final String TOKEN = "ref_of_";
    private String type;
    private List<RefBlock> contentType;

    public RefBlock(String type){
        this.type = type;
        this.contentType = new ArrayList<>();
    }

    public RefBlock(RefBlock contentType){
        this(TOKEN + contentType.getType());
        this.contentType.add(contentType);
    }

    public void def(PrintWriter out){
        out.println(String.format(".class public %s", this.type));
        out.println(".super java/lang/Object");

        String previous_type = this.contentType == null ? "I" : String.format("L%s", this.type);
        out.println(String.format(".field public v %s;", previous_type));

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
