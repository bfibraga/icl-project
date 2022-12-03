package src.astnodes.binding;

import src.astnodes.ASTNode;
import src.astnodes.TypeHolder;
import src.exceptions.InvalidTypeConvertion;
import src.exceptions.InvalidTypes;
import src.jvm.JVM;
import src.misc.CodeBlock;
import src.misc.Coordinates;
import src.misc.Environment;
import src.misc.frame.ReferenceHandler;
import src.type.TCell;
import src.misc.TypeFunctions;
import src.type.Type;
import src.value.Cell;
import src.value.Value;

public class ASTAssign extends TypeHolder implements ASTNode {

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
        this.l.compile(block, e);
        this.r.compile(block, e);
        Type refType = ((TypeHolder)this.l).getType();
        Type contentType = ((TCell) refType).getType();

        String contentTypename = contentType.jvmType();
        block.emit(String.format("%s %s/v %s", JVM.PUTFIELD, refType.jvmType(), contentTypename.contains("Ref_of_") ?
                "L" + contentTypename + ";" :
                contentTypename));
    }

    @Override
    public Type typecheck(Environment<Type> e) {
        Type targetType = new TCell();
        Type lType = this.l.typecheck(e);

        if (!TypeFunctions.sameType(lType, targetType)){
            throw new InvalidTypeConvertion(lType.show(), targetType.show(), this.getClass().getSimpleName());
        }

        this.setType(this.r.typecheck(e));
        return this.getType();
    }
}
