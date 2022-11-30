package src.type;

public class TVoid implements Type{

    //TODO Maybe add default value to this type...

    private static final String TYPE_NAME = "Void";

    @Override
    public String show() {
        return TYPE_NAME;
    }
}
