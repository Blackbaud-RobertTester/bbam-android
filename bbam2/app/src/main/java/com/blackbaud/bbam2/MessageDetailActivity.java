package com.blackbaud.bbam2;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import auth.NotificationService;
import notification.NotificationDetails;


public class MessageDetailActivity extends ActionBarActivity {

    private TextView appId;
    private TextView description;
    private TextView date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_detail);

        //We need to go and fetch the details about the message.
        Intent intent = getIntent();
        int notificationId = intent.getIntExtra(MessageList.NOTIFICATION_ITEM, 0);

        NotificationService notificationService = new NotificationService();
        NotificationDetails notificationDetails = notificationService.getNotificationDetails(notificationId);

        this.appId = (TextView)findViewById(R.id.appId);
        this.description = (TextView)findViewById(R.id.description);
        this.date = (TextView)findViewById(R.id.date);

        appId.setText(String.valueOf(notificationDetails.appId));
        description.setText(notificationDetails.description);
        date.setText(notificationDetails.date.toString());

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_notification_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
