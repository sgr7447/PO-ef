package m19.search;

import m19.Work;
import m19.Book;
import java.io.Serializable;

public class SearchBook implements Search, Serializable{

    @Override
    public boolean searchKeyWork(Work work, String keyWord){
        if(work instanceof Book){
            Book book = (Book) work;
            keyWord = keyWord.toLowerCase();
            return (book.getName().toLowerCase().contains(keyWord) ||
                    book.getAuthor().toLowerCase().contains(keyWord));
        }
        else return false;
}
}
