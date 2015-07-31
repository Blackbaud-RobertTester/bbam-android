package notification;

import android.app.Activity;
import android.graphics.Color;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.blackbaud.bbam2.R;

import org.w3c.dom.Text;

import java.util.Arrays;
import java.util.Date;
import java.util.List;


public class NotificationAdapter extends BaseAdapter {

    List<NotificationItem> list;
    LayoutInflater inflater;

    public NotificationAdapter(Activity parentActivity, List<NotificationItem> notificationItems)
    {
        this.inflater = parentActivity.getWindow().getLayoutInflater();
        this.list = notificationItems;
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
        TextView listItemBullet = (TextView) convertView.findViewById(R.id.bullet);
        TextView description = (TextView) convertView.findViewById(R.id.description);
        TextView date = (TextView) convertView.findViewById(R.id.date);

        NotificationItem item = (NotificationItem) this.getItem(position);

        appId.setText(String.valueOf(item.appId));
        description.setText(item.description);
        date.setText(item.date.toString());
        listItemBullet.setText(Html.fromHtml("&#8226;")); // ho ho ho

        switch(item.appId)
        {
            case 1:
                listItemBullet.setTextColor(Color.parseColor("#00a5e4"));
                break;
            case 2:
                listItemBullet.setTextColor(Color.parseColor("#f47c26"));
                break;
            case 3:
                listItemBullet.setTextColor(Color.parseColor("#eff709"));
            default:
                break;
        }

        return convertView;
    }
}
