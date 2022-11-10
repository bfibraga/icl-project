package src.astnodes;

import src.Interpreter;
import src.jvm.JVM;
import src.misc.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;

public class ASTDef implements ASTNode {

    private List<Bind<String, ASTNode>> init;
    private ASTNode body;

    public ASTDef(List<Bind<String, ASTNode>> init, ASTNode body){
        this.init = init;
        this.body = body;
    }

    @Override
    public int eval(Environment<Integer> e) {
        e = e.beginScope();

        int value;
        for (Bind<String, ASTNode> bind: init) {
            String id = bind.getId();
            ASTNode node = bind.getValue();

            value = node.eval(e);
            e.assoc(id, value);
        }

        value = body.eval(e);
        e = e.endScope();
        return value;
    }

    @Override
    public void compile(CodeBlock block, Environment<Coordinates> e) {
        e = e.beginScope();

        Frame currFrame = block.getCurrFrame();
        Frame newFrame = currFrame.pushFrame("v");

        try {
            newFrame.def(new PrintWriter("./src/jvm/result/" + newFrame + ".j"));
        } catch (FileNotFoundException ex) {
            throw new RuntimeException(ex);
        }

        Coordinates coordinates;
        int depth = e.getDepth();
        for (Bind<String, ASTNode> bind: init) {
            String id = bind.getId();
            ASTNode node = bind.getValue();

            node.compile(block, e);
            block.emit(String.format("%s_%d", JVM.ALOAD, 3));
            String sym = block.gensym();
            block.emit(String.format("%s %s/%s I", JVM.PUTFIELD, newFrame, sym));

            coordinates = new Coordinates(sym, depth);
            e.assoc(id, coordinates);
        }
        body.compile(block, e);

        //Generate Code to Pop the frame
        newFrame.pop(block);
        block.setCurrFrame(currFrame);

        e = e.endScope();
    }
}
