package src.astnodes.binding;

import src.astnodes.ASTNode;
import src.exceptions.InvalidTypeConvertion;
import src.exceptions.InvalidTypes;
import src.misc.CodeBlock;
import src.misc.Coordinates;
import src.misc.Environment;
import src.type.TCell;
import src.type.AbstractType;
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
    public AbstractType typecheck(Environment<AbstractType> e) {
        AbstractType targetAbstractType = new TCell();
        AbstractType lAbstractType = this.l.typecheck(e);

        if (!lAbstractType.sameType(targetAbstractType)){
            throw new InvalidTypeConvertion(lAbstractType.show(), targetAbstractType.show(), this.getClass().getSimpleName());
        }

        return this.r.typecheck(e);
    }
}
