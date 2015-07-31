package auth;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.blackbaud.bbam2.R;

import notification.MessagesBackgroundTask;
import rest.client.RestApiUtil;
import rest.client.RestClient;

/**
 * Created by avaky on 7/31/15.
 */
public class LoginTask extends AsyncTask<String, String, String>
{
    public static final String LOGIN_RESPONSE = "rest.client.LOGIN";

    private static final String LOGIN_NAME = "username";
    private static final String LOGIN_PW = "password";

    Context context;
    String gcm;

    public LoginTask(Context context)
    {
        this.context = context;
    }

    @Override
    protected String doInBackground(String... params)
    {
        String endpoint = RestApiUtil.getLoginEndpoint(params);
        String email = RestApiUtil.getLoginEmail(params);
        String password = RestApiUtil.getLoginPassword(params);

        RestClient client = new RestClient(endpoint);
        client.addParam(LOGIN_NAME, email);
        client.addParam(LOGIN_PW, password);

        String result = client.executePost();
        this.gcm = result;
        return result;
    }

    @Override
    protected void onPostExecute(String s)
    {
        super.onPostExecute(s);

        if(ValidatorUtil.hasNoValue(this.gcm))
        {
            Toast.makeText(this.context, R.string.activity_main_login_error, Toast.LENGTH_SHORT).show();
        }
        else
        {
            AsyncTask task = new MessagesBackgroundTask(this.context, this.gcm);
            String[] apiEndpoint = RestApiUtil.getMessagesApiParamString(this.gcm);
            task.execute(apiEndpoint);
        }
    }
}
