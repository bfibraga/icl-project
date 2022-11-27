package src.astnodes.value.function;

import src.astnodes.ASTNode;
import src.exceptions.InvalidTypeConvertion;
import src.exceptions.InvalidTypes;
import src.misc.CodeBlock;
import src.misc.Coordinates;
import src.misc.Environment;
import src.misc.Pair;
import src.type.TClosure;
import src.type.AbstractType;
import src.type.TVoid;
import src.type.Type;
import src.value.Closure;
import src.value.Value;

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

        e = e.beginScope();

        Closure<Value> closure = (Closure<Value>) funcValue;
        List<Pair<String, AbstractType>> paramsNames = closure.getParamNames();
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
    public AbstractType typecheck(Environment<AbstractType> e) {
        AbstractType targetAbstractType = new TClosure();
        Type fncAbstractType = this.fnc.typecheck(e);
        if (!fncAbstractType.show().equals(targetAbstractType.show()))
            throw new InvalidTypeConvertion(fncAbstractType.show(), targetAbstractType.show(), this.getClass().getSimpleName());

        /*List<Pair<String, AbstractType>> argList = ((TClosure) fncAbstractType).getParams();
        for (int a = 0; a < this.args.size(); a++) {
            Pair<String, AbstractType> curr_arg = argList.get(a);
            String argId = curr_arg.getKey();
            ASTNode argNode = this.args.get(a);

            AbstractType argAbstractType = argNode.typecheck(e);
            AbstractType givenAbstractType = curr_arg.getValue();

            if (!argAbstractType.sameType(givenAbstractType))
                throw new InvalidTypeConvertion(argAbstractType.show(), givenAbstractType.show(), this.getClass().getSimpleName());

            e.assoc(argId, argAbstractType);
        }*/

        //return ((TClosure) fncAbstractType).getBodyType();
        return new TVoid();
    }
}
