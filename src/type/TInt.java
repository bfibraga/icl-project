package src.type;

import src.jvm.JVM;

public class TInt implements Type{

    private static final String TYPE_NAME = "Int";
    private static final String JVM_TYPE = "I";

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
