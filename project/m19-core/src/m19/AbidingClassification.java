package m19;

import java.io.Serializable;

public class AbidingClassification extends Classification implements Serializable {

    public AbidingClassification(){
        super(5, 8, 15, 30, true);
    }
    @Override
    public String toString(){
        return "CUMPRIDOR";
    }
}
