package gcm;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

import com.blackbaud.bbam2.AccountLink;
import com.blackbaud.bbam2.AppSelection;
import com.google.android.gms.gcm.GoogleCloudMessaging;

import java.io.IOException;

/**
 * Created by avaky on 7/30/15.
 */
public class GCMBackgroundTask extends AsyncTask<Void, Void, String> {

    GoogleCloudMessaging gcm;
    String projectNumber;
    Context context;
    String gcmIdResult;

    public GCMBackgroundTask(String id, Context context)
    {
        this.projectNumber = id;
        this.context = context;
    }

    public String getGcmIdResult()
    {
        return this.gcmIdResult;
    }

    @Override
    protected String doInBackground(Void... params) {
        String msg = "";
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

        Intent intent = new Intent(this.context, AppSelection.class);
        GCMUtil.setGCM(intent, this.gcmIdResult);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        this.context.startActivity(intent);
    }
}
