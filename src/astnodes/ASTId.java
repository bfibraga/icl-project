package src.astnodes;

import src.misc.CodeBlock;
import src.misc.Coordinates;
import src.misc.Environment;

public class ASTId implements ASTNode {

    private final String id;

    public ASTId(String id){
        this.id = id;
    }

    @Override
    public int eval(Environment<Integer> e) {
        return e.find(this.id);
    }

    @Override
    public void compile(CodeBlock block, Environment<Coordinates> e) {
        Coordinates coordinates = e.find(this.id);
        int levelShift = e.getDepth() - coordinates.getDepth();


        for (int l = 0; l < levelShift; l++) {

        }
    }
}
