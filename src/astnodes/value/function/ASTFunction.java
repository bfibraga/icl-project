package src.astnodes.value.function;

import src.astnodes.ASTNode;
import src.astnodes.TypeHolder;
import src.misc.CodeBlock;
import src.misc.Coordinates;
import src.misc.Environment;
import src.misc.Pair;
import src.misc.frame.DefBlock;
import src.misc.frame.FuncBlock;
import src.type.TClosure;
import src.misc.TypeFunctions;
import src.type.Type;
import src.value.Closure;
import src.value.Value;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class ASTFunction extends TypeHolder implements ASTNode {

    private final List<Pair<String, Type>> params;
    private final ASTNode body;
    private final Type returnType;

    public ASTFunction(List<Pair<String,String>> params, ASTNode body, String returnTypename) {
        this.params = new ArrayList<>();
        for (Pair<String,String> pair: params) {
            this.params.add(new Pair<>(pair.getKey(), TypeFunctions.getType(pair.getValue())));
        }
        this.returnType = TypeFunctions.getType(returnTypename);
        this.body = body;
    }

    @Override
    public Value eval(Environment<Value> e) {
        Closure<Value> closure = new Closure<>(this.params, this.body, e);
        e.assoc(closure.show(), closure);
        closure.setEnvironment(e);
        return closure;
    }

    @Override
    public void compile(CodeBlock block, Environment<Coordinates> e) {
        DefBlock currDefBlock = block.getCurrFrame();
        FuncBlock funcBlock = new FuncBlock((TClosure) this.getType(), currDefBlock);

        try {
            funcBlock.defInterface(new PrintWriter("./src/jvm/result/" + funcBlock.getInterfaceId() + ".j"));
            funcBlock.defClosure(new PrintWriter("./src/jvm/result/" + "closure_of_" + funcBlock.getInterfaceId() + ".j"));
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
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
        this.setType(result);
        return result;
    }
}
