package notification;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

import com.blackbaud.bbam2.AccountLink;
import com.blackbaud.bbam2.MessageList;

import gcm.GCMUtil;
import rest.client.RestClient;

/**
 * Created by avaky on 7/30/15.
 */
public class MessagesBackgroundTask extends AsyncTask<String, String, String> {

    public final static String EXTRA_MESSAGE = "rest.client.MESSAGE";

    private String result;
    private Context context;
    private String id;

    public MessagesBackgroundTask(Context context, String gcmId)
    {
        this.context = context;
        this.id = gcmId;
    }



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

        Intent intent = new Intent(this.context, MessageList.class);
        intent.putExtra(EXTRA_MESSAGE, result);
        GCMUtil.setGCM(intent, this.id);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        this.context.startActivity(intent);
    }
}
