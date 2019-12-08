package m19.app.users;

import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.DialogException;
import pt.tecnico.po.ui.Input;
import m19.LibraryManager;
import m19.User;
import m19.exceptions.ActiveUserException;
import m19.exceptions.NoUserException;
import m19.app.exceptions.UserIsActiveException;
import m19.app.exceptions.NoSuchUserException;

/**
 * 4.2.5. Settle a fine.
 */
public class DoPayFine extends Command<LibraryManager> {

  private Input<Integer> _userID;

  /**
   * @param receiver
   */
  public DoPayFine(LibraryManager receiver) {
    super(Label.PAY_FINE, receiver);
    _userID = _form.addIntegerInput(Message.requestUserId());
  }

  /** @see pt.tecnico.po.ui.Command#execute() */
  @Override
  public final void execute() throws DialogException {
      _form.parse();
      try{
          _receiver.payFine(_userID.value());
      }
      catch (NoUserException e){
          throw new NoSuchUserException(_userID.value());
      }
      catch (ActiveUserException f){
          throw new UserIsActiveException(_userID.value());
      }
  }

}
