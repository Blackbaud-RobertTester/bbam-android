package gcm;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

import com.blackbaud.bbam2.AccountLink;
import com.blackbaud.bbam2.AppSelection;
import com.google.android.gms.gcm.GoogleCloudMessaging;

import java.io.IOException;

import auth.RegisterTask;
import rest.client.RestApiUtil;

/**
 * Created by avaky on 7/30/15.
 */
public class GCMCreationTask extends AsyncTask<Void, Void, String>
{
    final String projectNumber;
    final Context context;
    final String email;
    final String password;

    String gcmIdResult;

    public GCMCreationTask(String id, Context context, String email, String password)
    {
        this.projectNumber = id;
        this.context = context;
        this.email = email;
        this.password = password;
    }

    public String getGcmIdResult()
    {
        return this.gcmIdResult;
    }

    @Override
    protected String doInBackground(Void... params) {
        String msg = "";
        GoogleCloudMessaging gcm = null;
        try {
            if (gcm == null) {
                gcm = GoogleCloudMessaging.getInstance(this.context);
            }
            this.gcmIdResult = gcm.register(this.projectNumber);
            msg = "Device registered, registration ID=" + this.gcmIdResult;
            Log.i("GCM", msg);

        } catch (IOException ex) {
            msg = "Error :" + ex.getMessage();
        }
        System.out.println(msg);
        return msg;
    }

    @Override
    protected void onPostExecute(String msg) {
        super.onPostExecute(msg);
        RegisterTask task = new RegisterTask(this.context);
        String[] params = RestApiUtil.getRegistraitonParams(this.email, this.password, this.gcmIdResult);
        task.execute(params);
    }
}
