package src.astnodes.functions.io.out;

import src.astnodes.ASTNode;
import src.exceptions.InvalidTypes;
import src.misc.CodeBlock;
import src.misc.Coordinates;
import src.misc.Environment;
import src.type.TVoid;
import src.type.Type;
import src.value.Str;
import src.value.Value;

import java.util.List;

public class ASTPrintln implements ASTNode {

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

    }

    @Override
    public Type typecheck(Environment<Type> e) {
        return new TVoid();
    }
}
