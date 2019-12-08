package m19;

import m19.notifications.Notification;

import m19.search.*;
import java.util.ArrayList;
import java.io.Serializable;


public abstract class Work implements Comparable<Work>, Serializable {
    private int _workID;
    private String _name;
    private int _price;
    private int _numCopies;
    private int _numAvailableWorks;
    private ArrayList<User> _usersWaitList;
    private Category _category;

    public void createWork(String name, int price, int numCopies, String category, int countWorks){
        _workID = countWorks;
        _name = name;
        _price = price;
        _numCopies = numCopies;
        _numAvailableWorks = numCopies;
        _usersWaitList = new ArrayList<User>();
        _category = Category.valueOf(category);

    }

    public int getWorkID(){
        return _workID;
    }

    public String getName(){
        return _name;
    }

    public int getPrice(){
        return _price;
    }

    public int getNumCopies(){
        return _numCopies;
    }

    public void setNumAvailableWorks(int n){
        _numAvailableWorks+=n;
    }

    public int getNumAvailableWorks(){
        return _numAvailableWorks;
    }

    public Category getCategory(){
        return _category;
    }

    public ArrayList<User> getUsersWaitList(){
        return _usersWaitList;
    }

    public void addUserToWaitList (User user){
        _usersWaitList.add(user);
    }

    public void addNotification(Notification notification){
        for (User u: _usersWaitList){
            u.addNotification(notification);
        }
    }

    public void removeUserFromWaitList (User user){
        _usersWaitList.remove(user);
    }

    public boolean workAvailableToOrder(){
        if (getNumAvailableWorks() == 0){
            return false;
        }
        return true;
    }

    public abstract boolean searchForWork(String keyWord);

    @Override
    public boolean equals(Object other){
        if (other instanceof Work){
            Work work = (Work) other;
            return _workID == work.getWorkID();
        }
        return false;
    }

    public int CompareTo(Work other){
        return _workID - other.getWorkID();
    }
}
