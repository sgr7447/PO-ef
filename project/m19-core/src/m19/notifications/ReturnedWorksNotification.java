package m19.notifications;

import m19.User;
import m19.Work;
import java.io.Serializable;

public class ReturnedWorksNotification implements Notification, Serializable{
    private String _work;

    public ReturnedWorksNotification(Work work){
        _work = work.toString();
    }

    @Override
    public String toString(){
        return "ENTREGA: "+ _work;
    }
}
