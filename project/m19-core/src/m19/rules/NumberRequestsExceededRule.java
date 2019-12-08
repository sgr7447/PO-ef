package m19.rules;

import m19.User;
import m19.Classification;
import m19.exceptions.MissedRuleException;
import java.io.Serializable;

public class NumberRequestsExceededRule extends Rules implements Serializable{
    private User _user;

    public NumberRequestsExceededRule(User user){
        _user = user;
    }

    @Override
    public boolean applyRule() throws MissedRuleException{
        if (_user.getNumCurrentRequisitions() < _user.getClassification().getNumMaxOfRequisitions()){
            return true;
        }
        else throw new MissedRuleException(4);
    }
}
