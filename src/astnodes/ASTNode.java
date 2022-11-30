package src.astnodes;

import src.misc.CodeBlock;
import src.misc.Coordinates;
import src.misc.Environment;
import src.type.Type;
import src.value.Value;

public interface ASTNode {

    Value eval(Environment<Value> e);

    void compile(CodeBlock block, Environment<Coordinates> e);

    Type typecheck(Environment<Type> e);
	
}

