package com.blackbaud.bbam2;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.Arrays;
import java.util.List;

import apps.AppsAdapter;
import apps.BBApp;
import gcm.GCMUtil;


public class AppSelection extends Activity
{
    public static final String APP_SELECTION_ITEM = "app_selection_item";
    ListView leList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final String gcm = GCMUtil.getGCM(getIntent());

        List<BBApp> apps = Arrays.asList(new BBApp("Luminate Online"),
                                         new BBApp("RE NXT"),
                                         new BBApp("BBCRM"));

        setContentView(R.layout.activity_app_selection);
        this.leList = (ListView) findViewById(R.id.appList);
        final AppsAdapter appsAdapter = new AppsAdapter(this, apps);
        this.leList.setAdapter(appsAdapter);

        final AppSelection appSelection = this;

        this.leList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                BBApp bbApp = (BBApp) appsAdapter.getItem(position);
                Intent intent = new Intent(appSelection, AccountLink.class);
                GCMUtil.setGCM(intent, gcm);
                intent.putExtra(AppSelection.APP_SELECTION_ITEM, bbApp.appName);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_app_selection, menu);
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
}
