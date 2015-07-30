package notification;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.blackbaud.bbam2.R;

import org.w3c.dom.Text;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by avaky on 7/30/15.
 */
public class NotificationAdapter extends BaseAdapter {

    List<NotificationItem> list;
    LayoutInflater inflater;

    public NotificationAdapter(Activity parentActivity)
    {
        this.inflater = parentActivity.getWindow().getLayoutInflater();
        this.list = getNotifications();
    }

    @Override
    public int getCount() {
        return this.list.size();
    }

    @Override
    public Object getItem(int position) {
        return this.list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null) {
            convertView = inflater.inflate(R.layout.notification, parent, false);
        }

        TextView appId = (TextView) convertView.findViewById(R.id.appId);
        TextView description = (TextView) convertView.findViewById(R.id.description);
        TextView date = (TextView) convertView.findViewById(R.id.date);

        NotificationItem item = (NotificationItem) this.getItem(position);

        appId.setText(String.valueOf(item.appId));
        description.setText(item.description);
        date.setText(item.date.toString());

        return convertView;
    }

    public List<NotificationItem> getNotifications()
    {
        return Arrays.asList(
          new NotificationItem(1, "Something happened", new Date(1985, 7, 10)),
                new NotificationItem(2, "OMG SHOES!", new Date(1988, 8, 8)),
                new NotificationItem(1, "YOU SHALL NOT PASS!", new Date(1985, 5, 5))
        );
    }
}
