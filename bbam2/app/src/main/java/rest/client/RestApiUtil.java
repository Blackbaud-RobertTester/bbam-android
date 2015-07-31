package rest.client;

/**
 * Created by avaky on 7/30/15.
 */
public class RestApiUtil
{
    public static String[] getMessagesApiParamString(String gcmId)
    {
        return new String[]{ "/messages/1" };
    }

    public static String getMessageApiCall(String[] params)
    {
        return params[0];
    }

    public static String[] getRegistraitonParams(String email, String password, String gcm)
    {
        return new String[]{"/register", email, password, gcm};
    }

    public static String getCreationEndpoint(String[] strings)
    {
        return strings[0];
    }

    public static String getCreationEmail(String[] strings)
    {
        return strings[1];
    }

    public static String getCreationPassword(String[] strings)
    {
        return strings[2];
    }

    public static String getCreationGCM(String[] strings)
    {
        return strings[3];
    }


    public static String[] getLoginParams(String email, String password)
    {
        return new String[]{ "/login", email, password };
    }

    public static String getLoginEndpoint(String[] params)
    {
        return params[0];
    }

    public static String getLoginEmail(String[] params)
    {
        return params[1];
    }

    public static String getLoginPassword(String[] params)
    {
        return params[2];
    }

    public static String[] getLinkAcctParams(String appLogin, String appPw, String gcm, String appId)
    {
        return new String[] { "/link", appLogin, appPw, gcm, appId };
    }

    public static String getLinkAcctEndpoint(String[] params)
    {
        return params[0];
    }

    public static String getLinkAcctAppLogin(String[] params)
    {
        return params[1];
    }

    public static String getLinkAcctAppPassword(String[] params)
    {
        return params[2];
    }

    public static String getLinkAcctGCM(String[] params)
    {
        return params[3];
    }

    public static String getLinkAcctAppId(String[] params)
    {
        return params[4];
    }
}
