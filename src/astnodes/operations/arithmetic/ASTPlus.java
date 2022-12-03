package src.astnodes.operations.arithmetic;

import src.astnodes.ASTNode;
import src.astnodes.TypeHolder;
import src.exceptions.InvalidTypeConvertion;
import src.exceptions.InvalidTypes;
import src.jvm.JVM;
import src.misc.CodeBlock;
import src.misc.Coordinates;
import src.misc.Environment;
import src.type.TInt;
import src.misc.TypeFunctions;
import src.type.Type;
import src.value.Int;
import src.value.Str;
import src.value.Value;

public class ASTPlus extends TypeHolder implements ASTNode {

    private ASTNode l, r;

    public ASTPlus(ASTNode l, ASTNode r){
        this.l = l;
        this.r = r;
    }

    @Override
    public Value eval(Environment<Value> e) {
        Value valueL = this.l.eval(e);
        if ((!valueL.isNumber() && !valueL.isString() && !valueL.isBoolean())){
            throw new InvalidTypes(valueL.show());
        }

        Value valueR = this.r.eval(e);
        if ((!valueR.isNumber() && !valueR.isString() && !valueR.isBoolean())){
            throw new InvalidTypes(valueR.show());
        }

        if (valueL.isBoolean() && valueR.isBoolean()){
            throw new InvalidTypes(valueL.show());
        } else {
            if (valueL.isString()){
                return new Str(valueL.toString() + valueR);
            }

            if (valueR.isString()){
                return new Str(valueL + valueR.toString());
            }
        }

        if (valueL.isString()){
            String resultL = valueL.show();
            if (valueR.isString()){
                String resultR = valueR.show();
                return new Str(resultL + resultR);
            } else {
                int resultR = ((Int) valueR).getValue();
                return new Str(resultL + resultR);
            }
        } else {
            int resultL = ((Int) valueL).getValue();
            if (valueR.isString()){
                String resultR = valueR.show();
                return new Str(resultL + resultR);
            } else {
                int resultR = ((Int) valueR).getValue();
                return new Int(resultL + resultR);
            }
        }

    }

    @Override
    public void compile(CodeBlock block, Environment<Coordinates> e) {
        this.l.compile(block, e);
        this.r.compile(block, e);
        block.emit(JVM.IADD.toString());
    }

    @Override
    public Type typecheck(Environment<Type> e) {
        Type targetType = new TInt();
        Type lType = this.l.typecheck(e);
        if (!TypeFunctions.sameType(lType, targetType))
            throw new InvalidTypeConvertion(lType.show(), targetType.show(), this.getClass().getSimpleName());


        Type rType = this.r.typecheck(e);
        if (!TypeFunctions.sameType(rType, targetType))
            throw new InvalidTypeConvertion(rType.show(), targetType.show(), this.getClass().getSimpleName());

        this.setType(targetType);
        return targetType;
    }
}

