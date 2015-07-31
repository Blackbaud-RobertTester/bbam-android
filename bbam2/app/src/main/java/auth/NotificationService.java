package auth;

import java.util.Date;

import notification.NotificationItem;

/**
 * Created by avaky on 7/30/15.
 */
public class NotificationService {

    public NotificationItem getNotificationDetails(int notificationId)
    {
        NotificationItem notificationDetails = new NotificationItem();
        notificationDetails.appId = 1;
        notificationDetails.notificationId = 2;
        notificationDetails.date = new Date();
        notificationDetails.description = "You are on fire";

        return notificationDetails;
    }
}
