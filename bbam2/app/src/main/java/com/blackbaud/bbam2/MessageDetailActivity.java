package com.blackbaud.bbam2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import auth.NotificationService;
import notification.NotificationItem;


public class MessageDetailActivity extends Activity {

    private TextView appId;
    private TextView description;
    private TextView date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_detail);

        //We need to go and fetch the details about the message.
        Intent intent = getIntent();
        NotificationItem notification = (NotificationItem) intent.getSerializableExtra(MessageList.NOTIFICATION_ITEM);

        this.appId = (TextView)findViewById(R.id.appId);
        this.description = (TextView)findViewById(R.id.description);
        this.date = (TextView)findViewById(R.id.date);

        appId.setText(String.valueOf(notification.appId));
        description.setText(notification.description);
        date.setText(notification.date.toString());

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

        switch(item.getItemId()) {
            case R.id.action_settings:
                return true;
            case R.id.action_add_login:
                navToLinkAccount();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    // TODO: pull into util class
    private void navToLinkAccount() {
        Intent intent = new Intent(this, AppSelection.class);
        startActivity(intent);
    }
}
