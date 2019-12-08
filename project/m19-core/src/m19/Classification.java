package m19;

import m19.Work;
import java.io.Serializable;

public abstract class Classification implements Serializable {

    private int _numMaxOfRequisitions;
    private int _deadlineOneWork;
    private int _deadlineFiveWorksOrLess;
    private int _deadlineMoreThanFiveWorks;
    private boolean _ableToOrder25Plus;

    public Classification(int maxReq, int oneWork, int fiveWorksLess, int moreThanFive, boolean order25Plus){

        _numMaxOfRequisitions = maxReq;
        _deadlineOneWork = oneWork;
        _deadlineFiveWorksOrLess = fiveWorksLess;
        _deadlineMoreThanFiveWorks = moreThanFive;
        _ableToOrder25Plus = order25Plus;
    }

    public int getNumMaxOfRequisitions(){
        return _numMaxOfRequisitions;
    }

    public int getDeadlineOneWork(){
        return _deadlineOneWork;
    }

    public int getDeadlineFiveWorksOrLess(){
        return _deadlineFiveWorksOrLess;
    }

    public int getDeadlineMoreThanFiveWorks(){
        return _deadlineMoreThanFiveWorks;
    }

    public boolean getAbleToOrder25Plus(){
        return _ableToOrder25Plus;
    }

    public int calculateDeadline(Work work){
        if (work.getNumCopies()==1){
            return getDeadlineOneWork();
        }
        else if (work.getNumCopies() > 1 && work.getNumCopies() <= 5){
            return getDeadlineFiveWorksOrLess();
        }
        else return getDeadlineMoreThanFiveWorks();
    }

    @Override
    public boolean equals(Object other){
        if (other instanceof Classification){
            Classification classification = (Classification) other;
            return (_numMaxOfRequisitions == classification.getNumMaxOfRequisitions() &&
                    _deadlineOneWork == classification.getDeadlineOneWork() &&
                    _deadlineMoreThanFiveWorks == classification.getDeadlineMoreThanFiveWorks() &&
                    _deadlineFiveWorksOrLess == classification.getDeadlineFiveWorksOrLess() &&
                    _ableToOrder25Plus == classification.getAbleToOrder25Plus());
        }
        return false;
    }

}
