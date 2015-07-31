package com.blackbaud.bbam2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import apps.LinkerUtil;
import gcm.GCMUtil;
import notification.MessagesBackgroundTask;
import notification.NotificationAdapter;
import notification.NotificationItem;


public class MessageList extends Activity {

    ListView list;

    public static final String NOTIFICATION_ITEM = "notification_item";

    String gcm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_list);

        Intent intent = getIntent();
        List<NotificationItem> notificationItems = getNotifications(intent);

        this.gcm = GCMUtil.getGCM(intent);

        this.list = (ListView) findViewById(R.id.notifList);
        final NotificationAdapter notificationAdapter = new NotificationAdapter(this, notificationItems);
        this.list.setAdapter(notificationAdapter);

        final MessageList messageList = this;

        this.list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                NotificationItem notificationItem = (NotificationItem) notificationAdapter.getItem(position);
                Intent intent = new Intent(messageList, MessageDetailActivity.class);
                intent.putExtra(MessageList.NOTIFICATION_ITEM, notificationItem);
                startActivity(intent);
            }
        });
    }

    public List<NotificationItem> getNotifications(Intent intent) {
        return getNotifications(intent.getStringExtra(MessagesBackgroundTask.EXTRA_MESSAGE));
    }

    public static List<NotificationItem> getNotifications(String result) {
        List<NotificationItem> notifications = new ArrayList<>();

        try {
            JSONArray jsonArray = new JSONArray(result);

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                int id = jsonObject.getInt("Id");
                int appid = jsonObject.getInt("AppId");
                String description = jsonObject.getString("Message");
                String ts = jsonObject.getString("TimeCreated");
                SimpleDateFormat  format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
                Date date = format.parse(ts.substring(0, ts.length() - 8));
                NotificationItem notificationItem = new NotificationItem(id, appid, description, date);
                notifications.add(notificationItem);
            }
        } catch (Exception e) {
            System.out.println("U are screwed");
        }

        return notifications;
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

    // TODO: pull into util class
    private void navToLinkAccount() {
        Intent intent = new Intent(this, AppSelection.class);
        startActivity(intent);
    }
}
