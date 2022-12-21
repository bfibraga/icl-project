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

    public String getJVMID() {
        return JVMID;
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
        /*StringBuilder resultType = new StringBuilder("Lclosure_interface(");
        for (Pair<String, Type> pair: this.getParams()) {
            String id = pair.getKey();
            Type type = pair.getValue();

            resultType.append(type.jvmType()).append(",");
        }
        if (!resultType.isEmpty())
            resultType.deleteCharAt(resultType.length()-1);

        resultType.append(")").append(getReturnType().jvmType()).append(";");

        return resultType.toString();*/

        StringBuilder stringBuilder = new StringBuilder("Lclosure_" + this.JVMID + ";");
        return stringBuilder.toString();
    }

    @Override
    public String toString() {
        return String.format("%s -> [%s, %s]", this.show(), this.params.toString(), this.bodyAbstractType);
    }
}
