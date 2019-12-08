package m19.exceptions;

public class WorkNotBorrowedException extends Exception {

  static final long serialVersionUID = 201901091828L;

  private int _workID;
  private int _userID;

  public WorkNotBorrowedException(int workID, int userID) {
  _workID = workID;
  _userID = userID;
  }
}
