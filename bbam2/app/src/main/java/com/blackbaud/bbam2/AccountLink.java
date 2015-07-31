package com.blackbaud.bbam2;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Arrays;
import java.util.List;

import gcm.GCMUtil;
import notification.MessagesBackgroundTask;


public class AccountLink extends Activity implements View.OnClickListener
{
    Button linkAccount;
    String gcm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_link);

        this.linkAccount = (Button) findViewById(R.id.linkButton);
        this.gcm = GCMUtil.getGCM(getIntent());

        TextView selectedApp = (TextView) findViewById(R.id.appSelector);
        final String app = getIntent().getStringExtra(AppSelection.APP_SELECTION_ITEM);
        selectedApp.setText(app);

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
    public void onClick(View v) {
        //auth to service
        AsyncTask task = new MessagesBackgroundTask(getApplicationContext(), this.gcm);
        String [] params = MessagesBackgroundTask.getApiParams(this.gcm);
        task.execute(params);
    }
}
