package src.astnodes.value.function;

import src.astnodes.ASTNode;
import src.astnodes.TypeHolder;
import src.exceptions.InvalidTypeConvertionException;
import src.exceptions.InvalidValueConvertionException;
import src.exceptions.WrongNumberFuntionParametersException;
import src.jvm.JVM;
import src.misc.CodeBlock;
import src.misc.Coordinates;
import src.misc.Environment;
import src.misc.Pair;
import src.misc.frame.FuncBlock;
import src.type.TClosure;
import src.misc.TypeFunctions;
import src.type.TVoid;
import src.type.Type;
import src.value.Closure;
import src.value.Value;

import java.util.ArrayList;
import java.util.List;

public class ASTApplyFunc extends TypeHolder implements ASTNode {

    private final ASTNode fnc;
    private final List<ASTNode> args;

    public ASTApplyFunc(ASTNode fnc, List<ASTNode> args) {
        this.fnc = fnc;
        this.args = args;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Value eval(Environment<Value> e) {
        Value funcValue = this.fnc.eval(e);
        if (!funcValue.isFunc())
            throw new InvalidValueConvertionException(funcValue.show());

        Closure<Value> closure = (Closure<Value>) funcValue;

        Environment<Value> closureEnvironment = closure.getEnvironment();
        //closureEnvironment.assoc(closure.getId(), closure);

        List<Pair<String, Type>> paramsNames = closure.getParamNames();
        for (int a = 0; a < this.args.size(); a++) {
            String argId = paramsNames.get(a).getKey();
            ASTNode argNode = this.args.get(a);
            Value argValue = argNode.eval(e);

            closureEnvironment.assoc(argId, argValue);
        }

        Value result = closure.getBody().eval(closureEnvironment);
        closureEnvironment = closureEnvironment.endScope();

        return result;
    }

    @Override
    public void compile(CodeBlock block, Environment<Coordinates> e) {
        block.emit("");
        this.fnc.compile(block, e);
        TClosure closure = (TClosure) ((TypeHolder) this.fnc).getType();
        FuncBlock funcBlock = new FuncBlock(closure, block.getCurrFrame());

        StringBuilder applyParams = new StringBuilder("(");
        String[] parts = funcBlock.getInterfaceId().split("_");
        applyParams.append(parts[0]);
        for (int i = 1; i < parts.length-1; i++) {
            applyParams.append(parts[i]);
        }
        applyParams.append(")").append(parts[parts.length - 1]);

        block.emit(String.format("%s %s", JVM.CHECKCAST, "closure_interface_" + funcBlock.getInterfaceId()));

        for (ASTNode arg: this.args) {
            arg.compile(block, e);
        }

        block.emit(String.format("%s %s/apply%s %d", JVM.INVOKEINTERFACE, "closure_interface_" + funcBlock.getInterfaceId(), applyParams, closure.getParams().size()+1));
        block.emit("");
    }

    @Override
    public Type typecheck(Environment<Type> e) {
        Type targetType = new TClosure();
        Type fncType = this.fnc.typecheck(e);
        if (!TypeFunctions.sameType(fncType, targetType))
            throw new InvalidTypeConvertionException(fncType.show(), targetType.show(), this.getClass().getSimpleName());

        TClosure closureType = ((TClosure) fncType);

        e = e.beginScope();
        List<Pair<String, Type>> argList = closureType.getParams();

        if (argList.size() != this.args.size())
            throw new WrongNumberFuntionParametersException(argList.size(), this.args.size());

        //System.out.println(closureType);

        for (int a = 0; a < argList.size(); a++) {
            Pair<String, Type> curr_arg = argList.get(a);
            String argId = curr_arg.getKey();
            ASTNode argNode = this.args.get(a);

            Type argAbstractType = argNode.typecheck(e);
            Type givenAbstractType = curr_arg.getValue();

            if (!TypeFunctions.sameType(argAbstractType, givenAbstractType))
                throw new InvalidTypeConvertionException(argAbstractType.show(), givenAbstractType.show(), this.getClass().getSimpleName());

            e.assoc(argId, argAbstractType);
        }

        Type result = closureType.getBodyType();
        Type returnType = closureType.getReturnType();

        if (!TypeFunctions.sameType(result, returnType))
            throw new InvalidTypeConvertionException(result, returnType, this.getClass().getSimpleName());

        this.setType(returnType);

        e = e.endScope();

        return returnType;
    }
}
