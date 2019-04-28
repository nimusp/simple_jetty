/*
 * Developed by Sumin Pavel on 4/27/19 4:54 PM.
 * Last modified 4/27/19 4:54 PM
 */

package myself.inc.accounts;

import org.jetbrains.annotations.NotNull;

public class UserProfile {

    @NotNull
    private final String login;

    @NotNull
    private final String password;

    @NotNull
    private final String email;

    public UserProfile(@NotNull String login, @NotNull String password, @NotNull String email) {
        this.login = login;
        this.password = password;
        this.email = email;
    }

    @NotNull
    public String getLogin() {
        return login;
    }

    @NotNull
    public String getPassword() {
        return password;
    }

    @NotNull
    public String getEmail() {
        return email;
    }
}
