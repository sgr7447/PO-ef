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
            return (dvd.getName().toLowerCase().contains(keyWord) ||
                    dvd.getDirector().toLowerCase().contains(keyWord));
        }
        else return false;

    }
}
