package apps;

import android.content.Context;
import android.content.Intent;

import com.blackbaud.bbam2.AppSelection;

import gcm.GCMUtil;

/**
 * Created by avaky on 7/31/15.
 */
public class LinkerUtil
{
    public static void navToLinkAccount(Context context, String gcm) {
        Intent intent = new Intent(context, AppSelection.class);
        GCMUtil.setGCM(intent, gcm);
        context.startActivity(intent);
    }
}
