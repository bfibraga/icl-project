package src.astnodes;

import src.Interpreter;
import src.misc.Environment;

public interface ASTNode {

    int eval(Environment<Integer> e);
	
}

