package m19.app.users;

import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.DialogException;
import pt.tecnico.po.ui.Input;
import m19.LibraryManager;
import m19.User;
import m19.exceptions.NoUserException;
import m19.app.exceptions.NoSuchUserException;

/**
 * 4.2.2. Show specific user.
 */
public class DoShowUser extends Command<LibraryManager> {
    private Input<Integer> _userID;

  /**
   * @param receiver
   */
  public DoShowUser(LibraryManager receiver) {
    super(Label.SHOW_USER, receiver);
    _userID = _form.addIntegerInput(Message.requestUserId());
  }

  /** @see pt.tecnico.po.ui.Command#execute() */
  @Override
  public final void execute() throws DialogException{
      _form.parse();
      try{
          User user = _receiver.getUser(_userID.value());
          _display.popup(user.toString());
      }
      catch (NoUserException e){
          throw new NoSuchUserException(_userID.value());
      }
  }
}
