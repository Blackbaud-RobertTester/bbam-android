package gcm;

import android.content.Intent;

/**
 * Created by avaky on 7/30/15.
 */
public class GCMUtil
{
    private static final String ID_KEY = "id";

    public static String getGCM(Intent intent)
    {
        return intent.getStringExtra(ID_KEY);
    }

    public static void setGCM(Intent intent, String id)
    {
        intent.putExtra(ID_KEY, id);
    }
}
