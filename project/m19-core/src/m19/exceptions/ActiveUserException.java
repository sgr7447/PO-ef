package m19.exceptions;

public class ActiveUserException extends Exception{

    private static final long serialVersionUID = 201901101348L;

    private int _userID;

    public ActiveUserException(int userID){
        _userID = userID;
    }
}
