package src.jvm;

import src.value.Int;
import src.value.Value;

public enum JVMValues {
    TRUE(new Int(1)),
    FALSE(new Int(0)),
    ;

    private final Value value;
    JVMValues(Value value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return this.value.show();
    }
}
