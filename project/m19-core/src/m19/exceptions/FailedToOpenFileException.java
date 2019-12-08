package m19.exceptions;

/**
 * Class encoding file opening failure.
 */
public class FailedToOpenFileException extends Exception {

  /** Serial number for serialization. */
  static final long serialVersionUID = 201901091828L;

  /** Bad file name. */
  private String _name;

  /**
   * @param name
   */
  public FailedToOpenFileException(String name) {
    _name = name;
  }

  

}
