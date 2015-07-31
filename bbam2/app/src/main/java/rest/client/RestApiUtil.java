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
}
