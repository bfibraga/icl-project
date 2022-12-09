package src.type;

import java.util.HashMap;
import java.util.Map;

public class TStruct implements Type{

    private static final String TYPE_NAME = "Struct";

    private Map<String, Type> fields;

    public TStruct() {
        this.fields = new HashMap<>();
    }

    public TStruct(Map<String, Type> fields){
        this.fields = fields;
    }

    public Type get(String field){
        return this.fields.get(field);
    }

    @Override
    public String show() {
        return TYPE_NAME;
    }

    @Override
    public String jvmType() {
        return null;
    }

    @Override
    public String toString() {
        return this.show();
    }
}
