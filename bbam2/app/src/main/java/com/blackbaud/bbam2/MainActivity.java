package com.blackbaud.bbam2;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.gcm.GoogleCloudMessaging;

import auth.AccountAuthService;
import auth.LoginTask;
import gcm.GCMCreationTask;
import notification.MessagesBackgroundTask;
import rest.client.RestApiUtil;


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
        AccountAuthService authService = new AccountAuthService();
        String email = this.getEmail();
        String password = this.getPassword();
        if(authService.isValid(email, password)){
            switch(v.getId())
            {
                case R.id.loginButton:
                    this.login(email, password);
                    break;
                case R.id.signUpButton:
                    createAccount(email, password);
                    break;
            }
        }
        else {
            error.setVisibility(View.VISIBLE);
        }

    }

    private void createAccount(String email, String password)
    {
        new GCMCreationTask(PROJECT_ID, getApplicationContext(), email, password).execute(null, null, null);
    }

    private void login(String email, String password)
    {
        LoginTask task = new LoginTask(getApplicationContext());
        String[] params = RestApiUtil.getLoginParams(email, password);
        task.execute(params);
    }
}
