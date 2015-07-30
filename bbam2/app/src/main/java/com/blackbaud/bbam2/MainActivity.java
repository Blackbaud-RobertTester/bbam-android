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
import android.widget.TextView;

import auth.AccountAuthService;
import rest.client.RestClient;


public class MainActivity extends Activity
{
    public final static String EXTRA_MESSAGE = "rest.client.MESSAGE";

    String email;
    String password;

    Button login;
    Button create;
    EditText emailInput;
    EditText passwordInput;
    TextView error;

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

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = 1;
                Intent move = new Intent(mainActivity, AccountLink.class);
                move.putExtra(AccountLink.ID_KEY, id);
                startActivity(move);
            }
        });


        email = this.emailInput.getText().toString();
        password = this.passwordInput.getText().toString();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AccountAuthService authService = new AccountAuthService();
                if(authService.isValid(email, password)){
                    String [] params = new String[1];
                    params[0] = "/messages/1/1";
                    AsyncTask task = new MessagesBackgroundTask().execute(params);
                }
                else {
                    error.setVisibility(View.VISIBLE);
                }
            }
        });

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

    private class MessagesBackgroundTask extends AsyncTask<String, String, String> {

        private String result;


        @Override
        protected String doInBackground(String... params) {
            String urlString = params[0];

            RestClient restClient = new RestClient(urlString);
            result = restClient.executeGet();

            return result;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            Intent intent = new Intent(getApplicationContext(), MessageList.class);

            intent.putExtra(EXTRA_MESSAGE, result);

            startActivity(intent);


        }
    }
}
