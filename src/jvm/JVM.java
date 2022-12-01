package src.jvm;

public enum JVM {
    ALOAD,
    ASTORE,
    DUP,
    GETFIELD,
    GOTO,
    IADD,
    IAND,
    IDIV,
    IFGT,
    IMUL,
    INEG,
    INVOKESPECIAL,
    IOR,
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
