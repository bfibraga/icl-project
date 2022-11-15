package src.astnodes;

import src.jvm.JVM;
import src.misc.CodeBlock;
import src.misc.Coordinates;
import src.misc.Environment;
import src.misc.Frame;
import src.value.Value;

public class ASTId implements ASTNode {

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

        Frame currFrame = block.getCurrFrame();
        Frame previous = currFrame.getPrevious();

        block.emit(String.format("%s_%d", JVM.ALOAD, 3));

        for (int l = 0; l < levelShift; l++) {
            block.emit(String.format("%s %s/sl L%s;", JVM.GETFIELD, currFrame, previous));

            currFrame = previous;
            previous = currFrame.getPrevious();
        }

        block.emit(String.format("%s %s/%s I", JVM.GETFIELD, currFrame, coordinates.getId()));
    }
}
