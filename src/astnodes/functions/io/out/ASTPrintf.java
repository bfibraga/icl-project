package src.astnodes.functions.io.out;

import src.astnodes.ASTNode;
import src.astnodes.TypeHolder;
import src.exceptions.InvalidTypes;
import src.jvm.JVM;
import src.misc.CodeBlock;
import src.misc.Coordinates;
import src.misc.Environment;
import src.type.TVoid;
import src.type.Type;
import src.value.Value;

import java.util.ArrayList;
import java.util.List;

public class ASTPrintf extends TypeHolder implements ASTNode {

    private final ASTNode str;
    private final List<ASTNode> args;

    public ASTPrintf(ASTNode str, List<ASTNode> args) {
        this.str = str;
        this.args = args;
    }

    @Override
    public Value eval(Environment<Value> e) {
        Value strValue = this.str.eval(e);
        if (!strValue.isString()){
            throw new InvalidTypes(strValue.show());
        }

        List<Value> result = new ArrayList<>();
        for (ASTNode arg: args) {
            Value value = arg.eval(e);
            /*if (value.isCell())
                throw new InvalidTypes(value.show());*/

            result.add(value);
        }
        System.out.printf(strValue.show(), result);

        return null;
    }

    @Override
    public void compile(CodeBlock block, Environment<Coordinates> e) {
        //TODO Get right
        for (ASTNode arg: this.args) {
            arg.compile(block, e);
        }
        block.emit(String.format("%s java/lang/String/valueOf(I)Ljava/lang/String;", JVM.INVOKESTATIC));
        block.emit(String.format("%s java/io/PrintStream/println(Ljava/lang/String;)V", JVM.INVOKEVIRTUAL));
    }

    @Override
    public Type typecheck(Environment<Type> e) {
        this.setType(new TVoid());
        return new TVoid();
    }
}
