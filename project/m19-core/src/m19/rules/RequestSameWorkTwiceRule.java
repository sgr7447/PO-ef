package m19.rules;

import m19.User;
import m19.Work;
import m19.exceptions.MissedRuleException;
import java.io.Serializable;

public class RequestSameWorkTwiceRule extends Rules implements Serializable{
    private User _user;
    private Work _work;

    public RequestSameWorkTwiceRule(User user, Work work){
        _user = user;
        _work = work;
    }

    public boolean applyRule() throws MissedRuleException{
        if (!_user.workRequested(_work)){
            return true;
        }
        else throw new MissedRuleException(1);
    }
}
