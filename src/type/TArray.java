package src.type;

import java.util.ArrayList;
import java.util.List;

public class TArray implements Type {

    private static final String TYPE_NAME = "Array";
    private static final String JVM_TYPE = "A";

    private final Type fieldsType;

    public TArray(){
        this(new TVoid());
    }

    public TArray(Type fieldsType) {
        this.fieldsType = fieldsType;
    }

    public Type getFieldsType() {
        return fieldsType;
    }

    @Override
    public String show() {
        return TYPE_NAME;
    }

    @Override
    public String jvmType() {
        return JVM_TYPE;
    }
}
