package apps;

import android.content.Context;
import android.os.AsyncTask;

import notification.MessagesBackgroundTask;
import rest.client.RestApiUtil;
import rest.client.RestClient;

/**
 * Created by avaky on 7/31/15.
 */
public class LinkAppAcctTask extends AsyncTask<String, String, String>
{
    private static final String LINK_GCM = "registrationId";
    private static final String LINK_APP_ID = "appId";
    private static final String LINK_LOGIN = "username";
    private static final String LINK_PASSWORD = "password";

    Context context;
    String gcm;
    String result;

    public LinkAppAcctTask(Context context)
    {
        this.context = context;
    }

    @Override
    protected String doInBackground(String... params)
    {
        String endpoint = RestApiUtil.getLinkAcctEndpoint(params);
        String appLogin = RestApiUtil.getLinkAcctAppLogin(params);
        String appPw = RestApiUtil.getLinkAcctAppPassword(params);
        this.gcm = RestApiUtil.getLinkAcctGCM(params);
        String appIdString = RestApiUtil.getLinkAcctAppId(params);

        RestClient client = new RestClient(endpoint);
        client.addParam(LINK_APP_ID, appIdString);
        client.addParam(LINK_GCM, this.gcm);
        client.addParam(LINK_LOGIN, appLogin);
        client.addParam(LINK_PASSWORD, appPw);

        this.result = client.executePost();
        return this.result;
    }

    @Override
    protected void onPostExecute(String s)
    {
        super.onPostExecute(s);

        AsyncTask task = new MessagesBackgroundTask(this.context, this.gcm);
        String[] params = RestApiUtil.getMessagesApiParamString(this.gcm);
        task.execute(params);
    }
}
