package m19.exceptions;

public class NoWorkException extends Exception {

  static final long serialVersionUID = 201901091828L;

  private int _id;

  public NoWorkException(int id) {
    _id = id;
  }
}
