package com.blackbaud.bbam2;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.gcm.GoogleCloudMessaging;

import java.io.IOException;

import auth.AccountAuthService;
import gcm.GCMBackgroundTask;
import notification.MessagesBackgroundTask;
import rest.client.RestClient;


public class MainActivity extends Activity implements View.OnClickListener
{
    GoogleCloudMessaging gcm;
    String regid;

    Button login;
    Button create;
    EditText emailInput;
    EditText passwordInput;
    TextView error;

    public static String PROJECT_ID = "102437530721";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        emailInput = (EditText) findViewById(R.id.email);
        passwordInput = (EditText) findViewById(R.id.password);
        login = (Button) findViewById(R.id.loginButton);
        create = (Button) findViewById(R.id.signUpButton);
        error = (TextView) findViewById(R.id.error);

        final MainActivity mainActivity = this;

        create.setOnClickListener(this);
        login.setOnClickListener(this);
    }

    private String getEmail()
    {
        return emailInput.getText().toString();
    }

    private String getPassword()
    {
        return passwordInput.getText().toString();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
        switch(v.getId())
        {
            case R.id.loginButton:
                this.login();
                break;
            case R.id.signUpButton:
                this.createAccount();
                break;
        }
    }

    private void login()
    {
        AccountAuthService authService = new AccountAuthService();
        String email = this.getEmail();
        String password = this.getPassword();
        if(authService.isValid(email, password)){
            AsyncTask task = new MessagesBackgroundTask(getApplicationContext(), this.regid);


            String [] params = MessagesBackgroundTask.getApiParams(this.regid);
            task.execute(params);
        }
        else {
            error.setVisibility(View.VISIBLE);
        }
    }

    private void createAccount()
    {
        GCMBackgroundTask task = new GCMBackgroundTask(PROJECT_ID, getApplicationContext());
        task.execute(null, null, null);
        this.regid = task.getGcmIdResult();

        Intent move = new Intent(this, AccountLink.class);
        move.putExtra(AccountLink.ID_KEY, this.regid);
        startActivity(move);
    }
}
