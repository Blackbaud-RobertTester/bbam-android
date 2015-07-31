package notification;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by avaky on 7/30/15.
 */
public class NotificationItem implements Serializable
{
    public int appId;
    public int notificationId;
    public String description;
    public Date date;

    public NotificationItem(){}

    public NotificationItem(int notifId, int appId, String description, Date date)
    {
        this.appId = appId;
        this.notificationId = notifId;
        this.description = description;
        this.date = date;
    }
}
