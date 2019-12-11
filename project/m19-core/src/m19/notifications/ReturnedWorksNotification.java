package m19.notifications;

import m19.User;
import m19.Work;
import java.io.Serializable;

public class ReturnedWorksNotification implements Notification, Serializable{
    private String _work;

    public ReturnedWorksNotification(String work){
        _work = work;
    }

    @Override
    public String toString(){
        return "ENTREGA: "+ _work;
    }
}
