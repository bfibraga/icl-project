package src.astnodes.binding;

import src.astnodes.ASTNode;
import src.astnodes.TypeHolder;
import src.jvm.JVM;
import src.misc.CodeBlock;
import src.misc.Coordinates;
import src.misc.Environment;
import src.misc.frame.DefBlock;
import src.type.Type;
import src.value.Value;

public class ASTId extends TypeHolder implements ASTNode {

    private final String id;

    public ASTId(String id){
        this.id = id;
    }

    @Override
    public Value eval(Environment<Value> e) {
        return e.find(this.id);
    }

    @Override
    public void compile(CodeBlock block, Environment<Coordinates> e) {
        Coordinates coordinates = e.find(this.id);
        int levelShift = e.getDepth() - coordinates.getDepth();

        DefBlock currDefBlock = block.getCurrFrame();
        DefBlock previous = currDefBlock.getPrevious();

        block.emit(String.format("%s_%d", JVM.ALOAD, 3));

        for (int l = 0; l < levelShift; l++) {
            block.emit(String.format("%s %s/sl L%s;", JVM.GETFIELD, currDefBlock, previous));

            currDefBlock = previous;
            previous = currDefBlock.getPrevious();
        }

        block.emit(String.format("%s %s/%s %s", JVM.GETFIELD, currDefBlock, coordinates.getId(), coordinates.getTypename()));
    }

    @Override
    public Type typecheck(Environment<Type> e) {
        this.setType(e.find(this.id));
        return this.getType();
    }
}
