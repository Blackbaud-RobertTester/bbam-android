package com.blackbaud.bbam2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.List;

import apps.Apps;


public class AccountLink extends Activity implements AdapterView.OnItemSelectedListener, View.OnClickListener{
    public static final String ID_KEY = "id";

    Spinner productList;
    Button linkAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_link);

        this.linkAccount = (Button) findViewById(R.id.linkButton);

        productList = (Spinner) findViewById(R.id.appSelector);
        Apps applications = new Apps();
        List<String> apps = applications.getApps();
        ArrayAdapter<String> adatpter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, apps);
        adatpter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        productList.setAdapter(adatpter);
        productList.setOnItemSelectedListener(this);

        linkAccount.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_account_link, menu);
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
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String thing = (String) parent.getItemAtPosition(position);
        //
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onClick(View v) {
        //auth to service
        Intent intent = new Intent(this, MessageList.class);
        startActivity(intent);
    }
}
