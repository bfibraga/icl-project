package src.type;

public class TBool implements Type {
    private static final String TYPE_NAME = "Bool";
    private static final String JVM_TYPE = "Z";

    public TBool(){}

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
