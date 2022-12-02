package src.type;

import src.misc.Pair;

import java.util.ArrayList;
import java.util.List;

public class TClosure implements Type {

    private static final String TYPE_NAME = "Closure";

    private final List<Pair<String, Type>> params;
    private final Type bodyAbstractType;

    public TClosure(List<Pair<String, Type>> params, Type bodyAbstractType) {
        this.params = params;
        this.bodyAbstractType = bodyAbstractType;
    }

    public List<Pair<String, Type>> getParams() {
        return params;
    }

    public Type getBodyType() {
        return bodyAbstractType;
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
        return String.format("%s -> [%s, %s]", this.show(), this.params.toString(), this.bodyAbstractType);
    }
}
