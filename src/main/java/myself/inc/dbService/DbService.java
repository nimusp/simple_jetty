/*
 * Developed by Sumin Pavel on 4/28/19 3:12 PM.
 * Last modified 4/28/19 3:12 PM
 */

package myself.inc.dbService;

import myself.inc.accounts.UserProfile;

public interface DbService {

    void addUser(UserProfile profile);
    UserProfile getUserByLogin(String login);
    void printConnectInfo();
}
