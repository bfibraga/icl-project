package src.jvm;

public enum JVM {
    ALOAD,
    ASTORE,
    DUP,
    GETFIELD,
    GOTO,
    IADD,
    IDIV,
    IFGT,
    IMUL,
    INEG,
    INVOKESPECIAL,
    ISTORE,
    ISUB,
    NEW,
    PUTFIELD,
    SIPUSH,
    ILOAD;

    @Override
    public String toString() {
        return this.name().toLowerCase();
    }
}
