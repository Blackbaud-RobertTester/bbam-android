package apps;

import android.app.Activity;
import android.graphics.Color;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.blackbaud.bbam2.R;

import java.util.List;

/**
 * Created by avaky on 7/30/15.
 */
public class AppsAdapter extends BaseAdapter
{
    private final List<BBApp> appList;
    private final LayoutInflater inflater;

    public AppsAdapter(Activity parentActivity, List<BBApp> appList)
    {
        this.appList = appList;
        this.inflater = parentActivity.getWindow().getLayoutInflater();
    }

    @Override
    public int getCount() {
        return this.appList.size();
    }

    @Override
    public Object getItem(int position) {
        return this.appList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        if(convertView == null) {
            convertView = inflater.inflate(R.layout.bb_app, parent, false);
        }

        TextView appName = (TextView) convertView.findViewById(R.id.appName);
        TextView appId = (TextView) convertView.findViewById(R.id.appId);
        TextView bulletText = (TextView) convertView.findViewById(R.id.bullet);

        BBApp item = (BBApp) this.getItem(position);

        appName.setText(item.appName);
        appId.setText(String.valueOf(item.appId));
        bulletText.setText(Html.fromHtml("&#8226;")); // ho ho ho

        switch(item.appId) {
            case 1:
                bulletText.setTextColor(Color.parseColor("#00a5e4"));
                break;
            case 2:
                bulletText.setTextColor(Color.parseColor("#f47c26"));
                break;
            case 3:
                bulletText.setTextColor(Color.parseColor("#eff709"));
                break;
            default:
                bulletText.setTextColor(Color.argb(50, 0, 0, 0));
                break;
        }

        return convertView;
    }
}
