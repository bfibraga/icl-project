package src.astnodes.functions;

import src.astnodes.ASTNode;
import src.misc.CodeBlock;
import src.misc.Coordinates;
import src.misc.Environment;

import java.util.List;

public class ASTPrint implements ASTNode {

    private List<ASTNode> args;

    public ASTPrint(List<ASTNode> args){
        this.args = args;
    }

    @Override
    public int eval(Environment<Integer> e) {
        for (ASTNode arg: args) {
            int value = arg.eval(e);
            System.out.print(value);
        }
        return 0;
    }

    @Override
    public void compile(CodeBlock block, Environment<Coordinates> e) {

    }
}
