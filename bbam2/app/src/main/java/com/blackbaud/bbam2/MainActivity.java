package com.blackbaud.bbam2;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import auth.AccountAuthService;


public class MainActivity extends ActionBarActivity implements View.OnClickListener
{
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

        create.setOnClickListener(this);
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
    public void onClick(View v) {
        this.email = this.emailInput.getText().toString();
        this.password = this.passwordInput.getText().toString();

        AccountAuthService authService = new AccountAuthService();
        if(authService.isValid(this.email, this.password)){
            int id = 1;
            Intent move = new Intent(this, AccountLink.class);
            move.putExtra(AccountLink.ID_KEY, id);
            startActivity(move);
        }
        else {
            error.setVisibility(View.VISIBLE);
        }
    }
}
