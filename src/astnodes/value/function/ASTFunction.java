package src.astnodes.value.function;

import src.astnodes.ASTNode;
import src.astnodes.TypeHolder;
import src.jvm.JVM;
import src.misc.CodeBlock;
import src.misc.Coordinates;
import src.misc.Environment;
import src.misc.Pair;
import src.misc.frame.BlockType;
import src.misc.frame.DefBlock;
import src.misc.frame.FuncBlock;
import src.type.TClosure;
import src.misc.TypeFunctions;
import src.type.Type;
import src.value.Closure;
import src.value.Value;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class ASTFunction extends TypeHolder implements ASTNode {

    private final String id;
    private final List<Pair<String, Type>> params;
    private final ASTNode body;
    private final Type returnType;

    public ASTFunction(String id, List<Pair<String,String>> params,  ASTNode body, String returnTypename) {
        this.id = id;
        this.params = new ArrayList<>();
        for (Pair<String,String> pair: params) {
            this.params.add(new Pair<>(pair.getKey(), TypeFunctions.getType(pair.getValue())));
        }
        this.returnType = TypeFunctions.getType(returnTypename);
        this.body = body;
    }

    @Override
    public Value eval(Environment<Value> e) {
        Environment<Value> closureEnvironment = e.beginScope();
        Closure<Value> closure = new Closure<>(this.id, this.params, this.body, closureEnvironment);
        //closureEnvironment.assoc(this.id, closure);
        //closure.setEnvironment(closureEnvironment);
        return closure;
    }

    @Override
    public void compile(CodeBlock block, Environment<Coordinates> e) {
        e = e.beginScope();
        int depth = e.getDepth();
        DefBlock currDefBlock = block.getCurrFrame();

        TClosure closure = (TClosure) this.getType();
        String funcID = block.gensym(BlockType.FUNC);
        closure.setJVMID(funcID);

        for (Pair<String, Type> param: closure.getParams()) {
            String id = param.getKey();
            Type paramType = param.getValue();
            e.assoc(id, new Coordinates(id, depth, paramType.jvmType()));
        }

        FuncBlock funcBlock = new FuncBlock(closure, currDefBlock);
        DefBlock newDefBlock = funcBlock.getDefBlock();
        String result = "\"";
        try {
            funcBlock.defInterface(new PrintWriter("./src/jvm/result/closure_interface_" + funcBlock.getInterfaceId() + ".j"));

            PrintWriter closureOut = funcBlock.defClosure("./src/jvm/result", "closure_" + funcID);

            CodeBlock funcBodyBlock = new CodeBlock();

            //Init recent declared frame
            funcBodyBlock.emit(String.format("%s %s", JVM.NEW, newDefBlock));
            funcBodyBlock.emit(JVM.DUP.toString());
            funcBodyBlock.emit(String.format("%s %s/<init>()V", JVM.INVOKESPECIAL, newDefBlock));
            funcBodyBlock.emit(JVM.DUP.toString());

            //Init static link of current frame
            funcBodyBlock.emit(String.format("%s_%d", JVM.ALOAD, 3));
            funcBodyBlock.emit(String.format("%s %s/sl L%s;", JVM.GETFIELD, "closure_" + funcID, currDefBlock.getPrevious()));
            funcBodyBlock.emit(String.format("%s %s/sl L%s;", JVM.PUTFIELD, newDefBlock, currDefBlock));
            funcBodyBlock.emit(String.format("%s_%d", JVM.ASTORE, 3));

            this.body.compile(funcBodyBlock, e);
            funcBodyBlock.emit(JVM.IRETURN.toString());
            funcBodyBlock.emit(".end method");

            funcBodyBlock.dump(closureOut);
            closureOut.close();

            funcBlock.defFuncFrameBlock("./src/jvm/result");
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

        e = e.endScope();
    }

    @Override
    public Type typecheck(Environment<Type> e) {
        e = e.beginScope();

        for (Pair<String, Type> pair: this.params) {
            String paramID = pair.getKey();
            Type paramType = pair.getValue();

            e.assoc(paramID, paramType);
        }
        Type bodyType = this.body.typecheck(e);

        e = e.endScope();

        Type result = new TClosure(params, bodyType, returnType);

        //e.assoc(this.id, result);

        this.setType(result);
        return result;
    }
}
