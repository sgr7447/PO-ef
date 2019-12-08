package m19.app.works;

import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.DialogException;
import pt.tecnico.po.ui.Input;
import m19.LibraryManager;
import m19.exceptions.NoWorkException;
import m19.app.exceptions.NoSuchWorkException;

/**
 * 4.3.1. Display work.
 */
public class DoDisplayWork extends Command<LibraryManager> {
    private Input<Integer> _workID;

  /**
   * @param receiver
   */
  public DoDisplayWork(LibraryManager receiver) {
    super(Label.SHOW_WORK, receiver);
    _workID = _form.addIntegerInput(Message.requestWorkId());
  }

  /** @see pt.tecnico.po.ui.Command#execute() */
  @Override
  public final void execute() throws DialogException {
      _form.parse();
      try{
          _display.popup(_receiver.getWork(_workID.value()).toString());
      }
      catch (NoWorkException e){
          throw new NoSuchWorkException(_workID.value());
      }
  }
}
