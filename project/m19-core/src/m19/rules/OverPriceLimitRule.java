package m19.rules;

import m19.User;
import m19.Work;
import m19.exceptions.MissedRuleException;
import java.io.Serializable;

public class OverPriceLimitRule extends Rules implements Serializable{
    private Work _work;
    private User _user;

    public OverPriceLimitRule(User user, Work work){
        _work = work;
        _user = user;
    }

    @Override
    public boolean applyRule() throws MissedRuleException{
        if (_user.getClassification().getAbleToOrder25Plus() || _work.getPrice() < 25)
            return true;

        else throw new MissedRuleException(6);
    }
}
