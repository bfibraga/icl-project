package src.type;

public class TBool implements Type {
    private static final String TYPE_NAME = "Bool";

    @Override
    public String show() {
        return TYPE_NAME;
    }
}
