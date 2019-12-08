package m19.app.users;

import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.DialogException;
import pt.tecnico.po.ui.Input;
import m19.User;
import m19.LibraryManager;
import m19.exceptions.NoUserException;
import m19.app.exceptions.NoSuchUserException;

/**
 * 4.2.3. Show notifications of a specific user.
 */
public class DoShowUserNotifications extends Command<LibraryManager> {

    private Input<Integer> _userID;


  /**
   * @param receiver
   */
  public DoShowUserNotifications(LibraryManager receiver) {
    super(Label.SHOW_USER_NOTIFICATIONS, receiver);
    _userID = _form.addIntegerInput(Message.requestUserId());
  }

  /** @see pt.tecnico.po.ui.Command#execute() */
  @Override
  public final void execute() throws DialogException {
      _form.parse();
      try{
          for (String notification: _receiver.printNotification(_userID.value())){
              _display.addLine(notification);
          }
          _display.display();
      }
      catch (NoUserException e){
          throw new NoSuchUserException(_userID.value());
      }
  }
}
