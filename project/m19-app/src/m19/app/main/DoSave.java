package m19.app.main;

import java.io.IOException;
import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.Input;
import m19.LibraryManager;
import m19.exceptions.MissingFileAssociationException;
import m19.app.exceptions.FileOpenFailedException;

/**
 * 4.1.1. Save to file under current name (if unnamed, query for name).
 */
public class DoSave extends Command<LibraryManager> {
    private Input<String> _file;

    /**
    * @param receiver
    */
    public DoSave(LibraryManager receiver) {
        super(Label.SAVE, receiver);
        if (_receiver.getFileName()==null){
            _file = _form.addStringInput(Message.newSaveAs());
        }
    }

    /** @see pt.tecnico.po.ui.Command#execute() */
    @Override
    public final void execute() throws FileOpenFailedException{
        if (_receiver.getSaveFlag()){
            try{
                if (_receiver.getFileName() == null){
                    _form.parse();
                    _receiver.setFileName(_file.value());
                    _receiver.saveAs(_file.value());
                }
                else{
                    _receiver.save();
                }
            }
            catch (MissingFileAssociationException | IOException e) {
                throw new FileOpenFailedException(_file.value());
            }
        }
    }
}
