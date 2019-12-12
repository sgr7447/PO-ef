package m19.notifications;

import m19.User;
import m19.Work;
import java.util.ArrayList;
import java.io.Serializable;

public class RequestedWorksNotification implements Notification, Serializable{
    private String _work;

    public RequestedWorksNotification(Work work){
        _work = work.toString();
    }

    @Override
    public String toString(){
        return "REQUISIÇÃO: "+ _work;
    }

}
