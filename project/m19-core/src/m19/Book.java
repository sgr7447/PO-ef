package m19;

import m19.search.*;
import java.io.Serializable;

public class Book extends Work implements Serializable {

    private String _author;
    private String _ISBN;

    public Book (String name, int price, int numCopies, String author, String ISBN, String category, int works){
    super.createWork(name, price, numCopies, category, works);
    _author = author;
    _ISBN = ISBN;
    }

    public String getAuthor(){
    return _author;
    }

    public String getISBN(){
    return _ISBN;
    }

    @SuppressWarnings("nls")
    @Override
    public String toString(){ // shouldnt it be a toString??
    return (super.getWorkID() + " - " + super.getNumAvailableWorks() + " de " + super.getNumCopies()
            + " - Livro - " + super.getName() + " - " + super.getPrice() + " - " + super.getCategory().getString() +
            " - " + _author + " - " + _ISBN);
    }

    @Override
    public int compareTo(Work book){
        return super.getWorkID() - book.getWorkID();
    }

    @Override
    public boolean searchForWork(String keyWord){
        SearchStrategy _search = new SearchStrategy(new SearchBook());
        return _search.performSearch(this , keyWord);
    }
}
