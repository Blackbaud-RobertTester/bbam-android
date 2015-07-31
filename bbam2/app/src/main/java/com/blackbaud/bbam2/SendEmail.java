package com.blackbaud.bbam2;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import gcm.GCMUtil;
import notification.MessagesBackgroundTask;
import rest.client.RestApiUtil;


public class SendEmail extends Activity implements View.OnClickListener {

    Button send;
    String gcm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_email);

        Intent intent = getIntent();
        this.gcm = GCMUtil.getGCM(intent);

        this.send = (Button) findViewById(R.id.sendButton);
        this.send.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_send_email, menu);
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

    @Override
    public void onClick(View v)
    {
        //send email...
        Toast.makeText(getApplicationContext(), "Email Sent", Toast.LENGTH_LONG).show();

        AsyncTask task = new MessagesBackgroundTask(getApplicationContext(), this.gcm);
        String[] apiEndpoint = RestApiUtil.getMessagesApiParamString(this.gcm);
        task.execute(apiEndpoint);
    }
}
