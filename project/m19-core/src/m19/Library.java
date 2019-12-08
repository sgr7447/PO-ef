package m19;

import m19.exceptions.BadEntrySpecificationException;
import m19.exceptions.NoUserException;
import m19.exceptions.NoWorkException;
import m19.exceptions.MissedRuleException;
import m19.exceptions.WorkNotBorrowedException;
import m19.exceptions.ActiveUserException;

import m19.rules.Rules;
import m19.rules.NoAvailableWorksRule;
import m19.rules.NumberRequestsExceededRule;
import m19.rules.OverPriceLimitRule;
import m19.rules.ReferenceWorkRule;
import m19.rules.RequestSameWorkTwiceRule;
import m19.rules.SuspendedUserRule;
import m19.rules.VerifyAllRules;

import m19.search.SearchBook;
import m19.search.SearchDVD;
import m19.search.SearchStrategy;

import m19.notifications.Notification;
import m19.notifications.RequestedWorksNotification;
import m19.notifications.ReturnedWorksNotification;

import java.io.IOException;
import java.io.Serializable;
import java.io.BufferedReader;
import java.io.FileReader;
import java.text.Collator;
import java.util.Comparator;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Collection;
import java.util.Collections;
import java.lang.Iterable;

/**
 * Class that represents the library as a whole.
 */
public class Library implements Serializable {
    /** Serial number for serialization. */
    private static final long serialVersionUID = 201901101348L;
    private TreeMap<Integer, Work> _worksMap;
    private TreeMap<Integer, User> _usersMap;
    private LinkedList<Work> _searchResults;
    private int _countWorks;
    private int _countUsers;
    private int _date;


    public Library(){
        _worksMap = new TreeMap<Integer, Work>();
        _usersMap = new TreeMap<Integer, User>();
        _searchResults = new LinkedList<Work>();
        _countWorks = 0;
        _countUsers = 0;
        _date = 0;
    }

    public int getCountUsers(){
        return _countUsers;
    }

    public int getCountWorks(){
        return _countWorks;
    }

    public int getCurrentDate(){
        return _date;
    }


  /**
  * Read the text input file at the beginning of the program and populates the
  * instances of the various possible types (books, DVDs, users).
  *
  * @param filename
  *          name of the file to load
  * @throws BadEntrySpecificationException
  * @throws IOException
  */

    void importFile(String filename) throws BadEntrySpecificationException, IOException {
        int numLine = 0;
        try{
            BufferedReader input = new BufferedReader (new FileReader (filename));
            String s;

            while ((s = input.readLine()) != null) {
                String line = new String (s.getBytes(), "UTF-8");
                numLine++;
                String[] split = line.split(":");
                if (split[0].equals("BOOK")){
                    registerBook (split[1], Integer.parseInt(split[3]),
                    Integer.parseInt(split[6]),split[2],split[5],split[4]);
                }
                else if (split[0].equals("DVD")){
                    registerDVD (split[1], Integer.parseInt(split[3]),
                    Integer.parseInt(split[6]),split[2],split[5],split[4]);
                }
                else if (split[0].equals("USER")){
                    registerUser(split[1],split[2]);
                }
                else{
                    throw new BadEntrySpecificationException(filename);
                }
            }
            input.close();
        }
        catch (IOException e){
             throw new IOException();
        }
  }

/**
* verifies if n_days is a valid number (greater than 0) and
* adds it to the current date
*
* @param n_days
*          number of days to advance
*/

    public void advanceDate(int n_days){
        if (n_days > 0){
            _date += n_days;
            verifyUsers();
        }
    }

/**
* receives a name and an email and creates and User. Adds the new user in the
* _usersMap and incrementates the _countUsers
*
* @param name
*          name of the user
* @param email
*          user's email
*/

    public User registerUser (String name, String email) {
        User user = new User (name, email, _countUsers);
        _usersMap.put(user.getUserID(), user);
        _countUsers++;
        return user;
    }

/**
* Receives an userID and searches for the corresponding user in the _usersMap
*
* @param userID
* @throws NoUserException
*/

    public User getUser(int userID) throws NoUserException {
        User user = _usersMap.get(userID);
        if ( user == null){
            throw new NoUserException(userID);
        }
        return user;
    }

    public ArrayList<String> printNotification(int userID) throws NoUserException{
        User user = getUser(userID);
        ArrayList<String> lstNotifications = new ArrayList<String>();
        for (Notification notification: user.getNotificationsArray())
            lstNotifications.add(notification.toString());
        return lstNotifications;
    }
/**
* returns a LinkedList with all the users
*/

    public LinkedList<User> showAllUsers(){
        LinkedList<User> lstUsers = new LinkedList<User>();
        lstUsers.addAll(_usersMap.values());
        Collections.sort(lstUsers, new UserComparator());
        return lstUsers;
    }

/**
* Receives the required arguments and creates a book. Adds the new book to the
* the _worksMap and incrementates the _countWorks.
*
* @param name, price, numCopies, author, ISBN, category
*          new book's specifications
*/

