package auth;

import java.util.Date;

import notification.NotificationDetails;

/**
 * Created by avaky on 7/30/15.
 */
public class NotificationService {

    public NotificationDetails getNotificationDetails(int notificationId)
    {
        NotificationDetails notificationDetails = new NotificationDetails();
        notificationDetails.appId = 1;
        notificationDetails.notificationId = 2;
        notificationDetails.date = new Date();
        notificationDetails.description = "You are on fire";

        return notificationDetails;
    }
}
