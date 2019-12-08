package m19;

import m19.exceptions.MissingFileAssociationException;
import m19.exceptions.FailedToOpenFileException;
import m19.exceptions.ImportFileException;
import m19.exceptions.BadEntrySpecificationException;
import m19.exceptions.NoUserException;
import m19.exceptions.NoWorkException;
import m19.exceptions.WorkNotBorrowedException;
import m19.exceptions.MissedRuleException;
import m19.exceptions.ActiveUserException;



import java.io.IOException;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.LinkedList;
import java.util.ArrayList;

/**
 * The façade class.
 */

public class LibraryManager {

  private Library _library = new Library();
  private String _filename;
  private Boolean _saveFlag = false;

  /**
   * @throws MissingFileAssociationException
   * @throws IOException
   * @throws FileNotFoundException
   */

  public void save() throws MissingFileAssociationException, IOException {
      try{
          ObjectOutputStream out = new ObjectOutputStream (new BufferedOutputStream (new FileOutputStream(_filename)));
          out.writeObject(_library);
          out.close();
          _saveFlag = false;
      }
      catch (FileNotFoundException e){
          throw new MissingFileAssociationException();
      }
  }

  /**
   * @param filename
   * @throws MissingFileAssociationException
   * @throws IOException
   */

  public void saveAs(String filename) throws MissingFileAssociationException, IOException {
    _filename = filename;
    save();
  }

  /**
   * @param filename
   * @throws FailedToOpenFileException
   * @throws IOException
   * @throws ClassNotFoundException
   */

  public void load(String filename) throws FailedToOpenFileException, IOException, ClassNotFoundException {
     try{
         setFileName(filename);
      ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename));
      _library = (Library) in.readObject();
      in.close();
    }
    catch(FileNotFoundException e){
        throw new FailedToOpenFileException(filename);
    }
  }

  /**
   * @param datafile
   * @throws ImportFileException
   */

    public void importFile(String datafile) throws ImportFileException {
        try {
            _library.importFile(datafile);
            _saveFlag = true;
        } catch (IOException | BadEntrySpecificationException e) {
            throw new ImportFileException(e);
        }

    }
    public Boolean getSaveFlag(){
        return _saveFlag;
    }

    public String getFileName(){
        return _filename;
    }

    public void setFileName(String filename){
        _filename = filename;
    }

    public void advanceDate(int n_days){
        _library.advanceDate(n_days);
        _saveFlag = true;
    }

    public int getCurrentDate(){
        return _library.getCurrentDate();
    }

    public User registerUser(String name, String email) {
        _saveFlag = true;
        return _library.registerUser(name, email);
    }

    public User getUser (int userID) throws NoUserException {
        return _library.getUser(userID);
    }

    public ArrayList<String> printNotification(int userID) throws NoUserException{
        return _library.printNotification(userID);
    }

    public void addUserToWaitList (int workID, int userID) throws NoUserException, NoWorkException{
        _library.addUserToWaitList(workID, userID);
    }

    public LinkedList<User> showAllUsers(){
        return _library.showAllUsers();
    }

    public Work getWork(int workID) throws NoWorkException {
        return _library.getWork(workID);
    }

    public LinkedList<Work> showAllWorks (){
        return _library.showAllWorks();
    }

    public void payFine(int userID) throws ActiveUserException, NoUserException{
        _saveFlag = true;
        _library.payFine(userID);
    }

    public void addFine(int userID, int fine) throws NoUserException{
        _saveFlag = true;
        _library.addFine(userID, fine);
    }

    public void payFineOfWork(int userID) throws NoUserException{
        _saveFlag = true;
        _library.payFineOfWork(userID);
    }

    public int getTotalFine(int userID, int fine) throws NoUserException{
        return _library.getTotalFine(userID, fine);
    }

    public int calculateFine(int userID, int workID) throws NoUserException, NoWorkException{
        return _library.calculateFine(userID, workID);
    }

    public int requestWork (int userID, int workID) throws NoWorkException, NoUserException, MissedRuleException{
        _saveFlag = true;
        return _library.requestWork(userID, workID);
    }

    public void returnWork(int userID, int workID) throws NoWorkException, NoUserException, WorkNotBorrowedException{
        _saveFlag = true;
        _library.returnWork(userID, workID);
    }

    public LinkedList<String> searchResults(String keyword){
        return _library.searchResults(keyword);
    }
}
