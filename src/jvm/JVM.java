package src.jvm;

public enum JVM {
    ALOAD,
    ASTORE,
    ARETURN,
    CHECKCAST,
    DUP,
    GETFIELD,
    GETSTATIC,
    GOTO,
    IADD,
    IAND,
    IDIV,
    IFEQ,
    IFGE,
    IFGT,
    IFLE,
    IFLT,
    IFNE,
    ILOAD,
    IMUL,
    INEG,
    INVOKEINTERFACE,
    INVOKESPECIAL,
    INVOKESTATIC,
    INVOKEVIRTUAL,
    IOR,
    IRETURN,
    ISTORE,
    ISUB,
    IXOR,
    LDC,
    LDC_W,
    LOOKUPSWITCH,
    NEW,
    RETURN,
    POP,
    PUTFIELD,
    SIPUSH;

    @Override
    public String toString() {
        return this.name().toLowerCase();
    }
}
