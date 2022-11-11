package src.jvm;

public enum JVM {
    ALOAD, ASTORE, DUP, GETFIELD, IADD, IDIV, IMUL, INEG, INVOKESPECIAL, ISUB, NEW, PUTFIELD, SIPUSH;

    @Override
    public String toString() {
        return this.name().toLowerCase();
    }
}
