package m19.app.works;

import pt.tecnico.po.ui.Command;
import m19.LibraryManager;
import m19.Work;

/**
 * 4.3.2. Display all works.
 */
public class DoDisplayWorks extends Command<LibraryManager> {

  /**
   * @param receiver
   */
  public DoDisplayWorks(LibraryManager receiver) {
    super(Label.SHOW_WORKS, receiver);
  }

  /** @see pt.tecnico.po.ui.Command#execute() */
  @Override
  public final void execute() {
      for (Work w: _receiver.showAllWorks()){
          _display.addLine(w.toString());
      }
      _display.display();
  }
}
