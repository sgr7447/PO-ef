package m19.search;

import m19.Work;
import m19.DVD;
import java.io.Serializable;

public class SearchDVD implements Search, Serializable{

    @Override
    public boolean searchKeyWork(Work work, String keyWord){
        if(work instanceof DVD){
            DVD dvd = (DVD) work;
            keyWord = keyWord.toLowerCase();
            if (dvd.getName().toLowerCase().contains(keyWord) ||
                dvd.getDirector().toLowerCase().contains(keyWord)){
                    return true;}

            else return false;
        }
        else return false;

    }
}
