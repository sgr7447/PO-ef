package m19.exceptions;

/**
 * Exception if failed requisition rules.
 */
public class MissedRuleException extends Exception {

  /** Serial number for serialization. */
  private static final long serialVersionUID = 201901101348L;

  /** Bad missedRule. */
  private int _missedRule;

  /**
   * @param missedRule
   */
  public MissedRuleException(int missedRule) {
    _missedRule = missedRule;
  }

  public int getMissedRuleException(){
      return _missedRule;
  }
}
