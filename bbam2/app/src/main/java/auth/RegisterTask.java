package auth;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

import com.blackbaud.bbam2.AppSelection;

import gcm.GCMUtil;
import rest.client.RestApiUtil;
import rest.client.RestClient;

/**
 * Created by avaky on 7/31/15.
 */
public class RegisterTask extends AsyncTask<String, String, String>
{
    public static final String REGISTRATION_RESPONSE = "rest.client.REGISTER";

    private static final String REG_USERNAME = "username";
    private static final String REG_PW = "password";
    private static final String REG_GCM = "registrationId";

    String result;
    Context context;
    String gcm;

    public RegisterTask(Context context)
    {
        this.context = context;
    }

    @Override
    protected String doInBackground(String... params)
    {
        String endpoint = RestApiUtil.getCreationEndpoint(params);
        String email = RestApiUtil.getCreationEmail(params);
        String password = RestApiUtil.getCreationPassword(params);
        this.gcm = RestApiUtil.getCreationGCM(params);

        RestClient client = new RestClient(endpoint);
        client.addParam(REG_USERNAME, email);
        client.addParam(REG_PW, password);
        client.addParam(REG_GCM, this.gcm);

        this.result = client.executePost();
        return this.result;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);

        Intent intent = new Intent(this.context, AppSelection.class);
        intent.putExtra(REGISTRATION_RESPONSE, result);
        GCMUtil.setGCM(intent, this.gcm);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        this.context.startActivity(intent);
    }
}
