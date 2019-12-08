package m19.app.users;

import pt.tecnico.po.ui.Command;
import m19.LibraryManager;
import m19.User;

/**
 * 4.2.4. Show all users.
 */
public class DoShowUsers extends Command<LibraryManager> {

  /**
   * @param receiver
   */
  public DoShowUsers(LibraryManager receiver) {
    super(Label.SHOW_USERS, receiver);
  }

  /** @see pt.tecnico.po.ui.Command#execute() */
  @Override
  public final void execute() {
      for (String s: _receiver.showAllUsers()){
          _display.addLine(s);
      }
      _display.display();
  }
}
