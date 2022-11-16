package src.value;

public interface Value {

    String show();

    boolean isNumber();

    boolean isBoolean();

    boolean isCell();

    boolean isString();

    Value toInt();
    
    Value toBool();
}
