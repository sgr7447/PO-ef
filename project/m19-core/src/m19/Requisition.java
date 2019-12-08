package m19;

import java.io.Serializable;

public class Requisition implements Serializable{
    private int _deadline;
    private int _userID;
    private int _workID;

   public Requisition (int deadline, int userID, int workID){
     _deadline = deadline;
     _userID = userID;
     _workID = workID;
   }

   public int getDeadline(){
       return _deadline;
   }

   public int getUserID(){
       return _userID;
   }

   public int getWorkID(){
       return _workID;
   }
   
}
