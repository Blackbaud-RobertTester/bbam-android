package com.blackbaud.bbam2;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import auth.ValidatorUtil;
import gcm.GCMUtil;
import notification.MessagesBackgroundTask;
import rest.client.RestApiUtil;


public class SendEmail extends Activity implements View.OnClickListener
{
    EditText recipient;
    EditText sender;
    EditText subject;
    EditText message;
    Button send;
    String gcm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_email);

        Intent intent = getIntent();
        this.gcm = GCMUtil.getGCM(intent);
        String email = intent.getStringExtra(MessageDetailActivity.EMAIL_RECIPIENT);

        this.recipient = (EditText) findViewById(R.id.recipient);
        this.sender = (EditText) findViewById(R.id.sender);
        this.subject = (EditText) findViewById(R.id.subject);
        this.message = (EditText) findViewById(R.id.message);

        this.send = (Button) findViewById(R.id.sendButton);
        this.send.setOnClickListener(this);

        if(ValidatorUtil.hasNoValue(email))
        {
            //this.send.setVisibility(View.INVISIBLE);
        }
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
        if(ValidatorUtil.hasNoValue(this.recipient.getText().toString()))
        {
            getToastie("Invalid Recipient Email Address");
        }
        else if(ValidatorUtil.hasNoValue(this.sender.getText().toString()))
        {
            getToastie("Invalid Sender Email Address");
        }
        else if(ValidatorUtil.hasNoValue(this.subject.getText().toString()))
        {
            getToastie("Email Must Have a Subject");
        }
        else if(ValidatorUtil.hasNoValue(this.message.getText().toString()))
        {
            getToastie("Must Provide an Email Message");
        }
        else
        {
            //send email...
            this.getToastie("Email Sent");

            AsyncTask task = new MessagesBackgroundTask(getApplicationContext(), this.gcm);
            String[] apiEndpoint = RestApiUtil.getMessagesApiParamString(this.gcm);
            task.execute(apiEndpoint);
        }
    }

    private void getToastie(String messageToToast)
    {
        Toast.makeText(getApplicationContext(), messageToToast, Toast.LENGTH_SHORT).show();
    }
}
