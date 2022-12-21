package src.astnodes.functions.io.out;

import src.astnodes.ASTNode;
import src.astnodes.TypeHolder;
import src.jvm.JVM;
import src.misc.CodeBlock;
import src.misc.Coordinates;
import src.misc.Environment;
import src.type.TStr;
import src.type.TVoid;
import src.type.Type;
import src.value.Str;
import src.value.Value;

import java.util.List;

public class ASTPrint extends TypeHolder implements ASTNode {

    private static final String SPACING_TOKEN = " ";
    private List<ASTNode> args;

    public ASTPrint(List<ASTNode> args){
        this.args = args;
    }

    @Override
    public Value eval(Environment<Value> e) {
        StringBuilder result = new StringBuilder();
        for (ASTNode arg: args) {
            Value value = arg.eval(e);
            /*if (value.isCell())
                throw new InvalidTypes(value.show());*/

            result.append(value.show()).append(SPACING_TOKEN);
        }
        System.out.print(result);
        return new Str("");
    }

    @Override
    public void compile(CodeBlock block, Environment<Coordinates> e) {
        block.emit(String.format("%s java/lang/System/out Ljava/io/PrintStream;", JVM.GETSTATIC));

        block.emit(String.format("%s %s", JVM.NEW, "java/lang/StringBuffer"));
        block.emit(JVM.DUP.toString());
        block.emit(String.format("%s %s", JVM.INVOKESPECIAL, "java/lang/StringBuffer/<init>()V"));
        block.emit(String.format("%s_%d", JVM.ASTORE, 2));

        for (ASTNode arg: this.args) {
            block.emit(String.format("%s_%d", JVM.ALOAD, 2));

            arg.compile(block, e);

            Type argType = ((TypeHolder)arg).getType();
            String argTypename = argType.jvmType();
            argTypename = argTypename.contains("Ref_of") ? "L" + argTypename + ";" : argTypename;

            if (!argTypename.equals(new TStr().jvmType()))
                block.emit(String.format("%s java/lang/String/valueOf(%s)Ljava/lang/String;", JVM.INVOKESTATIC, argTypename));

            block.emit(String.format("%s java/lang/StringBuffer/append(Ljava/lang/String;)Ljava/lang/StringBuffer;", JVM.INVOKEVIRTUAL));

            block.emit(String.format("%s_%d", JVM.ALOAD, 2));

            block.emit(String.format("%s \"%s\"", JVM.LDC, SPACING_TOKEN));
            block.emit(String.format("%s java/lang/StringBuffer/append(Ljava/lang/String;)Ljava/lang/StringBuffer;", JVM.INVOKEVIRTUAL));
        }

        block.emit(String.format("%s java/lang/StringBuffer/toString()Ljava/lang/String;", JVM.INVOKEVIRTUAL));

        block.emit(String.format("%s java/io/PrintStream/println(Ljava/lang/String;)V", JVM.INVOKEVIRTUAL));
    }

    @Override
    public Type typecheck(Environment<Type> e) {
        this.setType(new TVoid());
        return new TVoid();
    }
}
