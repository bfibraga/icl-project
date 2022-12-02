package src.value;

public interface Value {

    String show();

    boolean isNumber();

    boolean isBoolean();

    boolean isCell();

    boolean isString();

    boolean isFunc();

    boolean isArray();

    Value toInt();
    
    Value toBool();
}
