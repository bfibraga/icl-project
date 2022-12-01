package src.misc.frame;

import java.util.ArrayList;
import java.util.List;

public class LabeledBlock implements SubBlock {

    private static final String TOKEN = "L";


    private List<String> labels;

    public LabeledBlock() {
        this.labels = new ArrayList<>();
    }

    @Override
    public String gensym() {
        int size = this.labels.size();
        String result = TOKEN + size;
        this.labels.add(result);
        return result;
    }
}
