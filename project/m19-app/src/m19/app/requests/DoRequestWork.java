package m19.app.requests;

import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.DialogException;
import pt.tecnico.po.ui.Input;
import m19.LibraryManager;
import m19.User;
import m19.Work;
import m19.Requisition;
import m19.app.exceptions.RuleFailedException;
import m19.app.exceptions.NoSuchUserException;
import m19.app.exceptions.NoSuchWorkException;
import m19.exceptions.MissedRuleException;
import m19.exceptions.NoUserException;
import m19.exceptions.NoWorkException;

/**
 * 4.4.1. Request work.
 */
public class DoRequestWork extends Command<LibraryManager> {
    private Input<Integer> _userID;
    private Input<Integer> _workID;
    private Input<String> _decision;

  /**
   * @param receiver
   */
  public DoRequestWork(LibraryManager receiver) {
    super(Label.REQUEST_WORK, receiver);

}

  /** @see pt.tecnico.po.ui.Command#execute() */
  @Override
  public final void execute() throws DialogException {
      _form.clear();
      _userID = _form.addIntegerInput(Message.requestUserId());
      _workID = _form.addIntegerInput(Message.requestWorkId());
      _form.parse();
      try{
          int _deadline = _receiver.requestWork(_userID.value(), _workID.value());
          _display.popup(Message.workReturnDay(_workID.value(), _deadline));
      }
      catch (MissedRuleException e){
          if (e.getMissedRuleException() == 3){
              try{
                  _form.clear();
                  _decision = _form.addStringInput(Message.requestReturnNotificationPreference());
                  _form.parse();
                  if (_decision.value().equals("s")){
                      _receiver.addUserToWaitList(_workID.value(),_userID.value());
                  }
              }
              catch (NoUserException f){
                  throw new NoSuchUserException(_userID.value());
              }
              catch (NoWorkException g){
                  throw new NoSuchWorkException(_workID.value());
              }
        }
        else{
            throw new RuleFailedException(_userID.value(), _workID.value(), e.getMissedRuleException());
        }
    }
    catch (NoUserException h){
        throw new NoSuchUserException(_userID.value());
    }
    catch (NoWorkException i){
        throw new NoSuchWorkException(_workID.value());
    }
}
}
