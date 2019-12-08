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
            if (book.getName().toLowerCase().contains(keyWord) ||
                book.getAuthor().toLowerCase().contains(keyWord)){
                    return true;}

            else return false;
        }
        else return false;
}
}
