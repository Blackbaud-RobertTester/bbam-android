package com.blackbaud.bbam2;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import apps.LinkerUtil;
import auth.ValidatorUtil;
import gcm.GCMUtil;
import notification.NotificationItem;


public class MessageDetailActivity extends Activity implements View.OnClickListener
{
    public static final String EMAIL_RECIPIENT = "recipient";

    private TextView appId;
    private TextView description;
    private TextView date;
    private Button emailButton;
    private TextView bulletText;
    private TextView appName;

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
        this.bulletText = (TextView) findViewById(R.id.bullet);
        this.appName = (TextView) findViewById(R.id.appName);

        appId.setText(String.valueOf(notification.appId));
        description.setText(notification.description);
        date.setText(notification.date.toString());
        bulletText.setText(Html.fromHtml("&#8226;")); // ho ho ho

        setAppSpecificColors(notification.appId);

        this.emailButton.setOnClickListener(this);

        if(ValidatorUtil.hasNoValue(this.recipient))
        {
            this.emailButton.setVisibility(View.INVISIBLE);
        }
    }

    private void setAppSpecificColors(int appId) {
        switch(appId)
        {
            case 1:
                bulletText.setTextColor(Color.parseColor("#00a5e4"));
                appName.setText("Luminate Online");
                break;
            case 2:
                bulletText.setTextColor(Color.parseColor("#f47c26"));
                appName.setText("Raiser's Edge NXT");
                break;
            case 3:
                bulletText.setTextColor(Color.parseColor("#eff709"));
                appName.setText("Blackbaud CRM");
            default:
                break;
        }
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

    @Override
    public void onClick(View v)
    {
        Intent intent = new Intent(getApplicationContext(), SendEmail.class);
        GCMUtil.setGCM(intent, this.gcm);
        intent.putExtra(EMAIL_RECIPIENT, this.recipient);
        startActivity(intent);
    }
}
