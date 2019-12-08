package m19.rules;

import m19.Work;
import m19.exceptions.MissedRuleException;
import java.io.Serializable;

public class ReferenceWorkRule extends Rules implements Serializable{
    private Work _work;

    public ReferenceWorkRule(Work work){
        _work = work;
    }

    @Override
    public boolean applyRule() throws MissedRuleException{
        if (!_work.getCategory().strCompare("Referência")){
            return true;
        }
        else throw new MissedRuleException(5);
    }
}
