package m19;

import java.io.Serializable;

public class FaultyClassification extends Classification implements Serializable {

    public FaultyClassification(){
        super(1, 2, 2, 2, false);
    }
    @Override
    public String toString(){
        return "FALTOSO";
    }
}
