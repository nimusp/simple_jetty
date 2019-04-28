/*
 * Developed by Sumin Pavel on 4/28/19 2:34 PM.
 * Last modified 4/28/19 2:34 PM
 */

package myself.inc.dao;

import myself.inc.dataSets.UsersDataSet;

public interface UserDao {

    void insertUser(String login, String password, String email);
    UsersDataSet getUserByLogin(String login);
}
