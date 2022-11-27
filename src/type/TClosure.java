package src.type;

import src.misc.Pair;

import java.util.ArrayList;
import java.util.List;

public class TClosure extends AbstractType implements Type {

    private List<Pair<String, AbstractType>> params;
    private AbstractType bodyAbstractType;

    public TClosure(List<Pair<String, AbstractType>> params, AbstractType bodyAbstractType) {
        super("Closure");
        this.params = params;
        this.bodyAbstractType = bodyAbstractType;
    }

    public TClosure() {
        super("Closure");
        this.params = new ArrayList<>();
        this.bodyAbstractType = new TVoid();
    }

    public List<Pair<String, AbstractType>> getParams() {
        return params;
    }

    public AbstractType getBodyType() {
        return bodyAbstractType;
    }
}
