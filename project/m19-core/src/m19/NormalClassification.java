package m19;

import java.io.Serializable;

public class NormalClassification extends Classification implements Serializable {

    public NormalClassification(){
        super(3, 3, 8, 15, false);
    }
    @Override
    public String toString(){
        return "NORMAL";
    }
}
