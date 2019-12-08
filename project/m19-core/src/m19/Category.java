package m19;

import java.io.Serializable;

public enum Category implements Serializable {
    FICTION (true, "Ficção"),
    SCITECH (true, "Técnica e Científica"),
    REFERENCE (false, "Referência");
    public boolean _ableToOrder;
    public String _string;

    Category(boolean ableToOrder, String string){
      _ableToOrder = ableToOrder;
      _string = string;
    }

    public boolean getAbleToOrder(){
      return _ableToOrder;
    }
    
    public String getString(){
      return _string;
    }

    public boolean strCompare(String str){
        return str == _string;
    }
}
