package src.jvm;

public enum JVM {
    ALOAD,
    ASTORE,
    DUP,
    GETFIELD,
    GETSTATIC,
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
    INVOKESTATIC,
    INVOKEVIRTUAL,
    IOR,
    ISTORE,
    ISUB,
    IXOR,
    NEW,
    POP,
    PUTFIELD,
    SIPUSH,
    ILOAD,
    IRETURN;

    @Override
    public String toString() {
        return this.name().toLowerCase();
    }
}
