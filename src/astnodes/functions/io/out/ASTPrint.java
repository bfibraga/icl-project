package src.astnodes.functions.io.out;

import src.astnodes.ASTNode;
import src.astnodes.TypeHolder;
import src.jvm.JVM;
import src.misc.CodeBlock;
import src.misc.Coordinates;
import src.misc.Environment;
import src.type.TVoid;
import src.type.Type;
import src.value.Str;
import src.value.Value;

import java.util.List;

public class ASTPrint extends TypeHolder implements ASTNode {

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

            result.append(value.show()).append(" ");
        }
        System.out.print(result);
        return new Str("");
    }

    @Override
    public void compile(CodeBlock block, Environment<Coordinates> e) {
        block.emit(String.format("%s java/lang/System/out Ljava/io/PrintStream;", JVM.GETSTATIC));
        for (ASTNode arg: this.args) {
            arg.compile(block, e);
            Type argType = ((TypeHolder)arg).getType();
            String argTypename = argType.jvmType();
            argTypename = argTypename.contains("Ref_of") ? "L" + argTypename + ";" : argTypename;

            block.emit(String.format("%s java/lang/String/valueOf(%s)Ljava/lang/String;", JVM.INVOKESTATIC, argTypename));
            block.emit(String.format("%s java/io/PrintStream/print(Ljava/lang/String;)V", JVM.INVOKEVIRTUAL));
        }
    }

    @Override
    public Type typecheck(Environment<Type> e) {
        this.setType(new TVoid());
        return new TVoid();
    }
}
