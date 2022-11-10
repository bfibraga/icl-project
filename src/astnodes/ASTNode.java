package src.astnodes;

import src.Interpreter;
import src.misc.CodeBlock;
import src.misc.Coordinates;
import src.misc.Environment;

public interface ASTNode {

    int eval(Environment<Integer> e);

    void compile(CodeBlock block, Environment<Coordinates> e);
	
}

