package m19.app.requests;

import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.DialogException;
import pt.tecnico.po.ui.Input;
import m19.LibraryManager;
import m19.User;
import m19.Work;
import m19.exceptions.NoUserException;
import m19.exceptions.NoWorkException;
import m19.exceptions.WorkNotBorrowedException;
import m19.app.exceptions.NoSuchWorkException;
import m19.app.exceptions.WorkNotBorrowedByUserException;
import m19.app.exceptions.NoSuchUserException;

/**
 * 4.4.2. Return a work.
 */
public class DoReturnWork extends Command<LibraryManager> {
    private Input<Integer> _userID;
    private Input<Integer> _workID;
    private int fine;

  /**
   * @param receiver
   */
  public DoReturnWork(LibraryManager receiver) {
    super(Label.RETURN_WORK, receiver);
  }

  /** @see pt.tecnico.po.ui.Command#execute() */
  @Override
  public final void execute() throws DialogException {
      _form.clear();
      _userID = _form.addIntegerInput(Message.requestUserId());
      _workID = _form.addIntegerInput(Message.requestWorkId());
      _form.parse();
      try{

            fine = _receiver.calculateFine(_userID.value(), _workID.value());

            _receiver.returnWork(_userID.value(), _workID.value());

            if (fine>0){
                _display.popup(Message.showFine(_userID.value(), fine));

                _form.clear();
                Input<String> payFine = _form.addStringInput(Message.requestFinePaymentChoice());
                _form.parse();

                if (payFine.value().equals("s")){
                    _receiver.payFineOfWork(_userID.value());
                }
                else _receiver.addFine(_userID.value(),fine);
            }

        }
        catch (NoUserException e){
            throw new NoSuchUserException(_userID.value());
        }
        catch (NoWorkException f){
            throw new NoSuchWorkException(_workID.value());
        }
        catch (WorkNotBorrowedException g){
            throw new WorkNotBorrowedByUserException(_workID.value(), _userID.value());
        }
  }

}
