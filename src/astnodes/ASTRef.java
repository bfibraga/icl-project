package src.astnodes;

import src.exceptions.InvalidTypes;
import src.misc.CodeBlock;
import src.misc.Coordinates;
import src.misc.Environment;
import src.value.Cell;
import src.value.Value;

public class ASTRef implements ASTNode {

    private final String id;

    public ASTRef(String id) {
        this.id = id;
    }

    @Override
    public Value eval(Environment<Value> e) {
        Value valueExp = e.find(id);
        if (!valueExp.isCell()){
            throw new InvalidTypes(valueExp.show());
        }

        return ((Cell) valueExp).get();
    }

    @Override
    public void compile(CodeBlock block, Environment<Coordinates> e) {

    }
}
