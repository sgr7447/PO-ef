package m19.rules;

import m19.User;
import m19.Work;
import java.io.Serializable;
import m19.exceptions.MissedRuleException;

public class VerifyAllRules implements Serializable {
    //private User _user;
    //private Work _work;
    private NoAvailableWorksRule _noAvailableWorksRule;
    private NumberRequestsExceededRule _numberRequestsExceededRule;
    private OverPriceLimitRule _overPriceLimitRule;
    private ReferenceWorkRule _referenceWorkRule;
    private RequestSameWorkTwiceRule _requestSameWorkTwiceRule;
    private SuspendedUserRule _suspendedUserRule;

    public VerifyAllRules(User user, Work work){
        //_user = user;
        //_work = work;
        _noAvailableWorksRule = new NoAvailableWorksRule(work);
        _numberRequestsExceededRule = new NumberRequestsExceededRule(user);
        _overPriceLimitRule = new OverPriceLimitRule(user, work);
        _referenceWorkRule = new ReferenceWorkRule(work);
        _requestSameWorkTwiceRule = new RequestSameWorkTwiceRule(user, work);
        _suspendedUserRule = new SuspendedUserRule(user);

    }

    public boolean AllRulesVerified() throws MissedRuleException{
        return (_noAvailableWorksRule.applyRule() && _numberRequestsExceededRule.applyRule()
            && _overPriceLimitRule.applyRule() && _referenceWorkRule.applyRule()
            && _requestSameWorkTwiceRule.applyRule() && _suspendedUserRule.applyRule());
    }
}
