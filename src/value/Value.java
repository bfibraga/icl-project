package src.value;

public interface Value {

    String show();

    Object getValue();

    boolean isNumber();

    boolean isBoolean();

    Value toInt();

    Value toFloat();

    Value toBool();
}
