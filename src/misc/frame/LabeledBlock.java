package src.misc.frame;

import java.util.ArrayList;
import java.util.List;

public class LabeledBlock implements SubBlock {

    private static final String TOKEN = "Label_";


    private int nlabels;

    public LabeledBlock() {
        this.nlabels = 0;
    }

    @Override
    public String gensym() {
        String result = TOKEN + nlabels;
        nlabels += 1;
        return result;
    }
}
