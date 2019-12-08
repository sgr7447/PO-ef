package m19.exceptions;

/**
 * Class encoding user registration failure.
 */
public class UserRegistrationException extends Exception {

  /** Serial number for serialization. */
  static final long serialVersionUID = 201901091828L;

  /** Bad user's name. */
  private String _name;

  /** Bad user's email. */
  private String _email;

  /**
   * @param name
   * @param email
   */
  public UserRegistrationException(String name, String email) {
    _name = name;
    _email = email;
  }

}
