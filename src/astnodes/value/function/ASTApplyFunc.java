package src.astnodes.value.function;

import src.astnodes.ASTNode;
import src.exceptions.InvalidTypeConvertion;
import src.exceptions.InvalidTypes;
import src.misc.CodeBlock;
import src.misc.Coordinates;
import src.misc.Environment;
import src.misc.Pair;
import src.type.TClosure;
import src.misc.TypeFunctions;
import src.type.TVoid;
import src.type.Type;
import src.value.Closure;
import src.value.Value;

import java.util.ArrayList;
import java.util.List;

public class ASTApplyFunc implements ASTNode {

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
            throw new InvalidTypes(funcValue.show());

        Closure<Value> closure = (Closure<Value>) funcValue;

        e = closure.getEnvironment().beginScope();

        List<Pair<String, Type>> paramsNames = closure.getParamNames();
        for (int a = 0; a < this.args.size(); a++) {
            String argId = paramsNames.get(a).getKey();
            ASTNode argNode = this.args.get(a);
            Value argValue = argNode.eval(e);

            e.assoc(argId, argValue);
        }
        Value result = closure.getBody().eval(e);

        e = e.endScope();

        return result;
    }

    @Override
    public void compile(CodeBlock block, Environment<Coordinates> e) {

    }

    @Override
    public Type typecheck(Environment<Type> e) {
        Type targetType = new TClosure(new ArrayList<>(), new TVoid());
        Type fncType = this.fnc.typecheck(e);
        if (!TypeFunctions.sameType(fncType, targetType))
            throw new InvalidTypeConvertion(fncType.show(), targetType.show(), this.getClass().getSimpleName());

        TClosure closureType = ((TClosure) fncType);
        List<Pair<String, Type>> argList = closureType.getParams();

        //System.out.println(closureType);

        for (int a = 0; a < argList.size(); a++) {
            Pair<String, Type> curr_arg = argList.get(a);
            String argId = curr_arg.getKey();
            ASTNode argNode = this.args.get(a);

            Type argAbstractType = argNode.typecheck(e);
            Type givenAbstractType = curr_arg.getValue();

            if (!TypeFunctions.sameType(argAbstractType, givenAbstractType))
                throw new InvalidTypeConvertion(argAbstractType.show(), givenAbstractType.show(), this.getClass().getSimpleName());

            e.assoc(argId, argAbstractType);
        }

        return closureType.getBodyType();
    }
}