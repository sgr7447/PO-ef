package m19;

import m19.search.*;
import java.io.Serializable;

public class DVD extends Work implements Comparable<Work>, Serializable {

    private String _director;
    private String _IGAC;

    public DVD(String name, int price, int numCopies, String director, String IGAC, String category, int countWorks){
        super.createWork(name, price, numCopies, category, countWorks);
        _director = director;
        _IGAC = IGAC;
    }

    public String getDirector(){
        return _director;
    }

    public String getIGAC(){
        return _IGAC;
    }

    @SuppressWarnings("nls")
    @Override
    public String toString(){
      return (super.getWorkID() + " - " + super.getNumAvailableWorks() + " de " + super.getNumCopies()
      + " - DVD - " + super.getName() + " - " + super.getPrice() + " - " + super.getCategory().getString() +
      " - " + _director + " - " + _IGAC);
    }

    @Override
    public int compareTo(Work dvd){
        return super.getWorkID() - dvd.getWorkID();
    }

    @Override
    public boolean searchForWork(String keyWord){
        SearchStrategy _search = new SearchStrategy(new SearchDVD());
        return _search.performSearch(this, keyWord);
    }
}
