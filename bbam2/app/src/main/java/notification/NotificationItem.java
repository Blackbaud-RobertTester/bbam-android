package notification;

import java.util.Date;

/**
 * Created by avaky on 7/30/15.
 */
public class NotificationItem {
    public int appId;
    public int notificationId;
    public String description;
    public Date date;

    public NotificationItem(int id, String description, Date date)
    {
        this.appId = id;
        this.description = description;
        this.date = date;
    }
}
