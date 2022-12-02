package src.type;

public class TStr implements Type{
    private static final String TYPE_NAME = "Str";
    private static final String JVM_TYPE = "S";


    @Override
    public String show() {
        return TYPE_NAME;
    }

    @Override
    public String jvmType() {
        return JVM_TYPE;
    }
}
