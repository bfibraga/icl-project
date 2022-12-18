package src.type;

public class TStr implements Type{
    private static final String TYPE_NAME = "Str";
    private static final String JVM_TYPE = "Ljava/lang/String;";

    @Override
    public String show() {
        return TYPE_NAME;
    }

    @Override
    public String jvmType() {
        return JVM_TYPE;
    }

    @Override
    public String toString() {
        return this.show();
    }
}
