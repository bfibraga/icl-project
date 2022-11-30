package src.type;

public class TStr implements Type{
    private static final String TYPE_NAME = "Str";

    @Override
    public String show() {
        return TYPE_NAME;
    }
}
