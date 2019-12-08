package m19.search;

import m19.Work;

public class SearchStrategy{
    private Search _search;

    public SearchStrategy(Search search){
        _search = search;
    }

    public boolean performSearch(Work work, String keyWord){
        return _search.searchKeyWork(work, keyWord);

    }
}
