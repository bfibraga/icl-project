package src.astnodes.functions.io.out;

import src.astnodes.ASTNode;
import src.exceptions.InvalidTypes;
import src.misc.CodeBlock;
import src.misc.Coordinates;
import src.misc.Environment;
import src.type.Type;
import src.value.Value;

import java.util.ArrayList;
import java.util.List;

public class ASTPrintf implements ASTNode {

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

    }

    @Override
    public Type typecheck(Environment<Type> e) {
        return null;
    }
}
