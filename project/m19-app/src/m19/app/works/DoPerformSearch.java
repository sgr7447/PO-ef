package m19.app.works;

import pt.tecnico.po.ui.Command;
import m19.LibraryManager;
import pt.tecnico.po.ui.Input;
// FIXME import core concepts
// FIXME import ui concepts

/**
 * 4.3.3. Perform search according to miscellaneous criteria.
 */
public class DoPerformSearch extends Command<LibraryManager> {
    private Input<String> _keyword;

  /**
   * @param m
   */
  public DoPerformSearch(LibraryManager m) {
    super(Label.PERFORM_SEARCH, m);
    _keyword = _form.addStringInput(Message.requestSearchTerm());
  }

  /** @see pt.tecnico.po.ui.Command#execute() */
  @Override
  public final void execute() {
      /*_form.parse();
      for (String s: _receiver.searchResults(_keyword.value())) {
          _display.addLine(s);
      }
      _display.display();
*/
  }

}
