package m19.rules;

import m19.Work;
import m19.exceptions.MissedRuleException;
import java.io.Serializable;

public class NoAvailableWorksRule extends Rules implements Serializable{
    private Work _work;

    public NoAvailableWorksRule(Work work){
        _work = work;
    }

    @Override
    public boolean applyRule() throws MissedRuleException{
        if (_work.getNumAvailableWorks()>0){
            return true;
        }
        else throw new MissedRuleException(3);
    }
}
