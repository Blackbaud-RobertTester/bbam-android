package gcm;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

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
}
