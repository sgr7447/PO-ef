package m19.rules;

import m19.User;
import m19.exceptions.MissedRuleException;
import java.io.Serializable;

public class SuspendedUserRule extends Rules implements Serializable{
    private User _user;

    public SuspendedUserRule(User user){
        _user = user;
    }

    @Override
    public boolean applyRule() throws MissedRuleException{
        if (_user.getStatus())
            return true;
        else throw new MissedRuleException(2);
    }
}