    public void registerBook (String name, int price, int numCopies, String author, String ISBN, String category) {
        Work work = new Book (name, price, numCopies, author, ISBN, category, _countWorks);
        _worksMap.put(work.getWorkID(), work);
        _countWorks++;
    }

/**
* Receives the required arguments and creates a DVD. Adds the new DVD to the
* the _worksMap and incrementates the _countWorks.
*
* @param name, price, numCopies, author, ISBN, category
*          new DVD's specifications
*/

    public void registerDVD (String name, int price, int numCopies, String director, String IGAC, String category) {
        Work work = new DVD (name, price, numCopies, director, IGAC, category, _countWorks);
        _worksMap.put(work.getWorkID(), work);
        _countWorks++;
    }

/**
* Receives a workID and searches for the corresponding work in the _worksMap
*
* @param workID
* @throws NoWorkException
*/

    public Work getWork(int workID) throws NoWorkException {
        Work work = _worksMap.get(workID);
        if (work == null){
            throw new NoWorkException(workID);
        }
        return work;
    }

/**
* returns a LinkedList with all the works
*/

    public LinkedList<Work> showAllWorks(){
        LinkedList<Work> lstWorks = new LinkedList<Work>();
        lstWorks.addAll(_worksMap.values());
        return lstWorks;
    }

    public int requestWork (int userID, int workID) throws NoWorkException, NoUserException, MissedRuleException{
        User user = getUser (userID);
        Work work = getWork (workID);
        VerifyAllRules verifyAllRules = new VerifyAllRules(user, work);
        int deadline = user.getClassification().calculateDeadline(work) + _date;
        if (verifyAllRules.AllRulesVerified()){
            Requisition requisition = new Requisition(deadline, userID, workID);
            user.addRequisition(work, requisition);
            work.setNumAvailableWorks(-1);
            work.addNotification(new RequestedWorksNotification(work));
        }
        return deadline;
    }

    public void addUserToWaitList (int workID, int userID) throws NoUserException, NoWorkException{
        User user = getUser (userID);
        Work work = getWork (workID);
        work.addUserToWaitList(user);
    }

    public int calculateFine(int userID, int workID) throws NoUserException, NoWorkException{
        User user = getUser(userID);
        Work work = getWork(workID);
        Requisition requisition = user.getRequisition(work);

        if (user.hasRequisition(work)){
            return user.calculateFine(work, _date);
        }
        else{
            return 0;
        }
    }

    public void payFineOfWork(int userID) throws NoUserException{
        User user = getUser(userID);
        verifyUser(user);
    }

    public void addFine(int userID, int fine) throws NoUserException{
        User user = getUser(userID);
        user.setFineValue(fine);
        verifyUser(user);
    }

    public void verifyUsers(){
        for (User u: _usersMap.values()){
            verifyUser(u);
        }
    }

    public void verifyUser(User user){
        user.updateStatus(_date);
        user.updateClassification();
    }

    public void payFine(int userID) throws ActiveUserException, NoUserException{
        User user = getUser(userID);
        if (!user.getStatus()){
            user.payFine();
            verifyUser(user);
        }
        else {
            throw new ActiveUserException(userID);
        }
    }

    public int getTotalFine(int userID, int fine) throws NoUserException{
        User user = getUser(userID);
        return (fine + user.getFineValue());
    }

    public void returnWork(int userID, int workID) throws NoWorkException, NoUserException, WorkNotBorrowedException{
        User user = getUser (userID);
        Work work = getWork (workID);
        if (user.hasRequisition(work)){
            user.removeRequisition(work, _date);
            work.setNumAvailableWorks(1);
            verifyUser(user);
            work.addNotification(new ReturnedWorksNotification(work));

        }
        else throw new WorkNotBorrowedException(workID, userID);
    }
/*
    public LinkedList<String> searchResults(String keyword){
            LinkedList<String> searchResults = new LinkedList<String>();
            for (Work w: _worksMap.values()){
                if (w instanceof Book){
                    SearchStrategy searchBooks = new SearchStrategy(new SearchBook());
                    if (searchBooks.performSearch(w, keyword)){
                        searchResults.add(w.toString());
                    }
                }
                else if (w instanceof DVD){
                    SearchStrategy searchDVDs = new SearchStrategy(new SearchDVD());
                    if ( searchDVDs.performSearch(w, keyword)){
                        searchResults.add(w.toString());
                    }
                }

            }
            return searchResults;
        }
        */
    public LinkedList<String> searchResults(String keyword){
        LinkedList<String> _searchResults = new LinkedList<String>();
        for (Work w: _worksMap.values()){
            if (w.searchForWork(keyword)){
                _searchResults.add(w.toString());
            }
        }
        return _searchResults;
    }

/**
* Receives 2 users, compares 2 their names and IDs and orders theis names alphabetically (case
* insensitive). If they have the same name, they are ordered by their userID
* @param user1
*/

    private static class UserComparator implements Comparator<User>{

        @Override
        public int compare(User user1, User user2) {
            Collator collator = Collator.getInstance(Locale.getDefault());
            int comparatorValue = collator.compare(user1.getName(), user2.getName());
            if (comparatorValue != 0){
                return comparatorValue;
            }
            else{
                return user1.getUserID() - user2.getUserID();
            }
        }
    }
}
