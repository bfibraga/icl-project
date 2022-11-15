package src.astnodes.functions;

import src.astnodes.ASTNode;
import src.misc.CodeBlock;
import src.misc.Coordinates;
import src.misc.Environment;
import src.value.Bool;
import src.value.Int;
import src.value.Str;
import src.value.Value;

import java.util.List;

public class ASTPrint implements ASTNode {

    private List<ASTNode> args;

    public ASTPrint(List<ASTNode> args){
        this.args = args;
    }

    @Override
    public Value eval(Environment<Value> e) {
        StringBuilder result = new StringBuilder();
        for (ASTNode arg: args) {
            Value value = arg.eval(e);
            result.append(value.show()).append(" ");
        }
        System.out.print(result);
        return new Str(result.toString());
    }

    @Override
    public void compile(CodeBlock block, Environment<Coordinates> e) {

    }
}
