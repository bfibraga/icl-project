package src.type;

public class TVoid implements Type{

    //TODO Maybe add default value to this type...

    private static final String TYPE_NAME = "Void";
    private static final String JVM_TYPE = "";

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
