package src.astnodes.binding;

import src.astnodes.ASTNode;
import src.jvm.JVM;
import src.misc.*;
import src.value.Value;

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
    public Value eval(Environment<Value> e) {
        e = e.beginScope();

        Value value;
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
        int depth = e.getDepth();

        //Define a new frame
        Frame currFrame = block.getCurrFrame();
        Frame newFrame = currFrame.pushFrame("frame" + (depth - 1));
        block.setCurrFrame(newFrame);

        //Init recent declared frame
        block.emit(String.format("%s %s", JVM.NEW, newFrame));
        block.emit(JVM.DUP.toString());
        block.emit(String.format("%s %s/<init>()V", JVM.INVOKESPECIAL, newFrame));
        block.emit(JVM.DUP.toString());

        //Init static link of current frame
        block.emit(String.format("%s_%d", JVM.ALOAD, 3));
        block.emit(String.format("%s %s/sl L%s;", JVM.PUTFIELD, newFrame, currFrame));
        block.emit(String.format("%s_%d", JVM.ASTORE, 3));

        //Init variables
        Coordinates coordinates;
        for (Bind<String, ASTNode> bind: init) {
            String id = bind.getId();
            ASTNode node = bind.getValue();

            block.emit(String.format("%s_%d", JVM.ALOAD, 3));
            node.compile(block, e);
            String sym = block.gensym();
            block.emit(String.format("%s %s/%s I", JVM.PUTFIELD, newFrame, sym));

            coordinates = new Coordinates(sym, depth);
            e.assoc(id, coordinates);
        }

        try {
            newFrame.def(new PrintWriter("./src/jvm/result/" + newFrame + ".j"));
        } catch (FileNotFoundException ex) {
            throw new RuntimeException(ex);
        }

        body.compile(block, e);

        //Generate Code to Pop the frame
        newFrame.pop(block);
        block.setCurrFrame(currFrame);

        e = e.endScope();
    }
}
