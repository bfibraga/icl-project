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
import java.util.UUID;

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

        TClosure closure = (TClosure) this.getType();
        String funcID = closure.getJVMID();

        DefBlock currDefBlock = block.getCurrFrame();

        /*for (Pair<String, Type> param: closure.getParams()) {
            String id = param.getKey();
            Type paramType = param.getValue();
            e.assoc(id, new Coordinates(id, depth, paramType.jvmType()));
        }*/

        FuncBlock funcBlock = new FuncBlock(closure, new DefBlock("closure_" + currDefBlock.getId()));
        DefBlock newDefBlock = funcBlock.getDefBlock();

        try {
            block.emit(String.format("%s %s", JVM.NEW, "closure_" + funcID));
            block.emit(JVM.DUP.toString());
            block.emit(String.format("%s %s/<init>()V", JVM.INVOKESPECIAL, "closure_" + funcID));
            block.emit(JVM.DUP.toString());

            block.emit(String.format("%s_%d", JVM.ALOAD, 3));
            block.emit(String.format("%s %s/sl L%s;", JVM.PUTFIELD, "closure_" + funcID, newDefBlock.getPrevious()));

            block.emit("");


            funcBlock.defInterface(new PrintWriter("./src/jvm/result/closure_interface_" + funcBlock.getInterfaceId() + ".j"));

            PrintWriter closureOut = funcBlock.defClosure("./src/jvm/result", "closure_" + funcID);

            CodeBlock funcBodyBlock = new CodeBlock(newDefBlock, block.getReference());

            //Init recent declared frame
            funcBodyBlock.emit(String.format("%s %s", JVM.NEW, newDefBlock));
            funcBodyBlock.emit(JVM.DUP.toString());
            funcBodyBlock.emit(String.format("%s %s/<init>()V", JVM.INVOKESPECIAL, newDefBlock));
            funcBodyBlock.emit(JVM.DUP.toString());

            //Init static link of current frame
            funcBodyBlock.emit(String.format("%s_%d", JVM.ALOAD, 0));
            funcBodyBlock.emit(String.format("%s %s/sl L%s;", JVM.GETFIELD, "closure_" + funcID, newDefBlock.getPrevious()));
            funcBodyBlock.emit(String.format("%s %s/sl L%s;", JVM.PUTFIELD, newDefBlock, newDefBlock.getPrevious()));
            funcBodyBlock.emit(JVM.DUP.toString());

            for (int i = 0; i < this.params.size()-1; i++) {
                saveCoordinates(funcBodyBlock, newDefBlock, e, i);
                funcBodyBlock.emit(JVM.DUP.toString());
            }
            saveCoordinates(funcBodyBlock, newDefBlock, e, this.params.size()-1);
            funcBodyBlock.emit(String.format("%s_%d", JVM.ASTORE, 3));

            this.body.compile(funcBodyBlock, e);

            funcBodyBlock.emit("");
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

    private void saveCoordinates(CodeBlock funcBlock, DefBlock defBlock, Environment<Coordinates> e, int index){
        int depth = e.getDepth();

        Pair<String, Type> pair = this.params.get(index);
        String id = pair.getKey();

        Type type = pair.getValue();
        String typename = type.jvmType();

        String sym = funcBlock.gensym(BlockType.CODE);
        defBlock.setType(sym, type);

        Coordinates coordinates = new Coordinates(sym, depth, typename);
        e.assoc(id, coordinates);

        funcBlock.emit("");
        funcBlock.emit(String.format("%s %d", JVM.ILOAD, (index+1)));
        funcBlock.emit(String.format("%s %s/%s %s", JVM.PUTFIELD, defBlock, sym, typename.contains("Ref_of_") ?
                "L" + typename + ";" :
                typename ));

        funcBlock.emit("");
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

        TClosure result = new TClosure(params, bodyType, returnType);

        //e.assoc(this.id, result);

        String JVMID = "f" + UUID.randomUUID().toString().replace("-", "_");
        result.setJVMID(JVMID);
        this.setType(result);

        return result;
    }
}
