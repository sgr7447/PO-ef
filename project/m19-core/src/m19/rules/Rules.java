package m19.rules;

import m19.exceptions.MissedRuleException;
import java.io.Serializable;

public abstract class Rules implements Serializable{
    public abstract boolean applyRule() throws MissedRuleException;
}
