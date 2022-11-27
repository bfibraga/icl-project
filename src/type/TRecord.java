package src.type;

import src.value.Value;

import java.util.HashMap;
import java.util.Map;

public class TRecord extends AbstractType implements Type{

    private Map<String, AbstractType> fields;

    public TRecord() {
        super("Record");
        this.fields = new HashMap<>();
    }

    public TRecord(Map<String, AbstractType> fields){
        super("Record");
        this.fields = fields;
    }

    public AbstractType get(String field){
        return this.fields.get(field);
    }
}
