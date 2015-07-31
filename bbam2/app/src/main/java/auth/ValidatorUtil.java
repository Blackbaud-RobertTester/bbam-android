package auth;

/**
 * Created by avaky on 7/30/15.
 */
public class ValidatorUtil
{
    public static boolean isValid(String email, String password)
    {
        boolean validEmail = hasValue(email);
        boolean validPassword = hasValue(password);
        return validEmail && validPassword;
    }

    public static boolean hasValue(String item)
    {
        return item != null && ! item.isEmpty();
    }

    public static boolean hasNoValue(String item)
    {
        return ! hasValue(item);
    }

    public boolean allHaveValue(String... items)
    {
        boolean allHaveValue = true;
        for(String item : items)
        {
            allHaveValue &= hasValue(item);
        }
        return allHaveValue;
    }
}
