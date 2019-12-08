package m19.exceptions;

public class NoUserException extends Exception {

  private static final long serialVersionUID = 201901101348L;

  private final int _id;

  public NoUserException(int id){
      _id = id;
  }
}
