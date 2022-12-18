package src.type;

import src.misc.Pair;

import java.util.List;

public class TClosure implements Type {

    private static final String TYPE_NAME = "Closure";

    private final List<Pair<String, Type>> params;
    private final Type bodyAbstractType;

    private final Type returnType;

    private String JVMID;

    public TClosure(List<Pair<String, Type>> params, Type bodyAbstractType, Type returnType) {
        this.params = params;
        this.bodyAbstractType = bodyAbstractType;
        this.returnType = returnType;
        this.JVMID = "";
    }

    public List<Pair<String, Type>> getParams() {
        return params;
    }

    public Type getBodyType() {
        return bodyAbstractType;
    }

    public Type getReturnType() {
        return returnType;
    }

    public void setJVMID(String JVMID) {
        this.JVMID = JVMID;
    }

    @Override
    public String show() {
        return TYPE_NAME;
    }

    @Override
    public String jvmType() {
        StringBuilder paramsList = new StringBuilder();
        for (Pair<String, Type> pair: this.getParams()) {
            String id = pair.getKey();
            Type type = pair.getValue();

            paramsList.append(type.jvmType()).append(",");
        }
        if (!paramsList.isEmpty())
            paramsList.deleteCharAt(paramsList.length()-1);
        return paramsList.toString();
    }

    @Override
    public String toString() {
        return String.format("%s -> [%s, %s]", this.show(), this.params.toString(), this.bodyAbstractType);
    }
}
