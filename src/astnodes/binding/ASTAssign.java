package src.astnodes.binding;

import src.astnodes.ASTNode;
import src.exceptions.InvalidTypeConvertion;
import src.exceptions.InvalidTypes;
import src.misc.CodeBlock;
import src.misc.Coordinates;
import src.misc.Environment;
import src.type.TBool;
import src.type.TCell;
import src.type.Type;
import src.value.Cell;
import src.value.Value;

public class ASTAssign implements ASTNode {

    private ASTNode l, r;

    public ASTAssign(ASTNode l, ASTNode r){
        this.r = r;
        this.l = l;
    }

    @Override
    public Value eval(Environment<Value> e) {
        Value valueL = this.l.eval(e);
        if (!valueL.isCell()){
            throw new InvalidTypes(valueL.show());
        }

        Value valueR = this.r.eval(e);
        /*if (valueR.isCell()){
            throw new InvalidTypes(valueR.show());
        }*/

        ((Cell) valueL).set(valueR);
        return valueR;
    }

    @Override
    public void compile(CodeBlock block, Environment<Coordinates> e) {

    }

    @Override
    public Type typecheck(Environment<Type> e) {
        Type targetType = new TCell();
        Type lType = this.l.typecheck(e);

        if (!lType.sameType(targetType)){
            throw new InvalidTypeConvertion(lType.show(), targetType.show(), this.getClass().getSimpleName());
        }

        return this.r.typecheck(e);
    }
}
