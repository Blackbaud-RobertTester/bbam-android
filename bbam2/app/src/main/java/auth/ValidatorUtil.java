package auth;

/**
 * Created by avaky on 7/30/15.
 */
public class ValidatorUtil
{
    public ValidatorUtil(){}

    public boolean isValid(String email, String password)
    {
        boolean validEmail = this.hasValue(email);
        boolean validPassword = this.hasValue(password);
        return this.hasValue(email) && this.hasValue(password);
    }

    private boolean hasValue(String item)
    {
        return item != null && ! item.isEmpty();
    }
}
