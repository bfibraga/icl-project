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
    IFEQ,
    IFNE,
    IFGT,
    IFGE,
    IFLT,
    IFLE,
    IMUL,
    INEG,
    INVOKESPECIAL,
    IOR,
    ISTORE,
    ISUB,
    IXOR,
    NEW,
    POP,
    PUTFIELD,
    SIPUSH,
    ILOAD;

    @Override
    public String toString() {
        return this.name().toLowerCase();
    }
}
