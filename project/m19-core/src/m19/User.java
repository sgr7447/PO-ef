package m19;

import java.util.ArrayList;
import java.util.TreeMap;
import java.util.Set;
import java.io.Serializable;

import m19.notifications.Notification;
import m19.notifications.RequestedWorksNotification;
import m19.notifications.ReturnedWorksNotification;

public class User implements Serializable {
    private int _userID;
    private String _name;
    private String _email;
    private Classification _classification;
    private boolean _status; //true if it is active, false if it is suspended
    private int _countOnTimeDeliveries;
    private int _countLateDeliveries;
    private int _numCurrentRequisitions;
    private int _fineValue ;
    private TreeMap<Work, Requisition> _requestsMap;
    private ArrayList<Notification> _notificationsArray;

    public User(String name, String email, int userID){
        _name = name;
        _email = email;
        _userID = userID;
        _classification = new NormalClassification();
        _status = true;
        _countOnTimeDeliveries = 0;
        _countLateDeliveries = 0;
        _numCurrentRequisitions = 0;
        _fineValue = 0;
        _requestsMap = new TreeMap<Work, Requisition>();
        _notificationsArray = new ArrayList<Notification>();
    }

    public String getName(){
        return _name;
    }

    public int getUserID(){
        return _userID;
    }

    public Classification getClassification(){
        return _classification;
    }

    public boolean getStatus(){
        return _status;
    }

    public int getNumCurrentRequisitions(){
        return _numCurrentRequisitions;
    }

    public int getFineValue(){
        return _fineValue;
    }

    public ArrayList<Notification> getNotificationsArray()
        ArrayList<Notification> copy = new ArrayList<Notification>();
        copy.addAll(_notificationsArray);
        _notificationsArray.clear();{
        return copy;
    }

    public boolean workRequested(Work work){
        return _requestsMap.containsKey(work);
    }

    public Requisition getRequisition(Work work){
        return _requestsMap.get(work);
    }

    public void setFineValue(int fine){
        _fineValue += fine;
    }

    public void addNotification(Notification notification){
        _notificationsArray.add(notification);
    }

    public void payFine(){
        _fineValue = 0;
    }

    public void setCountOnTimeDeliveries(){
        _countOnTimeDeliveries++;
        _countLateDeliveries = 0;
    }

    public void setCountLateDelieveries(){
        _countLateDeliveries++;
        _countOnTimeDeliveries = 0;
    }

    public void updateStatus(int date){
        if (_fineValue == 0 && countNotReturned(date)==0){
            _status = true;
        }
        else{
            _status = false;
        }
    }

    public int countNotReturned(int date){
        int countNotReturned = 0;
        for (Requisition r: _requestsMap.values()){
            if (date > r.getDeadline())
                countNotReturned +=1;
        }
        return countNotReturned;
    }

    public boolean hasRequisition(Work w){
        return _requestsMap.containsKey(w);
    }

    public void addRequisition(Work work, Requisition requisition){
        _requestsMap.put(work, requisition);
        _numCurrentRequisitions++;
    }

    public void removeRequisition(Work work, int date) {
        if (calculateDaysExceeded(work, date)>0){
            setCountLateDelieveries();
        }
        else{
            setCountOnTimeDeliveries();
        }
        _requestsMap.remove(work);
        _numCurrentRequisitions--;
    }

    public int calculateFine(Work work, int date){
            int daysExceeded = calculateDaysExceeded(work, date);
            if (daysExceeded > 0){
                int fine = 5 * daysExceeded;
                return fine;
            }
            return 0;
    }

    public int calculateDaysExceeded(Work work, int date){
        Requisition requisition = _requestsMap.get(work);
        return date - requisition.getDeadline();
    }

    public void updateClassification(){
        if (_classification.equals(new FaultyClassification()) && _countOnTimeDeliveries >= 3){
            _classification = new NormalClassification();
        }
        else if (_countOnTimeDeliveries >= 5){
            _classification = new AbidingClassification();
        }
        else if (_countLateDeliveries >= 3){
            _classification = new FaultyClassification();
        }
        else{
            _classification = new NormalClassification();
        }
    }

    public boolean userAbleToOrder(){
        if (!_status){ //if it is suspended
            return false;
        }
        else if (_numCurrentRequisitions < _classification.getNumMaxOfRequisitions()){
            return true;
        }
        else{
            return false;
        }
    }

    @Override
    public String toString(){
        if (_status == false){
            return (_userID + " - " + _name + " - " + _email
            + " - " + _classification + " - SUSPENSO - EUR " + _fineValue);
        }
        else{
            return (_userID + " - " + _name + " - " + _email
            + " - " + _classification + " - ACTIVO");
        }
    }
}
