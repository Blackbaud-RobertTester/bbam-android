package com.blackbaud.bbam2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import apps.LinkerUtil;
import gcm.GCMUtil;
import notification.NotificationItem;


public class MessageDetailActivity extends Activity implements View.OnClickListener
{
    public static final String EMAIL_RECIPIENT = "recipient";

    private TextView appId;
    private TextView description;
    private TextView date;
    private Button emailButton;

    String gcm;
    String recipient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_detail);

        //We need to go and fetch the details about the message.
        Intent intent = getIntent();
        NotificationItem notification = (NotificationItem) intent.getSerializableExtra(MessageList.NOTIFICATION_ITEM);

        this.recipient = notification.email;
        this.gcm = GCMUtil.getGCM(intent);

        this.appId = (TextView)findViewById(R.id.appId);
        this.description = (TextView)findViewById(R.id.description);
        this.date = (TextView)findViewById(R.id.date);
        this.emailButton = (Button) findViewById(R.id.emailButton);

        appId.setText(String.valueOf(notification.appId));
        description.setText(notification.description);
        date.setText(notification.date.toString());

        this.emailButton.setOnClickListener(this);
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
                LinkerUtil.navToLinkAccount(this, this.gcm);
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

    @Override
    public void onClick(View v)
    {
        Intent intent = new Intent(getApplicationContext(), SendEmail.class);
        GCMUtil.setGCM(intent, this.gcm);
        intent.putExtra(EMAIL_RECIPIENT, this.recipient);
        startActivity(intent);
    }
}
