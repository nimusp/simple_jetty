/*
 * Developed by Sumin Pavel on 4/27/19 4:50 PM.
 * Last modified 4/27/19 4:50 PM
 */

package myself.inc.accounts;

import java.util.HashMap;
import java.util.Map;

public class AccountService {

    private final Map<String, UserProfile> sessionsProfile;
    private final Map<String, UserProfile> loginProfile;

     public AccountService() {
         this.sessionsProfile = new HashMap<>();
         this.loginProfile = new HashMap<>();
     }

     public void addSession(String sessionId, UserProfile profile) {
         sessionsProfile.put(sessionId, profile);
     }

     public UserProfile getUserBySession(String sessionId) {
         return sessionsProfile.get(sessionId);
     }

     public void deleteSession(String sessionId) {
         sessionsProfile.remove(sessionId);
     }

     public void addUser(UserProfile profile) {
         loginProfile.put(profile.getLogin(), profile);
     }

     public UserProfile getUserByLogin(String login) {
         return loginProfile.get(login);
     }
}
