package src.astnodes.binding;

import src.astnodes.ASTNode;
import src.exceptions.InvalidTypeConvertion;
import src.jvm.JVM;
import src.misc.*;
import src.misc.frame.*;
import src.type.TCell;
import src.type.TVoid;
import src.type.Type;
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
        DefBlock currDefBlock = block.getCurrFrame();
        DefBlock newDefBlock = currDefBlock.pushFrame("frame" + (depth - 1));
        block.setCurrFrame(newDefBlock);

        //Init recent declared frame
        block.emit(String.format("%s %s", JVM.NEW, newDefBlock));
        block.emit(JVM.DUP.toString());
        block.emit(String.format("%s %s/<init>()V", JVM.INVOKESPECIAL, newDefBlock));
        block.emit(JVM.DUP.toString());

        //Init static link of current frame
        block.emit(String.format("%s_%d", JVM.ALOAD, 3));
        block.emit(String.format("%s %s/sl L%s;", JVM.PUTFIELD, newDefBlock, currDefBlock));
        block.emit(String.format("%s_%d", JVM.ASTORE, 3));

        //Init variables
        Coordinates coordinates;
        for (Bind<String, ASTNode> bind: init) {
            String id = bind.getId();
            Type type = bind.getType();
            String typename = type.jvmType();
            ASTNode node = bind.getValue();

            block.emit(String.format("%s_%d", JVM.ALOAD, 3));
            node.compile(block, e);
            String sym = block.gensym(BlockType.CODE);
            block.emit(String.format("%s %s/%s %s", JVM.PUTFIELD, newDefBlock, sym, typename));

            coordinates = new Coordinates(sym, depth, typename);
            e.assoc(id, coordinates);
        }

        try {
            newDefBlock.def(new PrintWriter("./src/jvm/result/" + newDefBlock + ".j"));
        } catch (FileNotFoundException ex) {
            throw new RuntimeException(ex);
        }

        body.compile(block, e);

        //Generate Code to Pop the frame
        newDefBlock.pop(block);
        block.setCurrFrame(currDefBlock);

        e = e.endScope();
    }

    @Override
    public Type typecheck(Environment<Type> e) {
        e = e.beginScope();

        Type nodeType;
        for (Bind<String, ASTNode> bind: init) {
            String id = bind.getId();
            Type defaultType = bind.getType();
            ASTNode node = bind.getValue();

            nodeType = node.typecheck(e);
            Type contentType = TypeFunctions.sameType(nodeType, new TCell()) ?
                    ((TCell) nodeType).getType() :
                    nodeType;

            if (!TypeFunctions.sameType(defaultType, new TVoid()) && !TypeFunctions.sameType(contentType, defaultType))
                throw new InvalidTypeConvertion(contentType.show(), defaultType.show(), this.getClass().getSimpleName());

            e.assoc(id, nodeType);
        }

        nodeType = body.typecheck(e);
        e = e.endScope();
        return nodeType;
    }
}
