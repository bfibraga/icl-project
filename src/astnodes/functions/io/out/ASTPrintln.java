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

public class ASTPrintln extends TypeHolder implements ASTNode {

    private List<ASTNode> args;

    public ASTPrintln(List<ASTNode> args){
        this.args = args;
    }

    @Override
    public Value eval(Environment<Value> e) {
        StringBuilder result = new StringBuilder();
        for (ASTNode arg: args) {
            Value value = arg.eval(e);
            /*if (value.isCell())
                throw new InvalidTypes(value.show());*/

            result.append(value.show()).append("\n");
        }
        result.deleteCharAt(result.length()-1);
        System.out.println(result);
        return new Str("");
    }

    @Override
    public void compile(CodeBlock block, Environment<Coordinates> e) {
        for (ASTNode arg: this.args) {
            arg.compile(block, e);
            Type argType = ((TypeHolder)arg).getType();
            String argTypename = argType.jvmType();
            argTypename = argTypename.contains("Ref_of") ? "L" + argTypename + ";" : argTypename;
            block.emit(String.format("%s java/lang/String/valueOf(%s)Ljava/lang/String;", JVM.INVOKESTATIC, argTypename));
            block.emit(String.format("%s java/io/PrintStream/println(Ljava/lang/String;)V", JVM.INVOKEVIRTUAL));
        }
    }

    @Override
    public Type typecheck(Environment<Type> e) {
        for (ASTNode arg: this.args) {
            arg.typecheck(e);
        }
        this.setType(new TVoid());
        return new TVoid();
    }
}
