package src.jvm;

public enum JVM {
    SIPUSH, ALOAD, ASTORE, IADD, IMUL, INEG, IDIV, ISUB, PUTFIELD, GETFIELD;

    @Override
    public String toString() {
        return this.name().toLowerCase();
    }
}
